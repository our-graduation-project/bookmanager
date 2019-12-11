package cn.hnist.bookmanager.service;

import cn.hnist.bookmanager.model.Book;
import cn.hnist.bookmanager.model.Label;
import cn.hnist.bookmanager.model.LabelRelation;
import com.github.pagehelper.PageInfo;

/**
 * @Author: Zxw
 * @Description:
 * @Date: Created in 14:37 2019/12/11
 * @Modifued By:
 */
public interface LabelRelationService {

    /**
     * 新增标签关系
     * @param labelRelation
     * @return
     */
    int addLabelRelation(LabelRelation labelRelation);

    /**
     * 删除标签关系
     * @param labelRelationId
     * @return
     */
    int deleteLabelRelation(Integer labelRelationId);


    /**
     * 更新标签名
     * @param labelRelation
     * @return
     */
    int updateLabelRelation(LabelRelation labelRelation);


    /**
     * 通过page 分页查询标签关系
     * @param indexPage 页码
     * @param pageSize 查询数量
     * @return
     */
    PageInfo searchLabelRelation(Integer indexPage,Integer pageSize);


}
