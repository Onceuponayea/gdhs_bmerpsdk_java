package com.hrghs.xycb.repositories;

import com.hrghs.xycb.domains.banmaerpDTO.BmerpPropertiesDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BanmaerpPropertiesRepository extends JpaRepository<BmerpPropertiesDTO,String> {
}
