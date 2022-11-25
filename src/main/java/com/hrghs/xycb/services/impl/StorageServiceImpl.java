package com.hrghs.xycb.services.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.hrghs.xycb.config.BanmaerpProperties;
import com.hrghs.xycb.domains.BanmaerpSigningVO;
import com.hrghs.xycb.domains.BanmaerpURL;
import com.hrghs.xycb.domains.banmaerpDTO.StorageDTO;
import com.hrghs.xycb.domains.banmaerpDTO.StoreDTO;
import com.hrghs.xycb.utils.BanmaTokenUtils;
import com.hrghs.xycb.utils.EncryptionUtils;
import com.hrghs.xycb.utils.HttpClientsUtils;
import com.hrghs.xycb.domains.common.BanmaErpResponseDTO;
import com.hrghs.xycb.services.StorageService;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static com.hrghs.xycb.domains.Constants.BANMAERP_FIELD_STORES;

@Service
public class StorageServiceImpl implements StorageService {
    @Autowired
    private BanmaTokenUtils banmaTokenUtils;
    @Autowired
    private EncryptionUtils encryptionUtils;
    @Autowired
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
    public BanmaErpResponseDTO<StorageDTO> getStoragetList(String ids, String name, String fileType, String fileCategoryId, int pageNumber, int pageSize,
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
    public BanmaErpResponseDTO<StorageDTO> getStorageById(String id,
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
    public BanmaErpResponseDTO<StorageDTO> uploadTheFileToStream(String contentType, String name, String file,
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
    public BanmaErpResponseDTO<StorageDTO> uploadTheFileToBase64(String contentType, String name, String file,
                                                     BanmaerpProperties banmaerpProperties) {
        String apiUrl = String.format(BanmaerpURL.banmaerp_storage_base64_POST,name);
        apiUrl = encryptionUtils.rmEmptyParas(apiUrl);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.TEXT_PLAIN);
        BanmaerpSigningVO banmaerpSigningVO = banmaTokenUtils.banmaerpSigningVO(banmaerpProperties);
        httpHeaders = banmaTokenUtils.banmaerpCommonHeaders(httpHeaders,banmaerpProperties,banmaerpSigningVO);
        HttpEntity requestBody = new HttpEntity(file,httpHeaders);
        return
                httpClients.restTemplateWithBanmaMasterToken(banmaerpProperties)
                        .exchange(BanmaerpURL.banmaerp_gateway.concat(apiUrl),
                                HttpMethod.POST,requestBody,
                                new ParameterizedTypeReference<BanmaErpResponseDTO<StorageDTO>>() {})
                        .getBody();
    }

    /**
     * 使用base64文件流的方式上传文件
     * @param contentType 内容类型（必填）
     * @param file  文件内容（必填）
     * @return
     */
    @Override
    public BanmaErpResponseDTO<StorageDTO> uploadTheFileToForm(String contentType, String file,
                                                   BanmaerpProperties banmaerpProperties) {
        return null;
    }
}
