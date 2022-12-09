package com.hrghs.xycb.services;

import com.hrghs.xycb.domains.BanmaerpProperties;
import com.hrghs.xycb.repositories.BanmaerpPropertiesRepository;
import net.javacrumbs.shedlock.core.SchedulerLock;
import org.joda.time.LocalDateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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


    @Async
    @Scheduled(cron = "${erp.banmaerp.tasks.sync.cron:-}")
    @SchedulerLock(name = "shedlock/banmaerp_periodically_pull", lockAtLeastForString = "PT1M", lockAtMostFor = 180000 )
    public void pullDataFromBanmaerp() throws InterruptedException {
        bmerpTasks();
    }
    private String[] bmerpDefaultTaskState(){
        return new String[]{"Products_0_100_timeStamp","Orders_0_100_timeStamp","Stores_0_100_timeStamp"
                ,"Categorys_0_100_timeStamp","Accounts_0_100_timeStamp"};
    }

    private Flux<String> defaultValue(){
        return Flux.just(bmerpDefaultTaskState());
    }

    private void bmerpTasks() throws InterruptedException {
        List<BanmaerpProperties> banmaerpPropertiesList = banmaerpPropertiesRepository.findAll();
        for (BanmaerpProperties banmaerpProperties: banmaerpPropertiesList){
            String redisKey =BANMAERP_FIELD_PREFIX.concat(COLON).concat(BANMAERP_FIELD_TASK).concat(COLON).concat(banmaerpProperties.getX_BANMA_MASTER_APP_ID());
            Set<String> taskStates = redisOperations.opsForSet().members(redisKey);
            if (!taskStates.isEmpty()){
                Set<String> newtaskStates = new LinkedHashSet<>(taskStates.size());
                for (String taskState: taskStates){
                    String[] keys = taskState.split(UNDERSCORE);
                    Integer pageNum = Integer.parseInt(keys[1]);
                    Integer pageSize = Integer.parseInt(keys[2]);
                    switch (keys[0]){
                        case BANMAERP_FIELD_PRODUCTS:
                            logger.info("synchronising Product list from BanmaErp for {},  page {} and size {}",banmaerpProperties.getX_BANMA_MASTER_APP_ACCOUNT(),pageNum,pageSize);
                            productService.getAndSaveProductList(pageNum,pageSize,banmaerpProperties);
                            break;
                        case BANMAERP_FIELD_ORDERS:
                            logger.info("synchronising Order list from BanmaErp for {},  page {} and size {}",banmaerpProperties.getX_BANMA_MASTER_APP_ACCOUNT(),pageNum,pageSize);
                            orderService.getAndSaveOrderList(pageNum,pageSize,banmaerpProperties);
                            break;
                        case BANMAERP_FIELD_STORES:
                            logger.info("synchronising Store list from BanmaErp for {},  page {} and size {}",banmaerpProperties.getX_BANMA_MASTER_APP_ACCOUNT(),pageNum,pageSize);
                            storeService.getAndSaveStoretList(pageNum,pageSize,banmaerpProperties);
                            break;
                        case BANMAERP_FIELD_CATEGORYS:
                            logger.info("synchronising Category list from BanmaErp for {},  page {} and size {}",banmaerpProperties.getX_BANMA_MASTER_APP_ACCOUNT(),pageNum,pageSize);
                            categoryService.getAndSaveCategoryList(pageNum,pageSize,banmaerpProperties);
                            break;
                        case BANMAERP_FIELD_ACCOUNTS:
                            logger.info("synchronising Account list from BanmaErp for {},  page {} and size {}",banmaerpProperties.getX_BANMA_MASTER_APP_ACCOUNT(),pageNum,pageSize);
                            accountService.getAndSaveAccountList(pageNum,pageSize,banmaerpProperties);
                            break;
                    }
                    //把新的页码和页数增加1并保存到新的set中,然后回写到redis中
                    pageNum++;
                    String newTaskState =keys[0].concat(UNDERSCORE).concat(pageNum.toString())
                            .concat(UNDERSCORE).concat(pageSize.toString()).concat(UNDERSCORE)
                            .concat(LocalDateTime.now().toString());
                    newtaskStates.add(newTaskState);
                    if (taskDelayInMs > 0){
                        logger.info("delaying task banmaerp_periodically_pull for {} milleSecond",taskDelayInMs);
                        Thread.sleep(taskDelayInMs);
                    }
                }
                redisOperations.opsForSet().remove(redisKey,taskStates.toArray(new String[0]));
                redisOperations.opsForSet().add(redisKey, newtaskStates.toArray(new String[0]));
            }else{
                redisOperations.opsForSet().add(redisKey,bmerpDefaultTaskState());
            }
        }
    }
}
