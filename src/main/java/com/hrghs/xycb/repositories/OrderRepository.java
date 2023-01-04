package com.hrghs.xycb.repositories;

import com.hrghs.xycb.domains.banmaerpDTO.*;
import org.springframework.data.jpa.repository.JpaRepository;;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<OrderDTO,String>, JpaSpecificationExecutor<OrderDTO> {

    OrderDTO findOrderDTOByMaster(OrderMasterDTO orderMasterDTO);

    /**
     * 根据订单状态统计金额（人民币和美金）
     * @param appID
     * @param orderStatus
     * @return
     */
    @Query(nativeQuery = true, value = "select sum(bmerp_order_master.Pay_Amount),sum(bmerp_order_master.pay_amount_usd),count(distinct bmerp_order.order_masterId),sum(bmerp_order_master.quantity)" +
            " from bmerp_order left join bmerp_order_master on bmerp_order.order_UUID=bmerp_order_master.order_uuid where bmerp_order.banma_master_app_id =:appID and bmerp_order_master.status =:orderStatus")
    Object countAmountByStatusAndBanmaerpProperties(String appID,String orderStatus);

    @Query(nativeQuery = true, value = "select sum(bmerp_order_master.Pay_Amount),sum(bmerp_order_master.pay_amount_usd),count(distinct bmerp_order.order_masterId),sum(bmerp_order_master.quantity)" +
            " from bmerp_order left join bmerp_order_master on bmerp_order.order_UUID=bmerp_order_master.order_uuid where bmerp_order.banma_master_app_id =:appID and bmerp_order_master.status =:orderStatus " +
            "and bmerp_order_master.pay_time >:payTimeStart and bmerp_order_master.pay_time <:payTimeEnd")
    Object countAmountByStatusAndBanmaerpPropertiesAndPayTime(String appID,String orderStatus, String payTimeStart, String payTimeEnd);
    /**
     * 根据斑马的主账号id 统计订单金额
     * @param appID
     * @return
     */
    @Query(nativeQuery = true,value = "select sum(bmerp_order_master.Pay_Amount),sum(bmerp_order_master.pay_amount_usd),count(distinct bmerp_order.order_masterId),sum(bmerp_order_master.quantity)" +
            " from bmerp_order left join bmerp_order_master on bmerp_order.order_UUID=bmerp_order_master.order_uuid where bmerp_order.banma_master_app_id =:appID")
    Object countAmountByBanmaerpProperties(String appID);

    @Query(nativeQuery = true,value = "select sum(bmerp_order_master.Pay_Amount),sum(bmerp_order_master.pay_amount_usd),count(distinct bmerp_order.order_masterId),sum(bmerp_order_master.quantity)" +
            " from bmerp_order left join bmerp_order_master on bmerp_order.order_UUID=bmerp_order_master.order_uuid where bmerp_order.banma_master_app_id =:appID " +
            "and bmerp_order_master.pay_time >:payTimeStart and bmerp_order_master.pay_time <:payTimeEnd")
    Object countAmountByBanmaerpPropertiesAndPayTime(String appID, String payTimeStart, String payTimeEnd);

    /**
     * 根据订单状态和子账号统计订单金额
     * @param appID
     * @param orderStatus
     * @param accountId
     * @return
     */
    @Query(nativeQuery = true,value = "select sum(bmerp_order_master.Pay_Amount),sum(bmerp_order_master.pay_amount_usd),count(distinct bmerp_order.order_masterId),sum(bmerp_order_master.quantity)" +
            "  from bmerp_order left join bmerp_order_master on bmerp_order.order_UUID=bmerp_order_master.order_uuid where bmerp_order.banma_master_app_id =:appID and bmerp_order_master.status =:orderStatus " +
            "and( bmerp_order.banma_account_id like %:accountId% )")
    Object countAmountByAccountAndStatus(String appID,String orderStatus,String accountId);

    @Query(nativeQuery = true,value = "select sum(bmerp_order_master.Pay_Amount),sum(bmerp_order_master.pay_amount_usd),count(distinct bmerp_order.order_masterId),sum(bmerp_order_master.quantity)" +
            "  from bmerp_order left join bmerp_order_master on bmerp_order.order_UUID=bmerp_order_master.order_uuid where bmerp_order.banma_master_app_id =:appID and bmerp_order_master.status =:orderStatus " +
            "and( bmerp_order.banma_account_id like %:accountId% ) and bmerp_order_master.pay_time >:payTimeStart and bmerp_order_master.pay_time <:payTimeEnd")
    Object countAmountByAccountAndStatusAndPayTime(String appID,String orderStatus,String accountId, String payTimeStart, String payTimeEnd);

    /**
     * 根据子账号统计订单金额
     * @param appID
     * @param accountId
     * @return
     */
    @Query(nativeQuery = true,value = "select sum(bmerp_order_master.Pay_Amount),sum(bmerp_order_master.pay_amount_usd),count(distinct bmerp_order.order_masterId),sum(bmerp_order_master.quantity)" +
            " from bmerp_order left join bmerp_order_master on bmerp_order.order_UUID=bmerp_order_master.order_uuid where bmerp_order.banma_master_app_id =:appID and(bmerp_order.banma_account_id like %:accountId%)")
    Object countAmountByAccount(String appID, String accountId);

    @Query(nativeQuery = true,value = "select sum(bmerp_order_master.Pay_Amount),sum(bmerp_order_master.pay_amount_usd),count(distinct bmerp_order.order_masterId),sum(bmerp_order_master.quantity)" +
            "  from bmerp_order left join bmerp_order_master on bmerp_order.order_UUID=bmerp_order_master.order_uuid where bmerp_order.banma_master_app_id =:appID" +
            " and(bmerp_order.banma_account_id like %:accountId%) and bmerp_order_master.pay_time >:payTimeStart and bmerp_order_master.pay_time <:payTimeEnd")
    Object countAmountByAccountAndPayTime(String appID, String accountId, String payTimeStart, String payTimeEnd);

    @Query(nativeQuery = true, value = "select count(distinct order_masterId) from bmerp_order where banma_master_app_id =:appID")
    Long countByBanmaerpProperties(String appID);

    @Query(nativeQuery = true, value = "select count(distinct bmerp_order.order_masterId) from bmerp_order left join bmerp_order_master on bmerp_order.order_UUID=bmerp_order_master.order_uuid " +
            " where bmerp_order.banma_master_app_id =:appID and bmerp_order_master.pay_time >:payTimeStart and bmerp_order_master.pay_time <:payTimeEnd")
    Long countByBanmaerpPropertiesAndPayTime(String appID, String payTimeStart, String payTimeEnd );

    @Query(nativeQuery = true, value = "select count(distinct id) from bmerp_order_master where banma_master_app_id =:appID and status =:orderstatus")
    Long countByStatusAndBanmaerpProperties(String appID,String orderstatus);

    @Query(nativeQuery = true, value = "select count(distinct id) from bmerp_order_master where banma_master_app_id =:appID and status =:orderstatus and pay_time >:payTimeStart and pay_time <:payTimeEnd")
    Long countByStatusAndBanmaerpPropertiesAndPayTime(String appID,String orderstatus, String payTimeStart, String payTimeEnd);

    @Query(nativeQuery = true, value = "select count(distinct order_masterId) from bmerp_order where banma_master_app_id =:appID and bmerp_order.banma_account_id like %:accountId% ")
    Long countByAccount(String appID,String accountId);

    @Query(nativeQuery = true, value = "select count(distinct bmerp_order.order_masterId) from bmerp_order left join bmerp_order_master on bmerp_order.order_UUID=bmerp_order_master.order_uuid " +
            " where bmerp_order.banma_master_app_id =:appID and bmerp_order.banma_account_id like %:accountId% and bmerp_order_master.pay_time >:payTimeStart and bmerp_order_master.pay_time <:payTimeEnd ")
    Long countByAccountAndPayTime(String appID,String accountId, String payTimeStart, String payTimeEnd );

    @Query(nativeQuery = true, value = "select count(distinct bmerp_order.order_masterId) from bmerp_order left join bmerp_order_master on bmerp_order.order_UUID=bmerp_order_master.order_uuid " +
            "where bmerp_order.banma_master_app_id =:appID and bmerp_order.banma_account_id like %:accountId% and bmerp_order_master.status =:orderstatus")
    Long countByAccountAndStatus(String appID,String orderstatus,String accountId);

    @Query(nativeQuery = true, value = "select count(distinct bmerp_order.order_masterId) from bmerp_order left join bmerp_order_master on bmerp_order.order_UUID=bmerp_order_master.order_uuid " +
            "where bmerp_order.banma_master_app_id =:appID and bmerp_order.banma_account_id like %:accountId% and bmerp_order_master.status =:orderstatus and bmerp_order_master.pay_time >:payTimeStart and bmerp_order_master.pay_time <:payTimeEnd ")
    Long countByAccountAndStatusAndPayTime(String appID,String orderstatus,String accountId, String payTimeStart, String payTimeEnd);
}
