package com.hrghs.xycb.repositories;

import com.hrghs.xycb.domains.banmaerpDTO.ProductSkusCombineDataDTO;
import com.hrghs.xycb.domains.banmaerpDTO.ProductSkusDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface ProductSkusCombineDataRepository extends JpaRepository<ProductSkusCombineDataDTO,String>, JpaSpecificationExecutor<ProductSkusCombineDataDTO> {
    @Modifying
    @Transactional
    public void deleteByProductSkusDTOIn(List<ProductSkusDTO> productSkusDTO);
}