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
import com.hrghs.xycb.domains.GetSsoPassportResponse;
import com.hrghs.xycb.domains.banmaerpDTO.AccountDTO;
import com.hrghs.xycb.domains.common.BanmaErpResponseDTO;
import com.hrghs.xycb.services.SsoService;
import com.hrghs.xycb.utils.BanmaTokenUtils;
import com.hrghs.xycb.utils.EncryptionUtils;
import com.hrghs.xycb.utils.HttpClientsUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;

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
    public GetSsoPassportResponse ssoPassport(String account, String clientIp, int userId, int mode, BanmaerpProperties banmaerpProperties) {
        String apiUrl = String.format(BanmaerpURL.banmaerp_sso_GET,account,clientIp,mode);
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
        GetSsoPassportResponse ssoPassportResponse = null;
        try {
            ssoPassportResponse = objectMapper.readValue(body.getData().toString(), new TypeReference<GetSsoPassportResponse>() {
            });
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return ssoPassportResponse;
    }
}
