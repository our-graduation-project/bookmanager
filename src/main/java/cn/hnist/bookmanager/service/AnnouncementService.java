package cn.hnist.bookmanager.service;

import cn.hnist.bookmanager.model.Announcement;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * 公告service,里面包含了所有关于公告的操作
 * @author 向清润
 * @createTime 2019.12.10.19:37
 */
public interface AnnouncementService {

    /**
     * 添加公告
     * @param announcement 公告实体
     * @return 受影响行数
     */
    int addAnnouncement(Announcement announcement);

    /**
     *修改删除状态，0为已经删除，1为启用
     * @param id 公告的id
     * @param state 需要修改成什么状态
     * @return 受影响行数
     */
    int isdelte(int id,int state);

    /**
     * 修改公告
     * @param announcement 修改的公告实体
     * @return 返回受影响行数
     */
    int updateAnnouncement(Announcement announcement);

    /**
     * 分页查询所有的公告
     * @param page 当前页
     * @param pageSize 页码大小
     * @return 返回查询出来的公告列表，如果没有则返回空列表
     */
    PageInfo<Announcement> searchAnnouncement(int page, int pageSize);

    /**
     * 根据状态分页查询公告
     * @param page 当前页
     * @param pageSize 页面大小
     * @param isDlete 状态
     * @return 公告页码信息
     */
    PageInfo<Announcement> searchAnnouncement(int page, int pageSize,int isDlete);


    /**
     * 根据ID查询公告
     * @param id 公告Id
     * @return 公告实体
     */
    Announcement searchOneAnnouncement(int id);

}
