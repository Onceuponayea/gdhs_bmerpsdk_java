package com.hrghs.xycb.repositories;

import com.hrghs.xycb.domains.banmaerpDTO.OrderMasterDTO;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;
@Repository
public interface OrderMasterRepository extends JpaRepository<OrderMasterDTO,String> {
    OrderMasterDTO findByID(String id);


    List<OrderMasterDTO> findByIDIn(List<String> id);
}
