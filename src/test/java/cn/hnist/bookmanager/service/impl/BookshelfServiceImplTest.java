package cn.hnist.bookmanager.service.impl;

import cn.hnist.bookmanager.BookManagerApplication;
import cn.hnist.bookmanager.model.Bookshelf;
import cn.hnist.bookmanager.service.BookshelfService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = BookManagerApplication.class)
class BookshelfServiceImplTest {

    @Autowired
    private BookshelfService bookshelfService;
    @Test
    void addBookshelf() {
        System.out.println(bookshelfService
                .addBookshelf(new Bookshelf
                        (1,"科技","三楼")));
        System.out.println(bookshelfService
                .addBookshelf(new Bookshelf
                        (2,"计算机","二楼")));
        System.out.println(bookshelfService
                .addBookshelf(new Bookshelf
                        (3,"数学","一楼")));
    }

    @Test
    void deleteBookshelf() {
        System.out.println(bookshelfService.deleteBookshelf(3));
    }

    @Test
    void updateBookshelf() {
        System.out.println(bookshelfService.
                updateBookshelf(new Bookshelf
                        (1,"语文","五楼")));
    }

    @Test
    void searchBookshelfByName() {
        System.out.println(bookshelfService.searchBookshelfByName("科技"));
    }

    @Test
    void searchBookshelf() {
        System.out.println(bookshelfService.searchBookshelf());
    }

    @Test
    void queryBookshelfByPage() {
        System.out.println(bookshelfService.queryBookshelfByPage(1,2));
    }
}