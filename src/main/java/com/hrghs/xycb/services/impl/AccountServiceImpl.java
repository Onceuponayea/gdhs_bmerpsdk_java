package com.hrghs.xycb.services.impl;


import cn.hutool.json.JSONUtil;
import com.alibaba.druid.support.json.JSONUtils;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.joda.JodaModule;
import com.fasterxml.jackson.core.JsonProcessingException;
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
import org.h2.util.json.JSONObject;
import org.h2.util.json.JSONString;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static com.hrghs.xycb.domains.Constants.BANMAERP_FIELD_ACCOUNTS;


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
    @CheckBanmaerpProperties
    public List<AccountDTO> getAccountList(String ids, String email, String realName, String phone, int pageNumber, int pageSize, DateTime searchTimeStart, DateTime searchTimeEnd, String searchTimeField, String sortField, String sortBy, BanmaerpProperties banmaerpProperties) {
        String apiUrl = String.format(BanmaerpURL.banmaerp_accountlist_GET,ids,email,realName,phone,pageNumber,pageSize,
                searchTimeStart, searchTimeEnd,searchTimeField,sortField,sortBy);
        apiUrl = encryptionUtils.rmEmptyParas(apiUrl);
        HttpHeaders httpHeaders = new HttpHeaders();
        BanmaerpSigningVO banmaerpSigningVO = banmaTokenUtils.banmaerpSigningVO(banmaerpProperties);
        //todo signing
        httpHeaders = banmaTokenUtils.banmaerpCommonHeaders(httpHeaders,banmaerpProperties,banmaerpSigningVO);
        HttpEntity requestBody = new HttpEntity(null,httpHeaders);
        List<AccountDTO> accountDTOList = Arrays.stream(httpClients.restTemplateWithBanmaMasterToken(banmaerpProperties)
                .exchange(BanmaerpURL.banmaerp_gateway.concat(apiUrl),HttpMethod.GET,requestBody,new ParameterizedTypeReference<BanmaErpResponseDTO<JsonNode>>() {})
                .getBody()
                .toDataList(BANMAERP_FIELD_ACCOUNTS)
        )
                .map(o -> (AccountDTO)o)
                .collect(Collectors.toList());
        return accountDTOList;
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
        String apiUrl = String.format(BanmaerpURL.banmaerp_account_GET,id);
        apiUrl = encryptionUtils.rmEmptyParas(apiUrl);
        HttpHeaders httpHeaders = new HttpHeaders();
        BanmaerpSigningVO banmaerpSigningVO = banmaTokenUtils.banmaerpSigningVO(banmaerpProperties);

        //todo signing
        httpHeaders = banmaTokenUtils.banmaerpCommonHeaders(httpHeaders,banmaerpProperties,banmaerpSigningVO);
        HttpEntity requestBody = new HttpEntity(null,httpHeaders);
        BanmaErpResponseDTO<JsonNode> body = httpClients.restTemplateWithBanmaMasterToken(banmaerpProperties)
                .exchange(BanmaerpURL.banmaerp_gateway.concat(apiUrl), HttpMethod.GET, requestBody, new ParameterizedTypeReference<BanmaErpResponseDTO<JsonNode>>() {
                })
                .getBody();

        ObjectMapper objectMapper = new ObjectMapper().registerModule(new JodaModule());
        AccountDTO accountDTO = null;
        try {
            accountDTO = objectMapper.readValue(body.getData().toString(), new TypeReference<AccountDTO>() {
            });
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return accountDTO;
    }


    /**
     * 添加子账号
     *
     * @param phone      中国大陆11位手机号
     * @param email      用户邮箱
     * @param realName   用户名称
     * @param department 部门
     * @param useVirtual 是否用虚拟账号，true则会生成虚拟手机号
     * @return
     */
    @Override
    @CheckBanmaerpProperties
    public BanmaErpResponseDTO<Boolean> addAccount(String phone, String email, String realName, String department,boolean useVirtual ,BanmaerpProperties banmaerpProperties) {
        AccountDTO accountDTO = new AccountDTO(
                0,realName,email,phone,department,null,null
        );
        String apiUrl = String.format(BanmaerpURL.banmaerp_accountAdd_POST);
        apiUrl = encryptionUtils.rmEmptyParas(apiUrl);
        HttpHeaders httpHeaders = new HttpHeaders();
        BanmaerpSigningVO banmaerpSigningVO = banmaTokenUtils.banmaerpSigningVO(banmaerpProperties);
        //todo signing
        httpHeaders = banmaTokenUtils.banmaerpCommonHeaders(httpHeaders,banmaerpProperties,banmaerpSigningVO);
        HttpEntity requestBody = new HttpEntity(accountDTO,httpHeaders);
        BanmaErpResponseDTO<JsonNode> body = httpClients.restTemplateWithBanmaMasterToken(banmaerpProperties)
                .exchange(BanmaerpURL.banmaerp_gateway.concat(apiUrl), HttpMethod.POST, requestBody, new ParameterizedTypeReference<BanmaErpResponseDTO<JsonNode>>() {
                })
                .getBody();

        ObjectMapper objectMapper = new ObjectMapper().registerModule(new JodaModule());
        BanmaErpResponseDTO<Boolean> banmaErpResponseDTO = null;
        try {
            banmaErpResponseDTO = objectMapper.readValue(body.getData().toString(), new TypeReference<BanmaErpResponseDTO>() {
            });
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return banmaErpResponseDTO;
    }

    /**
     * 注销账号
     *
     * @param id 用户ID
     * @return
     */
    @Override
    @CheckBanmaerpProperties
    public BanmaErpResponseDTO<Boolean> logoutAccount(int id, BanmaerpProperties banmaerpProperties) {
        String apiUrl = String.format(BanmaerpURL.banmaerp_accountLogout_DELETE,id);
        apiUrl = encryptionUtils.rmEmptyParas(apiUrl);
        HttpHeaders httpHeaders = new HttpHeaders();
        BanmaerpSigningVO banmaerpSigningVO = banmaTokenUtils.banmaerpSigningVO(banmaerpProperties);
        //todo signing
        httpHeaders = banmaTokenUtils.banmaerpCommonHeaders(httpHeaders,banmaerpProperties,banmaerpSigningVO);
        HttpEntity requestBody = new HttpEntity(null,httpHeaders);
        BanmaErpResponseDTO<JsonNode> body = httpClients.restTemplateWithBanmaMasterToken(banmaerpProperties)
                .exchange(BanmaerpURL.banmaerp_gateway.concat(apiUrl), HttpMethod.DELETE, requestBody, new ParameterizedTypeReference<BanmaErpResponseDTO<JsonNode>>() {
                })
                .getBody();

        ObjectMapper objectMapper = new ObjectMapper().registerModule(new JodaModule());
        BanmaErpResponseDTO<Boolean> banmaErpResponseDTO = null;
        try {
            banmaErpResponseDTO = objectMapper.readValue(body.getData().toString(), new TypeReference<BanmaErpResponseDTO>() {
            });
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return banmaErpResponseDTO;
    }

    /**
     * 查询用户店铺权限
     * @param id 用户id
     * @return
     */
    @Override
    @CheckBanmaerpProperties
    public DataAccessDTO getDataAccess(int id, BanmaerpProperties banmaerpProperties) {
        String apiUrl = String.format(BanmaerpURL.banmaerp_accountDataAccess_GET,id);
        apiUrl = encryptionUtils.rmEmptyParas(apiUrl);
        HttpHeaders httpHeaders = new HttpHeaders();
        BanmaerpSigningVO banmaerpSigningVO = banmaTokenUtils.banmaerpSigningVO(banmaerpProperties);
        //todo signing
        httpHeaders = banmaTokenUtils.banmaerpCommonHeaders(httpHeaders,banmaerpProperties,banmaerpSigningVO);
        HttpEntity requestBody = new HttpEntity(null,httpHeaders);
        BanmaErpResponseDTO<JsonNode> body = httpClients.restTemplateWithBanmaMasterToken(banmaerpProperties)
                .exchange(BanmaerpURL.banmaerp_gateway.concat(apiUrl), HttpMethod.GET, requestBody, new ParameterizedTypeReference<BanmaErpResponseDTO<JsonNode>>() {
                })
                .getBody();

        ObjectMapper objectMapper = new ObjectMapper().registerModule(new JodaModule());
        DataAccessDTO dataAccessDTO = null;
        try {
            dataAccessDTO = objectMapper.readValue(body.getData().toString(), new TypeReference<DataAccessDTO>() {
            });
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return dataAccessDTO;
    }

    @Override
    public List<AccountDTO> saveAccountList(List<AccountDTO> accountDTOList) {
        return accountRepository.saveAllAndFlush(accountDTOList);
    }

    @Override
    public AccountDTO saveAccount(AccountDTO accountDTO) {
        return accountRepository.saveAndFlush(accountDTO);
    }


}
