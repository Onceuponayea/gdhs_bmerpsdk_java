package com.hrghs.xycb.repositories;


import com.hrghs.xycb.domains.banmaerpDTO.ProductSuppliersDTO;
import com.hrghs.xycb.domains.banmaerpDTO.ProductSuppliersInfoDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductSuppliersRepository extends JpaRepository<ProductSuppliersDTO,String>, JpaSpecificationExecutor<ProductSuppliersDTO> {

}
