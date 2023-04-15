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
import com.hrghs.xycb.domains.banmaerpDTO.StoreDTO;
import com.hrghs.xycb.domains.banmaerpDTO.TokenResponseDTO;
import com.hrghs.xycb.domains.common.BanmaErpResponseDTO;
import com.hrghs.xycb.domains.enums.BanmaerpAccountEnums;
import com.hrghs.xycb.domains.enums.BanmaerpOrderEnums;
import com.hrghs.xycb.repositories.AccountRepository;
import com.hrghs.xycb.repositories.BanmaerpPropertiesRepository;
import com.hrghs.xycb.repositories.DataAccessRepository;
import com.hrghs.xycb.repositories.StoreRepository;
import com.hrghs.xycb.services.AccountService;
import com.hrghs.xycb.services.StoreService;
import com.hrghs.xycb.utils.*;
import org.joda.time.DateTime;
import org.joda.time.LocalDateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static com.hrghs.xycb.domains.Constants.BANMAERP_FIELD_DATAACCESS;
import static com.hrghs.xycb.domains.Constants.BANMAERP_MESSAGE_ILLEGAL_ARGS;


public class AccountServiceImpl implements AccountService {

    private final static Logger logger = LoggerFactory.getLogger(AccountServiceImpl.class);
    @Autowired
    private HttpClientsUtils httpClients;
    @Autowired
    private BanmaTokenUtils banmaTokenUtils;
    @Autowired
    private BanmaEncryptionUtils encryptionUtils;
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private DataAccessRepository dataAccessRepository;
    @Autowired
    private StoreRepository storeRepository;
    @Autowired
    private StoreService storeService;

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
        if (remote){
            pageNumber = BanmaParamsUtils.checkPageNum(pageNumber,remote);
            String apiUrl = String.format(BanmaerpURL.banmaerp_accountlist_GET,ids,email,realName,phone,pageNumber,pageSize,
                    searchTimeStart==null?null:searchTimeStart.toLocalDateTime(), searchTimeEnd==null?null:searchTimeEnd.toLocalDateTime(),searchTimeField,sortField,sortBy);
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
            accountDTOList.getContent().forEach(accountDTO -> {
                if (banmaerpProperties.getX_BANMA_MASTER_APP_ACCOUNT().equalsIgnoreCase(accountDTO.getPhone())){
                    accountDTO.setUserType(BanmaerpAccountEnums.UserType.MasterAccount);
                }else {
                    accountDTO.setUserType(BanmaerpAccountEnums.UserType.SubAccount);
                }
                accountDTO.setState(BanmaerpAccountEnums.UserState.Normal);
                accountDTO.setBanmaerpProperties(banmaerpProperties);
            });
 //           List<AccountDTO> accountDTOS = accountRepository.saveAll(accountDTOList);
//            accountRepository.flush();
              List<AccountDTO> accountDTOS = accountRepository.saveAllAndFlush(accountDTOList);
        }else{
            pageNumber = BanmaParamsUtils.checkPageNum(pageNumber,false);
            Specification<AccountDTO> specification = createSpecification(ids, email, realName, phone, searchTimeStart, searchTimeEnd, searchTimeField, sortField, sortBy,banmaerpProperties);
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
    public Page<AccountDTO> getAccountList( Integer pageNumber, Integer pageSize, Boolean remote, BanmaerpProperties banmaerpProperties) {
        return getAccountList(null,null,null,null,pageNumber,pageSize,null,null,null,null,null,remote,banmaerpProperties);
    }

    @Override
    @CheckBanmaerpProperties
    public Page<AccountDTO> getAndSaveAccountList(LocalDateTime lastPullTime,Integer pageNumber,Integer pageSize,BanmaerpProperties banmaerpProperties) {
        Page<AccountDTO> accountDTOS =getAccountList(null,null,null,null,pageNumber,null,lastPullTime.toDateTime(),null, BanmaerpOrderEnums.SearchTimeField.UpdateTime.name(),
                null,null,true,banmaerpProperties);
        accountDTOS.getContent().parallelStream().filter(accountDTO -> !accountDTO.getPhone().equalsIgnoreCase(banmaerpProperties.getX_BANMA_MASTER_APP_ACCOUNT()))
            .forEach(accountDTO -> {
            DataAccessDTO dataAccessDTO = getDataAccess(accountDTO,true,banmaerpProperties);
            //todo 要把账号关联的店铺也一起写进数据库
            List<String> storeIds=new ArrayList<>();
            logger.info("try to retrieve privileges for account {}, ID: {}",accountDTO.getRealName(),accountDTO.getID());
            switch (dataAccessDTO.getDataAccessMode()){
                case NoPermissions:
                    break;
                case FullPermissions:
                    /**
                     * @@implNote 斑马那边返回空数组，表示没有关联店铺
                     */
                    break;
                case SpecifyPermissions:
                    storeIds = dataAccessDTO.getData();
                    break;
            }
            List<Long> storeIdList = storeIds.stream().map(s -> Long.parseLong(s)).collect(Collectors.toList());
            List<StoreDTO> storeDTOList=storeRepository.findAllById(storeIdList);
            accountDTO.setStoreDTOList(storeDTOList);
            accountDTO.setDataAccessDTO(dataAccessDTO);
            accountDTO.setBanmaerpProperties(banmaerpProperties);
        });
//        banmaerpProperties.setBanmaErpAccounts(accountDTOS);
//        List<AccountDTO> result =  accountRepository.saveAll(accountDTOS);
//        accountRepository.flush();
        /** cascade saving will erase all relative account **/
        //List<AccountDTO> accounts =  banmaerpPropertiesRepository.saveAndFlush(banmaerpProperties).getBanmaErpAccounts();
        return accountDTOS;
    }

    @Override
    public List<AccountDTO> findAll() {
        return accountRepository.findAll();
    }

    @Override
    public List<AccountDTO> findAllByUserState(BanmaerpAccountEnums.UserState userState) {
        return accountRepository.findAllByState(userState);
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
        if (id ==null)throw  new IllegalArgumentException(BANMAERP_MESSAGE_ILLEGAL_ARGS);
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
        accountDTO.setBanmaerpProperties(banmaerpProperties);
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

    @Override
    public BanmaErpResponseDTO<Boolean> logoutAndDeleteAccount(Integer id, BanmaerpProperties banmaerpProperties) {
        BanmaErpResponseDTO<Boolean> result =  logoutAccount(id,banmaerpProperties);
        if (result.getSuccess()){
            accountRepository.deleteById(id);
        }
        return result;
    }

    @Override
    public void deleteAccount(Integer id, BanmaerpProperties banmaerpProperties) {
         accountRepository.deleteById(id);
    }

    /**
     * 查询用户店铺权限
     * @param account 用户id
     * @return
     */
    @Override
    @CheckBanmaerpProperties
    public DataAccessDTO getDataAccess(AccountDTO account, Boolean remote, BanmaerpProperties banmaerpProperties) {
        if (account ==null)throw  new IllegalArgumentException(BANMAERP_MESSAGE_ILLEGAL_ARGS);
        DataAccessDTO dataAccessDTO=null;
        if (remote){
            String apiUrl = String.format(BanmaerpURL.banmaerp_accountDataAccess_GET,account.getID());
            apiUrl = encryptionUtils.rmEmptyParas(apiUrl);
            HttpHeaders httpHeaders = new HttpHeaders();
            BanmaerpSigningVO banmaerpSigningVO = banmaTokenUtils.banmaerpSigningVO(banmaerpProperties,HttpMethod.GET,apiUrl,null);
            httpHeaders = banmaTokenUtils.banmaerpCommonHeaders(httpHeaders,banmaerpProperties,banmaerpSigningVO);
            HttpEntity requestBody = new HttpEntity(null,httpHeaders);
            dataAccessDTO = httpClients.restTemplateWithBanmaMasterToken(banmaerpProperties)
                    .exchange(BanmaerpURL.banmaerp_gateway.concat(apiUrl), HttpMethod.GET, requestBody, new ParameterizedTypeReference<BanmaErpResponseDTO<JsonNode>>() {})
                    .getBody().toDataAccessDTO(BANMAERP_FIELD_DATAACCESS);

            DataAccessDTO accountDataAccess = null;
            List<DataAccessDTO> dataAccessDTOList = dataAccessRepository.findByAccountDTOID(account.getID());
            if (dataAccessDTOList!=null&&dataAccessDTOList.size()>0){
                accountDataAccess = dataAccessDTOList.get(0);
                dataAccessDTOList.subList(1,dataAccessDTOList.size()).forEach(dataAccessDTO1 -> dataAccessRepository.delete(dataAccessDTO1));
            }
            if (accountDataAccess !=null && accountDataAccess.getId()!=null){
                dataAccessDTO.setId(accountDataAccess.getId());
                dataAccessDTO.setUpdateTime(new DateTime());
                dataAccessDTO.setCreateTime(accountDataAccess.getCreateTime());
            }else{
                dataAccessDTO.setCreateTime(new DateTime());
            }
            dataAccessDTO = dataAccessRepository.save(dataAccessDTO);
            dataAccessDTO.setAccountDTO(account);
            dataAccessDTO = dataAccessRepository.saveAndFlush(dataAccessDTO);
        }else{
            List<DataAccessDTO> dataAccessDTOList = dataAccessRepository.findByAccountDTOID(account.getID());
            if (dataAccessDTOList!=null&&dataAccessDTOList.size()>0){
                dataAccessDTO = dataAccessDTOList.get(0);
                dataAccessDTOList.subList(1,dataAccessDTOList.size()).forEach(dataAccessDTO1 -> dataAccessRepository.delete(dataAccessDTO1));
            }
        }
        return dataAccessDTO;
    }

    @Override
    @CheckBanmaerpProperties
    public List<AccountDTO> saveAccountList(List<AccountDTO> accountDTOList, BanmaerpProperties banmaerpProperties) {
        accountDTOList.parallelStream().forEach(accountDTO -> accountDTO.setBanmaerpProperties(banmaerpProperties));
        //List<AccountDTO> accountDTOS =  accountRepository.saveAll(accountDTOList);
        //accountRepository.flush();
        List<AccountDTO> accountDTOS =  accountRepository.saveAllAndFlush(accountDTOList);
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
     * @param searchTimeStart 查询的开始时间
     * @param searchTimeEnd   查询的结束时间
     * @param searchTimeField 查询的时间字段名
     * @param sortField       排序字段名
     * @param sortBy          排序方式
     * @return
     */
    private Specification<AccountDTO> createSpecification(String ids, String email, String realName, String phone
            , DateTime searchTimeStart, DateTime searchTimeEnd, String searchTimeField, String sortField, String sortBy
            ,BanmaerpProperties banmaerpProperties) {
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
            predicateList.add(criteriaBuilder.equal(root.get("banmaerpProperties").get("X_BANMA_MASTER_APP_ID"),banmaerpProperties.getX_BANMA_MASTER_APP_ID()));
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
