package cn.hnist.bookmanager.controller;

import cn.hnist.bookmanager.model.Announcement;
import cn.hnist.bookmanager.service.impl.AnnouncementServiceImpl;
import cn.hnist.bookmanager.utils.APIResult;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.sun.deploy.net.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.Map;

/**
 * @author 向清润
 * @createTime 2019.12.11.19:57
 */
@Controller
public class AnnouncementController {
    @Autowired
    AnnouncementServiceImpl announcementService;


    /**
     * 公告列表分页查询
     * @param page 当前页码
     * @param pageSize 页码大小
     * @return 查询出来的数据与页面
     */
    @RequestMapping("/announcementList")
    public ModelAndView announcementList(@RequestParam(value = "page" ,defaultValue = "1") int page,@RequestParam(value = "pageSize" ,defaultValue = "10") int pageSize){
        if("1".equals(page)||page <= 0){
            page = 1;
        }

        if("10".equals(pageSize)||pageSize == 0){
            pageSize = 10;
        }
        PageInfo<Announcement> pageInfo = announcementService.searchAnnouncement(page, pageSize,1);
        ModelAndView modelAndView = new ModelAndView("admin/noticelist");
        modelAndView.addObject("pageInfo",pageInfo);
        //modelAndView.addObject("error",error);
        return modelAndView;

    }


    /**
     * 前台公告展示
     * @param map 传输过来的数据，如页码等
     * @return 返回查询到的数据
     */
    @RequestMapping("/indexAnnouncementList")
@ResponseBody
    public APIResult indexAnnouncementList(@RequestBody Map map){
        int page = (int) map.get("page");

        if("1".equals(page)||page <= 0){
            page = 1;
        }
        int pageSize = 5;

        PageInfo<Announcement> pageInfo = announcementService.searchAnnouncement(page, pageSize,1);

        String jsonObject = JSONObject.toJSONString(pageInfo);

        return new APIResult("没错",true,200,pageInfo);

    }


    /**
     * 查询删除的公告
     * @param page 当前页码
     * @param pageSize 页码大小
     * @return 数据与页面
     */
    @RequestMapping("/deleteannouncementList")
    public ModelAndView deleteannouncementList(@RequestParam(value = "page" ,defaultValue = "1") int page,@RequestParam(value = "pageSize" ,defaultValue = "10") int pageSize){
        if("1".equals(page)||page <= 0){
            page = 1;
        }

        if("10".equals(pageSize)||pageSize == 0){
            pageSize = 10;
        }
        PageInfo<Announcement> pageInfo = announcementService.searchAnnouncement(page, pageSize,0);
        ModelAndView modelAndView = new ModelAndView("admin/deletedNoticelist");
        modelAndView.addObject("pageInfo",pageInfo);
        //modelAndView.addObject("error",error);
        return modelAndView;

    }

    /**
     * 删除公告，将该公告状态改为0
     * @param id 该公告Id
     * @param page 当前页面
     * @param response
     */
    @RequestMapping("/deleteAnnouncement")
    public void deleteAnnouncement(@RequestParam(value = "announcementId") int id,@RequestParam(value = "page")int page, HttpServletResponse response){

        int len = announcementService.updateIsDelete(id, 0);
        if(len == 0){
            try {
                response.sendRedirect("/bookmanager/announcementList?error=1&page="+page);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    try {
        response.sendRedirect("/bookmanager/announcementList?page="+page);
    } catch (IOException e) {
        e.printStackTrace();
    }

    }

    /**
     * 还原删除公告，将该公告状态改为1
     * @param id 当前公告的id
     * @param page 当前页面大小
     * @param response
     */
    @RequestMapping("/recoveryAnnouncement")
    public void recoveryAnnouncement(@RequestParam(value = "announcementId") int id,@RequestParam(value = "page")int page, HttpServletResponse response){
        int len = announcementService.updateIsDelete(id, 1);
        if(len == 0){
            try {
                response.sendRedirect("/bookmanager/announcementList?error=2&page="+page);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            response.sendRedirect("/bookmanager/announcementList?page="+page);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * 添加公告
     * @param state 状态为Ok则添加，否则跳转至添加页面
     * @param announcement 公告信息
     * @return 页面与数据
     */
    @RequestMapping("/addAnnouncement")
    //state=ok说明已经填写了信息，准备提交
   public ModelAndView addAnnouncement(@RequestParam(value = "state",defaultValue = "no") String state,Announcement announcement){
        ModelAndView modelAndView;
        System.out.println(state+announcement+(!"Ok".equals(state)));
        if(state == null||!"OK".equals(state)){
            modelAndView = new ModelAndView("/admin/addAnnouncement");
        }else {
            int len = 0;
            if(announcement != null&&announcement.getAnnouncementName()!=null&&announcement.getAnnouncementContent()!=null){
                announcement.setIsDelete(1);
                announcement.setPublishDate(new Date());
               len = announcementService.addAnnouncement(announcement);
            }
            System.out.println(len);
            if(len > 0){
                modelAndView = announcementList(1,10);
            }else {
                modelAndView = announcementList(1,10);
            }

        }

        return modelAndView;
    }

    /**
     * 跳转至公告修改界面
     * @param id 公告Id
     * @return 页面与具体数据
     */
    @RequestMapping("/inUpdate")
    public ModelAndView inUpdate(@RequestParam(value = "announcementId") int id){
        ModelAndView modelAndView = new ModelAndView("/admin/updateAnnouncement");
        Announcement announcement1 = announcementService.searchOneAnnouncement(id);
        modelAndView.addObject("announcement",announcement1);
        return modelAndView;
    }


    /**
     * 修改公告
     * @param announcement 公告数据
     * @return 修改是否成功
     */
    @RequestMapping("/update")
    @ResponseBody
    //提交信息
    public APIResult updateAnnouncement(@RequestBody Announcement announcement){


            int len = 0;
            if(announcement != null&&announcement.getAnnouncementName()!=null&&announcement.getAnnouncementContent()!=null&&announcement.getAnnouncementId()!=null){
                announcement.setIsDelete(1);
                announcement.setPublishDate(new Date());
                len = announcementService.updateAnnouncement(announcement);
            }
            if(len > 0){
                return new APIResult(true, 200);
            }else {
                return new APIResult(false, 200);
            }


    }

}
