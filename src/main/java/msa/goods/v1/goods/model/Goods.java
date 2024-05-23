package msa.goods.v1.goods.model;

import lombok.Data;

/**
 * packageName : msa.goods.v1.goods.model
 * fileName : Goods
 * author : BH
 * date : 2024-05-23
 * description :
 * ================================================
 * DATE                AUTHOR              NOTE
 * ================================================
 * 2024-05-23       Goods       최초 생성
 */
@Data
public class Goods {
    private String goodsName;
    private int goodsPrice;
    private int goodsStock;
    private String goodsDescription;
    private String goodsImageName;

    public Goods(String goodsName, int goodsPrice, int goodsStock, String goodsDescription, String goodsImageName) {
        this.goodsName = goodsName;
        this.goodsPrice = goodsPrice;
        this.goodsStock = goodsStock;
        this.goodsDescription = goodsDescription;
        this.goodsImageName = goodsImageName;
    }
}
