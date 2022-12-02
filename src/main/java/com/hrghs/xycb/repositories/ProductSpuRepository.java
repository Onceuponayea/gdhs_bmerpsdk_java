package com.hrghs.xycb.repositories;

import com.hrghs.xycb.domains.banmaerpDTO.OrderMasterDTO;
import com.hrghs.xycb.domains.banmaerpDTO.ProductSpuDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductSpuRepository extends JpaRepository<ProductSpuDTO,Long> {

    ProductSpuDTO findBySPUID(Long spuId);

    List<ProductSpuDTO> findBySPUIDIn(List<Long> id);
}
