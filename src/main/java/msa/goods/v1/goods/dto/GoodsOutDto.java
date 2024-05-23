package msa.goods.v1.goods.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * packageName : msa.goods.v1.goods.dto
 * fileName : GoodsOutDto
 * author : BH
 * date : 2024-05-23
 * description :
 * ================================================
 * DATE                AUTHOR              NOTE
 * ================================================
 * 2024-05-23       GoodsOutDto       최초 생성
 */
@Data
public class GoodsOutDto {
    private String goodsName;
    private int goodsPrice;
    private int goodsStock;
    private String goodsDescription;
    private MultipartFile imageFile;
    private List<MultipartFile> imageFiles;
}
