package com.hrghs.xycb.repositories;

import com.hrghs.xycb.domains.banmaerpDTO.ProductTagsDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductTagsRepository extends JpaRepository<ProductTagsDTO,String>, JpaSpecificationExecutor<ProductTagsDTO> {
}
