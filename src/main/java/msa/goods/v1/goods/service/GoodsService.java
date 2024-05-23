package msa.goods.v1.goods.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import msa.goods.v1.goods.dao.GoodsDao;
import msa.goods.v1.goods.dto.GoodsInfoDto;
import msa.goods.v1.goods.dto.GoodsListDto;
import msa.goods.v1.goods.model.GoodsImage;
import msa.goods.v1.goods.model.UploadFile;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * packageName : msa.goods.v1.goods.service
 * fileName : GoodsService
 * author : BH
 * date : 2024-05-23
 * description :
 * ================================================
 * DATE                AUTHOR              NOTE
 * ================================================
 * 2024-05-23       GoodsService       최초 생성
 */
@Service
@RequiredArgsConstructor
@Log4j2
public class GoodsService {
    private final FileUpload fileUpload;
    private final GoodsDao goodsDao;

    public List<GoodsListDto> getGoodsList() throws IOException {
        List<GoodsListDto> goodsListDto = goodsDao.getGoodsList();
        return goodsListDto;
    }

    public int insertGoodsInfo(GoodsInfoDto infoDto) {
        goodsDao.insertGoodsInfo(infoDto);
        return infoDto.getGoodsId();
    }

    public int insertGoodsImageInfo(int goodsId, List<MultipartFile> imageFiles) throws IOException {
        // 서버(현재 환경에서는 local)에 이미지 저장
        List<UploadFile> uploadedImageFiles = fileUpload.storeImages(imageFiles);

        // DB에 상품 이미지 정보 저장
        List<GoodsImage> goodsImageList = new ArrayList<>();
        for (UploadFile uploadFile : uploadedImageFiles) {
            GoodsImage goodsImage = new GoodsImage(goodsId, uploadFile.getStoreFileName());
            goodsImageList.add(goodsImage);
        }
        int resultCount = goodsDao.insertGoodsImageInfo(goodsImageList);
        return resultCount;
    }
}
