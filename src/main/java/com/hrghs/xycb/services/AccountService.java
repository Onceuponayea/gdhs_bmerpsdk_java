package com.hrghs.xycb.services;

import com.hrghs.xycb.config.BanmaerpProperties;
import com.hrghs.xycb.domains.banmaerpDTO.AccountDTO;
import com.hrghs.xycb.domains.banmaerpDTO.DataAccessDTO;
import org.joda.time.DateTime;

import java.util.List;

public interface AccountService {

    /**
     * 查询用户列表
     *
     * @param ids                用户 ID，用逗号分隔
     * @param email              用户邮箱
     * @param realName           用户名称
     * @param phone              电话
     * @param pageNumber         页码（必填）
     * @param pageSize           页大小
     * @param searchTimeStart    查询的开始时间
     * @param searchTimeEnd      查询的结束时间
     * @param searchTimeField    查询的时间字段名
     * @param sortField          排序字段名
     * @param sortBy             排序方式
     * @param banmaerpProperties 斑马erp主账号（供应商或者平台）
     * @return
     */
    List<AccountDTO> getAccountList(
            String ids,
            String email,
            String realName,
            String phone,
            int pageNumber,
            int pageSize,
            DateTime searchTimeStart,
            DateTime searchTimeEnd,
            String searchTimeField,
            String sortField,
            String sortBy,
            BanmaerpProperties banmaerpProperties
    );

    /**
     * 查询单个用户
     *
     * @param id 用户id
     * @param banmaerpProperties 斑马erp主账号（供应商或者平台）
     * @return
     */
    AccountDTO getAccountById(int id, BanmaerpProperties banmaerpProperties);

    /**
     * 添加子账号
     *
     * @param phone      中国大陆11位手机号
     * @param email      用户邮箱
     * @param realName   用户名称
     * @param department 部门
     * @param banmaerpProperties 斑马erp主账号（供应商或者平台）
     * @return
     */
    AccountDTO addAccount(
            String phone,
            String email,
            String realName,
            String department,
            BanmaerpProperties banmaerpProperties
    );

    /**
     * 注销账号
     *
     * @param id 用户ID
     * @param banmaerpProperties 斑马erp主账号（供应商或者平台）
     * @return
     */
    Boolean logoutAccount(int id, BanmaerpProperties banmaerpProperties);

    /**
     * 查询用户店铺权限
     * @param id 用户id
     * @return
     */
    DataAccessDTO getDataAccess(int id);
}
