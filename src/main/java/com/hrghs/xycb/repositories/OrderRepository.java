package com.hrghs.xycb.repositories;

import com.hrghs.xycb.domains.banmaerpDTO.OrderDTO;
import com.hrghs.xycb.domains.banmaerpDTO.OrderMasterDTO;
import org.springframework.data.jpa.repository.JpaRepository;;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<OrderDTO,String>, JpaSpecificationExecutor<OrderDTO> {

    OrderDTO findOrderDTOByMaster(OrderMasterDTO orderMasterDTO);


}
