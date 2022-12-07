package com.hrghs.xycb.repositories;

import com.hrghs.xycb.domains.banmaerpDTO.OrderTrackingDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderTrackingRepository extends JpaRepository<OrderTrackingDTO,String> {
    //@Query();
    List<OrderTrackingDTO> findOrderTrackingDTOSByOrderId(String orderId);
}
