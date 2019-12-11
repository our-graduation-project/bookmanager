package cn.hnist.bookmanager.service;

import cn.hnist.bookmanager.model.Reserve;

import java.util.List;

/**
 * @Author tzf
 * @Date 2019/12/11 9:06
 * @Version 1.0
 */
public interface ReserveService {

    /**
     * 增加预定信息
     * @param reserve
     * @return
     */
    int addReserve(Reserve reserve);

    /**
     * 取消预约时删除预约消息
     * @param reserveId
     * @return
     */
    int deleteReserve(int reserveId);

    /**
     * 通过用户ID查询预约消息
     * @param userid
     * @return
     */
    List<Reserve> selectReserveByUserId(int userid);

    /**
     * 查询所有预约消息
     * @return
     */
    List<Reserve> selectAllReserve();
}
