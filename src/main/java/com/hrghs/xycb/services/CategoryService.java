package com.hrghs.xycb.services;

import com.hrghs.xycb.config.BanmaerpProperties;
import com.hrghs.xycb.domains.common.BanmaErpResponseDTO;
import org.joda.time.DateTime;

public interface CategoryService {

    /**
     * 查询类目列表
     * @param ids 类目ID，用逗号分隔
     * @param name 类目名称
     * @param parentId 	类目父级id
     * @param pageNumber 	页码（必填）
     * @param SearchTimeStart 页大小
     * @param SearchTimeEnd 	查询的开始时间
     * @param searchTimeField 	查询的结束时间
     * @param sortField 查询的时间字段名，具体值参见:SearchTimeField
     * @param sortBy 排序字段名，具体值参见:SortField
     * @param banmaerpProperties 斑马erp主账号（供应商或者平台）
     * @return 排序方式，具体值参见:SortBy
     */
    BanmaErpResponseDTO getCategoryList(
            String ids,
            String name,
            String parentId,
            int pageNumber,
            DateTime SearchTimeStart,
            DateTime SearchTimeEnd,
            String searchTimeField,
            String sortField,
            String sortBy,
            BanmaerpProperties banmaerpProperties
    );

    /**
     * 查询单个类目
     * @param id 	类目ID（必填）
     * @param banmaerpProperties 斑马erp主账号（供应商或者平台）
     * @return
     */
    BanmaErpResponseDTO getCategoryById(String id,
                                        BanmaerpProperties banmaerpProperties);
}
