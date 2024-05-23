package msa.goods.v1.goods.model;

import lombok.Data;

/**
 * packageName : msa.goods.v1.goods.model
 * fileName : UploadFile
 * author : BH
 * date : 2024-05-23
 * description :
 * ================================================
 * DATE                AUTHOR              NOTE
 * ================================================
 * 2024-05-23       UploadFile       최초 생성
 */
@Data
public class UploadFile {
    private String uploadFileName;
    private String storeFileName;

    public UploadFile(String uploadFileName, String storeFileName) {
        this.uploadFileName = uploadFileName;
        this.storeFileName = storeFileName;
    }
}
