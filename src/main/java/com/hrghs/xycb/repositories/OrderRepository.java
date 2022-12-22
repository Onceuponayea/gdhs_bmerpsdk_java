package com.hrghs.xycb.repositories;

import com.hrghs.xycb.domains.BanmaerpProperties;
import com.hrghs.xycb.domains.banmaerpDTO.*;
import com.hrghs.xycb.domains.enums.BanmaerpOrderEnums;
import com.hrghs.xycb.domains.resultSet.Monetary;
import org.springframework.data.jpa.repository.JpaRepository;;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<OrderDTO,String>, JpaSpecificationExecutor<OrderDTO> {

    OrderDTO findOrderDTOByMaster(OrderMasterDTO orderMasterDTO);

    /**
     * 根据订单状态统计金额（人民币和美金）
     * @param appID
     * @param orderStatus
     * @return
     */
    @Query(nativeQuery = true, value = "select sum(bmerp_order_master.Pay_Amount),sum(bmerp_order_master.pay_amount_usd) from bmerp_order left join bmerp_order_master" +
            " on bmerp_order.order_UUID=bmerp_order_master.order_uuid where bmerp_order.banma_master_app_id =:appID and bmerp_order_master.status =:orderStatus")
    Object countAmountByStatusAndBanmaerpProperties(String appID,String orderStatus);

    /**
     * 根据斑马的主账号id 统计订单金额
     * @param appID
     * @return
     */
    @Query(nativeQuery = true,value = "select sum(bmerp_order_master.Pay_Amount),sum(bmerp_order_master.pay_amount_usd) from bmerp_order left join bmerp_order_master" +
            " on bmerp_order.order_UUID=bmerp_order_master.order_uuid where bmerp_order.banma_master_app_id =:appID")
    Object countAmountByBanmaerpProperties(String appID);

    /**
     * 根据订单状态和子账号统计订单金额
     * @param appID
     * @param orderStatus
     * @param accountId
     * @return
     */
    @Query(nativeQuery = true,value = "select sum(bmerp_order_master.Pay_Amount),sum(bmerp_order_master.pay_amount_usd)  from bmerp_order left join bmerp_order_master" +
            " on bmerp_order.order_UUID=bmerp_order_master.order_uuid where bmerp_order.banma_master_app_id =:appID and bmerp_order_master.status =:orderStatus " +
            "and( bmerp_order.banma_account_id like %:accountId% )")
    Object countAmountByAccountAndStatus(String appID,String orderStatus,String accountId);

    /**
     * 根据子账号统计订单金额
     * @param appID
     * @param accountId
     * @return
     */
    @Query(nativeQuery = true,value = "select sum(bmerp_order_master.Pay_Amount),sum(bmerp_order_master.pay_amount_usd)  from bmerp_order left join bmerp_order_master" +
            " on bmerp_order.order_UUID=bmerp_order_master.order_uuid where bmerp_order.banma_master_app_id =:appID and(bmerp_order.banma_account_id like %:accountId%)")
    Object countAmountByAccount(String appID, String accountId);

    @Query(nativeQuery = true, value = "select count(distinct order_masterId) from bmerp_order where banma_master_app_id =:appID")
    Long countByBanmaerpProperties(String appID);

    @Query(nativeQuery = true, value = "select count(distinct id) from bmerp_order_master where banma_master_app_id =:appID and status =:orderstatus")
    Long countByStatusAndBanmaerpProperties(String appID,String orderstatus);

    @Query(nativeQuery = true, value = "select count(distinct order_masterId) from bmerp_order where banma_master_app_id =:appID and bmerp_order.banma_account_id like %:accountId% ")
    Long countByAccount(String appID,String accountId);

    @Query(nativeQuery = true, value = "select count(distinct bmerp_order.order_masterId) from bmerp_order left join bmerp_order_master on bmerp_order.order_UUID=bmerp_order_master.order_uuid\n" +
            "where bmerp_order.banma_master_app_id =:appID and bmerp_order.banma_account_id like %:accountId% and bmerp_order_master.status =:orderstatus")
    Long countByAccountAndStatus(String appID,String orderstatus,String accountId);

}
