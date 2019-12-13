package cn.hnist.bookmanager.service.impl;

import cn.hnist.bookmanager.mapper.LoseMapper;
import cn.hnist.bookmanager.model.Lose;
import cn.hnist.bookmanager.model.LoseExample;
import cn.hnist.bookmanager.service.LoseService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @author 向清润
 * @createTime 2019.12.11.14:51
 */

@Service
public class LoseServiceImpl implements LoseService {

    @Autowired
    @Resource(type = LoseMapper.class)
    LoseMapper loseMapper;
    /**
     * 管理员添加遗失信息
     *
     * @param lose 遗失信息实体
     * @return 受影响行数
     */
    @Override
    public int addLose(Lose lose) {
        int len = 0;
        len = loseMapper.insert(lose);
        return len;
    }

    /**
     * 根据用户Id查询遗失信息
     *
     * @param page     当前页
     * @param pageSize 页面大小
     * @param userId   用户Id
     * @return 该用户所有遗失信息的列表
     */
    @Override
    public PageInfo<Lose> searchLoseByUserId(int page, int pageSize, int userId) {
        LoseExample loseExample = new LoseExample();
        loseExample.or().andUserIdEqualTo(userId);
        PageHelper.startPage(page,pageSize);
        List<Lose> loses = loseMapper.selectByExample(loseExample);
        PageInfo pageInfo = new PageInfo(loses,3);
        return pageInfo;
    }

    /**
     * 根据遗失信息Id查询遗失信息
     *
     * @param loseId 遗失信息Id
     * @return 遗失信息实体
     */
    @Override
    public Lose searchLoseByLoseId(int loseId) {

        Lose lose = loseMapper.selectByPrimaryKey(loseId);
        return lose;
    }

    /**
     * 查询
     *
     * @param page     当前页
     * @param pageSize 页面大小
     * @return 遗失数据列表
     */
    @Override
    public PageInfo<Map> searchLose(int page, int pageSize) {
        LoseExample loseExample = new LoseExample();
        loseExample.or();
        PageHelper.startPage(page,pageSize);
        List<Map> loses = loseMapper.selectLoseList();
        PageInfo pageInfo = new PageInfo(loses,3);
        return pageInfo;
    }
}
