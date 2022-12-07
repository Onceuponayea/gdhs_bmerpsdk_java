package com.hrghs.xycb.services;

import com.hrghs.xycb.domains.BanmaerpProperties;
import com.hrghs.xycb.domains.banmaerpDTO.AccountDTO;
import com.hrghs.xycb.domains.banmaerpDTO.DataAccessDTO;
import com.hrghs.xycb.domains.banmaerpDTO.TokenResponseDTO;
import com.hrghs.xycb.domains.common.BanmaErpResponseDTO;
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
            String ids, String email, String realName, String phone,
            Integer pageNumber, Integer pageSize, DateTime searchTimeStart,
            DateTime searchTimeEnd, String searchTimeField, String sortField,
            String sortBy,Boolean remote,
            BanmaerpProperties banmaerpProperties
    );

    List<AccountDTO> getAccountList(Integer pageNumber,Boolean remote, BanmaerpProperties banmaerpProperties);

    List<AccountDTO> getAccountList(Integer pageNumber,Integer pageSize,Boolean remote, BanmaerpProperties banmaerpProperties);

    List<AccountDTO> getAndSaveAccountList(Integer pageNumber,Integer pageSize, BanmaerpProperties banmaerpProperties);
    /**
     * 查询单个用户
     *
     * @param id                 用户id
     * @param banmaerpProperties 斑马erp主账号（供应商或者平台）
     * @return
     */
    AccountDTO getAccountById(Integer id,Boolean remote, BanmaerpProperties banmaerpProperties);

    /**
     * 添加子账号
     *
     * @param phone              中国大陆11位手机号
     * @param email              用户邮箱
     * @param realName           用户名称
     * @param department         部门
     * @param useVirtual         是否用虚拟账号，true则会生成虚拟手机号
     * @param banmaerpProperties 斑马erp主账号（供应商或者平台）
     * @return
     */
    AccountDTO addAccount(String phone, String email, String realName, String department,
       Boolean useVirtual, BanmaerpProperties banmaerpProperties
    );

    /**
     * 注销账号
     *
     * @param id                 用户ID
     * @param banmaerpProperties 斑马erp主账号（供应商或者平台）
     * @return
     */
    BanmaErpResponseDTO<Boolean> logoutAccount(Integer id, BanmaerpProperties banmaerpProperties);

    /**
     * 查询用户店铺权限
     *
     * @param id 用户id
     * @return
     */
    DataAccessDTO getDataAccess(Integer id, BanmaerpProperties banmaerpProperties);

    List<AccountDTO> saveAccountList(List<AccountDTO> accountDTOList, BanmaerpProperties banmaerpProperties);

    AccountDTO saveAccount(AccountDTO accountDTO, BanmaerpProperties banmaerpProperties);

    /**
     * 获取子账号ac token,斑马暂时没有提供
     * @param banmaerpProperties
     * @return
     */
    @Deprecated
    TokenResponseDTO getSubAccountAccessToken(BanmaerpProperties banmaerpProperties);
}
