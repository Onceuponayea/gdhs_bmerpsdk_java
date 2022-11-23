package com.hrghs.xycb.services.impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.hrghs.xycb.annotations.CheckBanmaerpProperties;
import com.hrghs.xycb.config.BanmaerpProperties;
import com.hrghs.xycb.domains.banmaerpDTO.StoreDTO;
import com.hrghs.xycb.domains.banmaerpDTO.TokenResponseDTO;
import com.hrghs.xycb.utils.BanmaTokenUtils;
import com.hrghs.xycb.utils.HttpClientsUtils;
import com.hrghs.xycb.domains.BanmaerpSigningVO;
import com.hrghs.xycb.domains.BanmaerpURL;
import com.hrghs.xycb.domains.common.BanmaErpResponseDTO;
import com.hrghs.xycb.domains.enums.BanmaerpPlatformEnums;
import com.hrghs.xycb.services.StoreService;
import com.hrghs.xycb.utils.EncryptionUtils;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import static com.hrghs.xycb.config.BanmaerpProperties.BANMA_HEADER_SIGN;
import static com.hrghs.xycb.domains.Constants.BANMAERP_FIELD_STORES;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8;

@Service
public class StoreServiceImpl implements StoreService {
    @Autowired
    private HttpClientsUtils httpClients;
    @Autowired
    private BanmaTokenUtils banmaTokenUtils;
    @Autowired
    private EncryptionUtils encryptionUtils;
    /**
     * 查询店铺列表
     * @param ids   店铺ID，用逗号分隔
     * @param name  	店铺名称
     * @param platform  平台，具体值参见:Platform
     * @param pageNumber    页码（必填）
     * @param pageSize  	页大小
     * @param searchTimeStart   查询的开始时间
     * @param searchTimeEnd     查询的结束时间
     * @param searchTimeField   查询的时间字段名，具体值参见:SearchTimeField
     * @param sortField     	排序字段名，具体值参见:SortField
     * @param sortBy        排序方式，具体值参见:SortBy
     * @return
     */
    @Deprecated
    @Override
    @CheckBanmaerpProperties
    public Mono<BanmaErpResponseDTO<List<StoreDTO>>> getStoretListMono(@Nullable String ids, @Nullable String name, @Nullable BanmaerpPlatformEnums.Platform platform, int pageNumber, @Nullable int pageSize,
                                                                 @Nullable DateTime searchTimeStart, @Nullable DateTime searchTimeEnd, @Nullable String searchTimeField,
                                                                 @Nullable String sortField, @Nullable String sortBy, BanmaerpProperties banmaerpProperties) {
        String apiUrl = String.format(BanmaerpURL.banmaerp_storelist_GET,ids,name,platform==null?"":platform.toString(),pageNumber,pageSize,
                searchTimeStart, searchTimeEnd,searchTimeField,sortField,sortBy);
        BanmaerpSigningVO banmaerpSigningVO = new BanmaerpSigningVO();
        //todo banmaerpSigningVO set parameters
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
    public List<StoreDTO> getStoretList(String ids, String name, BanmaerpPlatformEnums.Platform platform, int pageNumber, int pageSize, DateTime searchTimeStart,
                                        DateTime searchTimeEnd, String searchTimeField, String sortField, String sortBy,
                                        BanmaerpProperties banmaerpProperties) {
        String apiUrl = String.format(BanmaerpURL.banmaerp_storelist_GET,ids,name,platform==null?"":platform.toString(),pageNumber,pageSize,
                searchTimeStart, searchTimeEnd,searchTimeField,sortField,sortBy);
        apiUrl = encryptionUtils.rmEmptyParas(apiUrl);
        HttpHeaders httpHeaders = new HttpHeaders();
        BanmaerpSigningVO banmaerpSigningVO = banmaTokenUtils.banmaerpSigningVO(banmaerpProperties);
        //todo signing
        httpHeaders = banmaTokenUtils.banmaerpCommonHeaders(httpHeaders,banmaerpProperties,banmaerpSigningVO);
        HttpEntity requestBody = new HttpEntity(null,httpHeaders);
        List<StoreDTO> storeDTOList = Arrays.stream(httpClients.restTemplateWithBanmaMasterToken(banmaerpProperties)
            .exchange(BanmaerpURL.banmaerp_gateway.concat(apiUrl),HttpMethod.GET,requestBody,new ParameterizedTypeReference<BanmaErpResponseDTO<JsonNode>>() {})
            .getBody()
            .toDataList(BANMAERP_FIELD_STORES)
        )
        .map(o -> (StoreDTO)o)
        .collect(Collectors.toList());
        return storeDTOList;
    }

    /**
     * 查询单个店铺
     * @param spuId 店铺ID（必填）
     * @return
     */
    @Override
    public BanmaErpResponseDTO getStoreById(String spuId,
                                            BanmaerpProperties banmaerpProperties) {
        return null;
    }
}
