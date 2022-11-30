package com.hrghs.xycb.services.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.GsonBuilder;
import com.hrghs.xycb.annotations.CheckBanmaerpProperties;
import com.hrghs.xycb.domains.BanmaerpProperties;
import com.hrghs.xycb.domains.*;
import com.hrghs.xycb.domains.banmaerpDTO.AccountDTO;
import com.hrghs.xycb.domains.banmaerpDTO.AppsInfoDTO;
import com.hrghs.xycb.domains.common.BanmaErpResponseDTO;
import com.hrghs.xycb.repositories.BanmaerpPropertiesRepository;
import com.hrghs.xycb.services.SsoService;
import com.hrghs.xycb.utils.BanmaTokenUtils;
import com.hrghs.xycb.utils.EncryptionUtils;
import com.hrghs.xycb.utils.HttpClientsUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.redis.core.ReactiveRedisOperations;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import static com.hrghs.xycb.domains.Constants.*;
import static jodd.util.StringPool.COLON;

@Service
@Lazy
public class SsoServiceImpl implements SsoService {
    @Autowired
    private HttpClientsUtils httpClients;
    @Autowired
    private BanmaTokenUtils banmaTokenUtils;
    @Autowired
    private EncryptionUtils encryptionUtils;
    @Autowired
    @Lazy
    private ObjectMapper objectMapper;
    @Autowired
    private ReactiveRedisOperations<String,String> redisOperations;
    @Autowired
    private BanmaerpPropertiesRepository  banmaerpPropertiesRepository;
    /**
     * 获取认证令牌
     * @param account 登录账号（手机号或者邮箱，必填）
     * @param clientIp 用户客户端IP（必填）
     * @param clientIp 用户ID
     * @param mode
     * @param banmaerpProperties 斑马erp主账号（供应商或者平台）
     * 可用值：
     * 0=返回认证令牌(默认值)
     * 1=浏览器直接跳转
     * @return
     */
    @Override
    @CheckBanmaerpProperties
    public GetSsoPassportResponse ssoPassport(String account, String clientIp, Integer userId, Integer mode, BanmaerpProperties banmaerpProperties) {
        String apiUrl = String.format(BanmaerpURL.banmaerp_sso_GET,account,clientIp,mode);
        apiUrl = encryptionUtils.rmEmptyParas(apiUrl);
        HttpHeaders httpHeaders = new HttpHeaders();
        BanmaerpSigningVO banmaerpSigningVO = banmaTokenUtils.banmaerpSigningVO(banmaerpProperties);
        httpHeaders = banmaTokenUtils.banmaerpCommonHeaders(httpHeaders,banmaerpProperties,banmaerpSigningVO);
        HttpEntity requestBody = new HttpEntity(null,httpHeaders);
        return httpClients.restTemplateWithBanmaMasterToken(banmaerpProperties)
                .exchange(BanmaerpURL.banmaerp_gateway.concat(apiUrl), HttpMethod.GET, requestBody, new ParameterizedTypeReference<BanmaErpResponseDTO<GetSsoPassportResponse>>() {})
                .getBody().getData();
    }

    @Override
    public GetSsoPassportResponse ssoPassport(String account, BanmaerpProperties banmaerpProperties) {
        return
        redisOperations.opsForValue().get(ssoRedisKey(banmaerpProperties)).switchIfEmpty(Mono.just(httpClients.getLocalIp()))
                .map(clientIp -> ssoPassport(account,clientIp,null,null,banmaerpProperties))
                .block();
    }

    @Override
    public GetSsoPassportResponse ssoPassport(String account, String clientIp, BanmaerpProperties banmaerpProperties) {
        return ssoPassport(account,clientIp,null,null,banmaerpProperties);
    }

    private String ssoRedisKey(BanmaerpProperties banmaerpProperties){
        return BANMAERP_FIELD_PREFIX.concat(COLON).concat(BANMAERP_FIELD_CLIENTIP).concat(COLON)
                .concat(banmaerpProperties.getX_BANMA_MASTER_APP_ID());
    }

    /**
     * 注册账号
     * @param accountDTO 账号信息
     * @param appsInfoDTO APP信息
     * @return
     */
    @Override
    @CheckBanmaerpProperties
    public BanmaErpResponseDTO<SsoRegisterResponse> register(AccountDTO accountDTO, AppsInfoDTO appsInfoDTO,BanmaerpProperties banmaerpProperties) {
        SsoRegisterRequest ssoRegisterRequest = new SsoRegisterRequest(accountDTO,appsInfoDTO);
        String requestBodyJson = null;
        try {
            requestBodyJson = objectMapper.writeValueAsString(ssoRegisterRequest);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            requestBodyJson = new GsonBuilder().disableHtmlEscaping().create().toJson(ssoRegisterRequest);
        }
//        String requestBodyJson = JSONUtil.toJsonStr(ssoRegisterRequest);
        String apiUrl = String.format(BanmaerpURL.banmaerp_ssoRegister_POST);
        apiUrl = encryptionUtils.rmEmptyParas(apiUrl);
        HttpHeaders httpHeaders = new HttpHeaders();
        BanmaerpSigningVO banmaerpSigningVO = banmaTokenUtils.banmaerpSigningVO(banmaerpProperties);
        httpHeaders = banmaTokenUtils.banmaerpCommonHeaders(httpHeaders,banmaerpProperties,banmaerpSigningVO);
        httpHeaders.set(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        HttpEntity requestBody = new HttpEntity(requestBodyJson,httpHeaders);
        BanmaErpResponseDTO<SsoRegisterResponse> body = httpClients.restTemplateWithBanmaMasterToken(banmaerpProperties)
                .exchange(BanmaerpURL.banmaerp_gateway.concat(apiUrl), HttpMethod.POST, requestBody, new ParameterizedTypeReference<BanmaErpResponseDTO<SsoRegisterResponse>>() {})
                .getBody();
        banmaerpPropertiesRepository.saveAndFlush(body.getData().toBanmaerpProperties());
        return body;
    }
}
