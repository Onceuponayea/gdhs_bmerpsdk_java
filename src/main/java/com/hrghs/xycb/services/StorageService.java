package com.hrghs.xycb.services;

import com.hrghs.xycb.config.BanmaerpProperties;
import com.hrghs.xycb.domains.banmaerpDTO.StorageDTO;
import com.hrghs.xycb.domains.common.BanmaErpResponseDTO;
import org.joda.time.DateTime;
import java.util.List;

public interface StorageService {

    /**
     * 查询文件列表
     * @param ids   存储ID，用逗号分隔
     * @param name  存储名称
     * @param fileType  文件类型,具体值参见:FileType
     * @param fileCategoryId    存储分类id
     * @param pageNumber    页码（必填）
     * @param pageSize  页大小
     * @param searchTimeStart   查询的开始时间
     * @param searchTimeEnd     查询的结束时间
     * @param searchTimeField   查询的时间字段名，具体值参见:SearchTimeField
     * @param sortField     排序字段名，具体值参见:SortField
     * @param sortBy    排序方式，具体值参见:SortBy
     * @param banmaerpProperties 斑马erp主账号（供应商或者平台）
     * @return
     */
    List<StorageDTO> getStoragetList(
            String ids,
            String name,
            String fileType,
            String fileCategoryId,
            int pageNumber,
            int pageSize,
            DateTime searchTimeStart,
            DateTime searchTimeEnd,
            String searchTimeField,
            String sortField,
            String sortBy,
            BanmaerpProperties banmaerpProperties
    );

    /**
     * 查询单个文件
     * @param id  存储ID（必填）
     * @param banmaerpProperties 斑马erp主账号（供应商或者平台）
     * @return
     */
    StorageDTO getStorageById(String id,
                                       BanmaerpProperties banmaerpProperties);

    /**
     * 使用stream文件流的方式上传文件
     * @param contentType 内容类型（必填）
     * @param name  文件名称（必填）
     * @param file  文件内容（必填）
     * @param banmaerpProperties 斑马erp主账号（供应商或者平台）
     * @return
     */
    BanmaErpResponseDTO uploadTheFileToStream(
            String contentType,
            String name,
            String file,
            BanmaerpProperties banmaerpProperties
    );

    /**
     * 使用base64文件流的方式上传文件
     * @param name  文件名称（必填）
     * @param file  文件内容（必填）
     * @param banmaerpProperties 斑马erp主账号（供应商或者平台）
     * @return
     */
    BanmaErpResponseDTO uploadTheFileToBase64(
            String name,
            String file,
            BanmaerpProperties banmaerpProperties
    );

    /**
     * 使用form-data文件流的方式上传文件
     * @param contentType 内容类型（必填）
     * @param file  文件内容（必填）
     * @param banmaerpProperties 斑马erp主账号（供应商或者平台）
     * @return
     */
    BanmaErpResponseDTO uploadTheFileToForm(
            String contentType,
            String file,
            BanmaerpProperties banmaerpProperties
    );
}
