package com.hrghs.xycb.services.impl;

import com.hrghs.xycb.config.BanmaerpProperties;
import com.hrghs.xycb.utils.HttpClientsUtils;
import com.hrghs.xycb.domains.common.BanmaErpResponseDTO;
import com.hrghs.xycb.services.StorageService;
import org.joda.time.DateTime;

public class StorageServiceImpl implements StorageService {
    private HttpClientsUtils httpClients;
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
     * @return
     */
    @Override
    public BanmaErpResponseDTO getStoragetList(String ids, String name, String fileType, String fileCategoryId, int pageNumber, int pageSize,
                                               DateTime searchTimeStart, DateTime searchTimeEnd, String searchTimeField, String sortField,
                                               String sortBy,BanmaerpProperties banmaerpProperties) {
        return null;
    }

    /**
     * 查询单个文件
     * @param id  存储ID（必填）
     * @return
     */
    @Override
    public BanmaErpResponseDTO getStorageById(String id,
                                              BanmaerpProperties banmaerpProperties) {
        return null;
    }

    /**
     * 使用stream文件流的方式上传文件
     * @param contentType 内容类型（必填）
     * @param name  文件名称（必填）
     * @param file  文件内容（必填）
     * @return
     */
    @Override
    public BanmaErpResponseDTO uploadTheFileToStream(String contentType, String name, String file,
                                                     BanmaerpProperties banmaerpProperties) {
        return null;
    }

    /**
     * 使用base64文件流的方式上传文件
     * @param contentType 内容类型（必填）
     * @param name  文件名称（必填）
     * @param file  文件内容（必填）
     * @return
     */
    @Override
    public BanmaErpResponseDTO uploadTheFileToBase64(String contentType, String name, String file,
                                                     BanmaerpProperties banmaerpProperties) {
        return null;
    }

    /**
     * 使用base64文件流的方式上传文件
     * @param contentType 内容类型（必填）
     * @param file  文件内容（必填）
     * @return
     */
    @Override
    public BanmaErpResponseDTO uploadTheFileToForm(String contentType, String file,
                                                   BanmaerpProperties banmaerpProperties) {
        return null;
    }
}
