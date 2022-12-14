package com.hrghs.xycb.repositories;

import com.hrghs.xycb.domains.banmaerpDTO.StorageDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface StorageRepository extends JpaRepository<StorageDTO,String>, JpaSpecificationExecutor<StorageDTO> {

}
