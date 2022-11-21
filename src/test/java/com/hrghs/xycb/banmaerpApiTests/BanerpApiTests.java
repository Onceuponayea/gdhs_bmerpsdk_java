package com.hrghs.xycb.banmaerpApiTests;

import com.hrghs.xycb.domains.banmaerpDTO.StoreDTO;
import com.hrghs.xycb.domains.common.BanmaErpResponseDTO;

import com.hrghs.xycb.services.StoreService;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import reactor.core.publisher.Mono;

import java.util.List;

@SpringBootTest
@ActiveProfiles(value = {"dev"})
//@ComponentScan(basePackages = "com.hrghs.xycb")
public class BanerpApiTests {
    @Autowired
    StoreService storeService;
    @Test
    void getStoreList(){
        DateTimeFormatter timeFormatter = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");
        List<StoreDTO> storeList= storeService.getStoretList(null,null,null,1,100,timeFormatter.parseDateTime("2020-12-01T00:00:00"),timeFormatter.parseDateTime("2021-01-01"),"CreateTime",null,null,null);
        System.out.println("getStoreList");
        System.out.println(storeList.size());
    }

}
