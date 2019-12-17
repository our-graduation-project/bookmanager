package cn.hnist.bookmanager.service;

import cn.hnist.bookmanager.model.Reserve;
import com.github.pagehelper.PageInfo;

import java.util.List;
import java.util.Map;

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
     * @param userId
     * @param bookId
     * @return
     */
    int deleteReserve(int userId, int bookId);

    /**
     * 通过用户ID查询预约消息
     * @param userid
     * @return
     */
    PageInfo<Map> selectReserveByUserId(int page, int pageSize, int userid);

    /**
     * 查询所有预约消息
     * @return
     */
    PageInfo<Map> selectAllReserve(int page, int pageSize);
}
