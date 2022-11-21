package com.hrghs.xycb.aops;

import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * @@apiNote 对所有入参的日期进行统一格式化成yyyy-MM-dd'T'HH:mm:ssZ
 */
@Component
@Aspect
public class DateTimeUnifiedFormatter {
}
