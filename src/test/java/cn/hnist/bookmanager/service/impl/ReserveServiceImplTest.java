package cn.hnist.bookmanager.service.impl;

import cn.hnist.bookmanager.BookManagerApplication;
import cn.hnist.bookmanager.model.Reserve;
import cn.hnist.bookmanager.service.ReserveService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @Author tzf
 * @Date 2019/12/11 19:07
 * @Version 1.0
 */

@RunWith(SpringRunner.class)
@SpringBootTest(classes = BookManagerApplication.class)
class ReserveServiceImplTest {

    @Autowired
    private ReserveService reserveService;

    @Test
    void addReserve() {
        Reserve reserve = new Reserve();
        reserve.setUserId(1);
        reserve.setBookId(3);
        Date date = new Date();
        reserve.setReserveDate(date);
        int i = reserveService.addReserve(reserve);
        System.out.println(i);
    }

    @Test
    void deleteReserve() {
        System.out.println(reserveService.deleteReserve(1));
    }

    @Test
    void selectReserveByUserId() {
        System.out.println(reserveService.selectReserveByUserId(1));
    }

    @Test
    void selectAllReserve() {
        System.out.println(reserveService.selectAllReserve());
    }
}