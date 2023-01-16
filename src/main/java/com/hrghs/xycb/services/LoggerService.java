package com.hrghs.xycb.services;

import com.hrghs.xycb.domains.common.BanmaErpResponseLog;
import org.joda.time.DateTime;
import org.springframework.data.domain.Page;

import java.util.List;

public interface LoggerService {
    public BanmaErpResponseLog saveBanmaErpResponseLog(BanmaErpResponseLog banmaErpResponseLog);

    public Page<BanmaErpResponseLog> getBanmaErpResponseLogs(Long phone,DateTime begin, DateTime end);
}
