package com.hrghs.xycb.repositories;

import com.hrghs.xycb.domains.banmaerpDTO.DataAccessDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DataAccessRepository extends JpaRepository<DataAccessDTO,String> {
    List<DataAccessDTO> findByAccountDTOID(Integer accountId);
}
