package cn.hnist.bookmanager.service.impl;

import cn.hnist.bookmanager.mapper.ReserveMapper;
import cn.hnist.bookmanager.model.Reserve;
import cn.hnist.bookmanager.model.ReserveExample;
import cn.hnist.bookmanager.service.ReserveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
     * 根据预约ID取消预约时删除预约信息
     * @param reserveId
     * @return
     */
    @Override
    public int deleteReserve(int reserveId) {
        return reserveMapper.deleteByPrimaryKey(reserveId);
    }

    /**
     * 根据用户ID查询预约信息
     * @param userid
     * @return
     */
    @Override
    public List<Reserve> selectReserveByUserId(int userid) {
        ReserveExample reserveExample = new ReserveExample();
        reserveExample.or().andUserIdEqualTo(userid);
        return reserveMapper.selectByExample(reserveExample);
    }

    /**
     * 查询所有预约消息
     *
     * @return
     */
    @Override
    public List<Reserve> selectAllReserve() {
        ReserveExample reserveExample = new ReserveExample();
        reserveExample.or();
        List<Reserve> reserves = reserveMapper.selectByExample(reserveExample);
        return reserves;
    }
}

