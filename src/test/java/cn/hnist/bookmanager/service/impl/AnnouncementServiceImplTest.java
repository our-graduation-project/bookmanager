package cn.hnist.bookmanager.service.impl;

import cn.hnist.bookmanager.BookManagerApplication;
import cn.hnist.bookmanager.model.Announcement;
import com.github.pagehelper.PageInfo;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author 向清润
 * @createTime 2019.12.11.11:13
 */

@RunWith(SpringRunner.class)
@SpringBootTest(classes = BookManagerApplication.class)
class AnnouncementServiceImplTest {

    @Autowired

    AnnouncementServiceImpl announcementService;

    @Test
    void addAnnouncement() {
        for (int i = 0; i < 40; i++) {
            Announcement announcement = new Announcement();
            announcement.setAnnouncementName("新公告"+i);
            announcement.setAnnouncementContent("今天的作业写不完了啊"+i);
            announcement.setPublishDate(new Date());
            announcement.setIsDelete(1);
            int len = announcementService.addAnnouncement(announcement);
            System.out.println("受影响行数"+len);
        }

    }

    @Test
    void isdelte() {
        int len = announcementService.isdelte(2, 0);
        System.out.println(len);

    }

    @Test
    void updateAnnouncement() {
        Announcement announcement = new Announcement();
        announcement.setAnnouncementName("什么公告");
        announcement.setAnnouncementContent("快点写作业啊,不然写不完了");
        announcement.setPublishDate(new Date());
        announcement.setIsDelete(1);
        announcement.setAnnouncementId(3);
        int len = announcementService.updateAnnouncement(announcement);
        System.out.println(len);
    }

    @Test
    void searchAnnouncement() {
        PageInfo<Announcement> pageInfo = announcementService.searchAnnouncement(2, 3);
        List<Announcement> list = pageInfo.getList();
        for (Announcement a:
             list) {
            System.out.println(a);
        }
    }

    @Test
    void searchAnnouncement1() {
        PageInfo<Announcement> pageInfo = announcementService.searchAnnouncement(2, 3,0);
        List<Announcement> list = pageInfo.getList();
        for (Announcement a:
                list) {
            System.out.println(a);
        }
    }

    @Test
    void searchOneAnnouncement() {
        Announcement announcement = announcementService.searchOneAnnouncement(5);
        System.out.println(announcement);
    }
}