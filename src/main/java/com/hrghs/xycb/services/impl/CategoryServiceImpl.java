package com.hrghs.xycb.services.impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hrghs.xycb.annotations.CheckBanmaerpProperties;
import com.hrghs.xycb.domains.BanmaerpProperties;
import com.hrghs.xycb.domains.BanmaerpSigningVO;
import com.hrghs.xycb.domains.BanmaerpURL;
import com.hrghs.xycb.domains.banmaerpDTO.CategoryDTO;
import com.hrghs.xycb.repositories.CategoryRepository;
import com.hrghs.xycb.utils.BanmaTokenUtils;
import com.hrghs.xycb.utils.BanmaEncryptionUtils;
import com.hrghs.xycb.utils.HttpClientsUtils;
import com.hrghs.xycb.domains.common.BanmaErpResponseDTO;
import com.hrghs.xycb.services.CategoryService;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import static com.hrghs.xycb.domains.Constants.BANMAERP_FIELD_CATEGORYS;

public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private HttpClientsUtils httpClients;
    @Autowired
    private BanmaTokenUtils banmaTokenUtils;
    @Autowired
    private BanmaEncryptionUtils encryptionUtils;
    @Autowired
    CategoryRepository categoryRepository;

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
    public List<CategoryDTO> getCategoryList(String ids, String name, String parentId, Integer pageNumber, Integer pageSize, DateTime searchTimeStart
            ,DateTime searchTimeEnd, String searchTimeField, String sortField, String sortBy, Boolean remote, BanmaerpProperties banmaerpProperties) {
        List<CategoryDTO> categoryDTOList;
        if (remote){
            String apiUrl = String.format(BanmaerpURL.banmaerp_categorylist_GET, ids, name, parentId, pageNumber, pageSize, searchTimeStart, searchTimeEnd, searchTimeField,sortField,sortBy);
            apiUrl = encryptionUtils.rmEmptyParas(apiUrl);
            HttpHeaders httpHeaders = new HttpHeaders();
            BanmaerpSigningVO banmaerpSigningVO = banmaTokenUtils.banmaerpSigningVO(banmaerpProperties,HttpMethod.GET,apiUrl);
            httpHeaders = banmaTokenUtils.banmaerpCommonHeaders(httpHeaders, banmaerpProperties, banmaerpSigningVO);
            HttpEntity requestBody = new HttpEntity(null, httpHeaders);
            categoryDTOList = Arrays.stream(httpClients.restTemplateWithBanmaMasterToken(banmaerpProperties)
                            .exchange(BanmaerpURL.banmaerp_gateway.concat(apiUrl), HttpMethod.GET, requestBody, new ParameterizedTypeReference<BanmaErpResponseDTO<JsonNode>>() {})
                            .getBody()
                            .toDataList(BANMAERP_FIELD_CATEGORYS))
                    .map(o -> (CategoryDTO) o)
                    .collect(Collectors.toList());
        }else {
            Specification<CategoryDTO> specification = createSpecification(ids, name, parentId, pageNumber, pageSize, searchTimeStart, searchTimeEnd, searchTimeField, sortField, sortBy);
            categoryDTOList = categoryRepository.findAll(specification,PageRequest.of(pageNumber,pageSize)).toList();
        }
        return categoryDTOList;
    }

    @Override
    @CheckBanmaerpProperties
    public List<CategoryDTO> getCategoryList(Integer pageNumber, Boolean remote, BanmaerpProperties banmaerpProperties) {
        return getCategoryList(null, null, null, pageNumber, null, null, null, null, null, null,remote, banmaerpProperties);
    }

    @Override
    @CheckBanmaerpProperties
    public List<CategoryDTO> getCategoryList(Integer pageNumber, Integer pageSize, Boolean remote, BanmaerpProperties banmaerpProperties) {
        return getCategoryList(null, null, null, pageNumber, pageSize, null, null, null, null, null,remote, banmaerpProperties);
    }

    @Override
    @CheckBanmaerpProperties
    public List<CategoryDTO> getAndSaveCategoryList(Integer pageNumber, Integer pageSize, BanmaerpProperties banmaerpProperties) {
        List<CategoryDTO> categoryDTOList =
                getCategoryList(null, null, null, pageNumber, pageSize, null, null, null, null, null,true, banmaerpProperties);
        categoryDTOList.forEach(categoryDTO -> categoryDTO.setBanmaerpProperties(banmaerpProperties));
        return categoryRepository.saveAllAndFlush(categoryDTOList);
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
            BanmaerpSigningVO banmaerpSigningVO = banmaTokenUtils.banmaerpSigningVO(banmaerpProperties,HttpMethod.GET,apiUrl);
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
        return categoryRepository.saveAllAndFlush(categoryDTOList);
    }

    @Override
    @CheckBanmaerpProperties
    public CategoryDTO saveCategory(CategoryDTO categoryDTO, BanmaerpProperties banmaerpProperties) {
        categoryDTO.setBanmaerpProperties(banmaerpProperties);
        return categoryRepository.saveAndFlush(categoryDTO);
    }
    private Specification<CategoryDTO> createSpecification(String ids, String name, String parentId, Integer pageNumber, Integer pageSize, DateTime searchTimeStart, DateTime searchTimeEnd, String searchTimeField, String sortField, String sortBy) {
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
