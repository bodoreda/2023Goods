package msa.goods.v1.goods.dto;

import lombok.Data;

/**
 * packageName : msa.goods.v1.goods.dto
 * fileName : GoodsGetDto
 * author : BH
 * date : 2024-05-23
 * description :
 * ================================================
 * DATE                AUTHOR              NOTE
 * ================================================
 * 2024-05-23       GoodsGetDto       최초 생성
 */
@Data
public class GoodsGetDto {
    private int goodsId;
    private String goodsName;
    private int goodsPrice;
    private int goodsStock;
    private String sellerCuid;
}
