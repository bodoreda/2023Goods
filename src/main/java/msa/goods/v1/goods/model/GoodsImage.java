package msa.goods.v1.goods.model;

import lombok.Data;

/**
 * packageName : msa.goods.v1.goods.model
 * fileName : GoodsImage
 * author : BH
 * date : 2024-05-23
 * description :
 * ================================================
 * DATE                AUTHOR              NOTE
 * ================================================
 * 2024-05-23       GoodsImage       최초 생성
 */
@Data
public class GoodsImage {
    private int goodsId;
    private String goodsImageName;

    public GoodsImage(int goodsId, String goodsImageName) {
        this.goodsId = goodsId;
        this.goodsImageName = goodsImageName;
    }
}
