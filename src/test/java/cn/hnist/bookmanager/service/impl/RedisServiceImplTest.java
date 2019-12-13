//package cn.hnist.bookmanager.service.impl;
//
//import cn.hnist.bookmanager.BookManagerApplication;
//import org.junit.jupiter.api.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import static org.junit.jupiter.api.Assertions.*;
//
///**
// * @author whg
// * @date 2019/12/12 22:25
// **/
//@RunWith(SpringRunner.class)
//@SpringBootTest(classes = BookManagerApplication.class)
//class RedisServiceImplTest {
//
//    @Autowired
//    private RedisStringServiceImpl redisService;
//
//    @Test
//    void set() {
//        redisService.set("code","1234");
//    }
//
//    @Test
//    void get() {
//        System.out.println(redisService.get("code"));
//    }
//
//    @Test
//    void expire() {
//        boolean code = redisService.expire("code", 10);
//        System.out.println(code);
//    }
//
//    @Test
//    void remove() {
//        redisService.remove("code");
//    }
//
//    @Test
//    void increment() {
//        redisService.increment("code",10);
//    }
//}