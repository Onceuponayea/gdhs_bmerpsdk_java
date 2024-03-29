package com.hrghs.xycb.services.impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.hrghs.xycb.annotations.CheckBanmaerpProperties;
import com.hrghs.xycb.domains.BanmaerpProperties;
import com.hrghs.xycb.domains.BanmaerpSigningVO;
import com.hrghs.xycb.domains.BanmaerpURL;
import com.hrghs.xycb.domains.banmaerpDTO.*;
import com.hrghs.xycb.domains.common.BanmaErpResponseDTO;
import com.hrghs.xycb.domains.enums.BanmaerpOrderEnums;
import com.hrghs.xycb.domains.enums.BanmaerpPlatformEnums;
import com.hrghs.xycb.domains.resultSet.OrderStatistics;
import com.hrghs.xycb.repositories.OrderFulfillmentRepository;
import com.hrghs.xycb.repositories.OrderMasterRepository;
import com.hrghs.xycb.repositories.OrderRepository;
import com.hrghs.xycb.repositories.OrderTrackingRepository;
import com.hrghs.xycb.services.OrderService;
import com.hrghs.xycb.utils.BanmaParamsUtils;
import com.hrghs.xycb.utils.BanmaTokenUtils;
import com.hrghs.xycb.utils.BanmaEncryptionUtils;
import com.hrghs.xycb.utils.HttpClientsUtils;
import org.joda.time.DateTime;
import org.joda.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import static com.hrghs.xycb.domains.Constants.*;
import static jodd.util.StringPool.DASH;

public class OrderServiceImpl implements OrderService {

    @Autowired
    private HttpClientsUtils httpClients;
    @Autowired
    private BanmaTokenUtils banmaTokenUtils;
    @Autowired
    private BanmaEncryptionUtils encryptionUtils;
    @Autowired
    private OrderTrackingRepository trackingRepository;
    @Autowired
    private OrderFulfillmentRepository fulfillmentRepository;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderMasterRepository orderMasterRepository;

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
    public Page<OrderDTO> getOrderList(Integer accountId,String ids, String storeId, String platform, String status, String payStatus,
                                       String holdStatus, String refundStatus, String inventoryStatus, String countryCode,
                                       Integer pageNumber, Integer pageSize, DateTime searchTimeStart, DateTime searchTimeEnd,
                                       String searchTimeField, String sortField, String sortBy, Boolean remote,
                                       BanmaerpProperties banmaerpProperties) {
        Page<OrderDTO> orderDTOList;
        pageSize = BanmaParamsUtils.checkPageSize(pageSize);
        if (remote){
            pageNumber = BanmaParamsUtils.checkPageNum(pageNumber,remote);
            String apiUrl = String.format(BanmaerpURL.banmaerp_order_GET, ids,storeId,platform,status,payStatus,holdStatus,refundStatus,inventoryStatus,countryCode,pageNumber, pageSize,
                    searchTimeStart==null?null:searchTimeStart.toLocalDateTime(), searchTimeEnd==null?null:searchTimeEnd.toLocalDateTime(), searchTimeField,sortField,sortBy);
            apiUrl = encryptionUtils.rmEmptyParas(apiUrl);
            HttpHeaders httpHeaders = new HttpHeaders();
            BanmaerpSigningVO banmaerpSigningVO = banmaTokenUtils.banmaerpSigningVO(banmaerpProperties,HttpMethod.GET,apiUrl,null);
            httpHeaders = banmaTokenUtils.banmaerpCommonHeaders(httpHeaders, banmaerpProperties, banmaerpSigningVO);
            HttpEntity requestBody = new HttpEntity(null, httpHeaders);
            orderDTOList = (Page<OrderDTO>)
                    httpClients.restTemplateWithBanmaMasterToken(banmaerpProperties)
                            .exchange(BanmaerpURL.banmaerp_gateway.concat(apiUrl), HttpMethod.GET, requestBody, new ParameterizedTypeReference<BanmaErpResponseDTO<JsonNode>>() {})
                            .getBody()
                            .toDataList(OrderDTO.class,banmaerpProperties);
            orderDTOList.forEach(orderDTO -> orderDTO.setBanmaerpProperties(banmaerpProperties));
            saveAll(orderDTOList,banmaerpProperties);
        }else{
            pageNumber = BanmaParamsUtils.checkPageNum(pageNumber,false);
            Specification<OrderDTO> specification = createSpecification(accountId,ids, storeId, platform,status,payStatus,holdStatus,refundStatus,inventoryStatus,countryCode, searchTimeStart, searchTimeEnd, searchTimeField, sortField, sortBy,banmaerpProperties);
            orderDTOList = orderRepository.findAll(specification,PageRequest.of(pageNumber,pageSize));
        }
        return orderDTOList;
    }

    @Override
    @CheckBanmaerpProperties
    public Page<OrderDTO> getOrderList(Integer pageNumber,Boolean remote, BanmaerpProperties banmaerpProperties) {
        return getOrderList(null,null, null, null, null, null, null, null, null, null, pageNumber, null, null, null, null, null, null, remote,banmaerpProperties);
    }

    @Override
    @CheckBanmaerpProperties
    public Page<OrderDTO> getOrderList(Integer pageNumber, Integer pageSize,Boolean remote, BanmaerpProperties banmaerpProperties) {
        return getOrderList(null,null, null, null, null, null, null, null, null, null, pageNumber, pageSize, null, null, null, null, null, remote,banmaerpProperties);
    }

    @Override
    public List<OrderDTO> getOrderList(Integer pageNumber, Integer pageSize,DateTime payTimeStart,DateTime payTimeEnd, AccountDTO account) {
        int limitStart = pageSize * pageNumber;
        int limitEnd = (pageSize+1) * pageNumber;
        List<OrderDTO> orderDTOS = orderRepository.findOrderByAccount(account.getID(),payTimeStart.toString(),payTimeEnd.toString(),
                limitStart,limitEnd);
        return orderDTOS;
    }

    @Override
    public Page<OrderDTO> getOrderList(Integer pageNumber, Integer pageSize, StoreDTO account) {
        return null;
    }

    @Override
    @CheckBanmaerpProperties
    public Page<OrderDTO> getAndSaveOrderList(LocalDateTime lastPullTime, Integer pageNumber, Integer pageSize, BanmaerpProperties banmaerpProperties) {
        Page<OrderDTO> orderDTOList =
                getOrderList(null,null, null, null, null, null, null, null,
                        null, null, pageNumber, pageSize, lastPullTime.toDateTime(), null, BanmaerpOrderEnums.SearchTimeField.UpdateTime.name(),
                        null, null, true,banmaerpProperties);
         saveAll(orderDTOList.getContent(),banmaerpProperties);
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
    public OrderDTO getOrderById(String id, Boolean remote,BanmaerpProperties banmaerpProperties) {
        if (remote){
            String apiUrl = String.format(BanmaerpURL.banmaerp_orderdetail_GET,id);
            apiUrl = encryptionUtils.rmEmptyParas(apiUrl);
            HttpHeaders httpHeaders = new HttpHeaders();
            BanmaerpSigningVO banmaerpSigningVO = banmaTokenUtils.banmaerpSigningVO(banmaerpProperties,HttpMethod.GET,apiUrl,null);
            httpHeaders = banmaTokenUtils.banmaerpCommonHeaders(httpHeaders,banmaerpProperties,banmaerpSigningVO);
            HttpEntity requestBody = new HttpEntity(null,httpHeaders);
            return httpClients.restTemplateWithBanmaMasterToken(banmaerpProperties)
                    .exchange(BanmaerpURL.banmaerp_gateway.concat(apiUrl), HttpMethod.GET, requestBody, new ParameterizedTypeReference<BanmaErpResponseDTO<OrderDTO>>() {})
                    .getBody().getData();
        }else{
            return orderRepository.findOrderDTOByMaster(new OrderMasterDTO(id));
        }
    }

    /**
     * 查询物流履约
     *
     * @param orderId            订单id
     * @param banmaerpProperties 斑马erp主账号（供应商或者平台）
     * @return
     */
    @Override
    @CheckBanmaerpProperties
    public List<OrderFulfillmentDTO> getFulfillments(String orderId, Boolean remote,BanmaerpProperties banmaerpProperties) {
        List<OrderFulfillmentDTO> orderFulfillmentDTOList;
        if (remote){
            String apiUrl = String.format(BanmaerpURL.banmaerp_orderFulfillments_GET, orderId);
            apiUrl = encryptionUtils.rmEmptyParas(apiUrl);
            HttpHeaders httpHeaders = new HttpHeaders();
            BanmaerpSigningVO banmaerpSigningVO = banmaTokenUtils.banmaerpSigningVO(banmaerpProperties,HttpMethod.GET,apiUrl,null);
            httpHeaders = banmaTokenUtils.banmaerpCommonHeaders(httpHeaders, banmaerpProperties, banmaerpSigningVO);
            HttpEntity requestBody = new HttpEntity(null, httpHeaders);
            orderFulfillmentDTOList = Arrays.stream(httpClients.restTemplateWithBanmaMasterToken(banmaerpProperties)
                            .exchange(BanmaerpURL.banmaerp_gateway.concat(apiUrl), HttpMethod.GET, requestBody, new ParameterizedTypeReference<BanmaErpResponseDTO<OrderFulfillmentDTO>>() {})
                            .getBody()
                            .toDataList(BANMAERP_FIELD_FULFILLMENTS))
                    .map(o -> (OrderFulfillmentDTO) o)
                    .collect(Collectors.toList());
            orderFulfillmentDTOList.forEach(orderFulfillmentDTO -> {
                orderFulfillmentDTO.setOrderId(orderId);
                orderFulfillmentDTO.setBanmaerpProperties(banmaerpProperties);
            });
            //orderFulfillmentDTOList = fulfillmentRepository.saveAll(orderFulfillmentDTOList);
            //fulfillmentRepository.flush();
            orderFulfillmentDTOList =fulfillmentRepository.saveAllAndFlush(orderFulfillmentDTOList);
        }else{
            orderFulfillmentDTOList = fulfillmentRepository.findOrderFulfillmentDTOSByOrderId(orderId);
        }
        return orderFulfillmentDTOList;
    }

    /**
     * 查询物流追踪
     * todo 斑马接口还没实现，无数据，暂时无法测试
     * @param orderId            订单id
     * @param banmaerpProperties 斑马erp主账号（供应商或者平台）
     * @return
     */
    @Override
    @CheckBanmaerpProperties
    public List<OrderTrackingDTO> getTrackings(String orderId,Boolean remote, BanmaerpProperties banmaerpProperties) {
        List<OrderTrackingDTO> orderTrackingDTOList;
        if (remote){
            String apiUrl = String.format(BanmaerpURL.banmaerp_orderTrackings_GET, orderId);
            apiUrl = encryptionUtils.rmEmptyParas(apiUrl);
            HttpHeaders httpHeaders = new HttpHeaders();
            BanmaerpSigningVO banmaerpSigningVO = banmaTokenUtils.banmaerpSigningVO(banmaerpProperties,HttpMethod.GET,apiUrl,null);
            httpHeaders = banmaTokenUtils.banmaerpCommonHeaders(httpHeaders, banmaerpProperties, banmaerpSigningVO);
            HttpEntity requestBody = new HttpEntity(null, httpHeaders);
            orderTrackingDTOList = Arrays.stream(httpClients.restTemplateWithBanmaMasterToken(banmaerpProperties)
                            .exchange(BanmaerpURL.banmaerp_gateway.concat(apiUrl), HttpMethod.GET, requestBody, new ParameterizedTypeReference<BanmaErpResponseDTO<OrderTrackingDTO>>() {})
                            .getBody()
                            .toDataList(BANMAERP_FIELD_TRACKINGS))
                    .map(o -> (OrderTrackingDTO) o)
                    .collect(Collectors.toList());
            orderTrackingDTOList.forEach(orderFulfillmentDTO -> {
                orderFulfillmentDTO.setOrderId(orderId);
                orderFulfillmentDTO.setBanmaerpProperties(banmaerpProperties);
            });
            //orderTrackingDTOList = trackingRepository.saveAll(orderTrackingDTOList);
            //trackingRepository.flush();
            orderTrackingDTOList = trackingRepository.saveAllAndFlush(orderTrackingDTOList);
        }else{
            orderTrackingDTOList = trackingRepository.findOrderTrackingDTOSByOrderId(orderId);
        }
        return orderTrackingDTOList;
    }

    @Override
    @CheckBanmaerpProperties
    public List<OrderDTO> saveAll(Iterable<OrderDTO> orderDTOS, BanmaerpProperties banmaerpProperties) {
        List<OrderDTO> saveOrUpdateOrders = StreamSupport.stream(orderDTOS.spliterator(),true).collect(Collectors.toList());
        List<String> orderMasterId = saveOrUpdateOrders.parallelStream()
                .map(orderDTO -> orderDTO.getMaster().getID())
                .distinct()
                .collect(Collectors.toList());
        List<OrderMasterDTO> existingOrderMasters = new ArrayList<>();
        if (orderMasterId!=null && orderMasterId.size()>0){
            existingOrderMasters = orderMasterRepository.findByMasterIds(orderMasterId)
                    .parallelStream()
                    .collect(Collectors.toList());
        }
        /**
         * prevent duplicate key for orderMasterId
         */
        for (int i=0; i < saveOrUpdateOrders.size(); i++ ){
            OrderDTO orderDTO = saveOrUpdateOrders.get(i);
            for (OrderMasterDTO existedOrderMaster :existingOrderMasters) {
                if (existedOrderMaster.getID().equalsIgnoreCase(orderDTO.getMaster().getID())){
                    orderDTO.setOrderUUID(existedOrderMaster.getOrderDTO().getOrderUUID());
                }
            }
            orderDTO.setBanmaerpProperties(banmaerpProperties);
            saveOrUpdateOrders.set(i,orderDTO);
        }
        List<OrderDTO> orderDTOList =  orderRepository.saveAll(saveOrUpdateOrders);
        orderDTOList.forEach(orderDTO -> {
            orderDTO.getMaster().setOrderDTO(orderDTO);
            orderDTO.getMaster().setBanmaerpProperties(banmaerpProperties);
            orderDTO.getDetails().forEach(orderDetail -> orderDetail.setOrderDTO(orderDTO));
        });
//        orderDTOList = orderRepository.saveAll(orderDTOList);
//        orderRepository.flush();
        orderRepository.saveAllAndFlush(orderDTOList);
        return orderDTOList;
    }

    @Override
    @CheckBanmaerpProperties
    public OrderDTO save(OrderDTO orderDTO, BanmaerpProperties banmaerpProperties) {
        OrderDTO existedOrder = orderRepository.findOrderDTOByMaster(orderDTO.getMaster());
        if (existedOrder!=null){
            orderDTO.setOrderUUID(existedOrder.getOrderUUID());
        }
        orderDTO.setBanmaerpProperties(banmaerpProperties);
        return orderRepository.saveAndFlush(orderDTO);
    }

    @Override
    @CheckBanmaerpProperties
    public OrderStatistics countAmountByStatus(BanmaerpOrderEnums.Status orderStatus, DateTime payTimeStart, DateTime payTimeEnd, BanmaerpProperties banmaerpProperties) {
        if (orderStatus.equals(BanmaerpOrderEnums.Status.All)){
            return countAmount(payTimeStart,payTimeEnd,banmaerpProperties);
        }
        Object result;
        if (payTimeStart!=null || payTimeEnd !=null){
            result = orderRepository.countAmountByStatusAndBanmaerpPropertiesAndPayTime(banmaerpProperties.getX_BANMA_MASTER_APP_ID(),orderStatus.getValue()
                    ,payTimeStart.toString(), payTimeEnd.toString());
        }else {
            result = orderRepository.countAmountByStatusAndBanmaerpProperties(banmaerpProperties.getX_BANMA_MASTER_APP_ID(),orderStatus.getValue());
        }
        Object[] resultset= (Object[])result;
        OrderStatistics orderStatistics = new OrderStatistics();
        orderStatistics.setPay_Amount((BigDecimal)resultset[0]);
        orderStatistics.setPay_amount_usd((BigDecimal) resultset[1]);
        orderStatistics.setOrderQuantities((Long) resultset[2]);
        orderStatistics.setItemQuantities((Long) resultset[3]);
        return orderStatistics;
    }

    @Override
    @CheckBanmaerpProperties
    public OrderStatistics countAmount(DateTime payTimeStart, DateTime payTimeEnd, BanmaerpProperties banmaerpProperties) {
        Object result;
        if (payTimeStart!=null || payTimeEnd !=null){
            result = orderRepository.countAmountByBanmaerpPropertiesAndPayTime(banmaerpProperties.getX_BANMA_MASTER_APP_ID()
                    ,payTimeStart.toString(), payTimeEnd.toString());
        }else{
            result = orderRepository.countAmountByBanmaerpProperties(banmaerpProperties.getX_BANMA_MASTER_APP_ID());
        }
        Object[] resultset= (Object[])result;
        OrderStatistics orderStatistics = new OrderStatistics();
        orderStatistics.setPay_Amount((BigDecimal)resultset[0]);
        orderStatistics.setPay_amount_usd((BigDecimal) resultset[1]);
        orderStatistics.setOrderQuantities(Long.parseLong(resultset[2].toString()));
        orderStatistics.setItemQuantities(Long.parseLong(resultset[3].toString()));
        return orderStatistics;
    }

    @Override
    @CheckBanmaerpProperties
    public OrderStatistics countAmountByStatusAndAccount(BanmaerpOrderEnums.Status orderStatus, DateTime payTimeStart, DateTime payTimeEnd, AccountDTO accountDTO, BanmaerpProperties banmaerpProperties) {
        if (orderStatus.equals(BanmaerpOrderEnums.Status.All)){
            return countAmountByAccount(accountDTO,payTimeStart,payTimeEnd,banmaerpProperties);
        }
        Object result;
        if (payTimeStart!=null || payTimeEnd !=null){
            result =  orderRepository.countAmountByAccountAndStatusAndPayTime(banmaerpProperties.getX_BANMA_MASTER_APP_ID(),orderStatus.getValue()
                    ,accountDTO.getID().toString().concat(DASH),payTimeStart.toString(), payTimeEnd.toString());
        }else{
            result =  orderRepository.countAmountByAccountAndStatus(banmaerpProperties.getX_BANMA_MASTER_APP_ID(),orderStatus.getValue(),accountDTO.getID().toString().concat(DASH));
        }
        Object[] resultset= (Object[])result;
        OrderStatistics orderStatistics = new OrderStatistics();
        orderStatistics.setPay_Amount((BigDecimal)resultset[0]);
        orderStatistics.setPay_amount_usd((BigDecimal) resultset[1]);
        orderStatistics.setOrderQuantities((Long) resultset[2]);
        orderStatistics.setItemQuantities((Long) resultset[3]);
        return orderStatistics;
    }

    @Override
    public OrderStatistics countAmountByAccount(AccountDTO accountDTO, DateTime payTimeStart, DateTime payTimeEnd, BanmaerpProperties banmaerpProperties) {
        Object result;
        if (payTimeStart!=null || payTimeEnd !=null){
            result = orderRepository.countAmountByAccountAndPayTime(banmaerpProperties.getX_BANMA_MASTER_APP_ID(),accountDTO.getID().toString().concat(DASH)
                    ,payTimeStart.toString(), payTimeEnd.toString());
        }else {
            result = orderRepository.countAmountByAccount(banmaerpProperties.getX_BANMA_MASTER_APP_ID(),accountDTO.getID().toString().concat(DASH));

        }
        Object[] resultset= (Object[])result;
        OrderStatistics orderStatistics = new OrderStatistics();
        orderStatistics.setPay_Amount((BigDecimal)resultset[0]);
        orderStatistics.setPay_amount_usd((BigDecimal) resultset[1]);
        orderStatistics.setOrderQuantities((Long) resultset[2]);
        orderStatistics.setItemQuantities((Long) resultset[3]);
        return orderStatistics;
    }

    @Override
    @CheckBanmaerpProperties
    public Long countByStatus(BanmaerpOrderEnums.Status orderStatus,DateTime payTimeStart,DateTime payTimeEnd,BanmaerpProperties banmaerpProperties) {
        if (payTimeStart!=null || payTimeEnd !=null){
            if (orderStatus.equals(BanmaerpOrderEnums.Status.All)){
                return orderRepository.countByBanmaerpPropertiesAndPayTime(banmaerpProperties.getX_BANMA_MASTER_APP_ID(),payTimeStart.toString(), payTimeEnd.toString());
            }
            return orderRepository.countByStatusAndBanmaerpPropertiesAndPayTime(orderStatus.getValue(),banmaerpProperties.getX_BANMA_MASTER_APP_ID(),payTimeStart.toString(), payTimeEnd.toString());
        }else{
            if (orderStatus.equals(BanmaerpOrderEnums.Status.All)){
                return orderRepository.countByBanmaerpProperties(banmaerpProperties.getX_BANMA_MASTER_APP_ID());
            }
            return orderRepository.countByStatusAndBanmaerpProperties(orderStatus.getValue(),banmaerpProperties.getX_BANMA_MASTER_APP_ID());
        }
    }

    @Override
    @CheckBanmaerpProperties
    public Long countByStatusAndAccount(BanmaerpOrderEnums.Status orderStatus, AccountDTO accountDTO,DateTime payTimeStart,DateTime payTimeEnd,BanmaerpProperties banmaerpProperties) {
        if (payTimeStart!=null || payTimeEnd !=null){
            return orderRepository.countByAccountAndStatusAndPayTime(banmaerpProperties.getX_BANMA_MASTER_APP_ID(),orderStatus.getValue(),accountDTO.getID().toString(),payTimeStart.toString(), payTimeEnd.toString());
        }else {
            return orderRepository.countByAccountAndStatus(banmaerpProperties.getX_BANMA_MASTER_APP_ID(),orderStatus.getValue(),accountDTO.getID().toString());
        }
    }

    @Override
    @CheckBanmaerpProperties
    public Long countAll(DateTime payTimeStart,DateTime payTimeEnd,BanmaerpProperties banmaerpProperties) {
        if (payTimeStart!=null || payTimeEnd !=null){
            return orderRepository.countByBanmaerpPropertiesAndPayTime(banmaerpProperties.getX_BANMA_MASTER_APP_ID(),payTimeStart.toString(), payTimeEnd.toString());
        }else {
            return orderRepository.countByBanmaerpProperties(banmaerpProperties.getX_BANMA_MASTER_APP_ID());
        }
    }

    @Override
    @CheckBanmaerpProperties
    public Long countAllByAccount(AccountDTO accountDTO,DateTime payTimeStart,DateTime payTimeEnd,BanmaerpProperties banmaerpProperties) {
        if (payTimeStart!=null || payTimeEnd !=null){
            return orderRepository.countByAccountAndPayTime(accountDTO.getID().toString(),banmaerpProperties.getX_BANMA_MASTER_APP_ID(),payTimeStart.toString(), payTimeEnd.toString());
        }else {
            return orderRepository.countByAccount(accountDTO.getID().toString(),banmaerpProperties.getX_BANMA_MASTER_APP_ID());
        }
    }

    @Override
    public Long countOrderByStore(DateTime payTimeStart,DateTime payTimeEnd,Long storeId) {
        if (payTimeStart!=null || payTimeEnd !=null){
            return orderMasterRepository.countOrderByStoreAndPayTime(storeId,payTimeStart.toString(), payTimeEnd.toString());
        }else{
            return orderMasterRepository.countOrderByStore(storeId);
        }
    }

    private Specification<OrderDTO> createSpecification(Integer accountId,String ids, String storeId, String platform, String status, String payStatus
            , String holdStatus, String refundStatus, String inventoryStatus, String countryCode, DateTime searchTimeStart
            , DateTime searchTimeEnd, String searchTimeField, String sortField, String sortBy,BanmaerpProperties banmaerpProperties) {
        return (root, criteriaQuery, criteriaBuilder) -> {
            List<Predicate> predicateList = new ArrayList<>();

            if (ids != null && ids != "") {
                CriteriaBuilder.In<String> in = criteriaBuilder.in(root.get("master").get("ID"));
                if (ids.contains(",")) {
                    String[] id = ids.split(",");
                    for (int i = 0; i < id.length; i++) {
                        in.value(id[i]);
                    }
                } else {
                    in.value(ids);
                }
                predicateList.add(in);
            }


            if (storeId != null && storeId != "") {
                predicateList.add(criteriaBuilder
                        .equal(root.get("master").get("storeID"),
                                storeId ));
            }

            if (platform != null && platform != "") {
                predicateList.add(criteriaBuilder
                        .equal(root.get("master").get("platform"),
                                BanmaerpPlatformEnums.Platform.valueOf(platform)));
            }

            if (status != null && status != "") {
                predicateList.add(criteriaBuilder
                        .equal(root.get("master").get("status"),
                                BanmaerpOrderEnums.Status.valueof(status)));
            }

            if (payStatus != null && payStatus != "") {
                predicateList.add(criteriaBuilder
                        .equal(root.get("master").get("payStatus"),
                                payStatus));
            }
            if (holdStatus != null && holdStatus != "") {
                predicateList.add(criteriaBuilder
                        .equal(root.get("master").get("holdStatus"),
                                holdStatus));
            }
            if (refundStatus != null && refundStatus != "") {
                predicateList.add(criteriaBuilder
                        .equal(root.get("master").get("refundStatus"),
                                refundStatus));
            }
            if (inventoryStatus != null && inventoryStatus != "") {
                predicateList.add(criteriaBuilder
                        .equal(root.get("master").get("inventoryStatus"),
                                inventoryStatus));
            }
            if (countryCode != null && countryCode != "") {
                predicateList.add(criteriaBuilder
                        .equal(root.get("master").get("countryCode"),
                                countryCode));
            }

            // 开始时间
            if (searchTimeStart != null && searchTimeEnd != null) {
                predicateList.add(criteriaBuilder
                        .between(root.<DateTime>get("master").get(searchTimeField), searchTimeStart, searchTimeEnd));
            }
            if (accountId != null){
                Path<Object> accessUsers = root.get("accessUsers");
                Predicate p1 = criteriaBuilder.like(accessUsers.as(String.class), "%"+accountId+"%");
                predicateList.add(p1);
            }
            predicateList.add(criteriaBuilder.equal(root.get("banmaerpProperties").get("X_BANMA_MASTER_APP_ID"),banmaerpProperties.getX_BANMA_MASTER_APP_ID()));
            Predicate and = criteriaBuilder.and(predicateList.toArray(new Predicate[predicateList.size()]));
            Order order = criteriaBuilder.desc(root.get("master").get("createTime"));
            if (sortBy != null && sortBy != "") {
                if (sortBy.equals("ASC")) {
                    order = criteriaBuilder.asc(root.get("master").get("createTime"));
                }
            }
            if (sortField != null && sortField != "") {
                order = criteriaBuilder.desc(root.get("master").get(sortField));
                if (sortBy.equals("ASC")) {
                    order = criteriaBuilder.asc(root.get("master").get(sortField));
                }
            }
            return criteriaQuery.where(and).orderBy(order).getRestriction();
        };
    }
}
