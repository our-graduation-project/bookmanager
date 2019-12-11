package cn.hnist.bookmanager.service.impl;

import cn.hnist.bookmanager.BookManagerApplication;
import cn.hnist.bookmanager.model.Manager;
import cn.hnist.bookmanager.service.ManagerService;
import com.github.pagehelper.PageInfo;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @Author: Zxw
 * @Description:
 * @Date: Created in 10:08 2019/12/11
 * @Modifued By:
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = BookManagerApplication.class)
class ManagerServieImplTest {


    @Autowired
    private ManagerService managerService;

    @Test
    void login() {
        Manager manager = new Manager();
        manager.setMailbox("123@qq.com");
        manager.setManagerPwd("123456");
        boolean login = managerService.login(manager);
        System.out.println("----------login"+login);
    }

    @Test
    void addManager() {
        Manager manager = new Manager();
        manager.setManagerName("zzzzzzz");
        manager.setAge(11);
        manager.setManagerPwd("12345");
        manager.setMailbox("127895@qq.com");
        manager.setPhone("1548756563535");
        manager.setManagerNickname("zzzzzz");
        manager.setSex(1);
        int i = managerService.addManager(manager);
        System.out.println("----------i"+i);
    }

    @Test
    void updateManager() {
        Manager manager = new Manager();
        manager.setManagerName("12345");
        manager.setManagerId(1);
        int i = managerService.updateManager(manager);
        System.out.println("----------i"+i);
    }

    @Test
    void searchManagerByName() {
        PageInfo pageInfo = managerService.searchManagerByName(1, 1, "z");
        System.out.println("----------i"+pageInfo);
    }

    @Test
    void searchAllManager() {
        PageInfo pageInfo = managerService.searchAllManager(1, 1);
        System.out.println("----------i"+pageInfo.toString());
    }
}