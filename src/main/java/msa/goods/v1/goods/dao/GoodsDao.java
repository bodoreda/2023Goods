package msa.goods.v1.goods.dao;

import msa.goods.v1.goods.dto.GoodsInfoDto;
import msa.goods.v1.goods.dto.GoodsListDto;
import msa.goods.v1.goods.model.GoodsImage;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * packageName : msa.goods.v1.goods.dao
 * fileName : GoodsDao
 * author : BH
 * date : 2024-05-23
 * description :
 * ================================================
 * DATE                AUTHOR              NOTE
 * ================================================
 * 2024-05-23       GoodsDao       최초 생성
 */
@Mapper
public interface GoodsDao {
    List<GoodsListDto> getGoodsList();
    int insertGoodsInfo(GoodsInfoDto infoDto);
    int insertGoodsImageInfo(List<GoodsImage> goodsImageList);
}
