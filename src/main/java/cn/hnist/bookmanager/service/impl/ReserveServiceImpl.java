package cn.hnist.bookmanager.service.impl;

import cn.hnist.bookmanager.mapper.ReserveMapper;
import cn.hnist.bookmanager.model.Reserve;
import cn.hnist.bookmanager.model.ReserveExample;
import cn.hnist.bookmanager.service.ReserveService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @Author tzf
 * @Date 2019/12/11 17:44
 * @Version 1.0
 */
@Service
public class ReserveServiceImpl implements ReserveService {

    @Autowired
    private ReserveMapper reserveMapper;

    /**
     * 增加预约消息
     * @param reserve
     * @return
     */
    @Override
    public int addReserve(Reserve reserve) {
        if(reserve != null){
          return reserveMapper.insert(reserve);
        }
        return 0;
    }

    /**
     * 根据用户ID和图书ID取消预约
     * @param userId
     * @param bookId
     * @return
     */
    @Override
    public int deleteReserve(int userId, int bookId) {
        ReserveExample reserveExample = new ReserveExample();
        reserveExample.or().andUserIdEqualTo(userId).andBookIdEqualTo(bookId);
        int i = reserveMapper.deleteByExample(reserveExample);
        return i;
    }


    /**
     * 通过用户id查询预约信息
     * @param page
     * @param pageSize
     * @param userid
     * @return
     */
    @Override
    public PageInfo<Map> selectReserveByUserId(int page, int pageSize, int userid) {
        PageHelper.startPage(page,pageSize);
        List<Map> reserves = reserveMapper.selectUserReserve(userid);
        PageInfo pageInfo = new PageInfo(reserves,3);
        return pageInfo;
    }


    /**
     * 查询所有预约信息
     * @param page 当前页
     * @param pageSize 页面大小
     * @return
     */
    @Override
    public PageInfo<Map> selectAllReserve(int page, int pageSize) {
        ReserveExample reserveExample = new ReserveExample();
        reserveExample.or();
        PageHelper.startPage(page,pageSize);
        List<Map> reserves = reserveMapper.selectReserveList();
        PageInfo pageInfo = new PageInfo(reserves,3);
        return pageInfo;
    }


}

