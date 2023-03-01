package com.hrghs.xycb.services.impl;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.hrghs.xycb.annotations.CheckBanmaerpProperties;
import com.hrghs.xycb.domains.BanmaerpProperties;
import com.hrghs.xycb.domains.BanmaerpSigningVO;
import com.hrghs.xycb.domains.BanmaerpURL;
import com.hrghs.xycb.domains.banmaerpDTO.CategoryDTO;
import com.hrghs.xycb.domains.banmaerpDTO.ProductDTO;
import com.hrghs.xycb.repositories.CategoryRepository;
import com.hrghs.xycb.utils.*;
import com.hrghs.xycb.domains.common.BanmaErpResponseDTO;
import com.hrghs.xycb.services.CategoryService;
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
import org.springframework.util.CollectionUtils;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private HttpClientsUtils httpClients;
    @Autowired
    private BanmaTokenUtils banmaTokenUtils;
    @Autowired
    private BanmaEncryptionUtils encryptionUtils;
    @Autowired
    CategoryRepository categoryRepository;

    private Gson gson = new GsonBuilder().disableHtmlEscaping()
            .setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE)
            .registerTypeAdapter(DateTime.class,new DateTimeConverter())
            .setDateFormat("yyyy-MM-dd HH:mm:ss")
            .create();;
    /**
     * 查询类目列表
     *
     * @param ids             类目ID，用逗号分隔
     * @param name            类目名称
     * @param parentId        类目父级id
     * @param pageNumber      页码（必填）
     * @param searchTimeStart 页大小
     * @param searchTimeEnd   查询的开始时间
     * @param searchTimeField 查询的结束时间
     * @param sortField       查询的时间字段名，具体值参见:SearchTimeField
     * @param sortBy          排序字段名，具体值参见:SortField
     * @return 排序方式，具体值参见:SortBy
     */
    @Override
    @CheckBanmaerpProperties
    public Page<CategoryDTO> getCategoryList(String ids, String name, String parentId, Integer pageNumber, Integer pageSize, DateTime searchTimeStart
            , DateTime searchTimeEnd, String searchTimeField, String sortField, String sortBy, Boolean remote, BanmaerpProperties banmaerpProperties) {
        Page<CategoryDTO> categoryDTOList;
        pageSize = BanmaParamsUtils.checkPageSize(pageSize);
        if (remote){
            pageNumber = BanmaParamsUtils.checkPageNum(pageNumber,remote);
            String apiUrl = String.format(BanmaerpURL.banmaerp_categorylist_GET, ids, name, parentId, pageNumber, pageSize, searchTimeStart, searchTimeEnd, searchTimeField,sortField,sortBy);
            apiUrl = encryptionUtils.rmEmptyParas(apiUrl);
            HttpHeaders httpHeaders = new HttpHeaders();
            BanmaerpSigningVO banmaerpSigningVO = banmaTokenUtils.banmaerpSigningVO(banmaerpProperties,HttpMethod.GET,apiUrl,null);
            httpHeaders = banmaTokenUtils.banmaerpCommonHeaders(httpHeaders, banmaerpProperties, banmaerpSigningVO);
            HttpEntity requestBody = new HttpEntity(null, httpHeaders);
            categoryDTOList = (Page<CategoryDTO>)
                    httpClients.restTemplateWithBanmaMasterToken(banmaerpProperties)
                            .exchange(BanmaerpURL.banmaerp_gateway.concat(apiUrl), HttpMethod.GET, requestBody, new ParameterizedTypeReference<BanmaErpResponseDTO<JsonNode>>() {})
                            .getBody()
                            .toDataList(CategoryDTO.class,banmaerpProperties);
            categoryDTOList.forEach(categoryDTO -> categoryDTO.setBanmaerpProperties(banmaerpProperties));
//            List<CategoryDTO> categoryDTOS = categoryRepository.saveAll(categoryDTOList);
//            categoryRepository.flush();
            List<CategoryDTO> categoryDTOS =categoryRepository.saveAllAndFlush(categoryDTOList);
            //低版本的spring boot没有saveAllAndFlush
        }else {
            pageNumber = BanmaParamsUtils.checkPageNum(pageNumber,false);
            Specification<CategoryDTO> specification = createSpecification(ids, name, parentId, searchTimeStart, searchTimeEnd, searchTimeField, sortField, sortBy,banmaerpProperties);
            categoryDTOList = categoryRepository.findAll(specification,PageRequest.of(pageNumber,pageSize));
        }
        return categoryDTOList;
    }

    @Override
    @CheckBanmaerpProperties
    public Page<CategoryDTO> getCategoryList(Integer pageNumber, Boolean remote, BanmaerpProperties banmaerpProperties) {
        return getCategoryList(null, null, null, pageNumber, null, null, null, null, null, null,remote, banmaerpProperties);
    }

    @Override
    @CheckBanmaerpProperties
    public Page<CategoryDTO> getCategoryList(Integer pageNumber, Integer pageSize, Boolean remote, BanmaerpProperties banmaerpProperties) {
        return getCategoryList(null, null, null, pageNumber, pageSize, null, null, null, null, null,remote, banmaerpProperties);
    }

    @Override
    @CheckBanmaerpProperties
    public List<CategoryDTO> getAndSaveCategoryList(Integer pageNumber, Integer pageSize, BanmaerpProperties banmaerpProperties) {
        List<CategoryDTO> categoryDTOList = getCategoryList(null, null, null, pageNumber, pageSize, null,
                        null, null, null, null,true, banmaerpProperties).getContent();
        categoryDTOList.forEach(categoryDTO -> categoryDTO.setBanmaerpProperties(banmaerpProperties));
//      List<CategoryDTO> categoryDTOS = categoryRepository.saveAll(categoryDTOList);
//      categoryRepository.flush();
        List<CategoryDTO> categoryDTOS = categoryRepository.saveAllAndFlush(categoryDTOList);
        return categoryDTOS;
    }

    /**
     * @param idv
     * @param banmaerpProperties
     * @return
     */

    @Override
    @CheckBanmaerpProperties
    public CategoryDTO getCategoryById(String idv, Boolean remote, BanmaerpProperties banmaerpProperties) {
        if (remote){
            String apiUrl = String.format(BanmaerpURL.banmaerp_category_GET,idv);
            apiUrl = encryptionUtils.rmEmptyParas(apiUrl);
            HttpHeaders httpHeaders = new HttpHeaders();
            BanmaerpSigningVO banmaerpSigningVO = banmaTokenUtils.banmaerpSigningVO(banmaerpProperties,HttpMethod.GET,apiUrl,null);
            httpHeaders = banmaTokenUtils.banmaerpCommonHeaders(httpHeaders,banmaerpProperties,banmaerpSigningVO);
            HttpEntity requestBody = new HttpEntity(null,httpHeaders);
            return  httpClients.restTemplateWithBanmaMasterToken(banmaerpProperties)
                    .exchange(BanmaerpURL.banmaerp_gateway.concat(apiUrl), HttpMethod.GET, requestBody, new ParameterizedTypeReference<BanmaErpResponseDTO<CategoryDTO>>() {})
                    .getBody().getData();
        }else {
            return categoryRepository.findById(idv).get();
        }
    }

    @Override
    @CheckBanmaerpProperties
    public List<CategoryDTO> saveCategoryList(List<CategoryDTO> categoryDTOList, BanmaerpProperties banmaerpProperties) {
        categoryDTOList.forEach(categoryDTO -> categoryDTO.setBanmaerpProperties(banmaerpProperties));
        //List<CategoryDTO> categoryDTOS = categoryRepository.saveAll(categoryDTOList);
        //categoryRepository.flush();
        List<CategoryDTO> categoryDTOS = categoryRepository.saveAllAndFlush(categoryDTOList);
        return categoryDTOS;
    }

    @Override
    @CheckBanmaerpProperties
    public CategoryDTO saveCategory(CategoryDTO categoryDTO, BanmaerpProperties banmaerpProperties) {
        categoryDTO.setBanmaerpProperties(banmaerpProperties);
        return categoryRepository.saveAndFlush(categoryDTO);
    }

    @Override
    @CheckBanmaerpProperties
    public CategoryDTO createCategory(CategoryDTO categoryDTO, BanmaerpProperties banmaerpProperties) {
        String apiUrl = BanmaerpURL.banmaerp_category_POST;
        apiUrl = encryptionUtils.rmEmptyParas(apiUrl);
        HttpHeaders httpHeaders = new HttpHeaders();
        String requestBodyJson = gson.toJson(categoryDTO);
        BanmaerpSigningVO banmaerpSigningVO = banmaTokenUtils.banmaerpSigningVO(banmaerpProperties,HttpMethod.POST,apiUrl,requestBodyJson);
        httpHeaders = banmaTokenUtils.banmaerpCommonHeaders(httpHeaders,banmaerpProperties,banmaerpSigningVO);
        httpHeaders.set(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        HttpEntity requestBody = new HttpEntity(requestBodyJson,httpHeaders);
        return  httpClients.restTemplateWithBanmaMasterToken(banmaerpProperties)
                .exchange(BanmaerpURL.banmaerp_gateway.concat(apiUrl), HttpMethod.POST, requestBody, new ParameterizedTypeReference<BanmaErpResponseDTO<CategoryDTO>>() {})
                .getBody().getData();
    }

    private Specification<CategoryDTO> createSpecification(String ids, String name, String parentId, DateTime searchTimeStart
            , DateTime searchTimeEnd, String searchTimeField, String sortField, String sortBy,BanmaerpProperties banmaerpProperties) {
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
            if (parentId != null && parentId != "") {
                predicateList.add(criteriaBuilder
                        .equal(root.get("parentId"),
                                parentId));
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
