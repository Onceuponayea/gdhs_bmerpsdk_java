package com.hrghs.xycb.serialiseTest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.joda.JodaModule;
import com.hrghs.xycb.domains.banmaerpDTO.ProductDTO;
import com.hrghs.xycb.domains.banmaerpDTO.ProductSuppliersInfoDTO;
import com.hrghs.xycb.domains.banmaerpDTO.TokenResponseDTO;
import com.hrghs.xycb.domains.common.BanmaErpResponseDTO;
import com.hrghs.xycb.utils.converters.JodaDateTimeDeserialiser;
import com.hrghs.xycb.utils.converters.JodaDateTimeSerialiser;
import org.joda.time.DateTime;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static com.hrghs.xycb.domains.Constants.BANMAERP_FIELD_PRODUCTS;

public class BanmaerpTestCases {
    public static void main(String[] args) throws JsonProcessingException {
        String product ="{\n" +
                "    \"Code\": 200,\n" +
                "    \"Time\": \"2021-12-12 15:23:34\",\n" +
                "    \"Data\": {\n" +
                "        \"Products\": [\n" +
                "            {\n" +
                "                \"SPU\": {\n" +
                "                    \"SPUID\": \"1351482672542\",\n" +
                "                    \"Code\": \"100500203\",\n" +
                "                    \"Title\": \"男士条纹衬衫翻领短袖夏季透气2021纽扣休闲卡米萨斯街装沙滩夏威夷衬衫\",\n" +
                "                    \"LeiMuID\": \"c6ef5d23-8232-e811-8ffd-340804e01078\",\n" +
                "                    \"Image\": \"https://www.xxxx.com/images/78f44c98-411b-a5bbd-800x800.jpg\",\n" +
                "                    \"Status\": \"正常\",\n" +
                "                    \"Source\": \"平台同步\",\n" +
                "                    \"DefaultSupplierID\": \"00000000-0000-0000-0000-000000000000\",\n" +
                "                    \"Remark\": \"\",\n" +
                "                    \"IsExemptQuality\": false,\n" +
                "                    \"CreateTime\": \"2020-06-19 15:48:02\",\n" +
                "                    \"UpdateTime\": \"2021-10-16 14:32:46\"\n" +
                "                },\n" +
                "                \"Descriptions\": {\n" +
                "                    \"Html\": \"testlhtml\",\n" +
                "                    \"Text\": \"testText\",\n" +
                "                    \"Short\": \"testShort\"\n" +
                "                },\n" +
                "                \"SKUs\": [\n" +
                "                    {\n" +
                "                        \"SKUID\": \"1351482672974663680\",\n" +
                "                        \"Code\": \"14:10#Red;5:200000990\",\n" +
                "                        \"Specification\": \"款式1;4XL\",\n" +
                "                        \"CostPrice\": 77.28,\n" +
                "                        \"Image\": \"https://www.xxxx.com/images/78f44c98-411b-a5bbd-800x800.jpg\",\n" +
                "                        \"Weight\": 0,\n" +
                "                        \"Length\": 0,\n" +
                "                        \"Width\": 0,\n" +
                "                        \"Height\": 0,\n" +
                "                        \"IsValid\": true,\n" +
                "                        \"Status\": \"正常\",\n" +
                "                        \"Remark\": \"\",\n" +
                "                        \"Sort\": 0,\n" +
                "                        \"Type\": \"普通\",\n" +
                "                        \"CombineData\": [],\n" +
                "                        \"Options\": [\n" +
                "                            {\n" +
                "                                \"Name\": \"款式1\",\n" +
                "                                \"Value\": \"款式1\"\n" +
                "                            },\n" +
                "                            {\n" +
                "                                \"Name\": \"4XL\",\n" +
                "                                \"Value\": \"4XL\"\n" +
                "                            }\n" +
                "                        ]\n" +
                "                    }\n" +
                "                ],\n" +
                "                \"Options\": [\n" +
                "                    {\n" +
                "                        \"Name\": \"款式1\",\n" +
                "                        \"Sort\": 0,\n" +
                "                        \"Values\": [\n" +
                "                            \"款式1\"\n" +
                "                        ]\n" +
                "                    },\n" +
                "                    {\n" +
                "                        \"Name\": \"4XL\",\n" +
                "                        \"Sort\": 0,\n" +
                "                        \"Values\": [\n" +
                "                            \"4XL\",\n" +
                "                            \"5XL\",\n" +
                "                            \"L\",\n" +
                "                            \"M\",\n" +
                "                            \"S\",\n" +
                "                            \"XL\",\n" +
                "                            \"XXL\",\n" +
                "                            \"3XL\"\n" +
                "                        ]\n" +
                "                    }\n" +
                "                ],\n" +
                "                \"Images\": [\n" +
                "                    {\n" +
                "                        \"Src\": \"https://www.xxxx.com/images/78f44c98-411b-a5bbd-800x800.jpg\",\n" +
                "                        \"Sort\": 0\n" +
                "                    }\n" +
                "                ],\n" +
                "                \"Tags\": [\n" +
                "                    {\n" +
                "                        \"Name\": \"热卖\"\n" +
                "                    },\n" +
                "                    {\n" +
                "                        \"Name\": \"热销\"\n" +
                "                    }\n" +
                "                ],\n" +
                "                \"Requirements\": [],\n" +
                "                \"Sources\": [],\n" +
                "                \"PackMaterials\": []\n" +
                "            }\n" +
                "        ],\n" +
                "        \"Page\": {\n" +
                "            \"PageNumber\": 1,\n" +
                "            \"PageCount\": 1,\n" +
                "            \"PageSize\": 20,\n" +
                "            \"TotalCount\": 1,\n" +
                "            \"HasMore\": false\n" +
                "        }\n" +
                "    },\n" +
                "    \"Success\": true,\n" +
                "    \"Message\": \"成功\"\n" +
                "}";

        JodaModule jodaModule =new JodaModule();
        jodaModule.addDeserializer(DateTime.class, new JodaDateTimeDeserialiser());
        jodaModule.addSerializer(DateTime.class,new JodaDateTimeSerialiser());
        ObjectMapper objectMapper = new ObjectMapper().registerModule(jodaModule);

//
//        BanmaErpResponseDTO<ProductDTO> banmaErpResponseDTO = objectMapper.readValue(product,
//                new TypeReference<BanmaErpResponseDTO<ProductDTO>>() {});

        BanmaErpResponseDTO<JsonNode> projectListRaw =objectMapper.readValue(product, new TypeReference<BanmaErpResponseDTO<JsonNode>>() {});
        Object[] objects = projectListRaw.toDataList(BANMAERP_FIELD_PRODUCTS);
        List<ProductDTO> productDTOS=
                Arrays.stream(objects).map(o -> (ProductDTO)o)
                        .collect(Collectors.toList());
        System.out.println("saving product " + productDTOS.get(0).getSPU().getSPUID());


    }
}
