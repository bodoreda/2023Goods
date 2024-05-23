package msa.goods.v1.goods.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * packageName : msa.goods.v1.goods.dto
 * fileName : GoodsImageDto
 * author : BH
 * date : 2024-05-23
 * description :
 * ================================================
 * DATE                AUTHOR              NOTE
 * ================================================
 * 2024-05-23       GoodsImageDto       최초 생성
 */
@Data
public class GoodsImageDto {
    private int goodsId;
    private String goodsImageName;
    private List<MultipartFile> imageFiles;
}
