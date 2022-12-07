package com.hrghs.xycb.repositories;

import com.hrghs.xycb.domains.banmaerpDTO.OrderMasterDTO;
import com.hrghs.xycb.domains.banmaerpDTO.ProductSpuDTO;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;
@Repository
public interface OrderMasterRepository extends JpaRepository<OrderMasterDTO,String> {
    @Query(nativeQuery = true, value = "select * from bmerp_order_master where id in (:orderMasterId)")
    List<OrderMasterDTO> findByMasterIds(@Param("orderMasterId") List<String> orderMasterId);
}
