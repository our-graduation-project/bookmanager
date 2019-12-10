package cn.hnist.bookmanager.service;

import cn.hnist.bookmanager.model.Label;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @author whg
 * @date 2019/12/10 19:35
 **/
public interface LabelService {

    /**
     * 新增标签
     * @param label
     * @return
     */
    Boolean addLabel(Label label);

    /**
     * 删除标签
     * @param id
     * @return
     */
    Boolean deleteLabel(int id);

    /**
     * 通过标签id更新标签名
     * @param id
     * @param name
     * @return
     */
    Boolean updateLabel(int id, String name);

    /**
     * 通过标签名来搜索标签id
     * 如果没有查到id则返回null
     * @param name
     * @return
     */
    Integer selectLabelIdByName(String name);

    /**
     * 通过id查询标签名
     * @param id
     * @return
     */
    String selectLabelNameById(int id);

    /**
     * 查询所有标签
     * @return
     */
    List queryAllLabel();

    /**
     * 通过page 查询标签
     * @param page 页码
     * @param pageSize 查询数量
     * @return
     */
    PageInfo queryLabelByPage(int page, int pageSize);
}
