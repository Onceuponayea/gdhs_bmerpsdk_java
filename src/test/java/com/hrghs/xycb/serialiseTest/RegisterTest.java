package com.hrghs.xycb.serialiseTest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.joda.JodaModule;
import com.hrghs.xycb.domains.banmaerpDTO.RegisterResponseDTO;
import com.hrghs.xycb.domains.common.BanmaErpResponseDTO;


public class RegisterTest {
    public static void main(String[] args) throws JsonProcessingException {
        String resp ="{\n" +
                "    \"Code\": 200,\n" +
                "    \"Time\": \"2022-11-05 15:23:34\",    \n" +
                "    \"Data\": {\n" +
                "        \"Account\": {\n" +
                "            \"ID\": 12345,\n" +
                "            \"Phone\": \"1500000000\",\n" +
                "            \"Email\": \"a@b.com\",\n" +
                "            \"RealName\": \"test\",\n" +
                "            \"Department\":\"\"\n" +
                "        },\n" +
                "        \"App\": {\n" +
                "            \"ID\": 1371383319572253423,\n" +
                "            \"Name\": \"默认应用\",\n" +
                "            \"Status\":\"已上线\",\n" +
                "            \"Secret\":\"g15gfyc6862aa4e2cb45654d01031478\",\n" +
                "            \"Scopes\": [\n" +
                "                \"all_read\",\n" +
                "                \"all_write\"\n" +
                "            ],\n" +
                "            \"AuthType\":\"签名\"\n" +
                "        },\n" +
                "        \"Auth\": {\n" +
                "            \"AppID\": \"1371383319572253423\",\n" +
                "            \"Scopes\": [\n" +
                "                \"all_read\",\n" +
                "                \"all_write\"\n" +
                "            ],\n" +
                "            \"AccessTokenExpiryTime\": \"2020-12-16 15:44:27\",\n" +
                "            \"RefreshToken\": \"3bc03c6862aa4e2cb45654d01036754\",\n" +
                "            \"AccessToken\": \"1cbc1a951d3844245654601036754\",\n" +
                "            \"RefreshTokenExpiryTime\": \"2021-01-12 15:44:27\"\n" +
                "        }\n" +
                "    },\n" +
                "    \"Success\": true,\n" +
                "    \"Message\": \"成功\"\n" +
                "}";

        ObjectMapper objectMapper = new ObjectMapper().registerModule(new JodaModule());
        BanmaErpResponseDTO<RegisterResponseDTO> banmaErpResponseDTO = objectMapper.readValue(resp, new TypeReference<BanmaErpResponseDTO<RegisterResponseDTO>>() {
        });
        System.out.println(banmaErpResponseDTO.getData().getAppsInfoDTO().getName());
    }
}
