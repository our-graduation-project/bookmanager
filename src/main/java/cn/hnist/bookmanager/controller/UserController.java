package cn.hnist.bookmanager.controller;


import cn.hnist.bookmanager.component.RegisterComponent;
import cn.hnist.bookmanager.model.User;
import cn.hnist.bookmanager.service.UserService;
import cn.hnist.bookmanager.utils.APIResult;
import cn.hnist.bookmanager.utils.LogUtils;
import cn.hnist.bookmanager.utils.TokenUtil;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.HttpCookie;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Controller
public class UserController{

    @Autowired
    private UserService userService;

    @Autowired
    private RegisterComponent registerUtil;
    /**
     * 用户登录
     * @param map1
     * @param response
     * @return
     */
    @RequestMapping("user/login")
    @ResponseBody
    public APIResult login(@RequestBody Map map1,
                        HttpServletResponse response){
        System.out.println("用户登录");
        Logger log1 = LogUtils.getBussinessLogger();
        User user=new User();
        user.setMailbox(map1.get("mailbox").toString());
        System.out.println(map1.get("mailbox").toString());
        user.setUserPwd(map1.get("userPwd").toString());
        User login = userService.loginUser(user);
        if (login!=null){
            Map map=new HashMap();
            map.put("userId",login.getUserId());
            map.put("userName",login.getUserName());
            map.put("right","user");
            String  s= TokenUtil.becomeToken(map);
            Cookie token = new Cookie("token",s);
            token.setMaxAge(360*24*7);
            response.addCookie(token);
            log1.info("getBussinessLogger===用户登录");
            LogUtils.getControllerLogger().info("getControllerLogger===用户登录");
            return new APIResult(true);
        }else {
            LogUtils.getControllerLogger().info("getControllerLogger===用户登录失败");
            User user1 = userService.searchUserBymailbox(user.getMailbox());

            if (user1==null){
                return new APIResult("邮箱不存在",false,400);
            }

            return new APIResult(false);
        }
    }

    /**
     * 发送验证码到邮箱
     * @param user
     * @return
     */
    @RequestMapping("user/forgetPassWord")
    @ResponseBody
    public APIResult forgetPassWord(@RequestBody User user){
        String s = registerUtil.sendCodeToEmail(user.getMailbox());
        APIResult apiResult=new APIResult(true,200);
        apiResult.setMessage("5分钟内验证码有效");
        return apiResult;
    }

    /**
     * 修改密码
     * @param jsonObject
     * @return
     */
    @RequestMapping("user/updatepassword")
    @ResponseBody
    public APIResult updatePassWord(@RequestBody JSONObject jsonObject){
        String verificationCode=(String) jsonObject.get("verificationCode");
        String mailbox = (String) jsonObject.get("mailbox");
        String password = (String) jsonObject.get("password");
        String codeByEmail = registerUtil.getCodeByEmail(mailbox);
        if (codeByEmail==null){
            APIResult apiResult=new APIResult(false,200);
            apiResult.setMessage("验证码不正确");
            return apiResult;
        }
        if (!codeByEmail.equals(verificationCode)){
            APIResult apiResult=new APIResult(false,200);
            apiResult.setMessage("验证码不正确");
            return apiResult;
        }
        User user=new User();
        user.setMailbox(mailbox);
        user.setUserPwd(password);
        int i = userService.updatePasswordByMailbox(user);
        boolean isFlag=false;
        if (i>0){
            isFlag=true;
        }
        APIResult apiResult=new APIResult(isFlag,200);
        return apiResult;
    }

    /**
     * 根据用户编号查询用户
     * @param
     * @return
     */
    @RequestMapping("user/searchUserByUserId")
    @ResponseBody
    public APIResult searchUserByUserId(HttpServletRequest request){
        Cookie[] cookies = request.getCookies();
        Cookie cookie = null;
        for (Cookie c:cookies){
            if(c.getName().equals("token")){
                System.out.println("拿到token");
                cookie = c;
            }
        }
        String s = cookie.getValue();
        int tokenValue = (int) TokenUtil.getTokenValue(s, "userId");
        System.out.println("tokenValue = " + tokenValue);
        User user = userService.searchUserById(tokenValue);
        APIResult apiResult = new APIResult();
        apiResult.setData(user);
        return apiResult;
    }

    /**
     * 加载用户列表
     * @param page
     * @param pageSize
     * @return
     */
    @RequestMapping("/loadUsers")
    public ModelAndView searchAllUsers(@RequestParam(value = "page",defaultValue = "0") int page,
                                       @RequestParam(value = "pageSize",defaultValue = "3") int pageSize){
        System.out.println("page:"+page);
        System.out.println("pageSize："+pageSize);
        PageInfo<User> pageInfo = userService.searchUsers(page, pageSize);
        System.out.println("pageInfo = " + pageInfo);
        ModelAndView model=new ModelAndView("admin/userlist");
        model.addObject("pageInfo",pageInfo);
        return model;
    }

    /**
     * 跳入增加页面
     * @return
     */
    @RequestMapping("/touseradd")
    public String toaddUser(){
        return "admin/useradd";
    }
    /**
     * 增加用户
     * @param user
     * @return
     */
    @RequestMapping("/admin/addUser")
    @ResponseBody
    public APIResult addUsers(@RequestBody User user){
        if (user==null||user.getSex()==null||user.getPhone()==null
                ||user.getAge()==null||user.getMailbox()==null
                ||user.getUserName()==null||user.getUserPwd()==null){
            return new APIResult("参数有误",false,400);
        }
        boolean b = userService.addUser(user);
        return new APIResult(b,200);
    }

    /**
     * 根据ID删除用户
     * @param userId
     * @param page
     * @param response
     * @return
     */

    @RequestMapping("/deleteUser")
    public ModelAndView deleteUsers(@RequestParam("userId") Integer userId,
                                    @RequestParam(value = "page",defaultValue = "0") int page,
                                    HttpServletResponse response) {
        boolean b = userService.deleteUser(userId);
        try {
            response.sendRedirect("loadUsers");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 根据name查询用户
     * @param page
     * @param pageSize
     * @param userName
     * @return
     */

    @RequestMapping("/tosearchuserByName")
    public ModelAndView searchUserByName(@RequestParam(value = "page",defaultValue = "0") int page,
                                         @RequestParam(value = "pageSize",defaultValue = "6") int pageSize,
                                         @RequestParam("userName") String userName){
        PageInfo<User> pageInfo = userService.searchUserByName(page, pageSize, userName);
        ModelAndView model=new ModelAndView("admin/userlist");
        model.addObject("pageInfo",pageInfo);
        return model;
    }

    /**
     * 跳入修改页面
     * @param userId
     * @return
     */
    @RequestMapping("/toEdituser")
    public ModelAndView searchUserById(@RequestParam("userId") Integer userId){
        User user = userService.searchUserById(userId);
        ModelAndView model=new ModelAndView("admin/useredit");
        model.addObject("user",user);
        return model;
    }


    /**
     * 修改用户
     * @param user
     * @return
     */
    @RequestMapping("user/admin/edituser")
    @ResponseBody
    public APIResult editLabel(@RequestBody User user){
        if(user == null || user.getUserId()==null || user.getUserName()== null
                ||user.getUserPwd()==null||user.getMailbox()==null||user.getPhone()==null||
                user.getAge()==null||user.getSex()==null){
            return new APIResult("参数有误",false,400);
        }
        boolean b = userService.updateUser(user);
        return new APIResult(b,200);
    }

}
