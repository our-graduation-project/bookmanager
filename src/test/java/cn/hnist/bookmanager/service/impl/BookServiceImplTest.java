package cn.hnist.bookmanager.service.impl;

import cn.hnist.bookmanager.BookManagerApplication;
import cn.hnist.bookmanager.model.Book;
import cn.hnist.bookmanager.model.Booktype;
import cn.hnist.bookmanager.service.BookService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @Author tzf
 * @Date 2019/12/11 15:43
 * @Version 1.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = BookManagerApplication.class)
class BookServiceImplTest {

    @Autowired
    private BookService bookService;

    @Test
    void findBookById() {
        System.out.println(bookService.findBookById(1));
    }

    @Test
    void addBook() {
        Book book = new Book();
        book.setBookName("三国演义");
        book.setAuthor("罗贯中");
        book.setBookPicture("xxx");
        book.setBookType(1);
        book.setIntroduce("四大名著-三国演义");
        book.setPrice(20.0);
        book.setBookCount(100);
        book.setBookNum(100);
        book.setBookshelf(1);
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        String time = sdf.format(date);
        book.setBookIsbn(time);
        System.out.println(bookService.addBook(book));
    }

    @Test
    void deleteBook() {
        System.out.println(bookService.deleteBook(1));
    }

    @Test
    void updateBook() {
        Book book = new Book();
        book.setBookId(2);
        book.setBookName("三国演义");
        book.setAuthor("罗贯中");
        book.setBookPicture("xxx");
        book.setBookType(1);
        book.setIntroduce("四大名著-三国演义");
        book.setPrice(80.0);
        book.setBookCount(100);
        book.setBookNum(100);
        book.setBookshelf(1);
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        String time = sdf.format(date);
        book.setBookIsbn(time);
        System.out.println(bookService.updateBook(book.getBookId(),book));
    }

    @Test
    void selectBookIdByName() {
        System.out.println(bookService.selectBookIdByName("三国演义"));
    }

    @Test
    void selectBookByName() {
        System.out.println(bookService.selectBookByName("三国演义"));
    }

    @Test
    void selectBookByType() {
        Booktype booktype = new Booktype();
        booktype.setTypeId(1);
        booktype.setTypeName("小说");
        System.out.println(bookService.selectBookByType(booktype));
    }

    @Test
    void selectBookByAuthor() {
        System.out.println(bookService.selectBookByAuthor("罗贯中"));
    }

    @Test
    void selectBookByKeyWord() {
        System.out.println(bookService.selectBookByKeyWord("罗"));
        System.out.println(bookService.selectBookByKeyWord("三"));
    }

    @Test
    void selectBookByLabelName() {
        System.out.println(bookService.selectBookByLabelName("小说"));
    }

    @Test
    void selectAllBook() {
        System.out.println(bookService.selectAllBook());
    }

    @Test
    void queryBookByPage() {
        System.out.println(bookService.queryBookByPage(1,2).getList().size());
    }
}