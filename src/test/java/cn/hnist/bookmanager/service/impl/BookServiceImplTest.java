package cn.hnist.bookmanager.service.impl;

import cn.hnist.bookmanager.BookManagerApplication;
import cn.hnist.bookmanager.service.BookService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author whg
 * @date 2019/12/9 20:49
 **/
@RunWith(SpringRunner.class)
@SpringBootTest(classes = BookManagerApplication.class)
class BookServiceImplTest {

    @Autowired
    private BookService bookService;

    @Test
    void findBookById() {
        System.out.println(bookService.findBookById(1));
    }
}