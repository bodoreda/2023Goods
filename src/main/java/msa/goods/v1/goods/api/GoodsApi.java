package msa.goods.v1.goods.api;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import msa.goods.v1.common.jwt.JwtTokenProvider;
import msa.goods.v1.goods.dto.GoodsInfoDto;
import msa.goods.v1.goods.dto.GoodsListDto;
import msa.goods.v1.goods.service.GoodsService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * packageName : msa.goods.v1.goods.api
 * fileName : GoodsApi
 * author : BH
 * date : 2024-05-23
 * description :
 * ================================================
 * DATE                AUTHOR              NOTE
 * ================================================
 * 2024-05-23       GoodsApi       최초 생성
 */
@RestController
@RequestMapping("/v1/goods")
@RequiredArgsConstructor
@Log4j2
public class GoodsApi {
    @Value("${spring.jwt.secret}")
    private String secretKey;
    private final GoodsService goodsService;
    private final JwtTokenProvider jwtTokenProvider;

    @GetMapping("/goods")
    public ResponseEntity getGoodsList() throws IOException {
        List<GoodsListDto> goodsListDto = goodsService.getGoodsList();
        log.info("컨트롤러 : {}", goodsListDto);
        return ResponseEntity.status(HttpStatus.OK).body(goodsListDto);
    }

    @PostMapping("/goods")
    public ResponseEntity insertGoods(@RequestParam("name") String name,
                                      @RequestParam("price") int price,
                                      @RequestParam("stock") int stock,
                                      @RequestParam("description") String description,
                                      @RequestParam("images") List<MultipartFile> imageFiles,
                                      @RequestHeader("Authorization") String authorization) throws IOException {

        log.info("{} {} {} {}", name, price, stock, description);
        log.info("{}", imageFiles);

        if(!imageFiles.isEmpty()) {
            String cuid = jwtTokenProvider.getCuidFromAccessToken(authorization);

            // 상품 정보 DB 저장
            GoodsInfoDto infoDto = new GoodsInfoDto(name, price, stock, description, cuid);
            int goodsId = goodsService.insertGoodsInfo(infoDto);
            log.info("goods_id : {}", goodsId);

            // 상품 이미지 정보 DB 저장
            int resultCount = goodsService.insertGoodsImageInfo(goodsId, imageFiles);
            if(resultCount > 0) {
                return ResponseEntity.status(HttpStatus.OK).body(resultCount + "개의 이미지가 저장되었습니다.");
            }
        }
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("이미지 파일 없음");
    }
}
