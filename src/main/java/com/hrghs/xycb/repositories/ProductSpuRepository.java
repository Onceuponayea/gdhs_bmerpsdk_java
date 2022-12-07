package com.hrghs.xycb.repositories;

import com.hrghs.xycb.domains.banmaerpDTO.ProductSpuDTO;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ProductSpuRepository extends JpaRepository<ProductSpuDTO,Long> {
//    @Query(nativeQuery = true, value = "select * from bmerp_product_spu where spu_id in (:spus)")
//    List<ProductSpuDTO> findBySpuIds(@Param("spus") List<Long> spus);

    List<ProductSpuDTO> findProductSpuDTOSBySPUIDIn(List<Long> spuIds);
}
