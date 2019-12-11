package cn.hnist.bookmanager.service.impl;

import cn.hnist.bookmanager.BookManagerApplication;
import cn.hnist.bookmanager.model.LabelRelation;
import cn.hnist.bookmanager.service.LabelRelationService;
import com.github.pagehelper.PageInfo;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


/**
 * @Author: Zxw
 * @Description:
 * @Date: Created in 15:23 2019/12/11
 * @Modifued By:
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = BookManagerApplication.class)
class LabelRelationServiceImplTest {

    @Autowired
    LabelRelationService labelRelationService;

    @Test
    void addLabelRelation() {
        LabelRelation labelRelation = new LabelRelation();
        labelRelation.setLabelId(1);
        labelRelation.setBookId(1);
        int i = labelRelationService.addLabelRelation(labelRelation);
        System.out.println("------i"+i);
    }

    @Test
    void deleteLabelRelation() {
        int i = labelRelationService.deleteLabelRelation(1);
        System.out.println("------i"+i);
    }

    @Test
    void updateLabelRelation() {
        LabelRelation labelRelation = new LabelRelation();
        labelRelation.setLabelId(2);
        labelRelation.setBookId(2);
        labelRelation.setLabelRelationId(2);
        int i = labelRelationService.updateLabelRelation(labelRelation);
        System.out.println("------i"+i);
    }

    @Test
    void searchLabelRelation() {
        PageInfo pageInfo = labelRelationService.searchLabelRelation(1, 2);
        System.out.println(pageInfo);
    }
}