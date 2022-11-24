package com.hrghs.xycb.serialiseTest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.datatype.joda.JodaModule;
import com.hrghs.xycb.domains.banmaerpDTO.StoreDTO;
import com.hrghs.xycb.domains.common.BanmaErpResponseDTO;
import org.joda.time.format.ISODateTimeFormat;

import java.io.IOException;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static com.hrghs.xycb.domains.Constants.BANMAERP_FIELD_STORES;

/**
 * 2022.11.14 jzx
 * 店铺接口API序列化、反序列化没有问题
 */
public class BanmaerStoreTest {
    public static void main(String[] args) throws JsonProcessingException {

        // 查询店铺列表
        String getStoretList = "{\n" +
                "    \"Code\": 200,\n" +
                "    \"Time\": \"2021-12-12 15:23:34\",    \n" +
                "    \"Data\": {\n" +
                "        \"Stores\": [\n" +
                "            {\n" +
                "                \"ID\": \"400000333053000132\",\n" +
                "                \"Name\": \"test1\",\n" +
                "                \"Platform\": \"Wish\",\n" +
                "                \"CreateTime\": \"2019-01-10 16:57:58\",\n" +
                "                \"UpdateTime\": \"2020-06-13 09:59:25\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"ID\": \"400025423071000141\",\n" +
                "                \"Name\": \"test2\",\n" +
                "                \"Platform\": \"Wish\",\n" +
                "                \"CreateTime\": \"2019-03-18 14:16:04\",\n" +
                "                \"UpdateTime\": \"2020-08-19 16:58:07\"\n" +
                "            }\n" +
                "        ],\n" +
                "        \"Page\": {\n" +
                "            \"PageNumber\": 1,\n" +
                "            \"PageCount\": 1,\n" +
                "            \"PageSize\": 20,\n" +
                "            \"TotalCount\": 6,\n" +
                "            \"HasMore\": false\n" +
                "        }\n" +
                "    },\n" +
                "    \"Success\": true,\n" +
                "    \"Message\": \"成功\"\n" +
                "}\n";

        // 查询单个店铺
        String getStoreById = "{\n" +
                "    \"Code\": 200,\n" +
                "    \"Time\": \"2021-12-12 15:23:34\",    \n" +
                "    \"Data\": {\n" +
                "        \"Store\": {\n" +
                "            \"ID\": \"6377560570427\",\n" +
                "            \"Name\": \"test\",\n" +
                "            \"Platform\": \"Wish\",\n" +
                "            \"CreateTime\": \"2019-01-10 16:57:58\",\n" +
                "            \"UpdateTime\": \"2020-06-13 09:59:25\"\n" +
                "        }\n" +
                "    },\n" +
                "    \"Success\": true,\n" +
                "    \"Message\": \"成功\"\n" +
                "}\n";
        ObjectMapper objectMapper = new ObjectMapper().registerModule(new JodaModule());

        BanmaErpResponseDTO<StoreDTO> banmaErpResponseDTO = objectMapper.readValue(getStoreById, new TypeReference<BanmaErpResponseDTO<StoreDTO>>() {});
       System.out.println(banmaErpResponseDTO.getData().getName()+"\t" + banmaErpResponseDTO.getData().getID()+"\t"+banmaErpResponseDTO.getData().getPlatform());

        BanmaErpResponseDTO<JsonNode> storeListRaw =objectMapper.readValue(getStoretList, new TypeReference<BanmaErpResponseDTO<JsonNode>>() {});
        JsonNode storesNode = storeListRaw.getData();
        //java.lang.ClassCastException
//        StoreDTO[] storeDTOS = (StoreDTO[]) storeListRaw.toDataList(StoreDTO.class,BANMAERP_FIELD_STORES);
//        for (StoreDTO storeDTO :storeDTOS){
//            System.out.println(storeDTO.getName());
//        }

        Object[] objects = storeListRaw.toDataList(BANMAERP_FIELD_STORES);
        for (Object o :objects){
            System.out.println(((StoreDTO)o).getName());
        }
        List<StoreDTO> storeDTOList=
                Arrays.stream(objects).map(o -> (StoreDTO)o)
                        .collect(Collectors.toList());
        System.out.println(storeDTOList.size());
    }
}
