package com.hrghs.xycb;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.joda.JodaModule;
import com.hrghs.xycb.domains.GetSsoPassportResponse;
import com.hrghs.xycb.domains.common.BanmaErpResponseDTO;
import com.hrghs.xycb.domains.common.BanmaErpResponseDateDTO;

/**
 * 2022.11.14 jzx
 * SSO登录接口API序列化、反序列化没有问题
 */
public class BanmaerSsoTest {
    public static void main(String[] args) throws JsonProcessingException {

        // 获取认证令牌
        String ssoPassport = "{\n" +
                "    \"Code\": 200,\n" +
                "    \"Time\": \"2022-11-05 15:23:34\",    \n" +
                "    \"Data\": {\n" +
                "        \"UserID\": 1,\n" +
                "        \"AppID\": \"12022010087511\",\n" +
                "        \"Account\": \"18800000000\",\n" +
                "        \"ClientIP\": \"211.202.194.32\",\n" +
                "        \"SsoToken\":\"0357e364fc8c449ea799c5303bd68607\",\n" +
                "        \"SsoUrl\": \"https://erp.banmaerp.com/Auth/Sso/1a52567306174fe780915467a38cc0b9\"\n" +
                "    },\n" +
                "    \"Success\": true,\n" +
                "    \"Message\": \"成功\"\n" +
                "}\n";


        ObjectMapper objectMapper = new ObjectMapper().registerModule(new JodaModule());
        BanmaErpResponseDTO<GetSsoPassportResponse> banmaErpResponseDTO = objectMapper.readValue(ssoPassport, new TypeReference<BanmaErpResponseDTO<GetSsoPassportResponse>>() {
        });
        System.out.println(banmaErpResponseDTO.getData().getUserId());
    }
}
