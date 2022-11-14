package com.hrghs.xycb;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.joda.JodaModule;
import com.hrghs.xycb.domains.GetTokenResponse;
import com.hrghs.xycb.domains.banmaerpDTO.CategoryDTO;
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
        String  cateList ="{\n" +
                "    \"Code\": 200,\n" +
                "    \"Time\": \"2021-12-12 15:23:34\",    \n" +
                "    \"Data\": {\n" +
                "        \"Categorys\": [\n" +
                "            {\n" +
                "                \"ID\": \"57939d9a-7f32-e811-8ffd-340804e01078\",\n" +
                "                \"ParentID\": \"d039e074-7f32-e811-8ffd-340804e01078\",\n" +
                "                \"Name\": \"女鞋\",\n" +
                "                \"Description\": \"\",\n" +
                "                \"Code\":\"WH\",\n" +
                "                \"Sort\": 0,\n" +
                "                \"CreateTime\": \"2018-03-28 20:00:28\",\n" +
                "                \"UpdateTime\": \"2018-03-28 20:00:28\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"ID\": \"23339d9a-7f32-e811-8ffd-340232301078\",\n" +
                "                \"ParentID\": \"d039e074-7f32-e811-8ffd-340804e01078\",\n" +
                "                \"Name\": \"男鞋\",\n" +
                "                \"Description\": \"\",\n" +
                "                \"Code\":\"NH\",\n" +
                "                \"Sort\": 0,\n" +
                "                \"CreateTime\": \"2018-03-28 20:00:28\",\n" +
                "                \"UpdateTime\": \"2018-03-28 20:00:28\"\n" +
                "            }\n" +
                "        ],\n" +
                "        \"Page\": {\n" +
                "            \"PageNumber\": 1,\n" +
                "            \"PageCount\": 1,\n" +
                "            \"PageSize\": 20,\n" +
                "            \"TotalCount\": 2,\n" +
                "            \"HasMore\": false\n" +
                "        }\n" +
                "    },\n" +
                "    \"Success\": true,\n" +
                "    \"Message\": \"成功\"\n" +
                "}";
        ObjectMapper objectMapper = new ObjectMapper().registerModule(new JodaModule());
//        BanmaErpResponseDTO<GetTokenResponse> banmaErpResponseDTO = objectMapper.readValue(getTokenResp, new TypeReference<BanmaErpResponseDTO<GetTokenResponse>>() {});
//        System.out.println(banmaErpResponseDTO.getData().getAccessToken());
        BanmaErpResponseDTO<CategoryDTO> banmaErpResponseDTO = objectMapper.readValue(getTokenResp, new TypeReference<BanmaErpResponseDTO<CategoryDTO>>() {});
    }
}
