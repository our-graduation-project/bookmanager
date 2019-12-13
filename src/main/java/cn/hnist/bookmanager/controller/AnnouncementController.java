package cn.hnist.bookmanager.controller;

import cn.hnist.bookmanager.model.Announcement;
import cn.hnist.bookmanager.service.impl.AnnouncementServiceImpl;
import cn.hnist.bookmanager.utils.APIResult;
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

/**
 * @author 向清润
 * @createTime 2019.12.11.19:57
 */
@Controller
public class AnnouncementController {
    @Autowired
    AnnouncementServiceImpl announcementService;

    @RequestMapping("/announcementList")
    //error代表错误,0代表正常，1代表删除失败，2代表启用失败，3代表添加失败，4代表修改失败
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

    @RequestMapping("/deleteannouncementList")
    //error代表错误,0代表正常，1代表删除失败，2代表启用失败，3代表添加失败，4代表修改失败
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


    @RequestMapping("/inUpdate")
    public ModelAndView inUpdate(@RequestParam(value = "announcementId") int id){
        ModelAndView modelAndView = new ModelAndView("/admin/updateAnnouncement");
        Announcement announcement1 = announcementService.searchOneAnnouncement(id);
        modelAndView.addObject("announcement",announcement1);
        return modelAndView;
    }

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
