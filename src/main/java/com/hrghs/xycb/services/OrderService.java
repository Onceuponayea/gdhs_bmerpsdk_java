package com.hrghs.xycb.services;

import com.hrghs.xycb.config.BanmaerpProperties;
import com.hrghs.xycb.domains.banmaerpDTO.OrderDTO;
import com.hrghs.xycb.domains.common.BanmaErpResponseDTO;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;
import java.util.List;

public interface OrderService {

    /**
     * 查询订单列表
     * @param ids 	订单ID，用逗号分隔
     * @param storeId 	店铺ID
     * @param platform 平台，具体值参见:Platform
     * @param status 	订单状态，具体值参见:Status
     * @param payStatus 	订单支付状态，具体值参见:PayStatus
     * @param holdStatus 	搁置状态，具体值参见:HoldStatus
     * @param refundStatus 	退款状态，具体值参见:RefundStatus
     * @param inventoryStatus 	库存状态，具体值参见:InventoryStatus
     * @param countryCode 	国家的code ISO3166-1 二位字母
     * @param pageNumber 	页码（必填）
     * @param pageSize      页大小
     * @param searchTimeStart 查询的开始时间
     * @param searchTimeEnd   查询的结束时间
     * @param searchTimeField 查询的时间字段名，具体值参见:SearchTimeField
     * @param sortField	   排序字段名，具体值参见:SortField
     * @param sortBy   排序方式，具体值参见:SortBy
     * @param banmaerpProperties 斑马erp主账号（供应商或者平台）
     * @return
     */
    List<OrderDTO> getOrderList(
            String ids,
            String storeId,
            String platform,
            String status,
            String payStatus,
            String holdStatus,
            String refundStatus,
            String inventoryStatus,
            String countryCode,
            int pageNumber,
            int pageSize,
            DateTime searchTimeStart,
            DateTime searchTimeEnd,
            String searchTimeField,
            String sortField,
            String sortBy,
            BanmaerpProperties banmaerpProperties
    );

    /**
     * 查询单个订单
     * @param id 	订单ID（必填）
     * @param banmaerpProperties 斑马erp主账号（供应商或者平台）
     * @return
     */
    OrderDTO getOrderById(String id,
                          BanmaerpProperties banmaerpProperties);

    List<OrderDTO> saveAll(Iterable<OrderDTO> orderDTOS);

    OrderDTO save(OrderDTO orderDTO);
}
