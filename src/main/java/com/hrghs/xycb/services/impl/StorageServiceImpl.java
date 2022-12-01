package com.hrghs.xycb.services.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.hrghs.xycb.annotations.CheckBanmaerpProperties;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.joda.JodaModule;
import com.hrghs.xycb.annotations.CheckBanmaerpProperties;
import com.hrghs.xycb.config.BanmaerpProperties;
import com.hrghs.xycb.domains.BanmaerpSigningVO;
import com.hrghs.xycb.domains.BanmaerpURL;
import com.hrghs.xycb.domains.banmaerpDTO.StorageDTO;
import com.hrghs.xycb.domains.banmaerpDTO.StoreDTO;
import com.hrghs.xycb.repositories.StorageRepository;
import com.hrghs.xycb.utils.BanmaTokenUtils;
import com.hrghs.xycb.utils.EncryptionUtils;
import com.hrghs.xycb.utils.HttpClientsUtils;
import com.hrghs.xycb.domains.common.BanmaErpResponseDTO;
import com.hrghs.xycb.services.StorageService;
import org.apache.commons.imaging.ImageFormats;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import static com.hrghs.xycb.domains.Constants.BANMAERP_FIELD_STORES;
import static com.hrghs.xycb.domains.Constants.BANMAERP_FIELD_STORAGES;
import static com.hrghs.xycb.domains.Constants.BANMAERP_FIELD_STORES;

@Service
@Lazy
public class StorageServiceImpl implements StorageService {
    @Autowired
    private BanmaTokenUtils banmaTokenUtils;
    @Autowired
    private EncryptionUtils encryptionUtils;
    @Autowired
    private HttpClientsUtils httpClients;
    @Autowired
    private StorageRepository storageRepository;
    @Autowired
    @Lazy
    private ObjectMapper objectMapper;

    /**
     * 查询文件列表
     *
     * @param ids             存储ID，用逗号分隔
     * @param name            存储名称
     * @param fileType        文件类型,具体值参见:FileType
     * @param fileCategoryId  存储分类id
     * @param pageNumber      页码（必填）
     * @param pageSize        页大小
     * @param searchTimeStart 查询的开始时间
     * @param searchTimeEnd   查询的结束时间
     * @param searchTimeField 查询的时间字段名，具体值参见:SearchTimeField
     * @param sortField       排序字段名，具体值参见:SortField
     * @param sortBy          排序方式，具体值参见:SortBy
     * @return
     */
    @Override
    @CheckBanmaerpProperties
    public List<StorageDTO> getStoragetList(String ids, String name, String fileType, String fileCategoryId, Integer pageNumber, Integer pageSize,
                                            DateTime searchTimeStart, DateTime searchTimeEnd, String searchTimeField, String sortField,
                                            String sortBy, BanmaerpProperties banmaerpProperties) {
        String apiUrl = String.format(BanmaerpURL.banmaerp_storagelist_GET, pageNumber, pageSize,
                searchTimeStart, searchTimeEnd, searchTimeField);
        apiUrl = encryptionUtils.rmEmptyParas(apiUrl);
        HttpHeaders httpHeaders = new HttpHeaders();
        BanmaerpSigningVO banmaerpSigningVO = banmaTokenUtils.banmaerpSigningVO(banmaerpProperties);
        //todo signing
        httpHeaders = banmaTokenUtils.banmaerpCommonHeaders(httpHeaders, banmaerpProperties, banmaerpSigningVO);
        HttpEntity requestBody = new HttpEntity(null, httpHeaders);
        List<StorageDTO> storageDTOList = Arrays.stream(httpClients.restTemplateWithBanmaMasterToken(banmaerpProperties)
                .exchange(BanmaerpURL.banmaerp_gateway.concat(apiUrl), HttpMethod.GET, requestBody, new ParameterizedTypeReference<BanmaErpResponseDTO<JsonNode>>() {
                })
                .getBody()
                .toDataList(BANMAERP_FIELD_STORAGES)
        )
                .map(o -> (StorageDTO) o)
                .collect(Collectors.toList());
        return storageDTOList;
    }

    @Override
    public List<StorageDTO> getStoragetList(Integer pageNumber, BanmaerpProperties banmaerpProperties) {
        return getStoragetList(null, null, null, null, pageNumber
                , null, null, null, null, null, null, banmaerpProperties);
    }

    /**
     * 查询单个文件
     *
     * @param id 存储ID（必填）
     * @return
     */
    @Override
    @CheckBanmaerpProperties
    public StorageDTO getStorageById(String id,
                                     BanmaerpProperties banmaerpProperties) {
        String apiUrl = String.format(BanmaerpURL.banmaerp_storage_GET, id);
        apiUrl = encryptionUtils.rmEmptyParas(apiUrl);
        HttpHeaders httpHeaders = new HttpHeaders();
        BanmaerpSigningVO banmaerpSigningVO = banmaTokenUtils.banmaerpSigningVO(banmaerpProperties);
        //todo signing
        httpHeaders = banmaTokenUtils.banmaerpCommonHeaders(httpHeaders, banmaerpProperties, banmaerpSigningVO);
        HttpEntity requestBody = new HttpEntity(null, httpHeaders);
        BanmaErpResponseDTO<JsonNode> body = httpClients.restTemplateWithBanmaMasterToken(banmaerpProperties)
                .exchange(BanmaerpURL.banmaerp_gateway.concat(apiUrl), HttpMethod.GET, requestBody, new ParameterizedTypeReference<BanmaErpResponseDTO<JsonNode>>() {
                })
                .getBody();
        StorageDTO storageDTO = null;
        try {
            storageDTO = objectMapper.readValue(body.getData().toString(), new TypeReference<StorageDTO>() {
            });
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return storageDTO;
    }

    /**
     * 使用stream文件流的方式上传文件
     *
//     * @param contentType 内容类型（必填）
     * @param name        文件名称（必填）
     * @param file        文件内容（必填）
     * @return
     */
    @Override
    public BanmaErpResponseDTO<StorageDTO> uploadTheFileToStream(String name, String file,
                                                     BanmaerpProperties banmaerpProperties) {
        if (isFileNameValid(name)) {
            String apiUrl = String.format(BanmaerpURL.banmaerp_storage_stream_POST, name);
            apiUrl = encryptionUtils.rmEmptyParas(apiUrl);
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            httpHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_OCTET_STREAM));
            BanmaerpSigningVO banmaerpSigningVO = banmaTokenUtils.banmaerpSigningVO(banmaerpProperties);
            httpHeaders = banmaTokenUtils.banmaerpCommonHeaders(httpHeaders, banmaerpProperties, banmaerpSigningVO);
            HttpEntity requestBody = new HttpEntity(file, httpHeaders);
            BanmaErpResponseDTO<StorageDTO> responseDTO =
                    httpClients.restTemplateWithBanmaMasterToken(banmaerpProperties)
                            .exchange(BanmaerpURL.banmaerp_gateway.concat(apiUrl),
                                    HttpMethod.POST, requestBody,
                                    new ParameterizedTypeReference<BanmaErpResponseDTO<StorageDTO>>() {
                                    })
                            .getBody();
            storageRepository.saveAndFlush(responseDTO.getData());
            return responseDTO;
        } else {
            throw new IllegalArgumentException(name + "  is not a valid image file type!");
        }
    }

    /**
     * 使用base64文件流的方式上传文件
     *
     * @param name 文件名称（必填）
     * @param file 文件内容（必填）
     * @return
     */
    @Override
    @CheckBanmaerpProperties
    public BanmaErpResponseDTO<StorageDTO> uploadTheFileToBase64(String name, String file,
                                                                 BanmaerpProperties banmaerpProperties) {
        if (isFileNameValid(name)) {
            String apiUrl = String.format(BanmaerpURL.banmaerp_storage_base64_POST, name);
            apiUrl = encryptionUtils.rmEmptyParas(apiUrl);
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.setContentType(MediaType.TEXT_PLAIN);
            httpHeaders.setAccept(Arrays.asList(MediaType.TEXT_PLAIN));
            BanmaerpSigningVO banmaerpSigningVO = banmaTokenUtils.banmaerpSigningVO(banmaerpProperties);
            httpHeaders = banmaTokenUtils.banmaerpCommonHeaders(httpHeaders, banmaerpProperties, banmaerpSigningVO);
            HttpEntity requestBody = new HttpEntity(file, httpHeaders);
            BanmaErpResponseDTO<StorageDTO> responseDTO =
                    httpClients.restTemplateWithBanmaMasterToken(banmaerpProperties)
                            .exchange(BanmaerpURL.banmaerp_gateway.concat(apiUrl),
                                    HttpMethod.POST, requestBody,
                                    new ParameterizedTypeReference<BanmaErpResponseDTO<StorageDTO>>() {
                                    })
                            .getBody();
            storageRepository.saveAndFlush(responseDTO.getData());
            return responseDTO;
        } else {
            throw new IllegalArgumentException(name + "  is not a valid image file type!");
        }
    }

    /**
     * 使用form-data文件流的方式上传文件
     *
     * @param contentType        内容类型（必填）
     * @param file               文件内容（必填）
     * @param banmaerpProperties 斑马erp主账号（供应商或者平台）
     * @return
     */
    @Override
    public BanmaErpResponseDTO<StorageDTO> uploadTheFileToForm(String file,BanmaerpProperties banmaerpProperties) {

            String apiUrl = String.format(BanmaerpURL.banmaerp_storage_form_POST);
            apiUrl = encryptionUtils.rmEmptyParas(apiUrl);
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.setContentType(MediaType.MULTIPART_FORM_DATA);
            httpHeaders.setAccept(Arrays.asList(MediaType.MULTIPART_FORM_DATA));
            BanmaerpSigningVO banmaerpSigningVO = banmaTokenUtils.banmaerpSigningVO(banmaerpProperties);
            httpHeaders = banmaTokenUtils.banmaerpCommonHeaders(httpHeaders, banmaerpProperties, banmaerpSigningVO);
            HttpEntity requestBody = new HttpEntity(file, httpHeaders);
            BanmaErpResponseDTO<StorageDTO> responseDTO =
                    httpClients.restTemplateWithBanmaMasterToken(banmaerpProperties)
                            .exchange(BanmaerpURL.banmaerp_gateway.concat(apiUrl),
                                    HttpMethod.POST, requestBody,
                                    new ParameterizedTypeReference<BanmaErpResponseDTO<StorageDTO>>() {
                                    })
                            .getBody();
            storageRepository.saveAndFlush(responseDTO.getData());
            return responseDTO;
    }

    @Override
    public List<StorageDTO> saveAll(List<StorageDTO> storageDTOList) {
        return storageRepository.saveAllAndFlush(storageDTOList);
    }

    @Override
    public StorageDTO save(StorageDTO storageDTO) {
        return storageRepository.saveAndFlush(storageDTO);
    }

    private boolean isFileNameValid(String name) {
        return Arrays.stream(ImageFormats.values())
                .filter(format -> Arrays.stream(format.getExtensions())
                        .anyMatch(extension -> name.toLowerCase(Locale.ROOT).contains(extension.toLowerCase(Locale.ROOT))))
                .count() > 0;
    }
}
