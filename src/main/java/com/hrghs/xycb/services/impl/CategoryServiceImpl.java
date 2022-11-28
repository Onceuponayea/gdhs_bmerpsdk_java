package com.hrghs.xycb.services.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.joda.JodaModule;
import com.hrghs.xycb.annotations.CheckBanmaerpProperties;
import com.hrghs.xycb.config.BanmaerpProperties;
import com.hrghs.xycb.domains.BanmaerpSigningVO;
import com.hrghs.xycb.domains.BanmaerpURL;
import com.hrghs.xycb.domains.banmaerpDTO.AccountDTO;
import com.hrghs.xycb.domains.banmaerpDTO.CategoryDTO;
import com.hrghs.xycb.repositories.CategoryRepository;
import com.hrghs.xycb.utils.BanmaTokenUtils;
import com.hrghs.xycb.utils.EncryptionUtils;
import com.hrghs.xycb.utils.HttpClientsUtils;
import com.hrghs.xycb.domains.common.BanmaErpResponseDTO;
import com.hrghs.xycb.services.CategoryService;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static com.hrghs.xycb.domains.Constants.BANMAERP_FIELD_ACCOUNTS;
import static com.hrghs.xycb.domains.Constants.BANMAERP_FIELD_CATEGORYS;

@Service
@Lazy
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private HttpClientsUtils httpClients;
    @Autowired
    private BanmaTokenUtils banmaTokenUtils;
    @Autowired
    private EncryptionUtils encryptionUtils;
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    @Lazy
    private ObjectMapper objectMapper;

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
    public List<CategoryDTO> getCategoryList(String ids, String name, String parentId, int pageNumber, int pageSize, DateTime searchTimeStart,
                                               DateTime searchTimeEnd, String searchTimeField, String sortField, String sortBy,
                                               BanmaerpProperties banmaerpProperties) {
        String apiUrl = String.format(BanmaerpURL.banmaerp_categorylist_GET, pageNumber, pageSize,searchTimeStart,searchTimeEnd,searchTimeField);
        apiUrl = encryptionUtils.rmEmptyParas(apiUrl);
        HttpHeaders httpHeaders = new HttpHeaders();
        BanmaerpSigningVO banmaerpSigningVO = banmaTokenUtils.banmaerpSigningVO(banmaerpProperties);
        //todo signing
        httpHeaders = banmaTokenUtils.banmaerpCommonHeaders(httpHeaders, banmaerpProperties, banmaerpSigningVO);
        HttpEntity requestBody = new HttpEntity(null, httpHeaders);
        List<CategoryDTO> categoryDTOList = Arrays.stream(httpClients.restTemplateWithBanmaMasterToken(banmaerpProperties)
                .exchange(BanmaerpURL.banmaerp_gateway.concat(apiUrl), HttpMethod.GET, requestBody, new ParameterizedTypeReference<BanmaErpResponseDTO<JsonNode>>() {
                })
                .getBody()
                .toDataList(BANMAERP_FIELD_CATEGORYS)
        )
                .map(o -> (CategoryDTO) o)
                .collect(Collectors.toList());
        return categoryDTOList;
    }

    /**
     * @param idv
     * @param banmaerpProperties
     * @return
     */

    @Override
    @CheckBanmaerpProperties
    public CategoryDTO getCategoryById(String idv, BanmaerpProperties banmaerpProperties) {
        String apiUrl = String.format(BanmaerpURL.banmaerp_category_GET,idv);
        apiUrl = encryptionUtils.rmEmptyParas(apiUrl);
        HttpHeaders httpHeaders = new HttpHeaders();
        BanmaerpSigningVO banmaerpSigningVO = banmaTokenUtils.banmaerpSigningVO(banmaerpProperties);
        //todo signing
        httpHeaders = banmaTokenUtils.banmaerpCommonHeaders(httpHeaders,banmaerpProperties,banmaerpSigningVO);
        HttpEntity requestBody = new HttpEntity(null,httpHeaders);
        BanmaErpResponseDTO<JsonNode> body = httpClients.restTemplateWithBanmaMasterToken(banmaerpProperties)
                .exchange(BanmaerpURL.banmaerp_gateway.concat(apiUrl), HttpMethod.GET, requestBody, new ParameterizedTypeReference<BanmaErpResponseDTO<JsonNode>>() {
                })
                .getBody();
        CategoryDTO categoryDTO = null;
        try {
            categoryDTO = objectMapper.readValue(body.getData().toString(), new TypeReference<CategoryDTO>() {
            });
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return categoryDTO;
    }

    @Override
    public List<CategoryDTO> saveCategoryList(List<CategoryDTO> categoryDTOList) {
        return categoryRepository.saveAllAndFlush(categoryDTOList);
    }

    @Override
    public CategoryDTO saveCategory(CategoryDTO categoryDTO) {
        return categoryRepository.saveAndFlush(categoryDTO);
    }
}
