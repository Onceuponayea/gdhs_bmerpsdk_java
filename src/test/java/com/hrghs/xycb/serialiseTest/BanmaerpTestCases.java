package com.hrghs.xycb.serialiseTest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.joda.JodaModule;
import com.hrghs.xycb.domains.banmaerpDTO.ProductSuppliersInfoDTO;
import com.hrghs.xycb.domains.banmaerpDTO.TokenResponseDTO;
import com.hrghs.xycb.domains.common.BanmaErpResponseDTO;

public class BanmaerpTestCases {
    public static void main(String[] args) throws JsonProcessingException {
        String getTokenResp="{\n" +
                "    \"Code\": 200,\n" +
                "    \"Time\": \"2021-12-12 15:23:34\",    \n" +
                "    \"Data\": {\n" +
                "        \"AppID\": \"1371383319572253423\",\n" +
                "        \"Scopes\": [\n" +
                "            \"all_read\",\n" +
                "            \"all_write\"\n" +
                "        ],\n" +
                "        \"AccessTokenExpiryTime\": \"2020-12-16 15:44:27\",\n" +
                "        \"RefreshToken\": \"3bc03c6862aa4e2cb45654d01036754\",\n" +
                "        \"AccessToken\": \"1cbc1a951d3844245654601036754\",\n" +
                "        \"RefreshTokenExpiryTime\": \"2021-01-12 15:44:27\"\n" +
                "    },\n" +
                "    \"Success\": true,\n" +
                "    \"Message\": \"成功\"\n" +
                "}";

        ObjectMapper objectMapper = new ObjectMapper().registerModule(new JodaModule());
        BanmaErpResponseDTO<TokenResponseDTO> banmaErpResponseDTO = objectMapper.readValue(getTokenResp, new TypeReference<BanmaErpResponseDTO<TokenResponseDTO>>() {});
        System.out.println(banmaErpResponseDTO.getData().getAccessToken());


    }
}
