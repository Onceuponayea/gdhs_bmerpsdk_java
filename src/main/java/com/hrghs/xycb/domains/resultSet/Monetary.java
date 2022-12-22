package com.hrghs.xycb.domains.resultSet;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Monetary {
    BigDecimal Pay_Amount;
    BigDecimal pay_amount_usd;
}
