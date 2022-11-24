package com.hrghs.xycb.repositories;

import com.hrghs.xycb.domains.banmaerpDTO.StoreDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StoreRepository extends JpaRepository<StoreDTO,String> {
}
