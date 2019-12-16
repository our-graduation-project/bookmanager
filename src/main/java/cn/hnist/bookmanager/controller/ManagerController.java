package cn.hnist.bookmanager.controller;

import cn.hnist.bookmanager.component.RegisterComponent;
import cn.hnist.bookmanager.model.Manager;
import cn.hnist.bookmanager.service.ManagerService;
import cn.hnist.bookmanager.utils.APIResult;
import cn.hnist.bookmanager.utils.LogUtils;
import cn.hnist.bookmanager.utils.TokenUtil;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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


    @Autowired
    private RegisterComponent registerComponent;


    /**
     * 到login主页面
     * @param
     * @return
     */
    @RequestMapping("/forlogin")
    public String forLogin(){
        return "/admin/login";
    }


    /**
     * 验证manager登录主页面
     * @param mailbox
     * @param managerPwd
     * @return
     */
    @RequestMapping("/login")
    public String login(@RequestParam("mailbox") String mailbox,@RequestParam("managerPwd") String managerPwd){
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
            return "/admin/main";
        }
        log1.info("getBussinessLogger===管理员登录");
        LogUtils.getControllerLogger().info("getControllerLogger===管理员登录");
        return "/admin/login";
    }

    /**
     * 到manager主页面
     * @param
     * @return
     */
    @RequestMapping("/main")
    public String forMain(){
        return "/admin/main";
    }

    /**
     * 到managerlist页面
     * @param
     * @return
     */
    @RequestMapping("/managerlist")
    public ModelAndView forManagerList(@RequestParam(value = "indexPage" ,defaultValue = "0") Integer indexPage,
                                 @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize){

        //这里我默认分页带大小为10
        PageInfo pageInfo = managerService.searchAllManager(indexPage, pageSize);
        ModelAndView modelAndView = new ModelAndView("/admin/managerlist");
        modelAndView.addObject( "pageInfo", pageInfo);
        return modelAndView;
    }

    /**
     * 到添加manager记录页面
     * @param
     * @return
     */
    @RequestMapping("/formanageradd")
    public String forManagerAdd(){
        return "admin/manageradd";
    }

    /**
     * 添加manager记录
     * @param manager
     * @return
     */
    @RequestMapping("/admin/manageradd")
    @ResponseBody
    public APIResult ManagerAdd(@RequestBody Manager manager){
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

    /**
     * 到修改manager记录页面
     * @param managerId
     * @return
     */
    @RequestMapping("/tomanagermodify")
    public ModelAndView toManagerModify(@RequestParam("managerId") int managerId){
        Manager manager = managerService.searchManagerById(managerId);
        ModelAndView modelAndView = new ModelAndView("admin/manageredit");
        modelAndView.addObject("manager",manager);
        return modelAndView;
    }

    /**
     * 修改manager记录
     * @param manager
     * @return
     */

    @RequestMapping("/admin/managermodify")
    @ResponseBody
    public APIResult managerModify(@RequestBody Manager manager){
        int i = managerService.updateManager(manager);
        boolean flg = false;
        if(i > 0){
            flg = true;
        }
        return new APIResult(flg,200);
    }


    /**
     * 到添加manager记录页面
     * @param
     * @return
     */
    @RequestMapping("/toforgetpassword")
    public String toForgetPassword(){
        return "admin/forgetpassword";
    }


    @RequestMapping("/admin/forgetpassword")
    @ResponseBody
    public APIResult forgetPassword(@RequestBody Manager manager){
        registerComponent.sendCodeToEmail(manager.getMailbox());
        APIResult apiResult = new APIResult(true,200);
        apiResult.setMessage("5分钟内验证码有效");
        return apiResult;
    }

    @RequestMapping("/admin/updatepassword")
    @ResponseBody
    public APIResult updatepassword(@RequestBody JSONObject jsonObject){
        String verificationCode = (String) jsonObject.get("verificationCode");
        String mailbox = (String) jsonObject.get("mailbox");
        String password = (String) jsonObject.get("password");
        String codeByEmail = registerComponent.getCodeByEmail(mailbox);
        if(codeByEmail == null){
            APIResult apiResult = new APIResult(false,200);
            apiResult.setMessage("验证码不正确");
            return apiResult;
        }

        if(!codeByEmail.equals(verificationCode)){
            APIResult apiResult = new APIResult(false,200);
            apiResult.setMessage("验证码不正确");
            return apiResult;
        }
        Manager manager = new Manager();
        manager.setMailbox(mailbox);
        manager.setManagerPwd(password);
        int i = managerService.updatePasswordByMailbox(manager);
        boolean flg = false;
        if(i > 0){
            flg = true;
        }
        APIResult apiResult = new APIResult(flg,200);
        return apiResult;
    }

}