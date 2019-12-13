package cn.hnist.bookmanager.controller;

import cn.hnist.bookmanager.BookManagerApplication;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.servlet.http.HttpSession;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @Author: Zxw
 * @Description:
 * @Date: Created in 20:22 2019/12/11
 * @Modifued By:
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = BookManagerApplication.class)
class ManagerControllerTest {


    @Autowired
    private ManagerController managerController;

    @Test
    void forLogin() {
        managerController.forLogin();
    }

//    @Test
//    void login() {
//        String login = managerController.login("123@qq.com", "123456");
//        System.out.println(login);
//    }
}