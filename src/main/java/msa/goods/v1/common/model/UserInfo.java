package msa.goods.v1.common.model;

import lombok.Data;

import java.io.Serializable;

/**
 * packageName : msa.goods.v1.common.model
 * fileName : UserInfo
 * author : BH
 * date : 2024-05-23
 * description :
 * ================================================
 * DATE                AUTHOR              NOTE
 * ================================================
 * 2024-05-23       UserInfo       최초 생성
 */
@Data
public class UserInfo implements Serializable {
    /* redis에 저장하기 위해서는 implements Serializable이 필요 */
    private String cuid;
    private String login_id;
    private String user_name;
    private String phone;
    private String email;
    private String addr;
    private String addr_dtl;
    private String roles;
}
