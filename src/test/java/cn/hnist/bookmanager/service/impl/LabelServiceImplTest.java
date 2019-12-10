package cn.hnist.bookmanager.service.impl;

import cn.hnist.bookmanager.BookManagerApplication;
import cn.hnist.bookmanager.model.Label;
import cn.hnist.bookmanager.service.LabelService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author whg
 * @date 2019/12/10 20:16
 **/
@RunWith(SpringRunner.class)
@SpringBootTest(classes = BookManagerApplication.class)
class LabelServiceImplTest {

    @Autowired
    private LabelService labelService;

    @Test
    void addLabel() {
        System.out.println("----");
//        System.out.println(labelService.addLabel(new Label( "科学")));
        System.out.println(labelService.addLabel(new Label( "科学")));
        System.out.println(labelService.addLabel(new Label( "外语")));
        System.out.println(labelService.addLabel(new Label( "科普")));
        System.out.println(labelService.addLabel(new Label( "高中")));
        System.out.println(labelService.addLabel(new Label( "初中")));
        System.out.println(labelService.addLabel(new Label( "大学")));
    }

    @Test
    void deleteLabel() {
        System.out.println(labelService.deleteLabel(1));
    }

    @Test
    void updateLabel() {
        System.out.println(labelService.updateLabel(2, "自然"));
    }

    @Test
    void selectLabelIdByName() {
        System.out.println(labelService.selectLabelIdByName("自然"));
    }

    @Test
    void selectLabelNameById() {
        System.out.println(labelService.selectLabelNameById(2));
    }

    @Test
    void queryAllLabel() {
        System.out.println(labelService.queryAllLabel());
    }

    @Test
    void queryLabelByPage() {
        System.out.println(labelService.queryLabelByPage(1, 5));
    }
}