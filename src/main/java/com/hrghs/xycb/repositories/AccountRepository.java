package com.hrghs.xycb.repositories;

import com.hrghs.xycb.domains.banmaerpDTO.AccountDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<AccountDTO,Integer> {
}
