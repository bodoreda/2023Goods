package msa.goods.v1.goods.dto;

import lombok.Data;

/**
 * packageName : msa.goods.v1.goods.dto
 * fileName : GoodsInfoDto
 * author : BH
 * date : 2024-05-23
 * description :
 * ================================================
 * DATE                AUTHOR              NOTE
 * ================================================
 * 2024-05-23       GoodsInfoDto       최초 생성
 */
@Data
public class GoodsInfoDto {
    private String goodsName;
    private int goodsPrice;
    private int goodsStock;
    private String goodsDescription;
    private String sellerCuid;
    private int goodsId;

    public GoodsInfoDto(String goodsName, int goodsPrice, int goodsStock, String goodsDescription, String sellerCuid) {
        this.goodsName = goodsName;
        this.goodsPrice = goodsPrice;
        this.goodsStock = goodsStock;
        this.goodsDescription = goodsDescription;
        this.sellerCuid = sellerCuid;
    }
}
