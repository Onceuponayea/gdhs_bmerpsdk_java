package com.hrghs.xycb;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.joda.JodaModule;
import com.hrghs.xycb.domains.GetSsoPassportResponse;
import com.hrghs.xycb.domains.banmaerpDTO.ProductDTO;
import com.hrghs.xycb.domains.common.BanmaErpResponseDTO;
import com.hrghs.xycb.domains.enums.BanmaerpAuthEnums;
import org.apache.commons.codec.binary.Hex;

import java.net.*;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
//import com.atomikos.icatch.jta.hibernate3.TransactionManagerLookup;

public class SimpleTests {
    public static void main(String[] args) throws UnknownHostException, SocketException, MalformedURLException, JsonProcessingException {
        String test="{\n" +
                "    \"Product\": {\n" +
                "        \"SPU\": {\n" +
                "            \"SPUID\": \"1497121263854817280\",\n" +
                "            \"Code\": \"22112200001\",\n" +
                "            \"Title\": \"重磅卫衣男秋冬款连帽加绒加厚美式复古运动外套春秋潮牌宽松男装\",\n" +
                "            \"LeiMuID\": \"32781a0f-50bd-4fd5-b15a-af5500e69a33\",\n" +
                "            \"Image\": \"https://gw.alicdn.com/imgextra/i3/2966343102/O1CN01Z8e6HU1YmlNKvfP4k_!!2966343102.jpg_Q75.jpg_.webp\",\n" +
                "            \"Source\": \"手工创建\",\n" +
                "            \"DefaultSupplierID\": \"b4643438-15b5-4fee-9071-af5600fdece6\",\n" +
                "            \"Remark\": \"\",\n" +
                "            \"IsExemptQuality\": true,\n" +
                "            \"Keywords\": \"爆款\",\n" +
                "            \"CreateTime\": \"2022-11-22T14:25:24\",\n" +
                "            \"UpdateTime\": \"2022-11-23T15:26:46\"\n" +
                "        },\n" +
                "        \"Descriptions\": {\n" +
                "            \"Html\": \"<p><span style=\\\"font-family: &quot;PingFang SC&quot;; font-size: 20px; font-weight: 900; background-color: rgb(255, 255, 255);\\\">重磅卫衣男秋冬款连帽加绒加厚美式复古运动外套春秋潮牌宽松男装</span></p>\",\n" +
                "            \"Text\": \"\",\n" +
                "            \"Short\": \"\"\n" +
                "        },\n" +
                "        \"SKUs\": [\n" +
                "            {\n" +
                "                \"SKUID\": \"1497490072574889984\",\n" +
                "                \"Code\": \"22112200001-White-One-Size\",\n" +
                "                \"Specification\": \"White;One Size\",\n" +
                "                \"CostPrice\": 112.0,\n" +
                "                \"Image\": \"https://gw.alicdn.com/imgextra/i3/2966343102/O1CN01Z8e6HU1YmlNKvfP4k_!!2966343102.jpg_Q75.jpg_.webp\",\n" +
                "                \"Weight\": 60,\n" +
                "                \"Length\": 60,\n" +
                "                \"Width\": 60,\n" +
                "                \"Height\": 60,\n" +
                "                \"IsValid\": true,\n" +
                "                \"Remark\": \"\",\n" +
                "                \"Sort\": 0,\n" +
                "                \"Type\": \"普通\",\n" +
                "                \"CombineData\": [],\n" +
                "                \"Options\": [\n" +
                "                    {\n" +
                "                        \"Name\": \"Color\",\n" +
                "                        \"Value\": \"White\"\n" +
                "                    },\n" +
                "                    {\n" +
                "                        \"Name\": \"Size\",\n" +
                "                        \"Value\": \"One Size\"\n" +
                "                    }\n" +
                "                ]\n" +
                "            }\n" +
                "        ],\n" +
                "        \"Suppliers\": [\n" +
                "            {\n" +
                "                \"ID\": \"b4643438-15b5-4fee-9071-af5600fdece6\",\n" +
                "                \"Remark\": \"\",\n" +
                "                \"Sort\": 0,\n" +
                "                \"Info\": {\n" +
                "                    \"ID\": \"b4643438-15b5-4fee-9071-af5600fdece6\",\n" +
                "                    \"Name\": \"zzz\",\n" +
                "                    \"Contact\": \"将之心\",\n" +
                "                    \"Phone\": \"13799820202\",\n" +
                "                    \"Address\": \"福建省厦门市\",\n" +
                "                    \"Remark\": \"\",\n" +
                "                    \"Url\": \"\",\n" +
                "                    \"QQ\": \"\",\n" +
                "                    \"WeChat\": \"\",\n" +
                "                    \"WangWang\": \"\",\n" +
                "                    \"SettlementType\": \"货到付款\"\n" +
                "                }\n" +
                "            }\n" +
                "        ],\n" +
                "        \"Options\": [\n" +
                "            {\n" +
                "                \"Name\": \"Color\",\n" +
                "                \"Sort\": 0,\n" +
                "                \"Values\": [\n" +
                "                    \"White\"\n" +
                "                ]\n" +
                "            },\n" +
                "            {\n" +
                "                \"Name\": \"Size\",\n" +
                "                \"Sort\": 0,\n" +
                "                \"Values\": [\n" +
                "                    \"One Size\"\n" +
                "                ]\n" +
                "            }\n" +
                "        ],\n" +
                "        \"Requirements\": [],\n" +
                "        \"Sources\": [],\n" +
                "        \"Images\": [\n" +
                "            {\n" +
                "                \"Src\": \"https://gw.alicdn.com/imgextra/i3/2966343102/O1CN01Z8e6HU1YmlNKvfP4k_!!2966343102.jpg_Q75.jpg_.webp\",\n" +
                "                \"Sort\": 0\n" +
                "            }\n" +
                "        ],\n" +
                "        \"Tags\": [\n" +
                "            {\n" +
                "                \"Name\": \"休闲\"\n" +
                "            }\n" +
                "        ],\n" +
                "        \"PackMaterials\": [\n" +
                "            {\n" +
                "                \"ID\": \"1497488256156045312\",\n" +
                "                \"Name\": \"羊羔绒\",\n" +
                "                \"Remark\": \"\",\n" +
                "                \"Quantity\": 1\n" +
                "            }\n" +
                "        ]\n" +
                "    }\n" +
                "}";
        ObjectMapper objectMapper = new ObjectMapper().registerModule(new JodaModule());
//        ProductDTO banmaErpResponseDTO = objectMapper.readValue(test, new TypeReference<ProductDTO>() {});
        ProductDTO banmaErpResponseDTO = objectMapper.readValue(test, ProductDTO.class);
        System.out.println(banmaErpResponseDTO.getSPU().getTitle());

    }
    private void banmaSign(){
        boolean isIP = ("IP白名单" == BanmaerpAuthEnums.AuthMethod.IP_WHITELIST.getAuthType());
        System.out.println(isIP);
        System.out.println( BanmaerpAuthEnums.AuthMethod.IP_WHITELIST.getAuthType());
        System.out.println();

        String timeStamp=(System.currentTimeMillis()/1000L)+"";
        String signText=" GET/v1/Auth/GetTokenapp_id=1492055167686688768&app_secret=a7437ad68d0f457b9788e17f92e13b0f&"
                +timeStamp;
        System.out.println("timeStamp: "+ timeStamp);
        MessageDigest messageDigest=null;
        try {
            messageDigest = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        messageDigest.update(signText.getBytes(StandardCharsets.UTF_8));
        byte[] hash = messageDigest.digest(signText.getBytes(StandardCharsets.UTF_8));
        String result = Hex.encodeHexString(hash);
        System.out.println(result);
    }



}
