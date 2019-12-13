package cn.hnist.bookmanager.service.impl;

import cn.hnist.bookmanager.mapper.LabelRelationMapper;
import cn.hnist.bookmanager.model.LabelRelation;
import cn.hnist.bookmanager.model.LabelRelationExample;
import cn.hnist.bookmanager.service.LabelRelationService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: Zxw
 * @Description:
 * @Date: Created in 14:44 2019/12/11
 * @Modifued By:
 */
@Service
public class LabelRelationServiceImpl implements LabelRelationService {

    @Autowired
    private LabelRelationMapper labelRelationMapper;

    /**
     * 添加标签关系
     *
     * @param labelRelation 标签关系
     * @return
     */
    @Override
    public int addLabelRelation(LabelRelation labelRelation) {
        return labelRelationMapper.insert(labelRelation);
    }

    /**
     * 删除标签关系
     *
     * @param labelRationId 标签关系id
     * @return
     */
    @Override
    public int deleteLabelRelation(Integer labelRationId) {
        return  labelRelationMapper.deleteByPrimaryKey(labelRationId);
    }

    /**
     * 更新标签关系
     *
     * @param labelRelation 标签关系
     * @return
     */
    @Override
    public int updateLabelRelation(LabelRelation labelRelation) {
        return labelRelationMapper.updateByPrimaryKey(labelRelation);
    }

    /**
     * 通过page分页查询标签关系
     *
     * @param indexPage 页码
     * @param pageSize 查询数量
     * @return
     */
    @Override
    public PageInfo searchLabelRelation(Integer indexPage,Integer pageSize) {
        LabelRelationExample relationExample = new LabelRelationExample();
        relationExample.or();
        PageHelper.startPage(indexPage,pageSize);
        List<LabelRelation> labelRelations = labelRelationMapper.selectByExample(relationExample);
        PageInfo pageInfo = new PageInfo(labelRelations);
        return pageInfo;
    }
}