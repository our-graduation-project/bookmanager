package cn.hnist.bookmanager.service;

import cn.hnist.bookmanager.model.Lose;
import com.github.pagehelper.PageInfo;

import java.util.Map;

/**
 * 遗失情况Service,里面包含了遗失信息的操作
 * @author 向清润
 * @createTime 2019.12.10.19:38
 */
public interface LoseService {

    /**
     * 管理员添加遗失信息
     * @param lose 遗失信息实体
     * @return 受影响行数
     */
    int addLose(Lose lose);

    /**
     * 根据用户Id查询遗失信息
     * @param page 当前页
     * @param pageSize 页面大小
     * @param userId 用户Id
     * @return 该用户所有遗失信息的列表
     */
    PageInfo<Lose> searchLoseByUserId(int page, int pageSize, int userId);

    /**
     * 根据遗失信息Id查询遗失信息
     * @param loseId 遗失信息Id
     * @return 遗失信息实体
     */
    Lose searchLoseByLoseId(int loseId);


    /**
     * 查询
     * @param page 当前页
     * @param pageSize 页面大小
     * @return
     */
    PageInfo<Map> searchLose(int page, int pageSize);


}
