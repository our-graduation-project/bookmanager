package cn.hnist.bookmanager.controller;


import cn.hnist.bookmanager.model.User;
import cn.hnist.bookmanager.service.UserService;
import cn.hnist.bookmanager.utils.APIResult;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class UserController{
    @Autowired
    private UserService userService;

    @RequestMapping("/loginUser")
    public ModelAndView loginUser(User user){
         boolean login = userService.loginUser(user);
         ModelAndView model=new ModelAndView("redirect:index");
         model.addObject("user",login);
         return model;
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
     * @param userId
     * @return
     */
    @RequestMapping("/toEdituser")
    public ModelAndView searchUserById(@RequestParam("userId") Integer userId){
        User user = userService.searchUserById(userId);
        ModelAndView model=new ModelAndView("admin/useradd");
        model.addObject("user",user);
        return model;
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
     * 跳入修改页面
     * @param page
     * @param pageSize
     * @param userName
     * @return
     */

    @RequestMapping("/toEdituserByName")
    public ModelAndView searchUserByName(@RequestParam(value = "page",defaultValue = "0") int page,
                                         @RequestParam(value = "pageSize",defaultValue = "6") int pageSize,
                                         @RequestParam("userName") String userName){
        PageInfo<User> pageInfo = userService.searchUserByName(page, pageSize, userName);
        ModelAndView model=new ModelAndView("admin/useredit");
        model.addObject("pageInfo",pageInfo);
        return model;
    }

    /**
     * 修改用户
     * @param user
     * @return
     */
    @RequestMapping("/admin/edituser")
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
