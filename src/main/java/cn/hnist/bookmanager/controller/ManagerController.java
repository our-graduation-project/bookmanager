package cn.hnist.bookmanager.controller;

import cn.hnist.bookmanager.model.Manager;
import cn.hnist.bookmanager.service.ManagerService;
import cn.hnist.bookmanager.utils.APIResult;
import cn.hnist.bookmanager.utils.LogUtils;
import cn.hnist.bookmanager.utils.TokenUtil;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.slf4j.Logger;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: Zxw
 * @Description:
 * @Date: Created in 19:50 2019/12/11
 * @Modifued By:
 */

@Controller
public class ManagerController {

    @Autowired
    private ManagerService managerService;


    @RequestMapping("/forlogin")
    public String forLogin(){
        return "/admin/login";
    }


    @RequestMapping("/login")
    public String login(@RequestParam("mailbox") String mailbox,@RequestParam("managerPwd") String managerPwd){
        //System.out.println("come in");
        System.out.println("mailbox"+mailbox+"managerPwd"+managerPwd);
        Logger log = LogUtils.getExceptionLogger();
        Logger log1 = LogUtils.getBussinessLogger();
        Logger log2 = LogUtils.getDBLogger();
        Manager manager = new Manager();
        manager.setManagerPwd(managerPwd);
        manager.setMailbox(mailbox);
        Manager login = managerService.login(manager);
        if(login != null){
            Map map = new HashMap();
            map.put("managerName",login.getManagerName());
            map.put("right","admin");
            TokenUtil.becomeToken(map);
            log1.info("getBussinessLogger===管理员登录");
            LogUtils.getControllerLogger().info("getControllerLogger===管理员登录");
            System.out.println(login);
            return "/admin/main";
        }
        log1.info("getBussinessLogger===管理员登录");
        LogUtils.getControllerLogger().info("getControllerLogger===管理员登录");
        return "/admin/login";
    }

    @RequestMapping("/main")
    public String forMain(){
        return "/admin/main";
    }

    @RequestMapping("/managerlist")
    public ModelAndView forManagerList(@RequestParam(value = "page" ,defaultValue = "0") Integer indexPage,
                                 @RequestParam(value = "limit", defaultValue = "10") Integer limit){

        //这里我默认分页带大小为10
        PageInfo pageInfo = managerService.searchAllManager(indexPage, limit);
        ModelAndView modelAndView = new ModelAndView("/admin/managerlist");
        modelAndView.addObject( "pageInfo", pageInfo);
        return modelAndView;
    }

//    @RequestMapping("/formanageradd")
    @RequestMapping("/formanageradd")
    public String forManagerAdd(){
        return "admin/manageradd";
    }

    @RequestMapping("/admin/manageradd")
    @ResponseBody
    public APIResult ManagerAdd(@RequestBody Manager manager){
        System.out.println(manager);
        Manager manager1 = managerService.searchByMailBox(manager.getMailbox());
        if(manager1 != null){
            return new APIResult("邮箱已存在",false,400);
        }
        Manager manager2 = managerService.searchByManagerNickname(manager.getManagerNickname());
        if(manager2 != null){
            return new APIResult("管理员昵已存在",false,400);
        }

        Manager manager3 = managerService.searchByPhone(manager.getPhone());
        if(manager3 != null){
            return new APIResult("管理员电话已存在",false,400);
        }

        int i =  managerService.addManager(manager);
        boolean flg = false;
        if(i > 0){
            flg = true;
        }
        return new APIResult(flg,200);
    }

    @RequestMapping("/tomanagermodify")
    public ModelAndView toManagerModify(@RequestParam("managerId") int managerId){
        Manager manager = managerService.searchManagerById(managerId);
        ModelAndView modelAndView = new ModelAndView("admin/manageredit");
        modelAndView.addObject("manager",manager);
        return modelAndView;
    }


    @RequestMapping("/admin/managermodify")
    @ResponseBody
    public APIResult ManagerModify(@RequestBody Manager manager){
        System.out.println(manager);
        int i = managerService.updateManager(manager);
        System.out.println("i-----"+i);
        boolean flg = false;
        if(i > 0){
            flg = true;
        }
        return new APIResult(flg,200);
    }


}