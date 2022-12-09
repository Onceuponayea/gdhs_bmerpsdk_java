package com.hrghs.xycb.repositories;

import com.hrghs.xycb.domains.banmaerpDTO.ProductDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import java.util.UUID;

@Repository
public interface ProductRepository extends JpaRepository<ProductDTO, UUID>, JpaSpecificationExecutor<ProductDTO> {

}
