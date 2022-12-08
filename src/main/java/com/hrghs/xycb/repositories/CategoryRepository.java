package com.hrghs.xycb.repositories;

import com.hrghs.xycb.domains.banmaerpDTO.CategoryDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryDTO,String>, JpaSpecificationExecutor<CategoryDTO> {

}
