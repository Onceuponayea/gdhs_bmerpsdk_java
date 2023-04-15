package com.hrghs.xycb.services.impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.hrghs.xycb.annotations.CheckBanmaerpProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hrghs.xycb.domains.BanmaerpProperties;
import com.hrghs.xycb.domains.BanmaerpSigningVO;
import com.hrghs.xycb.domains.BanmaerpURL;
import com.hrghs.xycb.domains.banmaerpDTO.StorageDTO;
import com.hrghs.xycb.repositories.StorageRepository;
import com.hrghs.xycb.utils.BanmaParamsUtils;
import com.hrghs.xycb.utils.BanmaTokenUtils;
import com.hrghs.xycb.utils.BanmaEncryptionUtils;
import com.hrghs.xycb.utils.HttpClientsUtils;
import com.hrghs.xycb.domains.common.BanmaErpResponseDTO;
import com.hrghs.xycb.services.StorageService;
import org.apache.commons.imaging.ImageFormats;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public class StorageServiceImpl implements StorageService {
    @Autowired
    private BanmaTokenUtils banmaTokenUtils;
    @Autowired
    private BanmaEncryptionUtils encryptionUtils;
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
    public Page<StorageDTO> getStoragetList(String ids, String name, String fileType, String fileCategoryId, Integer pageNumber, Integer pageSize
            , DateTime searchTimeStart, DateTime searchTimeEnd, String searchTimeField, String sortField, String sortBy, Boolean remote
            , BanmaerpProperties banmaerpProperties) {
        Page<StorageDTO> storageDTOList;
        pageSize = BanmaParamsUtils.checkPageSize(pageSize);
        if (remote){
            pageNumber = BanmaParamsUtils.checkPageNum(pageNumber,remote);
            String apiUrl = String.format(BanmaerpURL.banmaerp_storagelist_GET,ids,name,fileType,fileCategoryId, pageNumber, pageSize,
                    searchTimeStart==null?null:searchTimeStart.toLocalDateTime(),searchTimeEnd==null?null:searchTimeEnd.toLocalDateTime(), searchTimeField,sortField,sortBy);
            apiUrl = encryptionUtils.rmEmptyParas(apiUrl);
            HttpHeaders httpHeaders = new HttpHeaders();
            BanmaerpSigningVO banmaerpSigningVO = banmaTokenUtils.banmaerpSigningVO(banmaerpProperties,HttpMethod.GET,apiUrl,null);
            httpHeaders = banmaTokenUtils.banmaerpCommonHeaders(httpHeaders, banmaerpProperties, banmaerpSigningVO);
            HttpEntity requestBody = new HttpEntity(null, httpHeaders);
//            storageDTOList = Arrays.stream(httpClients.restTemplateWithBanmaMasterToken(banmaerpProperties)
//                            .exchange(BanmaerpURL.banmaerp_gateway.concat(apiUrl), HttpMethod.GET, requestBody, new ParameterizedTypeReference<BanmaErpResponseDTO<JsonNode>>() {})
//                            .getBody()
//                            .toDataList(BANMAERP_FIELD_STORAGES))
//                    .map(o -> (StorageDTO) o)
//                    .collect(Collectors.toList());
            storageDTOList = (Page<StorageDTO>)
                    httpClients.restTemplateWithBanmaMasterToken(banmaerpProperties)
                            .exchange(BanmaerpURL.banmaerp_gateway.concat(apiUrl), HttpMethod.GET, requestBody, new ParameterizedTypeReference<BanmaErpResponseDTO<JsonNode>>() {})
                            .getBody()
                            .toDataList(StorageDTO.class,banmaerpProperties);
        }else{
            pageNumber = BanmaParamsUtils.checkPageNum(pageNumber,false);
            Specification<StorageDTO> specification = createSpecification(ids, name, fileType,fileCategoryId, searchTimeStart, searchTimeEnd, searchTimeField, sortField, sortBy,banmaerpProperties);
            storageDTOList = storageRepository.findAll(specification,PageRequest.of(pageNumber,pageSize));
        }
        return storageDTOList;
    }

    @Override
    @CheckBanmaerpProperties
    public Page<StorageDTO> getStoragetList(Integer pageNumber, Boolean remote, BanmaerpProperties banmaerpProperties) {
        return getStoragetList(null, null, null, null, pageNumber, null, null, null, null, null, null, remote,banmaerpProperties);
    }

    @Override
    @CheckBanmaerpProperties
    public Page<StorageDTO> getStoragetList(Integer pageNumber, Integer pageSize, Boolean remote, BanmaerpProperties banmaerpProperties) {
        return getStoragetList(null, null, null, null, pageNumber, pageSize, null, null, null, null, null, remote,banmaerpProperties);
    }

    /**
     * 查询单个文件
     *
     * @param id 存储ID（必填）
     * @return
     */
    @Override
    @CheckBanmaerpProperties
    public StorageDTO getStorageById(String id, Boolean remote, BanmaerpProperties banmaerpProperties) {
        if (remote){
            String apiUrl = String.format(BanmaerpURL.banmaerp_storage_GET, id);
            apiUrl = encryptionUtils.rmEmptyParas(apiUrl);
            HttpHeaders httpHeaders = new HttpHeaders();
            BanmaerpSigningVO banmaerpSigningVO = banmaTokenUtils.banmaerpSigningVO(banmaerpProperties,HttpMethod.GET,apiUrl,null);
            httpHeaders = banmaTokenUtils.banmaerpCommonHeaders(httpHeaders, banmaerpProperties, banmaerpSigningVO);
            HttpEntity requestBody = new HttpEntity(null, httpHeaders);
            return httpClients.restTemplateWithBanmaMasterToken(banmaerpProperties)
                    .exchange(BanmaerpURL.banmaerp_gateway.concat(apiUrl), HttpMethod.GET, requestBody, new ParameterizedTypeReference<BanmaErpResponseDTO<StorageDTO>>() {})
                    .getBody().getData();
        }else{
            return storageRepository.findById(id).get();
        }
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
    @CheckBanmaerpProperties
    public BanmaErpResponseDTO<StorageDTO> uploadTheFileToStream(String name, String file,
                                                                 BanmaerpProperties banmaerpProperties) {
        if (isFileNameValid(name)) {
            String apiUrl = String.format(BanmaerpURL.banmaerp_storage_stream_POST, name);
            apiUrl = encryptionUtils.rmEmptyParas(apiUrl);
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            httpHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_OCTET_STREAM));
            BanmaerpSigningVO banmaerpSigningVO = banmaTokenUtils.banmaerpSigningVO(banmaerpProperties,HttpMethod.POST,apiUrl,file);
            httpHeaders = banmaTokenUtils.banmaerpCommonHeaders(httpHeaders, banmaerpProperties, banmaerpSigningVO);
            HttpEntity requestBody = new HttpEntity(file, httpHeaders);
            BanmaErpResponseDTO<StorageDTO> responseDTO =
                    httpClients.restTemplateWithBanmaMasterToken(banmaerpProperties)
                            .exchange(BanmaerpURL.banmaerp_gateway.concat(apiUrl), HttpMethod.POST, requestBody, new ParameterizedTypeReference<BanmaErpResponseDTO<StorageDTO>>() {})
                            .getBody();
            StorageDTO storageDTO = responseDTO.getData();
            storageDTO.setBanmaerpProperties(banmaerpProperties);
            storageRepository.saveAndFlush(storageDTO);
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
    public BanmaErpResponseDTO<StorageDTO> uploadTheFileToBase64(String name, String file, BanmaerpProperties banmaerpProperties) {
        if (isFileNameValid(name)) {
            String apiUrl = String.format(BanmaerpURL.banmaerp_storage_base64_POST, name);
            apiUrl = encryptionUtils.rmEmptyParas(apiUrl);
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.setContentType(MediaType.TEXT_PLAIN);
            httpHeaders.setAccept(Arrays.asList(MediaType.TEXT_PLAIN));
            BanmaerpSigningVO banmaerpSigningVO = banmaTokenUtils.banmaerpSigningVO(banmaerpProperties,HttpMethod.POST,apiUrl,file);
            httpHeaders = banmaTokenUtils.banmaerpCommonHeaders(httpHeaders, banmaerpProperties, banmaerpSigningVO);
            HttpEntity requestBody = new HttpEntity(file, httpHeaders);
            BanmaErpResponseDTO<StorageDTO> responseDTO =
                    httpClients.restTemplateWithBanmaMasterToken(banmaerpProperties)
                            .exchange(BanmaerpURL.banmaerp_gateway.concat(apiUrl), HttpMethod.POST, requestBody, new ParameterizedTypeReference<BanmaErpResponseDTO<StorageDTO>>() {})
                            .getBody();
            StorageDTO storageDTO = responseDTO.getData();
            storageDTO.setBanmaerpProperties(banmaerpProperties);
            storageRepository.saveAndFlush(storageDTO);
            return responseDTO;
        } else {
            throw new IllegalArgumentException(name + "  is not a valid image file type!");
        }
    }

    /**
     * 使用form-data文件流的方式上传文件
     *
     * @param file               文件内容（必填）
     * @param banmaerpProperties 斑马erp主账号（供应商或者平台）
     * @return
     */
    @Override
    @CheckBanmaerpProperties
    public BanmaErpResponseDTO<StorageDTO> uploadTheFileToForm(String file,BanmaerpProperties banmaerpProperties) {

        String apiUrl = String.format(BanmaerpURL.banmaerp_storage_form_POST);
        apiUrl = encryptionUtils.rmEmptyParas(apiUrl);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.MULTIPART_FORM_DATA);
        httpHeaders.setAccept(Arrays.asList(MediaType.MULTIPART_FORM_DATA));
        BanmaerpSigningVO banmaerpSigningVO = banmaTokenUtils.banmaerpSigningVO(banmaerpProperties,HttpMethod.POST,apiUrl,file);
        httpHeaders = banmaTokenUtils.banmaerpCommonHeaders(httpHeaders, banmaerpProperties, banmaerpSigningVO);
        HttpEntity requestBody = new HttpEntity(file, httpHeaders);
        BanmaErpResponseDTO<StorageDTO> responseDTO =
                httpClients.restTemplateWithBanmaMasterToken(banmaerpProperties)
                        .exchange(BanmaerpURL.banmaerp_gateway.concat(apiUrl), HttpMethod.POST, requestBody, new ParameterizedTypeReference<BanmaErpResponseDTO<StorageDTO>>() {})
                        .getBody();
        StorageDTO storageDTO = responseDTO.getData();
        storageDTO.setBanmaerpProperties(banmaerpProperties);
        storageRepository.saveAndFlush(storageDTO);
        return responseDTO;
    }

    @Override
    @CheckBanmaerpProperties
    public List<StorageDTO> saveAll(List<StorageDTO> storageDTOList, BanmaerpProperties banmaerpProperties) {
        storageDTOList.forEach(storageDTO -> storageDTO.setBanmaerpProperties(banmaerpProperties));
        //List<StorageDTO> storageDTOS = storageRepository.saveAll(storageDTOList);
        //storageRepository.flush();
        List<StorageDTO> storageDTOS = storageRepository.saveAllAndFlush(storageDTOList);
        return storageDTOS;
    }

    @Override
    @CheckBanmaerpProperties
    public StorageDTO save(StorageDTO storageDTO, BanmaerpProperties banmaerpProperties) {
        storageDTO.setBanmaerpProperties(banmaerpProperties);
        return storageRepository.saveAndFlush(storageDTO);
    }

    private boolean isFileNameValid(String name) {
        return Arrays.stream(ImageFormats.values())
                .filter(format -> Arrays.stream(format.getExtensions())
                        .anyMatch(extension -> name.toLowerCase(Locale.ROOT).contains(extension.toLowerCase(Locale.ROOT))))
                .count() > 0;
    }

    private Specification<StorageDTO> createSpecification(String ids, String name, String fileType, String fileCategoryId
            , DateTime searchTimeStart, DateTime searchTimeEnd, String searchTimeField, String sortField, String sortBy
            ,BanmaerpProperties banmaerpProperties) {
        return (root, criteriaQuery, criteriaBuilder) -> {
            List<Predicate> predicateList = new ArrayList<>();

            if (ids != null && ids != "") {
                CriteriaBuilder.In<String> in = criteriaBuilder.in(root.get("ID"));
                if (ids.contains(",")) {
                    String[] id = ids.split(",");
                    for (int i = 0; i < id.length; i++) {
                        in.value(id[i]);
                    }
                } else {
                    in.value(ids);
                }
                predicateList.add(in);
            }

            if (name != null && name != "") {
                predicateList.add(criteriaBuilder
                        .like(root.get("name"),
                                "%" + name + "%"));
            }
            if (fileType != null && fileType != "") {
                predicateList.add(criteriaBuilder
                        .equal(root.get("fileType"),
                                fileType));
            }
            if (fileCategoryId != null && fileCategoryId != "") {
                predicateList.add(criteriaBuilder
                        .equal(root.get("fileCategoryID"),
                                fileCategoryId));
            }
            // 开始时间
            if (searchTimeStart != null && searchTimeEnd != null) {
                predicateList.add(criteriaBuilder
                        .between(root.<DateTime>get(searchTimeField), searchTimeStart, searchTimeEnd));
            }
            predicateList.add(criteriaBuilder.equal(root.get("banmaerpProperties").get("X_BANMA_MASTER_APP_ID"),banmaerpProperties.getX_BANMA_MASTER_APP_ID()));
            Predicate and = criteriaBuilder.and(predicateList.toArray(new Predicate[predicateList.size()]));
            Order order = criteriaBuilder.desc(root.get("createTime"));
            if (sortBy != null && sortBy != "") {
                if (sortBy.equals("ASC")) {
                    order = criteriaBuilder.asc(root.get("createTime"));
                }
            }
            if (sortField != null && sortField != "") {
                order = criteriaBuilder.desc(root.get(sortField));
                if (sortBy.equals("ASC")) {
                    order = criteriaBuilder.asc(root.get(sortField));
                }
            }
            return criteriaQuery.where(and).orderBy(order).getRestriction();
        };
    }
}
