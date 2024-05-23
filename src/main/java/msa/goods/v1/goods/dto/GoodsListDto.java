package msa.goods.v1.goods.dto;

import lombok.Data;

/**
 * packageName : msa.goods.v1.goods.dto
 * fileName : GoodsListDto
 * author : BH
 * date : 2024-05-23
 * description :
 * ================================================
 * DATE                AUTHOR              NOTE
 * ================================================
 * 2024-05-23       GoodsListDto       최초 생성
 */
@Data
public class GoodsListDto {
    private int goodsId;
    private String goodsName;
    private int goodsPrice;
    private String sellerCuid;
    private String thumbnailImageName;
}
