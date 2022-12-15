package com.hrghs.xycb.services.impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.hrghs.xycb.annotations.CheckBanmaerpProperties;
import com.hrghs.xycb.domains.BanmaerpProperties;
import com.hrghs.xycb.domains.BanmaerpSigningVO;
import com.hrghs.xycb.domains.BanmaerpURL;
import com.hrghs.xycb.domains.banmaerpDTO.AccountDTO;
import com.hrghs.xycb.domains.banmaerpDTO.DataAccessDTO;
import com.hrghs.xycb.domains.banmaerpDTO.TokenResponseDTO;
import com.hrghs.xycb.domains.common.BanmaErpResponseDTO;
import com.hrghs.xycb.domains.enums.BanmaerpAccountEnums;
import com.hrghs.xycb.repositories.AccountRepository;
import com.hrghs.xycb.repositories.BanmaerpPropertiesRepository;
import com.hrghs.xycb.services.AccountService;
import com.hrghs.xycb.utils.*;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;


public class AccountServiceImpl implements AccountService {

    @Autowired
    private HttpClientsUtils httpClients;
    @Autowired
    private BanmaTokenUtils banmaTokenUtils;
    @Autowired
    private BanmaEncryptionUtils encryptionUtils;
    @Autowired
    private AccountRepository accountRepository;

    private Gson gson = new GsonBuilder().disableHtmlEscaping()
            .setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE)
            .registerTypeAdapter(DateTime.class,new DateTimeConverter())
            .setDateFormat("yyyy-MM-dd HH:mm:ss")
            .create();;
    @Autowired
    private BanmaerpPropertiesRepository banmaerpPropertiesRepository;

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
    public Page<AccountDTO> getAccountList(String ids, String email, String realName, String phone, Integer pageNumber, Integer pageSize
            , DateTime searchTimeStart, DateTime searchTimeEnd, String searchTimeField, String sortField, String sortBy, Boolean remote
            , BanmaerpProperties banmaerpProperties) {
        Page<AccountDTO> accountDTOList =null;
        pageSize = BanmaParamsUtils.checkPageSize(pageSize);
        pageNumber = BanmaParamsUtils.checkPageNum(pageNumber);
        if (remote){
            String apiUrl = String.format(BanmaerpURL.banmaerp_accountlist_GET,ids,email,realName,phone,pageNumber,pageSize,
                    searchTimeStart, searchTimeEnd,searchTimeField,sortField,sortBy);
            apiUrl = encryptionUtils.rmEmptyParas(apiUrl);
            HttpHeaders httpHeaders = new HttpHeaders();
            BanmaerpSigningVO banmaerpSigningVO = banmaTokenUtils.banmaerpSigningVO(banmaerpProperties,HttpMethod.GET,apiUrl,null);
            httpHeaders = banmaTokenUtils.banmaerpCommonHeaders(httpHeaders,banmaerpProperties,banmaerpSigningVO);
            HttpEntity requestBody = new HttpEntity(null,httpHeaders);
            BanmaErpResponseDTO<JsonNode> responseDTO =
                httpClients.restTemplateWithBanmaMasterToken(banmaerpProperties)
                    .exchange(BanmaerpURL.banmaerp_gateway.concat(apiUrl),HttpMethod.GET,requestBody,new ParameterizedTypeReference<BanmaErpResponseDTO<JsonNode>>() {})
                    .getBody();
            /**
            List<AccountDTO> accountList = Arrays.stream(responseDTO.toDataList(BANMAERP_FIELD_ACCOUNTS))
                    .map(o -> (AccountDTO)o)
                    .collect(Collectors.toList());
            **/
            accountDTOList = (Page<AccountDTO>) responseDTO.toDataList(AccountDTO.class,banmaerpProperties);
        }else{
            Specification<AccountDTO> specification = createSpecification(ids, email, realName, phone, pageNumber, pageSize, searchTimeStart, searchTimeEnd, searchTimeField, sortField, sortBy);
            accountDTOList = accountRepository.findAll(specification,PageRequest.of(pageNumber,pageSize));
        }
        return accountDTOList;
    }

    @Override
    @CheckBanmaerpProperties
    public Page<AccountDTO> getAccountList(Integer pageNumber,Boolean remote, BanmaerpProperties banmaerpProperties) {
        return getAccountList(null,null,null,null,pageNumber,null,null,null,null,null,null,remote,banmaerpProperties);
    }

    @Override
    @CheckBanmaerpProperties
    public Page<AccountDTO> getAccountList(Integer pageNumber, Integer pageSize,Boolean remote, BanmaerpProperties banmaerpProperties) {
        return getAccountList(null,null,null,null,pageNumber,pageSize,null,null,null,null,null,remote,banmaerpProperties);
    }

    @Override
    @CheckBanmaerpProperties
    public List<AccountDTO> getAndSaveAccountList(Integer pageNumber,Integer pageSize,BanmaerpProperties banmaerpProperties) {
        List<AccountDTO> accountDTOS =getAccountList(null,null,null,null,pageNumber,null,null,null,null,null,null,true,banmaerpProperties)
                .getContent();
        banmaerpProperties.setBanmaErpAccounts(accountDTOS);
        return banmaerpPropertiesRepository.saveAndFlush(banmaerpProperties).getBanmaErpAccounts();
    }

    /**
     * 查询单个用户
     *
     * @param id 用户id
     * @return
     */
    @Override
    @CheckBanmaerpProperties
    public AccountDTO getAccountById(Integer id,Boolean remote, BanmaerpProperties banmaerpProperties) {
        if (remote){
            String apiUrl = String.format(BanmaerpURL.banmaerp_account_GET,id);
            apiUrl = encryptionUtils.rmEmptyParas(apiUrl);
            HttpHeaders httpHeaders = new HttpHeaders();
            BanmaerpSigningVO banmaerpSigningVO = banmaTokenUtils.banmaerpSigningVO(banmaerpProperties,HttpMethod.GET,apiUrl,null);
            httpHeaders = banmaTokenUtils.banmaerpCommonHeaders(httpHeaders,banmaerpProperties,banmaerpSigningVO);
            HttpEntity requestBody = new HttpEntity(null,httpHeaders);
            return httpClients.restTemplateWithBanmaMasterToken(banmaerpProperties)
                    .exchange(BanmaerpURL.banmaerp_gateway.concat(apiUrl), HttpMethod.GET, requestBody, new ParameterizedTypeReference<BanmaErpResponseDTO<AccountDTO>>() {})
                    .getBody().getData();
        }else {
            return accountRepository.findById(id).get();
        }

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
    public AccountDTO addAccount(String phone, String email, String realName, String department,Boolean useVirtual ,BanmaerpProperties banmaerpProperties) {
        AccountDTO accountDTO = new AccountDTO(realName,email,phone,department);
        String accountJson = gson.toJson(accountDTO);
        String apiUrl = String.format(BanmaerpURL.banmaerp_accountAdd_POST);
        apiUrl = encryptionUtils.rmEmptyParas(apiUrl);
        HttpHeaders httpHeaders = new HttpHeaders();
        BanmaerpSigningVO banmaerpSigningVO = banmaTokenUtils.banmaerpSigningVO(banmaerpProperties,HttpMethod.POST,apiUrl,accountJson);
        httpHeaders = banmaTokenUtils.banmaerpCommonHeaders(httpHeaders,banmaerpProperties,banmaerpSigningVO);
        httpHeaders.set(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        HttpEntity requestBody = new HttpEntity(accountJson,httpHeaders);
        accountDTO =
        httpClients.restTemplateWithBanmaMasterToken(banmaerpProperties)
                .exchange(BanmaerpURL.banmaerp_gateway.concat(apiUrl), HttpMethod.POST, requestBody, new ParameterizedTypeReference<BanmaErpResponseDTO<AccountDTO>>() {})
                .getBody().getData();
        accountDTO.setUserType(BanmaerpAccountEnums.UserType.SubAccount);
        accountDTO.setState(BanmaerpAccountEnums.UserState.Normal);
        return accountRepository.saveAndFlush(accountDTO);
    }

    /**
     * 注销账号
     *
     * @param id 用户ID
     * @return
     */
    @Override
    @CheckBanmaerpProperties
    public BanmaErpResponseDTO<Boolean> logoutAccount(Integer id, BanmaerpProperties banmaerpProperties) {
        String apiUrl = String.format(BanmaerpURL.banmaerp_accountLogout_DELETE,id);
        apiUrl = encryptionUtils.rmEmptyParas(apiUrl);
        HttpHeaders httpHeaders = new HttpHeaders();
        BanmaerpSigningVO banmaerpSigningVO = banmaTokenUtils.banmaerpSigningVO(banmaerpProperties,HttpMethod.DELETE,apiUrl,null);
        httpHeaders = banmaTokenUtils.banmaerpCommonHeaders(httpHeaders,banmaerpProperties,banmaerpSigningVO);
        HttpEntity requestBody = new HttpEntity(null,httpHeaders);
        BanmaErpResponseDTO<Boolean> body = httpClients.restTemplateWithBanmaMasterToken(banmaerpProperties)
                .exchange(BanmaerpURL.banmaerp_gateway.concat(apiUrl), HttpMethod.DELETE, requestBody, new ParameterizedTypeReference<BanmaErpResponseDTO<Boolean>>() {})
                .getBody();
        accountRepository.findById(id).ifPresent(accountDTO -> {
            accountDTO.setState(BanmaerpAccountEnums.UserState.Resigned);
            accountRepository.saveAndFlush(accountDTO);
        });
        return body;
    }

    /**
     * 查询用户店铺权限
     * @param id 用户id
     * @return
     */
    @Override
    @CheckBanmaerpProperties
    public DataAccessDTO getDataAccess(Integer id, BanmaerpProperties banmaerpProperties) {
        String apiUrl = String.format(BanmaerpURL.banmaerp_accountDataAccess_GET,id);
        apiUrl = encryptionUtils.rmEmptyParas(apiUrl);
        HttpHeaders httpHeaders = new HttpHeaders();
        BanmaerpSigningVO banmaerpSigningVO = banmaTokenUtils.banmaerpSigningVO(banmaerpProperties,HttpMethod.GET,apiUrl,null);
        httpHeaders = banmaTokenUtils.banmaerpCommonHeaders(httpHeaders,banmaerpProperties,banmaerpSigningVO);
        HttpEntity requestBody = new HttpEntity(null,httpHeaders);
        return httpClients.restTemplateWithBanmaMasterToken(banmaerpProperties)
                .exchange(BanmaerpURL.banmaerp_gateway.concat(apiUrl), HttpMethod.GET, requestBody, new ParameterizedTypeReference<BanmaErpResponseDTO<DataAccessDTO>>() {})
                .getBody().getData();
    }

    @Override
    @CheckBanmaerpProperties
    public List<AccountDTO> saveAccountList(List<AccountDTO> accountDTOList, BanmaerpProperties banmaerpProperties) {
        accountDTOList.parallelStream().forEach(accountDTO -> accountDTO.setBanmaerpProperties(banmaerpProperties));
        List<AccountDTO> accountDTOS =  accountRepository.saveAll(accountDTOList);
        accountRepository.flush();
        return accountDTOS;
    }

    @Override
    @CheckBanmaerpProperties
    public AccountDTO saveAccount(AccountDTO accountDTO, BanmaerpProperties banmaerpProperties) {
        accountDTO.setBanmaerpProperties(banmaerpProperties);
        return accountRepository.saveAndFlush(accountDTO);
    }

    @Override
    @CheckBanmaerpProperties
    public TokenResponseDTO getSubAccountAccessToken(BanmaerpProperties banmaerpProperties) {
        return null;
    }
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
    private Specification<AccountDTO> createSpecification(String ids, String email, String realName, String phone, Integer pageNumber, Integer pageSize, DateTime searchTimeStart, DateTime searchTimeEnd, String searchTimeField, String sortField, String sortBy) {
        return (root, criteriaQuery, criteriaBuilder) -> {
            List<Predicate> predicateList = new ArrayList<>();

            if (ids != null && ids != "") {
                CriteriaBuilder.In<Long> in = criteriaBuilder.in(root.get("ID"));
                if (ids.contains(",")) {
                    String[] id = ids.split(",");
                    for (int i = 0; i < id.length; i++) {
                        in.value(Long.parseLong(id[i]));
                    }
                } else {
                    in.value(Long.parseLong(ids));
                }
                predicateList.add(in);
            }

            if (email != null && email != "") {
                predicateList.add(criteriaBuilder
                        .like(root.get("email"),
                                "%" + email + "%"));
            }
            if (realName != null && realName != "") {
                predicateList.add(criteriaBuilder
                        .like(root.get("realName"),
                                "%" + realName + "%"));
            }
            if (phone != null && phone != "") {
                predicateList.add(criteriaBuilder
                        .like(root.get("phone"),
                                "%" + phone + "%"));
            }

            // 开始时间
            if (searchTimeStart != null && searchTimeEnd != null) {
                predicateList.add(criteriaBuilder
                        .between(root.<DateTime>get(searchTimeField), searchTimeStart, searchTimeEnd));
            }
            Predicate and = criteriaBuilder.and(predicateList.toArray(new Predicate[predicateList.size()]));
            Order order = criteriaBuilder.desc(root.get("createTime"));
            if (sortBy != null && sortBy != ""){
                if (sortBy.equals("ASC")) {
                    order = criteriaBuilder.asc(root.get("createTime"));
                }
            }
            if (sortField !=null && sortField != ""){
                order = criteriaBuilder.desc(root.get(sortField));
                if (sortBy.equals("ASC")){
                    order = criteriaBuilder.asc(root.get(sortField));
                }
            }
            return criteriaQuery.where(and).orderBy(order).getRestriction();
            //return criteriaBuilder.and(predicateList.toArray(new Predicate[predicateList.size()]));
        };
    }
}
