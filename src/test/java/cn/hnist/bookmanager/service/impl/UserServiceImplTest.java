package cn.hnist.bookmanager.service.impl;

import cn.hnist.bookmanager.BookManagerApplication;
import cn.hnist.bookmanager.model.User;
import cn.hnist.bookmanager.service.UserService;
import com.github.pagehelper.PageInfo;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = BookManagerApplication.class)
class UserServiceImplTest {

    @Autowired
    private UserService userService;
    @Test
    void loginUser() {
        System.out.println(userService.loginUser("lisi","qwe123"));
    }

    @Test
    void addUser() {
        System.out.println("-----------");
        System.out.println(userService.addUser
                (new User(1,"woqu","1761683537@qq.com",
                        "qwe123","152438975335",0,18)));
        System.out.println(userService.addUser
                (new User(2,"lisi","1761432101@qq.com",
                        "qwe123","15243896306",1,18)));
        System.out.println(userService.addUser
                (new User(3,"wangwu","1761432123@qq.com",
                        "qwe123","15243896305",0,18)));
    }


    @Test
    void deleteUser() {
        System.out.println(userService.deleteUser(1));
    }

    @Test
    void updateUser() {
        System.out.println(userService.updateUser(
                new User(6,"wangb","123456789@qq.com",
                        "1230","789456123",0,85)));
    }

    @Test
    void searchUserByName() {
        PageInfo pageInfo=userService.searchUserByName
                (1,1,"lisi");
        System.out.println(pageInfo.toString());
    }

    @Test
    void searchUserById() {
        System.out.println(userService.searchUserById(6));
    }

    @Test
    void searchUsers() {
        PageInfo pageInfo=userService.searchUsers(1,1);
        System.out.println(pageInfo.toString());
    }

}