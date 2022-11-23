package com.hrghs.xycb.services.impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.hrghs.xycb.annotations.CheckBanmaerpProperties;
import com.hrghs.xycb.config.BanmaerpProperties;
import com.hrghs.xycb.domains.BanmaerpSigningVO;
import com.hrghs.xycb.domains.BanmaerpURL;
import com.hrghs.xycb.domains.banmaerpDTO.AccountDTO;
import com.hrghs.xycb.domains.banmaerpDTO.DataAccessDTO;
import com.hrghs.xycb.domains.banmaerpDTO.StoreDTO;
import com.hrghs.xycb.domains.common.BanmaErpResponseDTO;
import com.hrghs.xycb.repositories.AccountRepository;
import com.hrghs.xycb.services.AccountService;
import com.hrghs.xycb.utils.BanmaTokenUtils;
import com.hrghs.xycb.utils.EncryptionUtils;
import com.hrghs.xycb.utils.HttpClientsUtils;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private HttpClientsUtils httpClients;
    @Autowired
    private BanmaTokenUtils banmaTokenUtils;
    @Autowired
    private EncryptionUtils encryptionUtils;
    @Autowired
    private AccountRepository accountRepository;

    /**
     * 查询用户列表
     *
     * @param ids             用户 ID，用逗号分隔
     * @param email           用户邮箱
     * @param realName        用户名称
     * @param phone           电话
     * @param pageNumber      页码（必填）
     * @param pageSize        页大小
     * @param searchTimeStart 查询的开始时间
     * @param searchTimeEnd   查询的结束时间
     * @param searchTimeField 查询的时间字段名
     * @param sortField       排序字段名
     * @param sortBy          排序方式
     * @return
     */
    @Override
    public List<AccountDTO> getAccountList(String ids, String email, String realName, String phone, int pageNumber, int pageSize, DateTime searchTimeStart, DateTime searchTimeEnd, String searchTimeField, String sortField, String sortBy, BanmaerpProperties banmaerpProperties) {
        return null;
    }

    /**
     * 查询单个用户
     *
     * @param id 用户id
     * @return
     */
    @Override
    @CheckBanmaerpProperties
    public AccountDTO getAccountById(int id, BanmaerpProperties banmaerpProperties) {

        return null;
    }

    /**
     * 添加子账号
     *
     * @param phone      中国大陆11位手机号
     * @param email      用户邮箱
     * @param realName   用户名称
     * @param department 部门
     * @return
     */
    @Override
    public AccountDTO addAccount(String phone, String email, String realName, String department, BanmaerpProperties banmaerpProperties) {

        return null;
    }

    /**
     * 注销账号
     *
     * @param id 用户ID
     * @return
     */
    @Override
    public Boolean logoutAccount(int id, BanmaerpProperties banmaerpProperties) {
        return null;
    }

    @Override
    public DataAccessDTO getDataAccess(int id) {
        return null;
    }
}
