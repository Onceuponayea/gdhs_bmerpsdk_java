package com.hrghs.xycb.serialiseTest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.joda.JodaModule;
import com.hrghs.xycb.domains.banmaerpDTO.OrderDTO;
import com.hrghs.xycb.domains.common.BanmaErpResponseDTO;

import java.util.List;

/**
 * 2022.11.14 jzx
 * 订单接口API序列化、反序列化没有问题
 */
public class BanmaerOrderTest {
    public static void main(String[] args) throws JsonProcessingException {
        // 查询订单列表
        String getOrderList = "{\n" +
                "    \"Code\": 200,\n" +
                "    \"Time\": \"2021-12-12 15:23:34\",    \n" +
                "    \"Data\": {\n" +
                "        \"Orders\": [\n" +
                "            {\n" +
                "                \"Master\": {\n" +
                "                    \"ID\": \"123455\",\n" +
                "                    \"StoreID\": \"12345\",\n" +
                "                    \"Platform\": \"Shoplazza\",\n" +
                "                    \"OriginalOrderID\": \"00a56052-3748-4a0d-9d26-69\",\n" +
                "                    \"DisplayOrderID\": \"#1232\",\n" +
                "                    \"PayTime\": \"2020-06-19 12:13:43\",\n" +
                "                    \"PayChannel\": \"paypal\",\n" +
                "                    \"PayType\": 0,\n" +
                "                    \"PayAmount\": 278.64,\n" +
                "                    \"PayCurrency\": \"USD\",\n" +
                "                    \"PayAmountUSD\": 278.64,\n" +
                "                    \"PayAmountCNY\": 1974.42,\n" +
                "                    \"PayFreight\": 12.00,\n" +
                "                    \"PayFreightCurrency\": \"USD\",\n" +
                "                    \"PayFreightUSD\": 12.00,\n" +
                "                    \"PayFreightCNY\": 85.03,\n" +
                "                    \"RefundAmount\": 0.00,\n" +
                "                    \"RefundCurrency\": \"USD\",\n" +
                "                    \"RefundAmountUSD\": 0.00,\n" +
                "                    \"RefundAmountCNY\": 0.00,\n" +
                "                    \"DiscountAmount\": 0.00,\n" +
                "                    \"DiscountCurrency\": \"USD\",\n" +
                "                    \"Status\": \"待发货\",\n" +
                "                    \"PayStatus\": \"未付款\",\n" +
                "                    \"ShippingType\": \"yunfei1\",\n" +
                "                    \"CountryCode\": \"CN\",\n" +
                "                    \"HoldStatus\": \"未暂停\",\n" +
                "                    \"RefundStatus\": \"无退款\",\n" +
                "                    \"Quantity\": 3,\n" +
                "                    \"PurchaseFreight\": 0.00,\n" +
                "                    \"UserIndentity\": \"111@qq.com\",\n" +
                "                    \"OrderTime\": \"2020-06-19 12:09:34\",\n" +
                "                    \"Tags\": [],\n" +
                "                    \"OriginalOrderStatus\": \"waiting\",\n" +
                "                    \"OriginalOrderTime\": \"2020-06-19 04:09:34\",\n" +
                "                    \"OriginalPayTime\": \"2020-06-19 04:13:43\",\n" +
                "                    \"HaveMessage\": false,\n" +
                "                    \"HaveRemark\": false,\n" +
                "                    \"LatestDeliveryTime\": null,\n" +
                "                    \"CreateTime\": \"2020-06-19 15:48:02\",\n" +
                "                    \"UpdateTime\": \"2021-10-16 14:32:46\",\n" +
                "                    \"IsCOD\": true,\n" +
                "                    \"Flags\": [\n" +
                "                        \"货到付款\"\n" +
                "                    ],\n" +
                "                    \"Email\": \"111@qq.com\",\n" +
                "                    \"InventoryStatus\": \"有货\",\n" +
                "                    \"InventoryMode\": \"本地仓库存\",\n" +
                "                    \"TxNo\": \"\",\n" +
                "                    \"BuyerName\": \"xxx\",\n" +
                "                    \"Message\": \"\",\n" +
                "                    \"ShippingTime\": null,\n" +
                "                    \"OriginalShippingTime\": null,\n" +
                "                    \"OriginalTags\": [],\n" +
                "                    \"BuyerPhone\": \"\"\n" +
                "                },\n" +
                "                \"Details\": [\n" +
                "                    {\n" +
                "                        \"ID\": \"51012f7d-00be-4773-83a4-abdf\",\n" +
                "                        \"PackageID\": \"135144319\",\n" +
                "                        \"OriginalOrderID\": \"00a56052-3748-4a0d-9d26-691c\",\n" +
                "                        \"OriginalDetailID\": \"297da93a-cf7a-4d67-9f7f-16b0\",\n" +
                "                        \"OriginalSKU\": \"12334\",\n" +
                "                        \"SPUID\": \"12334\",\n" +
                "                        \"SKUID\": \"12334\",\n" +
                "                        \"SKUType\": \"普通\",\n" +
                "                        \"SKUCode\": \"black\",\n" +
                "                        \"SKUSpecification\": \"black\",\n" +
                "                        \"Price\": 88.88,\n" +
                "                        \"Title\": \"Title\",\n" +
                "                        \"OriginalImage\": \"//cdn.xxx.com/7ace6e4281b267e0.jpg\",\n" +
                "                        \"Quantity\": 1,\n" +
                "                        \"Amount\": 177.76,\n" +
                "                        \"Status\": \"已取消\",\n" +
                "                        \"PriceCurrency\": \"USD\",\n" +
                "                        \"Properties\": [],\n" +
                "                        \"OriginalProductID\": \"BE0D575F-23E6-4045-91\",\n" +
                "                        \"OriginalSKUID\": \"F54570AA-207E-4B29-B6B\",\n" +
                "                        \"WarehouseID\": \"af3de541-8bdb-4754-99af-f89\",\n" +
                "                        \"InventoryStatus\": \"未知\",\n" +
                "                        \"InventoryLockQuantity\": 0,\n" +
                "                        \"SkuChangeStatus\": \"未变更\",\n" +
                "                        \"OriginalSpecification\": \"White\",\n" +
                "                        \"SKUImage\": \"https://www.xxx.com/0456ebc048fc.jpg\",\n" +
                "                        \"InventoryData\": [\n" +
                "                            {\n" +
                "                                \"SKUID\": \"1234\",\n" +
                "                                \"SKUCode\": \"black\",\n" +
                "                                \"ShortageQuantity\": 0,\n" +
                "                                \"LockQuantity\": 1,\n" +
                "                                \"SPUID\": \"12345\"\n" +
                "                            }\n" +
                "                        ],\n" +
                "                        \"Sort\": 1,\n" +
                "                        \"Type\": \"普通\",\n" +
                "                        \"ProductUrl\": \"\",\n" +
                "                        \"QuantityStatus\": \"已调整\"\n" +
                "                    }\n" +
                "                ]\n" +
                "            }\n" +
                "        ],\n" +
                "        \"Page\": {\n" +
                "            \"PageNumber\": 1,\n" +
                "            \"PageCount\": 10,\n" +
                "            \"PageSize\": 1,\n" +
                "            \"TotalCount\": 10,\n" +
                "            \"HasMore\": true\n" +
                "        }\n" +
                "    },\n" +
                "    \"Success\": true,\n" +
                "    \"Message\": \"成功\"\n" +
                "}\n";

        // 查询单个订单
        String getOrderById = "{\n" +
                "    \"Code\": 200,\n" +
                "    \"Time\": \"2021-12-12 15:23:34\",    \n" +
                "    \"Data\": {\n" +
                "        \"Order\": {\n" +
                "            \"Master\": {\n" +
                "                \"ID\": \"123455\",\n" +
                "                \"StoreID\": \"12345\",\n" +
                "                \"Platform\": \"Shoplazza\",\n" +
                "                \"OriginalOrderID\": \"00a56052-3748-4a0d-9d26-69\",\n" +
                "                \"DisplayOrderID\": \"#1232\",\n" +
                "                \"PayTime\": \"2020-06-19 12:13:43\",\n" +
                "                \"PayChannel\": \"paypal\",\n" +
                "                \"PayType\": 0,\n" +
                "                \"PayAmount\": 278.64,\n" +
                "                \"PayCurrency\": \"USD\",\n" +
                "                \"PayAmountUSD\": 278.64,\n" +
                "                \"PayAmountCNY\": 1974.42,\n" +
                "                \"PayFreight\": 12.00,\n" +
                "                \"PayFreightCurrency\": \"USD\",\n" +
                "                \"PayFreightUSD\": 12.00,\n" +
                "                \"PayFreightCNY\": 85.03,\n" +
                "                \"RefundAmount\": 0.00,\n" +
                "                \"RefundCurrency\": \"USD\",\n" +
                "                \"RefundAmountUSD\": 0.00,\n" +
                "                \"RefundAmountCNY\": 0.00,\n" +
                "                \"DiscountAmount\": 0.00,\n" +
                "                \"DiscountCurrency\": \"USD\",\n" +
                "                \"Status\": \"待发货\",\n" +
                "                \"PayStatus\": \"未付款\",\n" +
                "                \"ShippingType\": \"yunfei1\",\n" +
                "                \"CountryCode\": \"CN\",\n" +
                "                \"HoldStatus\": \"未暂停\",\n" +
                "                \"RefundStatus\": \"无退款\",\n" +
                "                \"Quantity\": 3,\n" +
                "                \"PurchaseFreight\": 0.00,\n" +
                "                \"UserIndentity\": \"111@qq.com\",\n" +
                "                \"OrderTime\": \"2020-06-19 12:09:34\",\n" +
                "                \"Tags\": [],\n" +
                "                \"OriginalOrderStatus\": \"waiting\",\n" +
                "                \"OriginalOrderTime\": \"2020-06-19 04:09:34\",\n" +
                "                \"OriginalPayTime\": \"2020-06-19 04:13:43\",\n" +
                "                \"HaveMessage\": false,\n" +
                "                \"HaveRemark\": false,\n" +
                "                \"LatestDeliveryTime\": null,\n" +
                "                \"CreateTime\": \"2020-06-19 15:48:02\",\n" +
                "                \"UpdateTime\": \"2021-10-16 14:32:46\",\n" +
                "                \"IsCOD\": true,\n" +
                "                \"Flags\": [\n" +
                "                    \"货到付款\"\n" +
                "                ],\n" +
                "                \"Email\": \"111@qq.com\",\n" +
                "                \"InventoryStatus\": \"有货\",\n" +
                "                \"InventoryMode\": \"本地仓库存\",\n" +
                "                \"TxNo\": \"\",\n" +
                "                \"BuyerName\": \"xxx\",\n" +
                "                \"Message\": \"\",\n" +
                "                \"ShippingTime\": null,\n" +
                "                \"OriginalShippingTime\": null,\n" +
                "                \"OriginalTags\": [],\n" +
                "                \"BuyerPhone\": \"\"\n" +
                "            },\n" +
                "            \"Details\": [\n" +
                "                {\n" +
                "                    \"ID\": \"51012f7d-00be-4773-83a4-abdf\",\n" +
                "                    \"PackageID\": \"135144319\",\n" +
                "                    \"OriginalOrderID\": \"00a56052-3748-4a0d-9d26-691c\",\n" +
                "                    \"OriginalDetailID\": \"297da93a-cf7a-4d67-9f7f-16b0\",\n" +
                "                    \"OriginalSKU\": \"12334\",\n" +
                "                    \"SPUID\": \"12334\",\n" +
                "                    \"SKUID\": \"12334\",\n" +
                "                    \"SKUType\": \"普通\",\n" +
                "                    \"SKUCode\": \"black\",\n" +
                "                    \"SKUSpecification\": \"black\",\n" +
                "                    \"Price\": 88.88,\n" +
                "                    \"Title\": \"Title\",\n" +
                "                    \"OriginalImage\": \"//cdn.xxxx.com/7ace6e4281b267e0.jpg\",\n" +
                "                    \"Quantity\": 1,\n" +
                "                    \"Amount\": 177.76,\n" +
                "                    \"Status\": \"已取消\",\n" +
                "                    \"PriceCurrency\": \"USD\",\n" +
                "                    \"Properties\": [],\n" +
                "                    \"OriginalProductID\": \"BE0D575F-23E6-4045-91\",\n" +
                "                    \"OriginalSKUID\": \"F54570AA-207E-4B29-B6B\",\n" +
                "                    \"WarehouseID\": \"af3de541-8bdb-4754-99af-f89\",\n" +
                "                    \"InventoryStatus\": \"未知\",\n" +
                "                    \"InventoryLockQuantity\": 0,\n" +
                "                    \"SkuChangeStatus\": \"未变更\",\n" +
                "                    \"OriginalSpecification\": \"White\",\n" +
                "                    \"SKUImage\": \"https://www.xxx.com/0456ebc048fc.jpg\",\n" +
                "                    \"InventoryData\": [\n" +
                "                        {\n" +
                "                            \"SKUID\": \"1234\",\n" +
                "                            \"SKUCode\": \"black\",\n" +
                "                            \"ShortageQuantity\": 0,\n" +
                "                            \"LockQuantity\": 1,\n" +
                "                            \"SPUID\": \"12345\"\n" +
                "                        }\n" +
                "                    ],\n" +
                "                    \"Sort\": 1,\n" +
                "                    \"Type\": \"普通\",\n" +
                "                    \"ProductUrl\": \"\",\n" +
                "                    \"QuantityStatus\": \"已调整\"\n" +
                "                }\n" +
                "            ],\n" +
                "            \"Addresses\": [\n" +
                "                {\n" +
                "                    \"Name\": \"name\",\n" +
                "                    \"NameKana\": \"\",\n" +
                "                    \"Country\": \"Korea\",\n" +
                "                    \"CountryCode\": \"KR\",\n" +
                "                    \"Province\": \"\",\n" +
                "                    \"City\": \"\",\n" +
                "                    \"Phone\": \"0503-086-8213\",\n" +
                "                    \"Email\": \"\",\n" +
                "                    \"PostalCode\": \"04610\",\n" +
                "                    \"AddrType\": \"收货地址\",\n" +
                "                    \"Address1\": \"地址1\",\n" +
                "                    \"Address2\": \"地址2\",\n" +
                "                    \"Address3\": \"\",\n" +
                "                    \"IsOriginal\": true,\n" +
                "                    \"TaxNo\": \"\"\n" +
                "                }\n" +
                "            ],\n" +
                "            \"Remarks\": [],\n" +
                "            \"Refunds\": [\n" +
                "                {\n" +
                "                    \"DetailID\": null,\n" +
                "                    \"OrignalRefundID\": \"469031488\",\n" +
                "                    \"RefundAmount\": 0.00,\n" +
                "                    \"RefundCurrency\": \"KRW\",\n" +
                "                    \"RefundType\": \"RETURN\",\n" +
                "                    \"RefundTime\": \"2021-12-13 11:47:55\"\n" +
                "                }\n" +
                "            ]\n" +
                "        }\n" +
                "    },\n" +
                "    \"Success\": true,\n" +
                "    \"Message\": \"成功\"\n" +
                "}\n";

        ObjectMapper objectMapper = new ObjectMapper().registerModule(new JodaModule());
        BanmaErpResponseDTO<List<OrderDTO>> banmaErpResponseDTO = objectMapper.readValue(getOrderById, new TypeReference<BanmaErpResponseDTO<List<OrderDTO>>>() {
        });
        System.out.println(banmaErpResponseDTO.getData().get(0).getMaster().getStoreID());
    }
}
