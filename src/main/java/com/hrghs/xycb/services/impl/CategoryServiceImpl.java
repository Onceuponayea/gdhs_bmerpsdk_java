package com.hrghs.xycb.services.impl;

import com.hrghs.xycb.config.BanmaerpProperties;
import com.hrghs.xycb.utils.HttpClientsUtils;
import com.hrghs.xycb.domains.common.BanmaErpResponseDTO;
import com.hrghs.xycb.services.CategoryService;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private HttpClientsUtils httpClients;
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
     * @return 排序方式，具体值参见:SortBy
     */
    @Override
    public BanmaErpResponseDTO getCategoryList(String ids, String name, String parentId, int pageNumber, DateTime SearchTimeStart,
                                               DateTime SearchTimeEnd, String searchTimeField, String sortField, String sortBy,
                                               BanmaerpProperties banmaerpProperties) {

        return null;
    }

    /**
     *
     * @param idv
     * @param banmaerpProperties
     * @return
     */
    @Override
    public BanmaErpResponseDTO getCategoryById(String idv,BanmaerpProperties banmaerpProperties) {

        return null;
    }
}
