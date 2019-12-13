package cn.hnist.bookmanager.service.impl;

import cn.hnist.bookmanager.mapper.AnnouncementMapper;
import cn.hnist.bookmanager.mapper.BorrowDetailMapper;
import cn.hnist.bookmanager.model.Announcement;
import cn.hnist.bookmanager.model.AnnouncementExample;
import cn.hnist.bookmanager.model.BorrowDetail;
import cn.hnist.bookmanager.service.AnnouncementService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 向清润
 * @createTime 2019.12.11.10:43
 */
@Service
public class AnnouncementServiceImpl implements AnnouncementService {

    @Autowired
    @Resource(type = AnnouncementMapper.class)
    AnnouncementMapper announcementMapper;



    /**
     * 添加公告
     *
     * @param announcement 公告实体
     * @return 受影响行数
     */
    @Override
    public int addAnnouncement(Announcement announcement) {
        int len = 0;
        len = announcementMapper.insert(announcement);
        return len;
    }

    /**
     * 修改删除状态，0为已经删除，1为启用
     *
     * @param id    公告的id
     * @param state 需要修改成什么状态
     * @return 受影响行数
     */
    @Override
    public int updateIsDelete(int id, int state) {
        int len = 0;
        Announcement announcement = searchOneAnnouncement(id);
        if(announcement!=null){
            announcement.setIsDelete(state);
            len = announcementMapper.updateByPrimaryKey(announcement);
        }

        return len;
    }

    /**
     * 修改公告
     *
     * @param announcement 修改的公告实体
     * @return 返回受影响行数
     */
    @Override
    public int updateAnnouncement(Announcement announcement) {
        int len = 0;
        if(announcement != null){
            len = announcementMapper.updateByPrimaryKeySelective(announcement);
        }

        return len;
    }

    /**
     * 分页查询所有的公告
     *
     * @param page     当前页
     * @param pageSize 页码大小
     * @return 返回查询出来的公告列表，如果没有则返回空列表
     */
    @Override
    public PageInfo<Announcement> searchAnnouncement(int page, int pageSize) {
        AnnouncementExample announcementExample = new AnnouncementExample();
        announcementExample.or();

        PageHelper.startPage(page,pageSize);

        List<Announcement> announcements = announcementMapper.selectByExampleWithBLOBs(announcementExample);

        PageInfo pageInfo = new PageInfo(announcements,5);
        return pageInfo;
    }

    /**
     * 根据状态分页查询公告
     *
     * @param page     当前页
     * @param pageSize 页面大小
     * @param isDlete  状态
     * @return 公告页码信息
     */
    @Override
    public PageInfo<Announcement> searchAnnouncement(int page, int pageSize, int isDlete) {
        AnnouncementExample announcementExample = new AnnouncementExample();
        announcementExample.or().andIsDeleteEqualTo(isDlete);
        PageHelper.startPage(page,pageSize);
        List<Announcement> announcements = announcementMapper.selectByExampleWithBLOBs(announcementExample);
        PageInfo pageInfo = new PageInfo(announcements,5);
        //announcementMapper.selectByExampleWithBLOBs()
        return pageInfo;
    }

    /**
     * 根据ID查询公告
     *
     * @param id 公告Id
     * @return 公告实体
     */
    @Override
    public Announcement searchOneAnnouncement(int id) {
        Announcement announcement = announcementMapper.selectByPrimaryKey(id);
        return announcement;
    }
}
