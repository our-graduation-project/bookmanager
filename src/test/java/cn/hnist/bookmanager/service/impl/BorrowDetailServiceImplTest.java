package cn.hnist.bookmanager.service.impl;

import cn.hnist.bookmanager.BookManagerApplication;
import cn.hnist.bookmanager.model.BorrowDetail;
import com.github.pagehelper.PageInfo;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author 向清润
 * @createTime 2019.12.11.9:35
 */

@RunWith(SpringRunner.class)
@SpringBootTest(classes = BookManagerApplication.class)
class BorrowDetailServiceImplTest {

    @Autowired
    BorrowDetailServiceImpl borrowDetailService;

    @Test
    void addBrrowDetail() {
        BorrowDetail borrowDetail = new BorrowDetail();

        for (int i = 0; i < 20; i++) {
            borrowDetail.setUserId(i);
            borrowDetail.setBookId(i);
            Date borrowDate = new Date();
            borrowDetail.setBorrowDate(borrowDate);
            borrowDetail.setStatus(1);

            Date returnDate = addDate(borrowDate,30);
            System.out.println(returnDate);
            borrowDetail.setShouldReturnDate(returnDate);
           System.out.println(borrowDetailService.searchOneBorrowDetail(1));
            int len = borrowDetailService.addBrrowDetail(borrowDetail);

            System.out.println("受影响行数"+len);
        }

    }

    /**
     * 增加天数
     * @param outDate 原来的时间
     * @param day 增加的天数
     * @return 新的时间
     */
    private Date addDate(Date outDate,int day){
        Calendar calendar=Calendar.getInstance();
        calendar.setTime(outDate);
        //long time = borrowDetail.getShouldReturnDate().getTime()+30*24*60*60*1000;
        calendar.add(Calendar.DATE,30);
        Date newDate = calendar.getTime();
        return newDate;
    }

    @Test
    void returnBook() {
        BorrowDetail borrowDetail = borrowDetailService.searchOneBorrowDetail(2);
        int len = borrowDetailService.returnBook(borrowDetail);
        System.out.println("受影响行数"+len);
    }

    @Test
    void renewalBook() {
        System.out.println("开始测试");
        for (int i = 55; i < 60; i++) {
            int len = borrowDetailService.renewalBook(i);
            System.out.println("受影响行数"+len);
        }

    }

    @Test
    void searchOverdueBorrowDetail() {
        PageInfo<BorrowDetail> pageInfo = borrowDetailService.searchOverdueBorrowDetail(1, 5);
        List<BorrowDetail> list = pageInfo.getList();
        for (BorrowDetail b:list) {
            System.out.println(b);
        }

    }

    @Test
    void searchOverdueBorrowDetail1() {
        List<BorrowDetail> list = borrowDetailService.searchOverdueBorrowDetail();
        for (BorrowDetail b:list
             ) {
            System.out.println(b);
        }
    }

    @Test
    void searchBorrowDetail() {
        PageInfo<BorrowDetail> pageInfo = borrowDetailService.searchBorrowDetail(3, 5);
        List<BorrowDetail> list = pageInfo.getList();
        for (BorrowDetail b :
                list) {
            System.out.println(b);
        }
    }

    @Test
    void searchBorrowDetailByUser() {
        PageInfo<BorrowDetail> pageInfo = borrowDetailService.searchBorrowDetailByUser(2,4,16);
        List<BorrowDetail> list = pageInfo.getList();
        for (BorrowDetail b :
                list) {
            System.out.println(b);
        }
    }

    @Test
    void searchOneBorrowDetail() {
        BorrowDetail borrowDetail = borrowDetailService.searchOneBorrowDetail(2);
        System.out.println(borrowDetail);
    }

    @Test
    void AboutToExpire(){

        List<BorrowDetail> borrowDetails = borrowDetailService.AboutToExpire();
        for (BorrowDetail b :
                borrowDetails) {
            System.out.println(b);
        }
    }

    @Test
    void AboutToExpire1(){
        PageInfo<BorrowDetail> pageInfo = borrowDetailService.AboutToExpire(1, 3);
        List<BorrowDetail> borrowDetails = pageInfo.getList();
        for (BorrowDetail b :
                borrowDetails) {
            System.out.println(b);
        }

    }

    @Test
    void updateOverdue(){
        int len = borrowDetailService.updateOverdue();
        System.out.println("受影响行数"+len);

    }
}