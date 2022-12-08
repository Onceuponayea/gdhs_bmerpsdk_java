package com.hrghs.xycb.repositories;


import com.hrghs.xycb.domains.banmaerpDTO.ProductSuppliersInfoDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductSuppliersRepository extends JpaRepository<ProductSuppliersInfoDTO,String>, JpaSpecificationExecutor<ProductSuppliersInfoDTO> {
}
