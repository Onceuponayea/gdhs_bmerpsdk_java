package com.hrghs.xycb.domains.resultSet;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.joda.time.DateTime;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderStatistics {
    /** 订单金额 **/
    BigDecimal Pay_Amount;
    /** 订单金额（美元） **/
    BigDecimal pay_amount_usd;
    /** 订单数 **/
    Long orderQuantities;
    /** 商品件数 **/
    Long itemQuantities;
    /** 日期 **/
    DateTime dateTime;
}
