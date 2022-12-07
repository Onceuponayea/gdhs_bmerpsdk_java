package com.hrghs.xycb.repositories;

import com.hrghs.xycb.domains.banmaerpDTO.OrderFulfillmentDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderFulfillmentRepository extends JpaRepository<OrderFulfillmentDTO,String> {
    List<OrderFulfillmentDTO> findOrderFulfillmentDTOSByOrderId(String orderId);
}
