package com.hrghs.xycb.repositories;

import com.hrghs.xycb.domains.BanmaerpProperties;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BanmaerpPropertiesRepository extends JpaRepository<BanmaerpProperties,String> {
    @Query(nativeQuery = true, value = "select * from bmerp_properties where X_BANMA_MASTER_APP_ACCOUNT = :account")
    BanmaerpProperties findBanmaerpPropertiesByX_BANMA_MASTER_APP_ACCOUNT(@Param("account") String account);
}
