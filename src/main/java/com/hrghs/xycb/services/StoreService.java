package com.hrghs.xycb.services;

import com.hrghs.xycb.domains.BanmaerpProperties;
import com.hrghs.xycb.domains.banmaerpDTO.StoreDTO;
import com.hrghs.xycb.domains.common.BanmaErpResponseDTO;
import com.hrghs.xycb.domains.enums.BanmaerpPlatformEnums;
import org.joda.time.DateTime;
import org.springframework.data.domain.Page;
import org.springframework.lang.Nullable;
import reactor.core.publisher.Mono;

import java.util.List;

public interface StoreService {

    /**
     * 查询店铺列表
     *
     * @param ids                店铺ID，用逗号分隔
     * @param name               店铺名称
     * @param platform           平台，具体值参见:Platform
     * @param pageNumber         页码（必填）
     * @param pageSize           页大小
     * @param searchTimeStart    查询的开始时间
     * @param searchTimeEnd      查询的结束时间
     * @param searchTimeField    查询的时间字段名，具体值参见:SearchTimeField
     * @param sortField          排序字段名，具体值参见:SortField
     * @param sortBy             排序方式，具体值参见:SortBy
     * @param banmaerpProperties 斑马erp主账号（供应商或者平台）
     * @return
     */
    Mono<BanmaErpResponseDTO<List<StoreDTO>>> getStoretListMono(
            @Nullable String ids,
            @Nullable String name,
            @Nullable BanmaerpPlatformEnums.Platform platform,
            Integer pageNumber,
            @Nullable Integer pageSize,
            @Nullable DateTime searchTimeStart,
            @Nullable DateTime searchTimeEnd,
            @Nullable String searchTimeField,
            @Nullable String sortField,
            @Nullable String sortBy,
            Boolean remote,
            BanmaerpProperties banmaerpProperties
    );

    Page<StoreDTO> getStoretList(@Nullable String ids, @Nullable String name,
                                 @Nullable BanmaerpPlatformEnums.Platform platform, Integer pageNumber, @Nullable Integer pageSize, @Nullable DateTime searchTimeStart,
                                 @Nullable DateTime searchTimeEnd, @Nullable String searchTimeField, @Nullable String sortField, @Nullable String sortBy,
                                 Boolean remote, BanmaerpProperties banmaerpProperties);

    Page<StoreDTO> getStoretList(Integer pageNumber,Boolean remote, BanmaerpProperties banmaerpProperties);

    Page<StoreDTO> getStoretList(Integer pageNumber, Integer pageSize,Boolean remote, BanmaerpProperties banmaerpProperties);

    List<StoreDTO> getAndSaveStoretList(Integer pageNumber, Integer pageSize,BanmaerpProperties banmaerpProperties);
    /**
     * 查询单个店铺
     *
     * @param storeId              店铺ID（必填）
     * @param banmaerpProperties 斑马erp主账号（供应商或者平台）
     * @return
     */
    StoreDTO getStoreById(String storeId, Boolean remote,BanmaerpProperties banmaerpProperties);

    List<StoreDTO> saveStoreList(List<StoreDTO> storeDTOList, BanmaerpProperties banmaerpProperties);

    StoreDTO saveStore(StoreDTO storeDTO, BanmaerpProperties banmaerpProperties);
}
