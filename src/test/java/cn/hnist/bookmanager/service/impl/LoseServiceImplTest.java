package cn.hnist.bookmanager.service.impl;

import cn.hnist.bookmanager.BookManagerApplication;
import cn.hnist.bookmanager.model.Lose;
import com.github.pagehelper.PageInfo;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author 向清润
 * @createTime 2019.12.11.15:01
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = BookManagerApplication.class)
class LoseServiceImplTest {

    @Autowired
    LoseServiceImpl loseService;
    @Test
    void addLose() {
        for (int i = 0; i < 10; i++) {
            Lose lose = new Lose();
            lose.setBookId(i);
            lose.setUserId(i);
            lose.setFine(20.0);
            lose.setLoseDate(new Date());

            int len = loseService.addLose(lose);
            System.out.println(len);

        }
    }

    @Test
    void searchLoseByUserId() {
        PageInfo<Lose> pageInfo = loseService.searchLoseByUserId(1, 3, 3);
        List<Lose> list = pageInfo.getList();
        for (Lose l :
                list) {
            System.out.println(l);
        }
    }

    @Test
    void searchLoseByLoseId() {
        Lose lose = loseService.searchLoseByLoseId(3);
        System.out.println(lose);
    }

    @Test
    void searchLose() {
        PageInfo<Lose> pageInfo = loseService.searchLose(2, 5);
        List<Lose> list = pageInfo.getList();
        for (Lose l :
                list) {
            System.out.println(l);
        }
    }
}