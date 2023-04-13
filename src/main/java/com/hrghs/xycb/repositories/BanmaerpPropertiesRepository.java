package com.hrghs.xycb.repositories;

import com.hrghs.xycb.domains.BanmaerpProperties;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BanmaerpPropertiesRepository extends JpaRepository<BanmaerpProperties,String> {
    //@Query(nativeQuery = true, value = "select * from bmerp_properties where X_BANMA_MASTER_APP_ACCOUNT = :account")
    @Query(nativeQuery = true,value = "select bmerp_properties.* from bmerp_properties left join bmerp_account on " +
            "bmerp_properties.X_BANMA_MASTER_APP_ID=bmerp_account.banma_master_app_id where bmerp_account.phone = :account and bmerp_account.state !='离职' ")
    BanmaerpProperties findBanmaerpPropertiesByX_BANMA_MASTER_APP_ACCOUNT(@Param("account") String account);

    @Query(nativeQuery = true,value = "select bmerp_properties.* from bmerp_properties left join bmerp_account on " +
            "bmerp_properties.X_BANMA_MASTER_APP_ID=bmerp_account.banma_master_app_id where bmerp_account.phone = :account and bmerp_account.state = :status")
    BanmaerpProperties findBanmaerpPropertiesByX_BANMA_MASTER_APP_ACCOUNTAndStatus(@Param("account") String account,@Param("status")String status);
}
