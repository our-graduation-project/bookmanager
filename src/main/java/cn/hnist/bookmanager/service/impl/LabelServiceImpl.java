package cn.hnist.bookmanager.service.impl;

import cn.hnist.bookmanager.mapper.LabelMapper;
import cn.hnist.bookmanager.model.Label;
import cn.hnist.bookmanager.model.LabelExample;
import cn.hnist.bookmanager.service.LabelService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author whg
 * @date 2019/12/10 19:50
 **/
@Service
public class LabelServiceImpl implements LabelService {

    @Autowired
    private LabelMapper labelMapper;

    /**
     * 新增标签
     *
     * @param label
     * @return
     */
    @Override
    public Boolean addLabel(Label label) {
        if(label != null && label.getLabelName() != null){
            return labelMapper.insert(label) > 0 ? true : false;
        }

        return false;
    }

    /**
     * 删除标签
     *
     * @param id
     * @return
     */
    @Override
    public Boolean deleteLabel(int id) {

        return labelMapper.deleteByPrimaryKey(id) > 0 ? true : false;
    }

    /**
     * 通过标签id更新标签名
     * @param id
     * @param name
     * @return
     */
    @Override
    public Boolean updateLabel(int id, String name) {
        LabelExample labelExample = new LabelExample();
        labelExample.or().andLabelIdEqualTo(id);
        int i = labelMapper.updateByExample(new Label(id,name), labelExample);
        return i > 0 ? true : false;
    }

    /**
     * 通过标签名来搜索标签id
     *
     * @param name
     * @return
     */
    @Override
    public Integer selectLabelIdByName(String name) {
        LabelExample labelExample = new LabelExample();
        labelExample.or().andLabelNameEqualTo(name);
        List<Label> labels = labelMapper.selectByExample(labelExample);
        if(labels != null && !labels.isEmpty()){
            return labels.get(0).getLabelId();
        }
        return null;
    }

    /**
     * 通过id查询标签名
     *
     * @param id
     * @return
     */
    @Override
    public Label selectLabelNameById(int id) {
        Label label = labelMapper.selectByPrimaryKey(id);
        return label;
    }

    /**
     * 查询所有标签
     *
     * @return
     */
    @Override
    public List queryAllLabel() {
        LabelExample labelExample = new LabelExample();
        labelExample.or();
        List<Label> labels = labelMapper.selectByExample(labelExample);
        return labels;
    }

    /**
     * 通过page分页查询标签
     *
     * @param page     页码
     * @param pageSize 查询数量
     * @return
     */
    @Override
    public PageInfo queryLabelByPage(int page, int pageSize) {
        PageHelper.startPage(page,pageSize);
        LabelExample labelExample = new LabelExample();
        labelExample.or();
        List<Label> labels = labelMapper.selectByExample(labelExample);
        PageInfo pageInfo = new PageInfo(labels,5);
        return pageInfo;

    }
}
