package com.hrghs.xycb.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

public class ScheduleService {
    @Autowired
    private StoreService storeService;
    @Autowired
    private ProductService productService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private CategoryService categoryService;
    /**
     * todo:
     * 1、定期拉取订单，保存到数据库
     * 2、定期拉取用户（子账号），主要是用来防止用户在erp端自己添加子账号的时候，选品中心感知不到
     * 3、定期拉取商品（排在最后）：单独表，不要跟选品中心的商品表混在一起。提前准备sql: where clause
     * 4、定期拉取类目
     *
     */
    /** https://segmentfault.com/a/1190000040036458/en
     * https://dzone.com/articles/distributed-java-locks-with-redis
     * https://www.baeldung.com/shedlock-spring
     * pull order from banmaerp every 5 mins.
     */
    @Scheduled(cron = "0 0/5 0 * * ?")
    public void pullOrders(){
        //todo
    }
}
