package com.hrghs.xycb.serialiseTest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.joda.JodaModule;
import com.hrghs.xycb.domains.banmaerpDTO.StorageDTO;
import com.hrghs.xycb.domains.common.BanmaErpResponseDTO;

import java.util.List;


/**
 * 2022.11.14 jzx
 * 存储空间接口API序列化、反序列化没有问题
 */
public class BanmaerStorageTest {
    public static void main(String[] args) throws JsonProcessingException {

        // 查询文件列表
        String getStoragetList = "{\n" +
                "    \"Code\": 200,\n" +
                "    \"Time\": \"2021-12-12 15:23:34\",    \n" +
                "    \"Data\": {\n" +
                "        \"Storages\": [\n" +
                "            {\n" +
                "                \"ID\": \"f7ce3d85-2724-4ed6-8c05-a9f601212569\",\n" +
                "                \"Name\": \"61798bf2bb5a1.jpg\",\n" +
                "                \"Url\": \"https://storage.banmaerp.com/s/0/61798bf2bb5a1.jpg\",\n" +
                "                \"FileCategoryID\": \"c737c88c-a8c4-448b-9d1c-5870c424a138\",\n" +
                "                \"FileType\": \"Image\",\n" +
                "                \"Size\": \"108159\",\n" +
                "                \"CreateTime\": \"2019-02-16 17:32:37\",\n" +
                "                \"UpdateTime\": \"2020-04-03 22:31:37\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"ID\": \"f7ce3d85-2724-4ed6-8c05-a9f601234544\",\n" +
                "                \"Name\": \"8bf2bb5a1.jpg\",\n" +
                "                \"Url\": \"https://storage.banmaerp.com/s/0/8bf2bb5a1.jpg\",\n" +
                "                \"FileCategoryID\": \"c737c88c-a8c4-448b-9d1c-5870c424a138\",\n" +
                "                \"FileType\": \"Image\",\n" +
                "                \"Size\": \"108159\",\n" +
                "                \"CreateTime\": \"2019-02-16 17:32:37\",\n" +
                "                \"UpdateTime\": \"2020-04-03 22:31:37\"\n" +
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

        // 查询单个文件
        String getStorageById = "{\n" +
                "    \"Code\": 200,\n" +
                "    \"Time\": \"2021-12-12 15:23:34\",    \n" +
                "    \"Data\": {\n" +
                "        \"Storage\": {\n" +
                "            \"ID\": \"f7ce3d85-2724-4ed6-8c05-a9f601212569\",\n" +
                "            \"Name\": \"e5a3b3e570e95ab495b8cd1693ca92d8.jpg\",\n" +
                "            \"Url\": \"https://storage.banmaerp.com/s/0/cc326ef7d151416788961798bf2bb5a1.jpg\",\n" +
                "            \"FileCategoryID\": \"c737c88c-a8c4-448b-9d1c-5870c424a138\",\n" +
                "            \"FileType\": \"Image\",\n" +
                "            \"Size\": \"108159\",\n" +
                "            \"CreateTime\": \"2019-02-16 17:32:37\",\n" +
                "            \"UpdateTime\": \"2020-04-03 22:31:37\"\n" +
                "        }\n" +
                "    },\n" +
                "    \"Success\": true,\n" +
                "    \"Message\": \"成功\"\n" +
                "}\n";

        // 使用stream文件流的方式上传文件
        String uploadTheFileToStream = "{\n" +
                "    \"Code\": 200,\n" +
                "    \"Time\": \"2021-12-12 15:23:34\",    \n" +
                "    \"Data\": {\n" +
                "        \"Storage\": {\n" +
                "            \"ID\": \"f7ce3d85-2724-4ed6-8c05-a9f601212569\",\n" +
                "            \"Name\": \"e5a3b3e570e95ab495b8cd1693ca92d8.jpg\",\n" +
                "            \"Url\": \"https://storage.banmaerp.com/s/0/cc326ef7d151416788961798bf2bb5a1.jpg\",\n" +
                "            \"FileCategoryID\": \"c737c88c-a8c4-448b-9d1c-5870c424a138\",\n" +
                "            \"FileType\": \"Image\",\n" +
                "            \"Size\": \"108159\",\n" +
                "            \"CreateTime\": \"2019-02-16 17:32:37\",\n" +
                "            \"UpdateTime\": \"2020-04-03 22:31:37\"\n" +
                "        }\n" +
                "    },\n" +
                "    \"Success\": true,\n" +
                "    \"Message\": \"成功\"\n" +
                "}\n";

        // 使用base64文件流的方式上传文件
        String uploadTheFileToBase64 = "{\n" +
                "    \"Code\": 200,\n" +
                "    \"Time\": \"2021-12-12 15:23:34\",    \n" +
                "    \"Data\": {\n" +
                "        \"Storage\": {\n" +
                "            \"ID\": \"f7ce3d85-2724-4ed6-8c05-a9f601212569\",\n" +
                "            \"Name\": \"e5a3b3e570e95ab495b8cd1693ca92d8.jpg\",\n" +
                "            \"Url\": \"https://storage.banmaerp.com/s/0/cc326ef7d151416788961798bf2bb5a1.jpg\",\n" +
                "            \"FileCategoryID\": \"c737c88c-a8c4-448b-9d1c-5870c424a138\",\n" +
                "            \"FileType\": \"Image\",\n" +
                "            \"Size\": \"108159\",\n" +
                "            \"CreateTime\": \"2019-02-16 17:32:37\",\n" +
                "            \"UpdateTime\": \"2020-04-03 22:31:37\"\n" +
                "        }\n" +
                "    },\n" +
                "    \"Success\": true,\n" +
                "    \"Message\": \"成功\"\n" +
                "}\n";

        // 使用base64文件流的方式上传文件
        String uploadTheFileToForm = "{\n" +
                "    \"Code\": 200,\n" +
                "    \"Time\": \"2021-12-12 15:23:34\",    \n" +
                "    \"Data\": {\n" +
                "        \"Storage\": {\n" +
                "            \"ID\": \"f7ce3d85-2724-4ed6-8c05-a9f601212569\",\n" +
                "            \"Name\": \"e5a3b3e570e95ab495b8cd1693ca92d8.jpg\",\n" +
                "            \"Url\": \"https://storage.banmaerp.com/s/0/cc326ef7d151416788961798bf2bb5a1.jpg\",\n" +
                "            \"FileCategoryID\": \"c737c88c-a8c4-448b-9d1c-5870c424a138\",\n" +
                "            \"FileType\": \"Image\",\n" +
                "            \"Size\": \"108159\",\n" +
                "            \"CreateTime\": \"2019-02-16 17:32:37\",\n" +
                "            \"UpdateTime\": \"2020-04-03 22:31:37\"\n" +
                "        }\n" +
                "    },\n" +
                "    \"Success\": true,\n" +
                "    \"Message\": \"成功\"\n" +
                "}\n";
        ObjectMapper objectMapper = new ObjectMapper().registerModule(new JodaModule());
        BanmaErpResponseDTO<List<StorageDTO>> banmaErpResponseDTO = objectMapper.readValue(uploadTheFileToForm, new TypeReference<BanmaErpResponseDTO<List<StorageDTO>>>() {
        });
        System.out.println(banmaErpResponseDTO.getData().get(0).getId());
    }
}
