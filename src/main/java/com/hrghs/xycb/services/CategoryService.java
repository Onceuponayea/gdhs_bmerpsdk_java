package com.hrghs.xycb.services;

import com.hrghs.xycb.domains.BanmaerpProperties;
import com.hrghs.xycb.domains.banmaerpDTO.CategoryDTO;
import java.util.List;
import org.joda.time.DateTime;
import org.joda.time.LocalDateTime;
import org.springframework.data.domain.Page;

public interface CategoryService {

    /**
     * 查询类目列表
     *
     * @param ids                类目ID，用逗号分隔
     * @param name               类目名称
     * @param parentId           类目父级id
     * @param pageNumber         页码（必填）
     * @param searchTimeStart    页大小
     * @param pageSize           页大小
     * @param searchTimeEnd      查询的开始时间
     * @param searchTimeField    查询的结束时间
     * @param sortField          查询的时间字段名，具体值参见:SearchTimeField
     * @param sortBy             排序字段名，具体值参见:SortField
     * @param banmaerpProperties 斑马erp主账号（供应商或者平台）
     * @return 排序方式，具体值参见:SortBy
     */
    Page<CategoryDTO> getCategoryList(
            String ids, String name, String parentId, Integer pageNumber, Integer pageSize,
            DateTime searchTimeStart, DateTime searchTimeEnd, String searchTimeField, String sortField,
            String sortBy, Boolean remote,BanmaerpProperties banmaerpProperties
    );

    Page<CategoryDTO> getCategoryList(Integer pageNumber, Boolean remote, BanmaerpProperties banmaerpProperties);

    Page<CategoryDTO> getCategoryList(Integer pageNumber,Integer pageSize, Boolean remote, BanmaerpProperties banmaerpProperties);

    Page<CategoryDTO> getAndSaveCategoryList(LocalDateTime lastPullTime, Integer pageNumber, Integer pageSize, BanmaerpProperties banmaerpProperties);
    /**
     * 查询单个类目
     *
     * @param id                 类目ID（必填）
     * @param banmaerpProperties 斑马erp主账号（供应商或者平台）
     * @return
     */
    CategoryDTO getCategoryById(String id, Boolean remote, BanmaerpProperties banmaerpProperties);

    List<CategoryDTO> saveCategoryList(List<CategoryDTO> categoryDTOList, BanmaerpProperties banmaerpProperties);

    CategoryDTO saveCategory(CategoryDTO categoryDTO, BanmaerpProperties banmaerpProperties);

    CategoryDTO createCategory(CategoryDTO categoryDTO, BanmaerpProperties banmaerpProperties);
}
