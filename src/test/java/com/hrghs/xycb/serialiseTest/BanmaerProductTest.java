package com.hrghs.xycb.serialiseTest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.joda.JodaModule;
import com.hrghs.xycb.domains.banmaerpDTO.*;
import com.hrghs.xycb.domains.common.BanmaErpResponseDTO;
import com.hrghs.xycb.utils.converters.JodaDateTimeDeserialiser;
import com.hrghs.xycb.utils.converters.JodaDateTimeSerialiser;
import org.joda.time.DateTime;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static com.hrghs.xycb.domains.Constants.BANMAERP_FIELD_PRODUCTS;
import static com.hrghs.xycb.domains.Constants.BANMAERP_FIELD_STORES;


/**
 * 2022.11.14 jzx
 * 产品接口API序列化、反序列化没有问题
 */
public class BanmaerProductTest {
    public static void main(String[] args) throws JsonProcessingException {
        JodaModule jodaModule =new JodaModule();
        jodaModule.addDeserializer(DateTime.class, new JodaDateTimeDeserialiser());
        jodaModule.addSerializer(DateTime.class,new JodaDateTimeSerialiser());
        ObjectMapper objectMapper = new ObjectMapper().registerModule(jodaModule);

        // 查询产品列表
        String getProductList = "{\n" +
                "    \"Code\": 200,\n" +
                "    \"Success\": true,\n" +
                "    \"Message\": null,\n" +
                "    \"Time\": \"2022-11-25T19:57:36\",\n" +
                "    \"Data\": {\n" +
                "        \"Products\": [\n" +
                "            {\n" +
                "                \"SPU\": {\n" +
                "                    \"SPUID\": \"1497121263854817280\",\n" +
                "                    \"Code\": \"22112200001\",\n" +
                "                    \"Title\": \"重磅卫衣男秋冬款连帽加绒加厚美式复古运动外套春秋潮牌宽松男装\",\n" +
                "                    \"LeiMuID\": \"32781a0f-50bd-4fd5-b15a-af5500e69a33\",\n" +
                "                    \"Image\": \"https://gw.alicdn.com/imgextra/i3/2966343102/O1CN01Z8e6HU1YmlNKvfP4k_!!2966343102.jpg_Q75.jpg_.webp\",\n" +
                "                    \"Source\": \"手工创建\",\n" +
                "                    \"DefaultSupplierID\": \"b4643438-15b5-4fee-9071-af5600fdece6\",\n" +
                "                    \"Remark\": \"\",\n" +
                "                    \"IsExemptQuality\": true,\n" +
                "                    \"Keywords\": \"爆款\",\n" +
                "                    \"CreateTime\": \"2022-11-22T14:25:24\",\n" +
                "                    \"UpdateTime\": \"2022-11-23T15:26:46\"\n" +
                "                },\n" +
                "                \"Descriptions\": null,\n" +
                "                \"SKUs\": [\n" +
                "                    {\n" +
                "                        \"SKUID\": \"1497490072574889984\",\n" +
                "                        \"Code\": \"22112200001-White-One-Size\",\n" +
                "                        \"Specification\": \"White;One Size\",\n" +
                "                        \"CostPrice\": 112,\n" +
                "                        \"Image\": \"https://gw.alicdn.com/imgextra/i3/2966343102/O1CN01Z8e6HU1YmlNKvfP4k_!!2966343102.jpg_Q75.jpg_.webp\",\n" +
                "                        \"Weight\": 60,\n" +
                "                        \"Length\": 60,\n" +
                "                        \"Width\": 60,\n" +
                "                        \"Height\": 60,\n" +
                "                        \"IsValid\": true,\n" +
                "                        \"Remark\": \"\",\n" +
                "                        \"Sort\": 0,\n" +
                "                        \"Type\": \"普通\",\n" +
                "                        \"CombineData\": [],\n" +
                "                        \"Options\": [\n" +
                "                            {\n" +
                "                                \"Name\": \"Color\",\n" +
                "                                \"Value\": \"White\"\n" +
                "                            },\n" +
                "                            {\n" +
                "                                \"Name\": \"Size\",\n" +
                "                                \"Value\": \"One Size\"\n" +
                "                            }\n" +
                "                        ]\n" +
                "                    }\n" +
                "                ],\n" +
                "                \"Suppliers\": [],\n" +
                "                \"Options\": [],\n" +
                "                \"Requirements\": [],\n" +
                "                \"Sources\": [],\n" +
                "                \"Images\": [],\n" +
                "                \"Tags\": [],\n" +
                "                \"PackMaterials\": []\n" +
                "            },\n" +
                "            {\n" +
                "                \"SPU\": {\n" +
                "                    \"SPUID\": \"1497488584330973184\",\n" +
                "                    \"Code\": \"N-22112300001\",\n" +
                "                    \"Title\": \"踏进银河 日系软糯毛茸茸外套小个子甜美加厚保暖羊羔绒棉服秋冬\",\n" +
                "                    \"LeiMuID\": \"43a0acba-367a-4f00-819d-af5600f0caa3\",\n" +
                "                    \"Image\": \"https://gd4.alicdn.com/imgextra/i1/2601139430/O1CN01xn0Jro2JWzgjULs67_!!2601139430.jpg_400x400.jpg\",\n" +
                "                    \"Source\": \"手工创建\",\n" +
                "                    \"DefaultSupplierID\": \"efd8c68d-50c2-4d6f-a04c-af5500f805b2\",\n" +
                "                    \"Remark\": \"\",\n" +
                "                    \"IsExemptQuality\": true,\n" +
                "                    \"Keywords\": \"爆款\",\n" +
                "                    \"CreateTime\": \"2022-11-23T14:45:00\",\n" +
                "                    \"UpdateTime\": \"2022-11-23T14:45:28\"\n" +
                "                },\n" +
                "                \"Descriptions\": null,\n" +
                "                \"SKUs\": [\n" +
                "                    {\n" +
                "                        \"SKUID\": \"1497488584339361793\",\n" +
                "                        \"Code\": \"N-22112300001-White-One-Size\",\n" +
                "                        \"Specification\": \"White;One Size\",\n" +
                "                        \"CostPrice\": 111,\n" +
                "                        \"Image\": \"https://gd4.alicdn.com/imgextra/i1/2601139430/O1CN01xn0Jro2JWzgjULs67_!!2601139430.jpg_400x400.jpg\",\n" +
                "                        \"Weight\": 50,\n" +
                "                        \"Length\": 11,\n" +
                "                        \"Width\": 11,\n" +
                "                        \"Height\": 11,\n" +
                "                        \"IsValid\": true,\n" +
                "                        \"Remark\": \"\",\n" +
                "                        \"Sort\": 0,\n" +
                "                        \"Type\": \"普通\",\n" +
                "                        \"CombineData\": [],\n" +
                "                        \"Options\": [\n" +
                "                            {\n" +
                "                                \"Name\": \"Color\",\n" +
                "                                \"Value\": \"White\"\n" +
                "                            },\n" +
                "                            {\n" +
                "                                \"Name\": \"Size\",\n" +
                "                                \"Value\": \"One Size\"\n" +
                "                            }\n" +
                "                        ]\n" +
                "                    }\n" +
                "                ],\n" +
                "                \"Suppliers\": [],\n" +
                "                \"Options\": [],\n" +
                "                \"Requirements\": [],\n" +
                "                \"Sources\": [],\n" +
                "                \"Images\": [],\n" +
                "                \"Tags\": [],\n" +
                "                \"PackMaterials\": []\n" +
                "            }\n" +
                "        ],\n" +
                "        \"Page\": {\n" +
                "            \"PageNumber\": 1,\n" +
                "            \"PageCount\": 1,\n" +
                "            \"PageSize\": 100,\n" +
                "            \"TotalCount\": 2,\n" +
                "            \"HasMore\": false\n" +
                "        }\n" +
                "    },\n" +
                "    \"RequestId\": \"8000cf85-2002-6900-b63f-84710c7967bb\"\n" +
                "}";

        // 查询单个产品
        String getProductById = "{\n" +
                "    \"Code\": 200,\n" +
                "    \"Time\": \"2021-12-12 15:23:34\",    \n" +
                "    \"Data\": {\n" +
                "        \"Product\": {\n" +
                "            \"SPU\": {\n" +
                "                \"SPUID\": \"1351482672542\",\n" +
                "                \"Code\": \"100500203\",\n" +
                "                \"Title\": \"男士条纹衬衫翻领短袖夏季透气2021纽扣休闲卡米萨斯街装沙滩夏威夷衬衫\",\n" +
                "                \"LeiMuID\": \"c6ef5d23-8232-e811-8ffd-340804e01078\",\n" +
                "                \"Image\": \"https://www.xxxx.com/images/78f44c98-411b-a5bbd-800x800.jpg\",\n" +
                "                \"Status\": \"正常\",\n" +
                "                \"Source\": \"平台同步\",\n" +
                "                \"DefaultSupplierID\": \"00000000-0000-0000-0000-000000000000\",\n" +
                "                \"Remark\": \"\",\n" +
                "                \"IsExemptQuality\": false,\n" +
                "                \"CreateTime\": \"2020-06-19 15:48:02\",\n" +
                "                \"UpdateTime\": \"2021-10-16 14:32:46\"\n" +
                "            },\n" +
                "            \"Descriptions\": {\n" +
                "                \"Html\": \"testlhtml\",\n" +
                "                \"Text\": \"testText\",\n" +
                "                \"Short\": \"testShort\"\n" +
                "            },\n" +
                "            \"SKUs\": [\n" +
                "                {\n" +
                "                    \"SKUID\": \"1351482672974663680\",\n" +
                "                    \"Code\": \"14:10#Red;5:200000990\",\n" +
                "                    \"Specification\": \"款式1;4XL\",\n" +
                "                    \"CostPrice\": 77.28,\n" +
                "                    \"Image\": \"https://www.xxxx.com/images/78f44c98-411b-a5bbd-800x800.jpg\",\n" +
                "                    \"Weight\": 0,\n" +
                "                    \"Length\": 0,\n" +
                "                    \"Width\": 0,\n" +
                "                    \"Height\": 0,\n" +
                "                    \"IsValid\": true,\n" +
                "                    \"Status\": \"正常\",\n" +
                "                    \"Remark\": \"\",\n" +
                "                    \"Sort\": 0,\n" +
                "                    \"Type\": \"普通\",\n" +
                "                    \"CombineData\": [],\n" +
                "                    \"Options\": [\n" +
                "                        {\n" +
                "                            \"Name\": \"款式1\",\n" +
                "                            \"Value\": \"款式1\"\n" +
                "                        },\n" +
                "                        {\n" +
                "                            \"Name\": \"4XL\",\n" +
                "                            \"Value\": \"4XL\"\n" +
                "                        }\n" +
                "                    ]\n" +
                "                }\n" +
                "            ],\n" +
                "            \"Suppliers\": [],\n" +
                "            \"Options\": [\n" +
                "                {\n" +
                "                    \"Name\": \"款式1\",\n" +
                "                    \"Sort\": 0,\n" +
                "                    \"Values\": [\n" +
                "                        \"款式1\"\n" +
                "                    ]\n" +
                "                },\n" +
                "                {\n" +
                "                    \"Name\": \"4XL\",\n" +
                "                    \"Sort\": 0,\n" +
                "                    \"Values\": [\n" +
                "                        \"4XL\",\n" +
                "                        \"5XL\",\n" +
                "                        \"L\",\n" +
                "                        \"M\",\n" +
                "                        \"S\",\n" +
                "                        \"XL\",\n" +
                "                        \"XXL\",\n" +
                "                        \"3XL\"\n" +
                "                    ]\n" +
                "                }\n" +
                "            ],\n" +
                "            \"Requirements\": [],\n" +
                "            \"Sources\": [],\n" +
                "            \"Images\": [\n" +
                "                {\n" +
                "                    \"Src\": \"https://www.xxxx.com/images/78f44c98-411b-a5bbd-800x800.jpg\",\n" +
                "                    \"Sort\": 0\n" +
                "                }\n" +
                "            ],\n" +
                "            \"Tags\": [],\n" +
                "            \"PackMaterials\": []\n" +
                "        }\n" +
                "    },\n" +
                "    \"Success\": true,\n" +
                "    \"Message\": \"成功\"\n" +
                "}\n";

        // 添加商品请求
        String insertProductReq = "{\n" +
                "    \"SPU\": {\n" +
                "        \"Code\": \"100500203449635822222\",\n" +
                "        \"Title\": \"男士条纹衬衫翻领短袖夏季透气2021纽扣休闲卡米萨斯街装沙滩夏威夷衬衫11111\",\n" +
                "        \"LeiMuID\": \"c6ef5d23-8232-e811-8ffd-340804e01078\",\n" +
                "        \"SourceUrl\": \"\",\n" +
                "        \"TagIDs\": \"\",\n" +
                "        \"Image\": \"https://s3.forcloudcdn.com/item/images/dmc/78f44c98-411b-4e96-bd91-800.jpg\",\n" +
                "        \"Source\": \"平台同步\",\n" +
                "        \"Remark\": \"\",\n" +
                "        \"IsExemptQuality\": false\n" +
                "    },\n" +
                "    \"SKUs\": [\n" +
                "        {\n" +
                "            \"Code\": \"14:10#Red;5:2000009911111\",\n" +
                "            \"Specification\": \"款式1;4XL\",\n" +
                "            \"CostPrice\": 77.28,\n" +
                "            \"Image\": \"https://s3.forcloudcdn.com/item/images/dmc/365115d6-1c32-4cb5-9a82-640.jpg\",\n" +
                "            \"Weight\": 0,\n" +
                "            \"Length\": 0,\n" +
                "            \"Width\": 0,\n" +
                "            \"Height\": 0,\n" +
                "            \"IsValid\": true,\n" +
                "            \"Status\": \"正常\",\n" +
                "            \"Remark\": \"\",\n" +
                "            \"Sort\": 0,\n" +
                "            \"Type\": \"普通\",\n" +
                "            \"CombineData\": [],\n" +
                "            \"Options\": [\n" +
                "                {\n" +
                "                    \"Name\": \"款式1\",\n" +
                "                    \"Value\": \"款式1\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"Name\": \"4XL\",\n" +
                "                    \"Value\": \"4XL\"\n" +
                "                }\n" +
                "            ]\n" +
                "        },\n" +
                "        {\n" +
                "            \"Code\": \"14:10#Red;5:2000009922221\",\n" +
                "            \"Specification\": \"款式1;5XL\",\n" +
                "            \"CostPrice\": 77.28,\n" +
                "            \"Image\": \"https://s3.forcloudcdn.com/item/images/dmc/365115d6-1c32-4cb5-9a82-af9640.jpg\",\n" +
                "            \"Weight\": 0,\n" +
                "            \"Length\": 0,\n" +
                "            \"Width\": 0,\n" +
                "            \"Height\": 0,\n" +
                "            \"IsValid\": true,\n" +
                "            \"Status\": \"正常\",\n" +
                "            \"Remark\": \"\",\n" +
                "            \"Sort\": 1,\n" +
                "            \"Type\": \"普通\",\n" +
                "            \"CombineData\": [],\n" +
                "            \"Options\": [\n" +
                "                {\n" +
                "                    \"Name\": \"款式1\",\n" +
                "                    \"Value\": \"款式1\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"Name\": \"4XL\",\n" +
                "                    \"Value\": \"5XL\"\n" +
                "                }\n" +
                "            ]\n" +
                "        }\n" +
                "    ],\n" +
                "    \"Options\": [\n" +
                "        {\n" +
                "            \"Name\": \"款式1\",\n" +
                "            \"Sort\": 0,\n" +
                "            \"Values\": [\n" +
                "                \"款式1\"\n" +
                "            ]\n" +
                "        },\n" +
                "        {\n" +
                "            \"Name\": \"4XL\",\n" +
                "            \"Sort\": 0,\n" +
                "            \"Values\": [\n" +
                "                \"4XL\",\n" +
                "                \"5XL\"\n" +
                "            ]\n" +
                "        }\n" +
                "    ],\n" +
                "    \"Images\": [\n" +
                "        {\n" +
                "            \"Src\": \"https://s3.forcloudcdn.com/item/images/dmc/78f44c98-411b-4e96-bd91-800.jpg\",\n" +
                "            \"Sort\": 0\n" +
                "        },\n" +
                "        {\n" +
                "            \"Src\": \"https://s3.forcloudcdn.com/item/images/dmc/ff600237-574e-4a93-a091-800.jpg\",\n" +
                "            \"Sort\": 1\n" +
                "        }\n" +
                "    ],\n" +
                "    \"Tags\": [\n" +
                "        {\n" +
                "            \"Name\":\"热卖\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"Name\":\"热销\"\n" +
                "        }\n" +
                "    ],\n" +
                "    \"Suppliers\": [\n" +
                "        {\n" +
                "            \"ID\":\"48F8184E-6A2B-443F-8FB4-EB777F7618D9\",\n" +
                "            \"Remark\":\"test\",\n" +
                "            \"Sort\":1\n" +
                "        },\n" +
                "        {\n" +
                "            \"ID\":\"B1A86AA7-665C-4196-8509-CFEDF366AA82\",\n" +
                "            \"Remark\":\"test1\",\n" +
                "            \"Sort\":2\n" +
                "        }\n" +
                "    ]\n" +
                "}\n";
//        ProductDTO productDTO = objectMapper.readValue(insertProductReq, new TypeReference<ProductDTO>() {
//        });
//        System.out.println(productDTO.getSPU().getCode());

        // 添加商品响应
        String insertProductResp = "{\n" +
                "    \"Code\": 200,\n" +
                "    \"Time\": \"2021-12-12 15:23:34\",    \n" +
                "    \"Data\": {\n" +
                "        \"Product\": {\n" +
                "            \"SPU\": {\n" +
                "                \"SPUID\": \"1351482672542\",\n" +
                "                \"Code\": \"100500203449635822222\",\n" +
                "                \"Title\": \"男士条纹衬衫翻领短袖夏季透气2021纽扣休闲卡米萨斯街装沙滩夏威夷衬衫11111\",\n" +
                "                \"LeiMuID\": \"c6ef5d23-8232-e811-8ffd-340804e01078\",\n" +
                "                \"SourceUrl\": \"\",\n" +
                "                \"TagIDs\": \"\",\n" +
                "                \"Image\": \"https://s3.forcloudcdn.com/item/images/dmc/78f44c98-411b-4e96-bd91-800.jpg\",\n" +
                "                \"Source\": \"平台同步\",\n" +
                "                \"Remark\": \"\",\n" +
                "                \"IsExemptQuality\": false,\n" +
                "                \"CreateTime\": \"2020-06-19 15:48:02\",\n" +
                "                \"UpdateTime\": \"2021-10-16 14:32:46\"\n" +
                "            },\n" +
                "            \"Descriptions\": {\n" +
                "                \"Html\": \"testlhtml\",\n" +
                "                \"Text\": \"testText\",\n" +
                "                \"Short\": \"testShort\"\n" +
                "            },\n" +
                "            \"SKUs\": [\n" +
                "                {\n" +
                "                    \"SKUID\": \"1351482672974663680\",\n" +
                "                    \"Code\": \"14:10#Red;5:200000990\",\n" +
                "                    \"Specification\": \"款式1;4XL\",\n" +
                "                    \"CostPrice\": 77.28,\n" +
                "                    \"Image\": \"https://www.xxxx.com/images/78f44c98-411b-a5bbd-800x800.jpg\",\n" +
                "                    \"Weight\": 0,\n" +
                "                    \"Length\": 0,\n" +
                "                    \"Width\": 0,\n" +
                "                    \"Height\": 0,\n" +
                "                    \"IsValid\": true,\n" +
                "                    \"Status\": \"正常\",\n" +
                "                    \"Remark\": \"\",\n" +
                "                    \"Sort\": 0,\n" +
                "                    \"Type\": \"普通\",\n" +
                "                    \"CombineData\": [],\n" +
                "                    \"Options\": [\n" +
                "                        {\n" +
                "                            \"Name\": \"款式1\",\n" +
                "                            \"Value\": \"款式1\"\n" +
                "                        },\n" +
                "                        {\n" +
                "                            \"Name\": \"4XL\",\n" +
                "                            \"Value\": \"4XL\"\n" +
                "                        }\n" +
                "                    ]\n" +
                "                },\n" +
                "                {\n" +
                "                    \"SKUID\": \"135148267297466546\",\n" +
                "                    \"Code\": \"14:10#Red;5:2000009922221\",\n" +
                "                    \"Specification\": \"款式1;5XL\",\n" +
                "                    \"CostPrice\": 77.28,\n" +
                "                    \"Image\": \"https://s3.forcloudcdn.com/item/images/dmc/365115d6-1c32-4cb5-9a82-af9640.jpg\",\n" +
                "                    \"Weight\": 0,\n" +
                "                    \"Length\": 0,\n" +
                "                    \"Width\": 0,\n" +
                "                    \"Height\": 0,\n" +
                "                    \"IsValid\": true,\n" +
                "                    \"Status\": \"正常\",\n" +
                "                    \"Remark\": \"\",\n" +
                "                    \"Sort\": 1,\n" +
                "                    \"Type\": \"普通\",\n" +
                "                    \"CombineData\": [],\n" +
                "                    \"Options\": [\n" +
                "                        {\n" +
                "                            \"Name\": \"款式1\",\n" +
                "                            \"Value\": \"款式1\"\n" +
                "                        },\n" +
                "                        {\n" +
                "                            \"Name\": \"4XL\",\n" +
                "                            \"Value\": \"5XL\"\n" +
                "                        }\n" +
                "                    ]\n" +
                "                }\n" +
                "            ],\n" +
                "            \"Suppliers\": [],\n" +
                "            \"Options\": [\n" +
                "                {\n" +
                "                    \"Name\": \"款式1\",\n" +
                "                    \"Sort\": 0,\n" +
                "                    \"Values\": [\n" +
                "                        \"款式1\"\n" +
                "                    ]\n" +
                "                },\n" +
                "                {\n" +
                "                    \"Name\": \"4XL\",\n" +
                "                    \"Sort\": 0,\n" +
                "                    \"Values\": [\n" +
                "                        \"4XL\",\n" +
                "                        \"5XL\",\n" +
                "                        \"L\",\n" +
                "                        \"M\",\n" +
                "                        \"S\",\n" +
                "                        \"XL\",\n" +
                "                        \"XXL\",\n" +
                "                        \"3XL\"\n" +
                "                    ]\n" +
                "                }\n" +
                "            ],\n" +
                "            \"Requirements\": [],\n" +
                "            \"Sources\": [],\n" +
                "            \"Images\": [\n" +
                "                {\n" +
                "                    \"Src\": \"https://www.xxxx.com/images/78f44c98-411b-a5bbd-800x800.jpg\",\n" +
                "                    \"Sort\": 0\n" +
                "                }\n" +
                "            ],\n" +
                "            \"Tags\": [],\n" +
                "            \"PackMaterials\": []\n" +
                "        }\n" +
                "    },\n" +
                "    \"Success\": true,\n" +
                "    \"Message\": \"成功\"\n" +
                "}\n";

        // 更新产品请求
        String updateProductReq = "{\n" +
                "    \"SPU\": {\n" +
                "        \"SPUID\": \"1351482672542\",\n" +
                "        \"Code\": \"100500203449635822222\",\n" +
                "        \"Title\": \"男士条纹衬衫翻领短袖夏季透气2021纽扣休闲卡米萨斯街装沙滩夏威夷衬衫11111\",\n" +
                "        \"LeiMuID\": \"c6ef5d23-8232-e811-8ffd-340804e01078\",\n" +
                "        \"SourceUrl\": \"\",\n" +
                "        \"TagIDs\": \"\",\n" +
                "        \"Image\": \"https://s3.forcloudcdn.com/item/images/dmc/78f44c98-411b-4e96-bd91-800.jpg\",\n" +
                "        \"Source\": \"平台同步\",\n" +
                "        \"Remark\": \"\",\n" +
                "        \"IsExemptQuality\": false\n" +
                "    },\n" +
                "    \"SKUs\": [\n" +
                "        {\n" +
                "            \"SKUID\": \"1351482672974663680\",\n" +
                "            \"Code\": \"14:10#Red;5:2000009911111\",\n" +
                "            \"Specification\": \"款式1;4XL\",\n" +
                "            \"CostPrice\": 77.28,\n" +
                "            \"Image\": \"https://s3.forcloudcdn.com/item/images/dmc/365115d6-1c32-4cb5-9a82-640.jpg\",\n" +
                "            \"Weight\": 0,\n" +
                "            \"Length\": 0,\n" +
                "            \"Width\": 0,\n" +
                "            \"Height\": 0,\n" +
                "            \"IsValid\": true,\n" +
                "            \"Status\": \"正常\",\n" +
                "            \"Remark\": \"\",\n" +
                "            \"Sort\": 0,\n" +
                "            \"Type\": \"普通\",\n" +
                "            \"CombineData\": [],\n" +
                "            \"Options\": [\n" +
                "                {\n" +
                "                    \"Name\": \"款式1\",\n" +
                "                    \"Value\": \"款式1\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"Name\": \"4XL\",\n" +
                "                    \"Value\": \"4XL\"\n" +
                "                }\n" +
                "            ]\n" +
                "        },\n" +
                "        {\n" +
                "            \"SKUID\": \"135148267297466546\",\n" +
                "            \"Code\": \"14:10#Red;5:2000009922221\",\n" +
                "            \"Specification\": \"款式1;5XL\",\n" +
                "            \"CostPrice\": 77.28,\n" +
                "            \"Image\": \"https://s3.forcloudcdn.com/item/images/dmc/365115d6-1c32-4cb5-9a82-af9640.jpg\",\n" +
                "            \"Weight\": 0,\n" +
                "            \"Length\": 0,\n" +
                "            \"Width\": 0,\n" +
                "            \"Height\": 0,\n" +
                "            \"IsValid\": true,\n" +
                "            \"Status\": \"正常\",\n" +
                "            \"Remark\": \"\",\n" +
                "            \"Sort\": 1,\n" +
                "            \"Type\": \"普通\",\n" +
                "            \"CombineData\": [],\n" +
                "            \"Options\": [\n" +
                "                {\n" +
                "                    \"Name\": \"款式1\",\n" +
                "                    \"Value\": \"款式1\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"Name\": \"4XL\",\n" +
                "                    \"Value\": \"5XL\"\n" +
                "                }\n" +
                "            ]\n" +
                "        }\n" +
                "    ],\n" +
                "    \"Options\": [\n" +
                "        {\n" +
                "            \"Name\": \"款式1\",\n" +
                "            \"Sort\": 0,\n" +
                "            \"Values\": [\n" +
                "                \"款式1\"\n" +
                "            ]\n" +
                "        },\n" +
                "        {\n" +
                "            \"Name\": \"4XL\",\n" +
                "            \"Sort\": 0,\n" +
                "            \"Values\": [\n" +
                "                \"4XL\",\n" +
                "                \"5XL\"\n" +
                "            ]\n" +
                "        }\n" +
                "    ],\n" +
                "    \"Images\": [\n" +
                "        {\n" +
                "            \"Src\": \"https://s3.forcloudcdn.com/item/images/dmc/78f44c98-411b-4e96-bd91-800.jpg\",\n" +
                "            \"Sort\": 0\n" +
                "        },\n" +
                "        {\n" +
                "            \"Src\": \"https://s3.forcloudcdn.com/item/images/dmc/ff600237-574e-4a93-a091-800.jpg\",\n" +
                "            \"Sort\": 1\n" +
                "        }\n" +
                "    ],\n" +
                "    \"Tags\": [\n" +
                "        {\n" +
                "            \"Name\":\"热卖\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"Name\":\"热销\"\n" +
                "        }\n" +
                "    ],\n" +
                "    \"Suppliers\": [\n" +
                "        {\n" +
                "            \"ID\":\"48F8184E-6A2B-443F-8FB4-EB777F7618D9\",\n" +
                "            \"Remark\":\"test\",\n" +
                "            \"Sort\":1\n" +
                "        },\n" +
                "        {\n" +
                "            \"ID\":\"B1A86AA7-665C-4196-8509-CFEDF366AA82\",\n" +
                "            \"Remark\":\"test1\",\n" +
                "            \"Sort\":2\n" +
                "        }\n" +
                "    ]\n" +
                "}\n";
//        ProductDTO productDTO1 = objectMapper.readValue(updateProductReq, new TypeReference<ProductDTO>() {
//        });
//        System.out.println(productDTO1.getSPU().getCode());
        // 更新产品响应
        String updateProductResp = "{\n" +
                "    \"Code\": 200,\n" +
                "    \"Time\": \"2021-12-12 15:23:34\",    \n" +
                "    \"Data\": {\n" +
                "        \"Product\": {\n" +
                "            \"SPU\": {\n" +
                "                \"SPUID\": \"1351482672542\",\n" +
                "                \"Code\": \"100500203449635822222\",\n" +
                "                \"Title\": \"男士条纹衬衫翻领短袖夏季透气2021纽扣休闲卡米萨斯街装沙滩夏威夷衬衫11111\",\n" +
                "                \"LeiMuID\": \"c6ef5d23-8232-e811-8ffd-340804e01078\",\n" +
                "                \"SourceUrl\": \"\",\n" +
                "                \"TagIDs\": \"\",\n" +
                "                \"Image\": \"https://s3.forcloudcdn.com/item/images/dmc/78f44c98-411b-4e96-bd91-800.jpg\",\n" +
                "                \"Source\": \"平台同步\",\n" +
                "                \"Remark\": \"\",\n" +
                "                \"IsExemptQuality\": \"false\",\n" +
                "                \"CreateTime\": \"2020-06-19 15:48:02\",\n" +
                "                \"UpdateTime\": \"2021-10-16 14:32:46\"\n" +
                "            },\n" +
                "            \"Descriptions\": {\n" +
                "                \"Html\": \"testlhtml\",\n" +
                "                \"Text\": \"testText\",\n" +
                "                \"Short\": \"testShort\"\n" +
                "            },\n" +
                "            \"SKUs\": [\n" +
                "                {\n" +
                "                    \"SKUID\": \"1351482672974663680\",\n" +
                "                    \"Code\": \"14:10#Red;5:200000990\",\n" +
                "                    \"Specification\": \"款式1;4XL\",\n" +
                "                    \"CostPrice\": 77.28,\n" +
                "                    \"Image\": \"https://www.xxxx.com/images/78f44c98-411b-a5bbd-800x800.jpg\",\n" +
                "                    \"Weight\": 0,\n" +
                "                    \"Length\": 0,\n" +
                "                    \"Width\": 0,\n" +
                "                    \"Height\": 0,\n" +
                "                    \"IsValid\": true,\n" +
                "                    \"Status\": \"正常\",\n" +
                "                    \"Remark\": \"\",\n" +
                "                    \"Sort\": 0,\n" +
                "                    \"Type\": \"普通\",\n" +
                "                    \"CombineData\": [],\n" +
                "                    \"Options\": [\n" +
                "                        {\n" +
                "                            \"Name\": \"款式1\",\n" +
                "                            \"Value\": \"款式1\"\n" +
                "                        },\n" +
                "                        {\n" +
                "                            \"Name\": \"4XL\",\n" +
                "                            \"Value\": \"4XL\"\n" +
                "                        }\n" +
                "                    ]\n" +
                "                },\n" +
                "                {\n" +
                "                    \"SKUID\": \"135148267297466546\",\n" +
                "                    \"Code\": \"14:10#Red;5:2000009922221\",\n" +
                "                    \"Specification\": \"款式1;5XL\",\n" +
                "                    \"CostPrice\": 77.28,\n" +
                "                    \"Image\": \"https://s3.forcloudcdn.com/item/images/dmc/365115d6-1c32-4cb5-9a82-af9640.jpg\",\n" +
                "                    \"Weight\": 0,\n" +
                "                    \"Length\": 0,\n" +
                "                    \"Width\": 0,\n" +
                "                    \"Height\": 0,\n" +
                "                    \"IsValid\": true,\n" +
                "                    \"Status\": \"正常\",\n" +
                "                    \"Remark\": \"\",\n" +
                "                    \"Sort\": 1,\n" +
                "                    \"Type\": \"普通\",\n" +
                "                    \"CombineData\": [],\n" +
                "                    \"Options\": [\n" +
                "                        {\n" +
                "                            \"Name\": \"款式1\",\n" +
                "                            \"Value\": \"款式1\"\n" +
                "                        },\n" +
                "                        {\n" +
                "                            \"Name\": \"4XL\",\n" +
                "                            \"Value\": \"5XL\"\n" +
                "                        }\n" +
                "                    ]\n" +
                "                }\n" +
                "            ],\n" +
                "            \"Suppliers\": [],\n" +
                "            \"Options\": [\n" +
                "                {\n" +
                "                    \"Name\": \"款式1\",\n" +
                "                    \"Sort\": 0,\n" +
                "                    \"Values\": [\n" +
                "                        \"款式1\"\n" +
                "                    ]\n" +
                "                },\n" +
                "                {\n" +
                "                    \"Name\": \"4XL\",\n" +
                "                    \"Sort\": 0,\n" +
                "                    \"Values\": [\n" +
                "                        \"4XL\",\n" +
                "                        \"5XL\",\n" +
                "                        \"L\",\n" +
                "                        \"M\",\n" +
                "                        \"S\",\n" +
                "                        \"XL\",\n" +
                "                        \"XXL\",\n" +
                "                        \"3XL\"\n" +
                "                    ]\n" +
                "                }\n" +
                "            ],\n" +
                "            \"Requirements\": [],\n" +
                "            \"Sources\": [],\n" +
                "            \"Images\": [\n" +
                "                {\n" +
                "                    \"Src\": \"https://www.xxxx.com/images/78f44c98-411b-a5bbd-800x800.jpg\",\n" +
                "                    \"Sort\": 0\n" +
                "                }\n" +
                "            ],\n" +
                "            \"Tags\": [],\n" +
                "            \"PackMaterials\": []\n" +
                "        }\n" +
                "    },\n" +
                "    \"Success\": true,\n" +
                "    \"Message\": \"成功\"\n" +
                "}\n";

        // 获取SKU列表
        String getProductSkuList = "{\n" +
                "    \"Code\": 200,\n" +
                "    \"Time\": \"2021-12-12 15:23:34\",    \n" +
                "    \"Data\": {\n" +
                "        \"SKUs\": [\n" +
                "            {\n" +
                "                \"SKUID\": \"1343126950939463692\",\n" +
                "                \"Code\": \"only-Always-Forever\",\n" +
                "                \"Specification\": \"Blade only;Always Forever\",\n" +
                "                \"CostPrice\": 162.93,\n" +
                "                \"Image\": \"https://www.xxxx.com/tgg6.jpg\",\n" +
                "                \"Weight\": 0,\n" +
                "                \"Length\": 0,\n" +
                "                \"Width\": 0,\n" +
                "                \"Height\": 0,\n" +
                "                \"IsValid\": true,\n" +
                "                \"Remark\": \"\",\n" +
                "                \"Sort\": 12,\n" +
                "                \"Type\": \"普通\",\n" +
                "                \"CombineData\": [],\n" +
                "                \"Options\": [\n" +
                "                    {\n" +
                "                        \"Name\": \"Engraving Options\",\n" +
                "                        \"Value\": \"Blade only\"\n" +
                "                    },\n" +
                "                    {\n" +
                "                        \"Name\": \"Fonts\",\n" +
                "                        \"Value\": \"Always Forever\"\n" +
                "                    }\n" +
                "                ]\n" +
                "            },\n" +
                "            {\n" +
                "                \"SKUID\": \"1343126950939463688\",\n" +
                "                \"Code\": \"Blade-only-Arial\",\n" +
                "                \"Specification\": \"Blade only;Arial\",\n" +
                "                \"CostPrice\": 162.93,\n" +
                "                \"Image\": \"https://www.xxx.com/tgg5.jpg\",\n" +
                "                \"Weight\": 0,\n" +
                "                \"Length\": 0,\n" +
                "                \"Width\": 0,\n" +
                "                \"Height\": 0,\n" +
                "                \"IsValid\": true,\n" +
                "                \"Remark\": \"\",\n" +
                "                \"Sort\": 8,\n" +
                "                \"Type\": \"普通\",\n" +
                "                \"CombineData\": [],\n" +
                "                \"Options\": [\n" +
                "                    {\n" +
                "                        \"Name\": \"Engraving Options\",\n" +
                "                        \"Value\": \"Blade only\"\n" +
                "                    },\n" +
                "                    {\n" +
                "                        \"Name\": \"Fonts\",\n" +
                "                        \"Value\": \"Arial\"\n" +
                "                    }\n" +
                "                ]\n" +
                "            }\n" +
                "        ],\n" +
                "        \"Page\": {\n" +
                "            \"PageNumber\": 1,\n" +
                "            \"PageCount\": 2,\n" +
                "            \"PageSize\": 20,\n" +
                "            \"TotalCount\": 21,\n" +
                "            \"HasMore\": true\n" +
                "        }\n" +
                "    },\n" +
                "    \"Success\": true,\n" +
                "    \"Message\": \"成功\"\n" +
                "}\n";

        // 查询单个SKU
        String getProductSkuById = "{\n" +
                "    \"Code\": 200,\n" +
                "    \"Time\": \"2021-12-12 15:23:34\",    \n" +
                "    \"Data\": {\n" +
                "        \"SKU\": {\n" +
                "            \"SKUID\": \"135148243953\",\n" +
                "            \"Code\": \"shzhu037\",\n" +
                "            \"Specification\": \"黄色;S\",\n" +
                "            \"CostPrice\": 81.79,\n" +
                "            \"Image\": \"https://s3.forcloudcdn.com/merchant/upload/35b759eed00f5b.jpg\",\n" +
                "            \"Weight\": 100,\n" +
                "            \"Length\": 5,\n" +
                "            \"Width\": 5,\n" +
                "            \"Height\": 1,\n" +
                "            \"IsValid\": true,\n" +
                "            \"Remark\": \"\",\n" +
                "            \"Sort\": 1,\n" +
                "            \"Type\": \"普通\",\n" +
                "            \"CombineData\": [],\n" +
                "            \"Options\": [\n" +
                "                {\n" +
                "                    \"Name\": \"黄色\",\n" +
                "                    \"Value\": \"黄色\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"Name\": \"M\",\n" +
                "                    \"Value\": \"S\"\n" +
                "                }\n" +
                "            ]\n" +
                "        }\n" +
                "    },\n" +
                "    \"Success\": true,\n" +
                "    \"Message\": \"成功\"\n" +
                "}\n";

        // 查询Tag列表
        String getProductTagList = "{\n" +
                "    \"Code\": 200,\n" +
                "    \"Time\": \"2021-12-12 15:23:34\",    \n" +
                "    \"Data\": {\n" +
                "        \"Tags\": [\n" +
                "            {\n" +
                "                \"Name\": \"缺货\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"Name\": \"断货\"\n" +
                "            }\n" +
                "        ],\n" +
                "        \"Page\": {\n" +
                "            \"PageNumber\": 1,\n" +
                "            \"PageCount\": 2,\n" +
                "            \"PageSize\": 20,\n" +
                "            \"TotalCount\": 21,\n" +
                "            \"HasMore\": true\n" +
                "        }\n" +
                "    },\n" +
                "    \"Success\": true,\n" +
                "    \"Message\": \"成功\"\n" +
                "}\n";

        // 查询供应商列表
        String getProductSuppliersList = "{\n" +
                "    \"Code\": 200,\n" +
                "    \"Time\": \"2021-12-12 15:23:34\",    \n" +
                "    \"Data\": {\n" +
                "        \"Suppliers\": [\n" +
                "            {\n" +
                "                \"ID\": \"3956825b-d05b-492d-8b62-454545ac5d\",\n" +
                "                \"Name\": \"xxx百货商行\",\n" +
                "                \"Contact\": \"\",\n" +
                "                \"Phone\": \"\",\n" +
                "                \"Address\": \"\",\n" +
                "                \"Remark\": \"\",\n" +
                "                \"Url\": \"https://shop111111.1688.com\",\n" +
                "                \"QQ\": \"\",\n" +
                "                \"WeChat\": \"\",\n" +
                "                \"WangWang\": \"\",\n" +
                "                \"SettlementType\": \"货到付款\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"ID\": \"9dd583af-9a05-4c95-a720-45450c39750\",\n" +
                "                \"Name\": \"xxx百货商行\",\n" +
                "                \"Contact\": \"\",\n" +
                "                \"Phone\": \"\",\n" +
                "                \"Address\": \"\",\n" +
                "                \"Remark\": \"\",\n" +
                "                \"Url\": \"https://11111.1688.com\",\n" +
                "                \"QQ\": \"\",\n" +
                "                \"WeChat\": \"\",\n" +
                "                \"WangWang\": \"\",\n" +
                "                \"SettlementType\": \"货到付款\"\n" +
                "            }\n" +
                "        ],\n" +
                "        \"Page\": {\n" +
                "            \"PageNumber\": 1,\n" +
                "            \"PageCount\": 2,\n" +
                "            \"PageSize\": 20,\n" +
                "            \"TotalCount\": 21,\n" +
                "            \"HasMore\": true\n" +
                "        }\n" +
                "    },\n" +
                "    \"Success\": true,\n" +
                "    \"Message\": \"成功\"\n" +
                "}\n";
        String simpleProduct1= "{\n" +
                "    \"Code\": 200,\n" +
                "    \"Time\": \"2021-12-12 15:23:34\",\n" +
                "    \"Data\": {\n" +
                "        \"Product\": {\n" +
                "            \"SPU\": {\n" +
                "                \"SPUID\": \"1351482672542\",\n" +
                "                \"Code\": \"100500203\",\n" +
                "                \"Title\": \"男士条纹衬衫翻领短袖夏季透气2021纽扣休闲卡米萨斯街装沙滩夏威夷衬衫\",\n" +
                "                \"LeiMuID\": \"c6ef5d23-8232-e811-8ffd-340804e01078\",\n" +
                "                \"Image\": \"https://www.xxxx.com/images/78f44c98-411b-a5bbd-800x800.jpg\",\n" +
                "                \"Status\": \"正常\",\n" +
                "                \"Source\": \"平台同步\",\n" +
                "                \"DefaultSupplierID\": \"00000000-0000-0000-0000-000000000000\",\n" +
                "                \"Remark\": \"\",\n" +
                "                \"IsExemptQuality\": false,\n" +
                "                \"CreateTime\": \"2020-06-19 15:48:02\",\n" +
                "                \"UpdateTime\": \"2021-10-16 14:32:46\"\n" +
                "            }\n" +
                "        }\n" +
                "    },\n" +
                "    \"Success\": true,\n" +
                "    \"Message\": \"成功\"\n" +
                "}";

        String product ="{\n" +
                "    \"Code\": 200,\n" +
                "    \"Time\": \"2021-12-12 15:23:34\",    \n" +
                "    \"Data\": {\n" +
                "        \"Product\": {\n" +
                "            \"SPU\": {\n" +
                "                \"SPUID\": \"1351482672542\",\n" +
                "                \"Code\": \"100500203\",\n" +
                "                \"Title\": \"男士条纹衬衫翻领短袖夏季透气2021纽扣休闲卡米萨斯街装沙滩夏威夷衬衫\",\n" +
                "                \"LeiMuID\": \"c6ef5d23-8232-e811-8ffd-340804e01078\",\n" +
                "                \"Image\": \"https://www.xxxx.com/images/78f44c98-411b-a5bbd-800x800.jpg\",\n" +
                "                \"Status\": \"正常\",\n" +
                "                \"Source\": \"平台同步\",\n" +
                "                \"DefaultSupplierID\": \"00000000-0000-0000-0000-000000000000\",\n" +
                "                \"Remark\": \"\",\n" +
                "                \"IsExemptQuality\": false,\n" +
                "                \"CreateTime\": \"2020-06-19 15:48:02\",\n" +
                "                \"UpdateTime\": \"2021-10-16 14:32:46\"\n" +
                "            },\n" +
                "            \"Descriptions\": {\n" +
                "                \"Html\": \"testlhtml\",\n" +
                "                \"Text\": \"testText\",\n" +
                "                \"Short\": \"testShort\"\n" +
                "            },\n" +
                "            \"SKUs\": [\n" +
                "                {\n" +
                "                    \"SKUID\": \"1351482672974663680\",\n" +
                "                    \"Code\": \"14:10#Red;5:200000990\",\n" +
                "                    \"Specification\": \"款式1;4XL\",\n" +
                "                    \"CostPrice\": 77.28,\n" +
                "                    \"Image\": \"https://www.xxxx.com/images/78f44c98-411b-a5bbd-800x800.jpg\",\n" +
                "                    \"Weight\": 0,\n" +
                "                    \"Length\": 0,\n" +
                "                    \"Width\": 0,\n" +
                "                    \"Height\": 0,\n" +
                "                    \"IsValid\": true,\n" +
                "                    \"Status\": \"正常\",\n" +
                "                    \"Remark\": \"\",\n" +
                "                    \"Sort\": 0,\n" +
                "                    \"Type\": \"普通\",\n" +
                "                    \"CombineData\": [],\n" +
                "                    \"Options\": [\n" +
                "                        {\n" +
                "                            \"Name\": \"款式1\",\n" +
                "                            \"Value\": \"款式1\"\n" +
                "                        },\n" +
                "                        {\n" +
                "                            \"Name\": \"4XL\",\n" +
                "                            \"Value\": \"4XL\"\n" +
                "                        }\n" +
                "                    ]\n" +
                "                }\n" +
                "            ]\n" +
                "        }\n" +
                "    },\n" +
                "    \"Success\": true,\n" +
                "    \"Message\": \"成功\"\n" +
                "}\n";

//        BanmaErpResponseDTO<GetTokenResponse> banmaErpResponseDTO = objectMapper.readValue(getTokenResp, new TypeReference<BanmaErpResponseDTO<GetTokenResponse>>() {});
//        System.out.println(banmaErpResponseDTO.getData().getAccessToken());

//        objectMapper.registerSubtypes(ProductSpuDTO.class, ProductDescriptionsDTO.class,
//                ProductOptionsDTO.class,ProductImagesDTO.class,ProductSuppliersDTO.class,ProductRequirementsDTO.class,
//                ProductSourcesDTO.class,ProductTagsDTO.class,ProductPackMaterialsDTO.class);

        BanmaErpResponseDTO<ProductDTO> banmaErpResponseDTO = objectMapper.readValue(product,
                new TypeReference<BanmaErpResponseDTO<ProductDTO>>() {});

//        System.out.println(banmaErpResponseDTO.getData().getSPU().getTitle()+"\t"
//                +banmaErpResponseDTO.getData().getDescriptions().getHtml()+"\t"+
//                banmaErpResponseDTO.getData().getSKUs()[0].getImage()+"\t"+
//                banmaErpResponseDTO.getData().getOptions()[0].getName());


        BanmaErpResponseDTO<JsonNode> projectListRaw =objectMapper.readValue(getProductList, new TypeReference<BanmaErpResponseDTO<JsonNode>>() {});
        Object[] objects = projectListRaw.toDataList(BANMAERP_FIELD_PRODUCTS);
        List<ProductDTO> productDTOS=
                Arrays.stream(objects).map(o -> (ProductDTO)o)
                        .collect(Collectors.toList());
        productDTOS.forEach(productDTO -> System.out.println(productDTO.getSPU().getSource()));
    }
}
