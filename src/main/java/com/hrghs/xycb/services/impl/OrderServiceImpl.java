package com.hrghs.xycb.services.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.joda.JodaModule;
import com.hrghs.xycb.annotations.CheckBanmaerpProperties;
import com.hrghs.xycb.config.BanmaerpProperties;
import com.hrghs.xycb.domains.BanmaerpSigningVO;
import com.hrghs.xycb.domains.BanmaerpURL;
import com.hrghs.xycb.domains.banmaerpDTO.AccountDTO;
import com.hrghs.xycb.domains.banmaerpDTO.CategoryDTO;
import com.hrghs.xycb.domains.banmaerpDTO.OrderDTO;
import com.hrghs.xycb.domains.common.BanmaErpResponseDTO;
import com.hrghs.xycb.repositories.OrderRepository;
import com.hrghs.xycb.services.OrderService;
import com.hrghs.xycb.utils.BanmaTokenUtils;
import com.hrghs.xycb.utils.EncryptionUtils;
import com.hrghs.xycb.utils.HttpClientsUtils;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static com.hrghs.xycb.domains.Constants.BANMAERP_FIELD_CATEGORYS;
import static com.hrghs.xycb.domains.Constants.BANMAERP_FIELD_ORDERS;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private HttpClientsUtils httpClients;
    @Autowired
    private BanmaTokenUtils banmaTokenUtils;
    @Autowired
    private EncryptionUtils encryptionUtils;

    /**
     * 查询订单列表
     *
     * @param ids             订单ID，用逗号分隔
     * @param storeId         店铺ID
     * @param platform        平台，具体值参见:Platform
     * @param status          订单状态，具体值参见:Status
     * @param payStatus       订单支付状态，具体值参见:PayStatus
     * @param holdStatus      搁置状态，具体值参见:HoldStatus
     * @param refundStatus    退款状态，具体值参见:RefundStatus
     * @param inventoryStatus 库存状态，具体值参见:InventoryStatus
     * @param countryCode     国家的code ISO3166-1 二位字母
     * @param pageNumber      页码（必填）
     * @param pageSize        页大小
     * @param searchTimeStart 查询的开始时间
     * @param searchTimeEnd   查询的结束时间
     * @param searchTimeField 查询的时间字段名，具体值参见:SearchTimeField
     * @param sortField       排序字段名，具体值参见:SortField
     * @param sortBy          排序方式，具体值参见:SortBy
     * @return
     */
    @Override
    @CheckBanmaerpProperties
    public List<OrderDTO> getOrderList(String ids, String storeId, String platform, String status, String payStatus,
                                            String holdStatus, String refundStatus, String inventoryStatus, String countryCode,
                                            int pageNumber, int pageSize, DateTime searchTimeStart, DateTime searchTimeEnd,
                                            String searchTimeField, String sortField, String sortBy,
                                            BanmaerpProperties banmaerpProperties) {
        String apiUrl = String.format(BanmaerpURL.banmaerp_order_GET, pageNumber, pageSize,searchTimeStart,searchTimeEnd,searchTimeField);
        apiUrl = encryptionUtils.rmEmptyParas(apiUrl);
        HttpHeaders httpHeaders = new HttpHeaders();
        BanmaerpSigningVO banmaerpSigningVO = banmaTokenUtils.banmaerpSigningVO(banmaerpProperties);
        //todo signing
        httpHeaders = banmaTokenUtils.banmaerpCommonHeaders(httpHeaders, banmaerpProperties, banmaerpSigningVO);
        HttpEntity requestBody = new HttpEntity(null, httpHeaders);
        List<OrderDTO> orderDTOList = Arrays.stream(httpClients.restTemplateWithBanmaMasterToken(banmaerpProperties)
                .exchange(BanmaerpURL.banmaerp_gateway.concat(apiUrl), HttpMethod.GET, requestBody, new ParameterizedTypeReference<BanmaErpResponseDTO<JsonNode>>() {
                })
                .getBody()
                .toDataList(BANMAERP_FIELD_ORDERS)
        )
                .map(o -> (OrderDTO) o)
                .collect(Collectors.toList());
        return orderDTOList;
    }

    /**
     * 查询单个订单
     *
     * @param id 订单ID（必填）
     * @return
     */
    @Override
    @CheckBanmaerpProperties
    public OrderDTO getOrderById(String id, BanmaerpProperties banmaerpProperties) {
        String apiUrl = String.format(BanmaerpURL.banmaerp_orderdetail_GET,id);
        apiUrl = encryptionUtils.rmEmptyParas(apiUrl);
        HttpHeaders httpHeaders = new HttpHeaders();
        BanmaerpSigningVO banmaerpSigningVO = banmaTokenUtils.banmaerpSigningVO(banmaerpProperties);
        //todo signing
        httpHeaders = banmaTokenUtils.banmaerpCommonHeaders(httpHeaders,banmaerpProperties,banmaerpSigningVO);
        HttpEntity requestBody = new HttpEntity(null,httpHeaders);
        BanmaErpResponseDTO<JsonNode> body = httpClients.restTemplateWithBanmaMasterToken(banmaerpProperties)
                .exchange(BanmaerpURL.banmaerp_gateway.concat(apiUrl), HttpMethod.GET, requestBody, new ParameterizedTypeReference<BanmaErpResponseDTO<JsonNode>>() {
                })
                .getBody();

        ObjectMapper objectMapper = new ObjectMapper().registerModule(new JodaModule());
        OrderDTO orderDTO = null;
        try {
            orderDTO = objectMapper.readValue(body.getData().toString(), new TypeReference<OrderDTO>() {
            });
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return orderDTO;
    }
}
