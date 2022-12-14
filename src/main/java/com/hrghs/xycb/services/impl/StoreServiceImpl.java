package com.hrghs.xycb.services.impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.hrghs.xycb.annotations.CheckBanmaerpProperties;
import com.hrghs.xycb.domains.BanmaerpProperties;
import com.hrghs.xycb.domains.banmaerpDTO.DataAccessDTO;
import com.hrghs.xycb.domains.banmaerpDTO.StoreDTO;
import com.hrghs.xycb.repositories.StoreRepository;
import com.hrghs.xycb.services.AccountService;
import com.hrghs.xycb.utils.BanmaParamsUtils;
import com.hrghs.xycb.utils.BanmaTokenUtils;
import com.hrghs.xycb.utils.HttpClientsUtils;
import com.hrghs.xycb.domains.BanmaerpSigningVO;
import com.hrghs.xycb.domains.BanmaerpURL;
import com.hrghs.xycb.domains.common.BanmaErpResponseDTO;
import com.hrghs.xycb.domains.enums.BanmaerpPlatformEnums;
import com.hrghs.xycb.services.StoreService;
import com.hrghs.xycb.utils.BanmaEncryptionUtils;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import org.springframework.lang.Nullable;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Predicate;
import java.lang.reflect.Type;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static com.hrghs.xycb.domains.BanmaerpProperties.BANMA_HEADER_SIGN;
import static com.hrghs.xycb.domains.Constants.*;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8;

public class StoreServiceImpl implements StoreService {

    private final static Logger logger = LoggerFactory.getLogger(StoreServiceImpl.class);

    @Autowired
    private HttpClientsUtils httpClients;
    @Autowired
    @Lazy
    private BanmaTokenUtils banmaTokenUtils;
    @Autowired
    private BanmaEncryptionUtils encryptionUtils;
    @Autowired
    private StoreRepository storeRepository;

    /**
     * ??????????????????
     * @param ids   ??????ID??????????????????
     * @param name  	????????????
     * @param platform  ????????????????????????:Platform
     * @param pageNumber    ??????????????????
     * @param pageSize  	?????????
     * @param searchTimeStart   ?????????????????????
     * @param searchTimeEnd     ?????????????????????
     * @param searchTimeField   ??????????????????????????????????????????:SearchTimeField
     * @param sortField     	?????????????????????????????????:SortField
     * @param sortBy        ??????????????????????????????:SortBy
     * @return
     */
    @Deprecated
    @Override
    @CheckBanmaerpProperties
    public Mono<BanmaErpResponseDTO<List<StoreDTO>>> getStoretListMono(@Nullable String ids, @Nullable String name, @Nullable BanmaerpPlatformEnums.Platform platform, Integer pageNumber, @Nullable Integer pageSize,
                                                                       @Nullable DateTime searchTimeStart, @Nullable DateTime searchTimeEnd, @Nullable String searchTimeField,
                                                                       @Nullable String sortField, @Nullable String sortBy,Boolean remote, BanmaerpProperties banmaerpProperties) {
        String apiUrl = String.format(BanmaerpURL.banmaerp_storelist_GET,ids,name,platform==null?"":platform.toString(),pageNumber,pageSize,
                searchTimeStart, searchTimeEnd,searchTimeField,sortField,sortBy);
        BanmaerpSigningVO banmaerpSigningVO = new BanmaerpSigningVO();
        Mono<WebClient> webClientMono = (banmaerpProperties==null)? httpClients.webClientWithBanmaMasterToken(): httpClients.webClientWithBanmaMasterToken(banmaerpProperties);
        //Mono<List<StoreDTO>> responseDTOMono =
        webClientMono
                .flatMap(webClient -> webClient.method(HttpMethod.GET).uri(apiUrl)
                                .header(BANMA_HEADER_SIGN,encryptionUtils.banmaerpSigning(banmaerpSigningVO))
                                .accept(MediaType.APPLICATION_JSON).accept(APPLICATION_JSON_UTF8).acceptCharset(Charset.defaultCharset())
                                .retrieve().bodyToMono(new ParameterizedTypeReference<BanmaErpResponseDTO<JsonNode>>() {})
                                .map(responseDTO -> responseDTO.toDataList(BANMAERP_FIELD_STORES))
                        //   .map(objects -> )
                );
        //return responseDTOMono;
        return Mono.empty();
    }

    @Override
    @CheckBanmaerpProperties
    public Page<StoreDTO> getStoretList(String ids, String name, BanmaerpPlatformEnums.Platform platform, Integer pageNumber, Integer pageSize, DateTime searchTimeStart,
                                        DateTime searchTimeEnd, String searchTimeField, String sortField, String sortBy, Boolean remote, BanmaerpProperties banmaerpProperties) {
        Page<StoreDTO> storeDTOList;
        pageSize = BanmaParamsUtils.checkPageSize(pageSize);
        if (remote){
            pageNumber = BanmaParamsUtils.checkPageNum(pageNumber,remote);
            String apiUrl = String.format(BanmaerpURL.banmaerp_storelist_GET,ids,name,platform==null?"":platform.toString(),pageNumber,pageSize,
                    searchTimeStart, searchTimeEnd,searchTimeField,sortField,sortBy);
            apiUrl = encryptionUtils.rmEmptyParas(apiUrl);
            HttpHeaders httpHeaders = new HttpHeaders();
            BanmaerpSigningVO banmaerpSigningVO = banmaTokenUtils.banmaerpSigningVO(banmaerpProperties,HttpMethod.GET,apiUrl,null);
            httpHeaders = banmaTokenUtils.banmaerpCommonHeaders(httpHeaders,banmaerpProperties,banmaerpSigningVO);
            HttpEntity requestBody = new HttpEntity(null,httpHeaders);
            storeDTOList = (Page<StoreDTO>)
                    httpClients.restTemplateWithBanmaMasterToken(banmaerpProperties)
                            .exchange(BanmaerpURL.banmaerp_gateway.concat(apiUrl),HttpMethod.GET,requestBody,new ParameterizedTypeReference<BanmaErpResponseDTO<JsonNode>>() {})
                            .getBody()
                            .toDataList(StoreDTO.class,banmaerpProperties);
            storeDTOList.forEach(storeDTO -> storeDTO.setBanmaerpProperties(banmaerpProperties));
            List<StoreDTO> storeDTOS = storeRepository.saveAll(storeDTOList);
            //storeRepository.saveAllAndFlush(storeDTOS);
            storeRepository.flush();
        }else {
            pageNumber = BanmaParamsUtils.checkPageNum(pageNumber,false);
            Specification<StoreDTO> specification = createSpecification(ids, name, platform, searchTimeStart, searchTimeEnd, searchTimeField, sortField, sortBy,banmaerpProperties);
            storeDTOList = storeRepository.findAll(specification,PageRequest.of(pageNumber,pageSize));
        }
        return storeDTOList;
    }

    @Override
    @CheckBanmaerpProperties
    public Page<StoreDTO> getStoretList(Integer pageNumber,Boolean remote, BanmaerpProperties banmaerpProperties) {
        return getStoretList(null, null, null, pageNumber, null, null, null, null, null, null,remote, banmaerpProperties);
    }

    @Override
    @CheckBanmaerpProperties
    public Page<StoreDTO> getStoretList(Integer pageNumber, Integer pageSize,Boolean remote, BanmaerpProperties banmaerpProperties) {
        return getStoretList(null, null, null, pageNumber, pageSize, null, null, null, null, null,remote, banmaerpProperties);
    }

    @Override
    @CheckBanmaerpProperties
    public List<StoreDTO> getAndSaveStoretList(Integer pageNumber, Integer pageSize, BanmaerpProperties banmaerpProperties) {
        List<StoreDTO> storeDTOList =
                getStoretList(null, null, null, pageNumber, pageSize, null, null,
                        null, null, null,true, banmaerpProperties).getContent();
        List<StoreDTO> storeDTOS = storeRepository.saveAll(storeDTOList);
        storeRepository.flush();
        return storeDTOS;
    }

    @Override
    public List<StoreDTO> getAllStores(Integer pageNum,List<StoreDTO> storeDTOList, BanmaerpProperties banmaerpProperties) {
        storeDTOList = storeDTOList==null?new ArrayList<>():storeDTOList;
        String apiUrl = String.format(BanmaerpURL.banmaerp_storelist_GET_Simple,pageNum);
        HttpHeaders httpHeaders = new HttpHeaders();
        BanmaerpSigningVO banmaerpSigningVO = banmaTokenUtils.banmaerpSigningVO(banmaerpProperties,HttpMethod.GET,apiUrl,null);
        httpHeaders = banmaTokenUtils.banmaerpCommonHeaders(httpHeaders,banmaerpProperties,banmaerpSigningVO);
        HttpEntity requestBody = new HttpEntity(null,httpHeaders);
        JsonNode jsonNode =
        httpClients.restTemplateWithBanmaMasterToken(banmaerpProperties)
                .exchange(BanmaerpURL.banmaerp_gateway.concat(apiUrl),HttpMethod.GET,requestBody,new ParameterizedTypeReference<BanmaErpResponseDTO<JsonNode>>() {})
                .getBody()
                .getData();
        Boolean hasMore =jsonNode.findPath(BANMAERP_FIELD_PAGE).get(BANMAERP_FIELD_HasMore).asBoolean();
        Type type = BanmaErpResponseDTO.getGsonType(BANMAERP_FIELD_STORES);
        StoreDTO[]  storeDTOS = BanmaErpResponseDTO.gson.fromJson(jsonNode.findPath(BANMAERP_FIELD_STORES).toString(),type);
        storeDTOList.addAll(Arrays.asList(storeDTOS));
        logger.info("retrieving stores from banmaerp at page {}, has more page {} ",pageNum, hasMore);
        if (hasMore){
            return getAllStores(pageNum++,storeDTOList,banmaerpProperties);
        }else{
            return storeDTOList;
        }
    }

    /**
     * ??????????????????
     * @param storeId ??????ID????????????
     * @return
     */
    @Override
    @CheckBanmaerpProperties
    public StoreDTO getStoreById(String storeId, Boolean remote,BanmaerpProperties banmaerpProperties) {
        if(remote){
            String apiUrl = String.format(BanmaerpURL.banmaerp_store_GET,storeId);
            apiUrl = encryptionUtils.rmEmptyParas(apiUrl);
            HttpHeaders httpHeaders = new HttpHeaders();
            BanmaerpSigningVO banmaerpSigningVO = banmaTokenUtils.banmaerpSigningVO(banmaerpProperties, HttpMethod.GET,apiUrl,null);
            httpHeaders = banmaTokenUtils.banmaerpCommonHeaders(httpHeaders,banmaerpProperties,banmaerpSigningVO);
            HttpEntity requestBody = new HttpEntity(null,httpHeaders);
            return httpClients.restTemplateWithBanmaMasterToken(banmaerpProperties)
                    .exchange(BanmaerpURL.banmaerp_gateway.concat(apiUrl), HttpMethod.GET, requestBody, new ParameterizedTypeReference<BanmaErpResponseDTO<StoreDTO>>() {})
                    .getBody().getData();
        }else{
            return storeRepository.findById(Long.parseLong(storeId)).get();
        }
    }

    @Override
    @CheckBanmaerpProperties
    public List<StoreDTO> saveStoreList(List<StoreDTO> storeDTOList, BanmaerpProperties banmaerpProperties) {
        storeDTOList.forEach(storeDTO -> storeDTO.setBanmaerpProperties(banmaerpProperties));
        List<StoreDTO> storeDTOS = storeRepository.saveAll(storeDTOList);
        //storeDTOS=storeRepository.saveAllAndFlush(storeDTOS);
        storeRepository.flush();
        return storeDTOS;
    }

    @Override
    @CheckBanmaerpProperties
    public StoreDTO saveStore(StoreDTO storeDTO, BanmaerpProperties banmaerpProperties) {
        storeDTO.setBanmaerpProperties(banmaerpProperties);
        return storeRepository.saveAndFlush(storeDTO);
    }

    private Specification<StoreDTO> createSpecification(String ids, String name, BanmaerpPlatformEnums.Platform platform
            , DateTime searchTimeStart, DateTime searchTimeEnd, String searchTimeField, String sortField, String sortBy
            , BanmaerpProperties banmaerpProperties) {
        return (root, criteriaQuery, criteriaBuilder) -> {
            List<Predicate> predicateList = new ArrayList<>();

            if (ids != null && ids != "") {
                CriteriaBuilder.In<Long> in = criteriaBuilder.in(root.get("ID"));
                if (ids.contains(",")) {
                    String[] id = ids.split(",");
                    for (int i = 0; i < id.length; i++) {
                        in.value(Long.parseLong(id[i]));
                    }
                } else {
                    in.value(Long.parseLong(ids));
                }
                predicateList.add(in);
            }

            if (name != null && name != "") {
                predicateList.add(criteriaBuilder
                        .like(root.get("name"),
                                "%" + name + "%"));
            }
            if (platform != null) {
                predicateList.add(criteriaBuilder
                        .equal(root.get("platform"),
                                platform));
            }

            // ????????????
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
