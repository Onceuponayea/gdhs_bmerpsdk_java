package com.hrghs.xycb.services.impl;

import com.hrghs.xycb.domains.common.BanmaErpResponseLog;
import com.hrghs.xycb.repositories.BanmaErpResponseLogRepository;
import com.hrghs.xycb.services.LoggerService;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

public class LoggerServiceImpl implements LoggerService {
    @Autowired
    private BanmaErpResponseLogRepository repository;

    @Override
    public BanmaErpResponseLog saveBanmaErpResponseLog(BanmaErpResponseLog banmaErpResponseLog) {
        return repository.saveAndFlush(banmaErpResponseLog);
    }

    @Override
    public Page<BanmaErpResponseLog> getBanmaErpResponseLogs(Long phone,DateTime begin, DateTime end) {
        Specification<BanmaErpResponseLog> specification = (root,criteriaQuery,criteriaBuilder)->{
            Path<DateTime> requestTime = root.get("requestTime");
            Path<Long> requestPhone = root.get("requestPhone");
            List<Predicate> predicates = new ArrayList<>();
            predicates.add(criteriaBuilder.equal(requestPhone,phone));
            if (begin!=null && end!=null){
                predicates.add(criteriaBuilder.between(requestTime,begin,end));
            }
            return criteriaQuery.where(predicates.toArray(new Predicate[predicates.size()]))
                    .orderBy(criteriaBuilder.desc(requestTime)).getRestriction();
        };
        return repository.findAll(specification, PageRequest.of(0,100));
    }
}
