package com.hrghs.xycb.repositories;

import com.hrghs.xycb.domains.banmaerpDTO.ProductSkusDTO;
import com.hrghs.xycb.domains.banmaerpDTO.ProductSkusOptionsDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface ProductSkusOptionsRepository extends JpaRepository<ProductSkusOptionsDTO,String>, JpaSpecificationExecutor<ProductSkusOptionsDTO> {
    @Modifying
    @Transactional
    public void deleteByProductSkusDTOIn(List<ProductSkusDTO> productSkusDTO);
}