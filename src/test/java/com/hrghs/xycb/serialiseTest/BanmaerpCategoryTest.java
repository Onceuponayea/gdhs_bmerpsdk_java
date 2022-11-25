package com.hrghs.xycb.serialiseTest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.joda.JodaModule;
import com.hrghs.xycb.domains.banmaerpDTO.CategoryDTO;
import com.hrghs.xycb.domains.common.BanmaErpResponseDTO;


import java.util.List;

/**
 * 2022.11.14 jzx
 * 类目接口API序列化、反序列化没有问题
 */
public class BanmaerpCategoryTest {
    public static void main(String[] args) throws JsonProcessingException {

        // 查询类目列表
        String getCategoryList="{\n" +
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
                "}\n";

        // 查询单个类目
        String getCategoryById = "{\n" +
                "    \"Code\": 200,\n" +
                "    \"Time\": \"2021-12-12 15:23:34\",    \n" +
                "    \"Data\": {\n" +
                "        \"Category\": {\n" +
                "            \"ID\": \"57939d9a-7f32-e811-8ffd-340804e01078\",\n" +
                "            \"ParentID\": \"d039e074-7f32-e811-8ffd-340804e01078\",\n" +
                "            \"Name\": \"女鞋\",\n" +
                "            \"Description\": \"\",\n" +
                "            \"Code\":\"WH\",\n" +
                "            \"Sort\": 0,\n" +
                "            \"CreateTime\": \"2018-03-28 20:00:28\",\n" +
                "            \"UpdateTime\": \"2018-03-28 20:00:28\"\n" +
                "        }\n" +
                "    },\n" +
                "    \"Success\": true,\n" +
                "    \"Message\": \"成功\"\n" +
                "}\n";

        ObjectMapper objectMapper = new ObjectMapper().registerModule(new JodaModule());
//        BanmaErpResponseDTO<BanmaErpResponseDataDTO> banmaErpResponseDTO = objectMapper.readValue(getCategoryById, new TypeReference<BanmaErpResponseDTO<BanmaErpResponseDataDTO>>() {});
//        System.out.println(banmaErpResponseDTO.getData().getCategory().getId());
//        BanmaErpResponseDTO<BanmaErpResponseDataDTO> banmaErpResponseDTOs =objectMapper.readValue(getCategoryList,new TypeReference<BanmaErpResponseDTO<BanmaErpResponseDataDTO>>() {});
//        banmaErpResponseDTOs.getData();

        //objectMapper.configure(SerializationFeature.WRAP_ROOT_VALUE,true);
        BanmaErpResponseDTO<CategoryDTO> banmaErpResponseDTO = objectMapper.readValue(getCategoryById, new TypeReference<BanmaErpResponseDTO<CategoryDTO>>() {});
        System.out.println(banmaErpResponseDTO.getData().getID());
        BanmaErpResponseDTO<List<CategoryDTO>> banmaErpResponseDTOs =objectMapper.readValue(getCategoryList,new TypeReference<BanmaErpResponseDTO<List<CategoryDTO>>>() {});
        banmaErpResponseDTOs.getData();
    }
}
