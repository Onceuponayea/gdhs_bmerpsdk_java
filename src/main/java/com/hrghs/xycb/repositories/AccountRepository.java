package com.hrghs.xycb.repositories;

import com.hrghs.xycb.domains.banmaerpDTO.AccountDTO;
import com.hrghs.xycb.domains.enums.BanmaerpAccountEnums;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<AccountDTO,Integer>, JpaSpecificationExecutor<AccountDTO> {

    List<AccountDTO> findAllByState(BanmaerpAccountEnums.UserState userState);
}
