package com.hrghs.xycb.services;

import com.hrghs.xycb.domains.BanmaerpProperties;
import com.hrghs.xycb.domains.banmaerpDTO.*;
import com.hrghs.xycb.domains.enums.BanmaerpAccountEnums;
import com.hrghs.xycb.repositories.BanmaerpPropertiesRepository;
import net.javacrumbs.shedlock.core.SchedulerLock;
import org.joda.time.DateTime;
import org.joda.time.LocalDateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import reactor.core.publisher.Flux;
import java.util.*;
import static com.hrghs.xycb.domains.Constants.*;
import static jodd.util.StringPool.COLON;
import static jodd.util.StringPool.UNDERSCORE;

public class ScheduleService {
    private static final Logger logger = LoggerFactory.getLogger(ScheduleService.class);
    @Autowired
    private StoreService storeService;
    @Autowired
    private ProductService productService;
    @Autowired
    private AccountService accountService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private BanmaerpPropertiesRepository banmaerpPropertiesRepository;
    @Autowired
    private RedisOperations<String,String> redisOperations;
    @Value("${erp.banmaerp.tasks.sync.delayInMs: 0}")
    private Long taskDelayInMs;
    private static String defaultSearchBeginTime ="2020-12-01T00:00:00";


    @Async
    @Scheduled(cron = "${erp.banmaerp.tasks.sync.cron:-}")
    @SchedulerLock(name = "shedlock/banmaerp_periodically_pull", lockAtLeastForString = "PT1M", lockAtMostFor = 180000 )
    public void pullDataFromBanmaerp() throws InterruptedException {
        bmerpTasks();
    }
    private String[] bmerpDefaultTaskState(){
        return new String[]{"Products_0_"+PAGE_SIZE_DEFAULT+"_timeStamp","Orders_0_"+PAGE_SIZE_DEFAULT+"_timeStamp",
                "Stores_0_"+PAGE_SIZE_DEFAULT+"_timeStamp","Categorys_0_"+PAGE_SIZE_DEFAULT+"_timeStamp",
                "Accounts_0_"+PAGE_SIZE_DEFAULT+"_timeStamp"};
    }

    private Flux<String> defaultValue(){
        return Flux.just(bmerpDefaultTaskState());
    }

    private void bmerpTasks() throws InterruptedException {
        List<BanmaerpProperties> banmaerpPropertiesList = banmaerpPropertiesRepository.findAll();
        banmaerpPropertiesList.parallelStream().forEach(banmaerpProperties -> {
            String redisKey =BANMAERP_FIELD_PREFIX.concat(COLON).concat(BANMAERP_FIELD_TASK).concat(COLON).concat(banmaerpProperties.getX_BANMA_MASTER_APP_ID());
            Set<String> taskStates = redisOperations.opsForSet().members(redisKey);
            if (!taskStates.isEmpty()){
                Set<String> newtaskStates = new LinkedHashSet<>(taskStates.size());
                for (String taskState: taskStates){
                    String[] keys = taskState.split(UNDERSCORE);
                    Integer pageNum = Integer.parseInt(keys[1]);
                    Integer pageSize = Integer.parseInt(keys[2]);
                    LocalDateTime lastPullTime ;
                    try {
                        lastPullTime = DateTime.parse(keys[3]).toLocalDateTime();
                    }catch (IllegalArgumentException exp){
                        lastPullTime = LocalDateTime.parse(defaultSearchBeginTime);
                    }
                    /** todo
                     *  1、当redis 挂掉后就需要重启应用才能重新定时拉取数据，最便宜的方式就是redis要持久化
                     *  2、redis页码只往上增，当出现斑马删除数据后又重新导入数据的话就会导致拉取的数据错误。
                     */
                    switch (keys[0]){
                        case BANMAERP_FIELD_PRODUCTS:
                            logger.info("synchronising Product list from BanmaErp for {},  page {} and size {}",banmaerpProperties.getX_BANMA_MASTER_APP_ACCOUNT(),pageNum,pageSize);
                            Page<ProductDTO> productDTOPage = productService.getAndSaveProductList(lastPullTime,pageNum,pageSize,banmaerpProperties);
                            int product_totalPages = productDTOPage.getTotalPages();
                            while (product_totalPages>pageNum){
                                pageNum+=1;
                                productDTOPage = productService.getAndSaveProductList(lastPullTime,pageNum,pageSize,banmaerpProperties);
                                product_totalPages = productDTOPage.getTotalPages();
                            }
                            //pageNum= (productDTOList.size()>=pageSize?pageNum+1:pageNum);
                            pageNum = 0; //归0
                            break;
                        case BANMAERP_FIELD_ORDERS:
                            logger.info("synchronising Order list from BanmaErp for {},  page {} and size {}",banmaerpProperties.getX_BANMA_MASTER_APP_ACCOUNT(),pageNum,pageSize);
                            Page<OrderDTO> orderDTOPage = orderService.getAndSaveOrderList(lastPullTime,pageNum,pageSize,banmaerpProperties);
                            int order_totalPages = orderDTOPage.getTotalPages();
                            while (order_totalPages>pageNum){
                                pageNum+=1;
                                orderDTOPage = orderService.getAndSaveOrderList(lastPullTime,pageNum,pageSize,banmaerpProperties);
                                order_totalPages = orderDTOPage.getTotalPages();
                            }
                            pageNum = 0;
                            break;
                        case BANMAERP_FIELD_STORES:
                            logger.info("synchronising Store list from BanmaErp for {},  page {} and size {}",banmaerpProperties.getX_BANMA_MASTER_APP_ACCOUNT(),pageNum,pageSize);
                            Page<StoreDTO> storeDTOPage = storeService.getAndSaveStoretList(lastPullTime,pageNum,pageSize,banmaerpProperties);
                            int store_totalPages = storeDTOPage.getTotalPages();
                            while (store_totalPages>pageNum){
                                pageNum+=1;
                                storeDTOPage = storeService.getAndSaveStoretList(lastPullTime,pageNum,pageSize,banmaerpProperties);
                                store_totalPages = storeDTOPage.getTotalPages();
                            }
                            pageNum = 0;
                            break;
                        case BANMAERP_FIELD_CATEGORYS:
                            logger.info("synchronising Category list from BanmaErp for {},  page {} and size {}",banmaerpProperties.getX_BANMA_MASTER_APP_ACCOUNT(),pageNum,pageSize);
                            Page<CategoryDTO> categoryDTOPage =  categoryService.getAndSaveCategoryList(lastPullTime,pageNum,pageSize,banmaerpProperties);
                            int category_totalPages = categoryDTOPage.getTotalPages();
                            while (category_totalPages>pageNum){
                                pageNum+=1;
                                categoryDTOPage = categoryService.getAndSaveCategoryList(lastPullTime,pageNum,pageSize,banmaerpProperties);
                                category_totalPages = categoryDTOPage.getTotalPages();
                            }
                            pageNum = 0;
                            break;
                        case BANMAERP_FIELD_ACCOUNTS:
                            logger.info("synchronising Account list from BanmaErp for {},  page {} and size {}",banmaerpProperties.getX_BANMA_MASTER_APP_ACCOUNT(),pageNum,pageSize);
                            Page<AccountDTO> accountDTOPage =  accountService.getAndSaveAccountList(lastPullTime,pageNum,pageSize,banmaerpProperties);
                            int account_totalPages = accountDTOPage.getTotalPages();
                            while (account_totalPages>pageNum){
                                pageNum+=1;
                                accountDTOPage = accountService.getAndSaveAccountList(lastPullTime,pageNum,pageSize,banmaerpProperties);
                                account_totalPages = accountDTOPage.getTotalPages();
                            }
                            pageNum = 0;
                            break;
                        case BANMAERP_FIELD_DATAACCESS:
                            List<AccountDTO> accountDTOS = accountService.findAllByUserState(BanmaerpAccountEnums.UserState.Normal);
                            List<DataAccessDTO> dataAccessDTOS= new ArrayList<>();
                            accountDTOS.parallelStream().forEach(accountDTO -> {
                                logger.info("synchronising Store privileges for Account {}, Phone: {}",accountDTO.getRealName(),accountDTO.getPhone());
                                dataAccessDTOS.add(accountService.getDataAccess(accountDTO,true,banmaerpProperties));
                            });
                            pageNum = 0;
                            break;
                    }
                    String newTaskState =keys[0].concat(UNDERSCORE).concat(pageNum.toString())
                            .concat(UNDERSCORE).concat(pageSize.toString()).concat(UNDERSCORE)
                            .concat(lastPullTime.toString());
                    newtaskStates.add(newTaskState);
                    if (taskDelayInMs > 0){
                        logger.info("delaying task banmaerp_periodically_pull for {} milleSecond",taskDelayInMs);
                        try {
                            Thread.sleep(taskDelayInMs);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    //把相同开头的key[0]任务都清空
                    String[] removableTaskState= taskStates.stream().filter(s -> s.split(UNDERSCORE)[0].equalsIgnoreCase(keys[0])).toArray(String[]::new);
                    redisOperations.opsForSet().remove(redisKey,removableTaskState);
                    redisOperations.opsForSet().add(redisKey, newTaskState);
                }
//                redisOperations.opsForSet().remove(redisKey,taskStates.toArray(new String[taskStates.size()]));
//                redisOperations.opsForSet().add(redisKey, newtaskStates.toArray(new String[newtaskStates.size()]));
            }else{
                redisOperations.opsForSet().add(redisKey,bmerpDefaultTaskState());
            }
        });
    }
    private void bmerpTasksByUpdateTime(){
        /*todo
           redis记录上一次的updateTime时间是多久，每次hasmore时通过pageSize进行遍历的获取，直到没有多余的页码.
           拉取完后记录下updateTime到redis.
         */

    }
}
