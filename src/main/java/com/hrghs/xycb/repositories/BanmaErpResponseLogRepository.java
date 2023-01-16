package com.hrghs.xycb.repositories;

import com.hrghs.xycb.domains.common.BanmaErpResponseLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface BanmaErpResponseLogRepository extends JpaRepository<BanmaErpResponseLog,String>, JpaSpecificationExecutor<BanmaErpResponseLog> {
}
