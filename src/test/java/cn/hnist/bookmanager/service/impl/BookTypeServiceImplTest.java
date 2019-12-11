package cn.hnist.bookmanager.service.impl;

import cn.hnist.bookmanager.BookManagerApplication;
import cn.hnist.bookmanager.model.Booktype;
import cn.hnist.bookmanager.service.BookTypeService;
import com.github.pagehelper.PageInfo;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @Author: Zxw
 * @Description:
 * @Date: Created in 20:59 2019/12/10
 * @Modifued By:
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = BookManagerApplication.class)
class BookTypeServiceImplTest {

    @Autowired
    private BookTypeService bookTypeService;

    @Test
    void deleteBookType() {
        int i = bookTypeService.deleteBookType(6);
        System.out.println("------------------i:"+i);
    }

    @Test
    void addBook() {
        Booktype booktype = new Booktype();
        booktype.setTypeId(6);
        booktype.setTypeName("bb");
        int i = bookTypeService.addBook(booktype);
        System.out.println("------------------i:"+i);
    }

    @Test
    void updateBookType() {
        Booktype booktype = new Booktype();
        booktype.setTypeId(5);
        booktype.setTypeName("basic");
        int i = bookTypeService.updateBookType(booktype);
        System.out.println("------------------------i:"+i);
    }

    @Test
    void searchBookTypeByName() {
        PageInfo pageInfo = bookTypeService.searchBookTypeByName(1, 1, "c");
        System.out.println(pageInfo.toString());

    }

    @Test
    void searchAllBookType() {
        PageInfo pageInfo = bookTypeService.searchAllBookType(1, 2);
        System.out.println(pageInfo.toString());

    }

}