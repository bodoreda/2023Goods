package msa.goods.v1.goods.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * packageName : msa.goods.v1.goods.dto
 * fileName : GoodsInDto
 * author : BH
 * date : 2024-05-23
 * description :
 * ================================================
 * DATE                AUTHOR              NOTE
 * ================================================
 * 2024-05-23       GoodsInDto       최초 생성
 */
@Data
public class GoodsInDto {
    private String goodsName;
    private int goodsPrice;
    private int goodsStock;
    private String goodsDescription;
    private List<MultipartFile> imageFiles;

    public GoodsInDto(String goodsName, int goodsPrice, int goodsStock, String goodsDescription, List<MultipartFile> imageFiles) {
        this.goodsName = goodsName;
        this.goodsPrice = goodsPrice;
        this.goodsStock = goodsStock;
        this.goodsDescription = goodsDescription;
        this.imageFiles = imageFiles;
    }
}
