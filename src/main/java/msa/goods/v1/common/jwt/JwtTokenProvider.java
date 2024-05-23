package msa.goods.v1.common.jwt;

import io.jsonwebtoken.*;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.Date;

/**
 * packageName : msa.goods.v1.jwt
 * fileName : JwtTokenProvider
 * author : BH
 * date : 2024-05-23
 * description :
 * ================================================
 * DATE                AUTHOR              NOTE
 * ================================================
 * 2024-05-23       JwtTokenProvider       최초 생성
 */
@Component
@Log4j2
public class JwtTokenProvider {
    @Value("${spring.jwt.secret}")
    private String secretKey;

    @Value("${spring.jwt.token.accessExpTime}")
    private long accessExpirationTime;

    @Value("${spring.jwt.token.refreshExpTime}")
    private long refreshExpirationTime;


    /**
     * Access 토큰 생성
     */
    public String createAccessToken(String cuid, String roles){
        Claims claims = Jwts.claims().setSubject(cuid);
        claims.put("roles", roles);
        Date now = new Date();
        Date expireDate = new Date(now.getTime() + accessExpirationTime);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(expireDate)
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }

    /**
     * Refresh 토큰 생성
     */
    public String createRefreshToken(String cuid, String roles){
        Claims claims = Jwts.claims().setSubject(cuid);
        claims.put("roles", roles);
        Date now = new Date();
        Date expireDate = new Date(now.getTime() + refreshExpirationTime);

        String refreshToken = Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(expireDate)
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();

        return refreshToken;
    }

    /**
     * http 헤더로부터 bearer 토큰을 가져옴.
     */
    public String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }

    /**
     * Access/Refresh 토큰을 검증
     */
    public boolean validateToken(String token, TokenType tokenType) {
        try {
            // 토큰을 파싱하여 JWS 객체 획득
            Jws<Claims> jws = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);

            // 클레임 추출
            Claims claims = jws.getBody();

            // 토큰의 유효성 검증
            if (tokenType == TokenType.ACCESS) {
                // ACCESS 토큰의 경우, 만료 여부만 확인
                return true;
            } else if (tokenType == TokenType.REFRESH) {
                // REFRESH 토큰의 경우, 만료 시간과 현재 시간을 비교하여 만료 여부 확인
                Date expirationDate = claims.getExpiration();
                return expirationDate != null && !expirationDate.before(new Date());
            } else {
                // 지정된 토큰 유형이 없는 경우 유효하지 않음
                return false;
            }
        } catch (ExpiredJwtException e) {
            // 토큰이 만료된 경우
            return false;
        } catch (Exception e) {
            // 그 외의 예외 발생 시 유효하지 않음
            return false;
        }
    }

    /**
     * Refresh 토큰을 사용하여 Access 토큰 재발급
     */
    public String createAccessTokenFromRefreshToken(String refreshToken) {
        Claims claims = Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(refreshToken)
                .getBody();

        String cuid = claims.getSubject();
        String roles = claims.get("roles", String.class);

        return createAccessToken(cuid, roles);
    }

    /**
     * Header의 AccessToken으로부터 cuid 추출
     */
    public String getCuidFromAccessToken(@RequestHeader("Authorization") String authorization) {
        // Authorization header에서 access token 추출
        String accessToken = authorization.replace("Bearer ", "");

        // JWT 파싱하여 Claims 추출
        Claims claims = Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(accessToken)
                .getBody();

        // Claims에서 cuid 추출
        String cuid = claims.get("sub", String.class);
        return cuid;
    }
}
