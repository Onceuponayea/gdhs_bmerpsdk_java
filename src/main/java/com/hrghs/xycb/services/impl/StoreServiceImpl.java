package com.hrghs.xycb.services.impl;

import com.hrghs.xycb.domains.common.BanmaErpResponseDTO;
import com.hrghs.xycb.services.StoreService;
import org.joda.time.DateTime;

public class StoreServiceImpl implements StoreService {

    /**
     * 查询店铺列表
     * @param ids   店铺ID，用逗号分隔
     * @param name  	店铺名称
     * @param platform  平台，具体值参见:Platform
     * @param pageNumber    页码（必填）
     * @param pageSize  	页大小
     * @param searchTimeStart   查询的开始时间
     * @param searchTimeEnd     查询的结束时间
     * @param searchTimeField   查询的时间字段名，具体值参见:SearchTimeField
     * @param sortField     	排序字段名，具体值参见:SortField
     * @param sortBy        排序方式，具体值参见:SortBy
     * @return
     */
    @Override
    public BanmaErpResponseDTO getStoretList(String ids, String name, String platform, int pageNumber, int pageSize, DateTime searchTimeStart, DateTime searchTimeEnd, String searchTimeField, String sortField, String sortBy) {
        return null;
    }

    /**
     * 查询单个店铺
     * @param spuId 店铺ID（必填）
     * @return
     */
    @Override
    public BanmaErpResponseDTO getStoreById(String spuId) {
        return null;
    }
}
