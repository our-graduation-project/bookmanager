package cn.hnist.bookmanager.controller;

import cn.hnist.bookmanager.model.Reserve;
import cn.hnist.bookmanager.model.User;
import cn.hnist.bookmanager.service.ReserveService;
import cn.hnist.bookmanager.service.impl.BookServiceImpl;
import cn.hnist.bookmanager.service.impl.UserServiceImpl;
import cn.hnist.bookmanager.utils.APIResult;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * @Author tzf
 * @Date 2019/12/16 19:07
 * @Version 1.0
 */
@Controller
public class ReserveController {
    @Autowired
    ReserveService reserveService;

    @Autowired
    UserServiceImpl userService;

    @Autowired
    BookServiceImpl bookService;

    @RequestMapping("/reserveList")
    public ModelAndView reserveList(@RequestParam(value = "page",defaultValue = "1") int page){
        ModelAndView modelAndView = new ModelAndView("/admin/reservelist");
        PageInfo<Map> pageInfo = reserveService.selectAllReserve(1,10);
        modelAndView.addObject("pageInfo",pageInfo);
        return modelAndView;
    }

    @RequestMapping("/userReservedetail")
    public ModelAndView userReservedetail(@RequestParam(value = "userName",defaultValue = "") String userName,@RequestParam(value = "page",defaultValue = "1") int page){
        ModelAndView modelAndView ;
        System.out.println(userName);
        if(userName!=null&&!"".equals(userName)){
            modelAndView= new ModelAndView("/admin/reservelist");
            System.out.println(userName);
            PageInfo<User> pageInfo = userService.searchUserByName(1, 1, userName);
            Integer userId = pageInfo.getList().get(0).getUserId();

            PageInfo<Map> mapPageInfo = reserveService.selectReserveByUserId(1, 10, userId);
            modelAndView.addObject("pageInfo",mapPageInfo);
            modelAndView.addObject("userName",userName);


        }else {
            System.out.println("开始");
            modelAndView = reserveList(1);
        }

        return modelAndView;
    }

    @RequestMapping("/deleteUserReserve")
    @ResponseBody
    public APIResult deleteUserReserve(@RequestBody JSONObject jsonParam,
                                 HttpServletResponse response){
        PageInfo<User> user = userService.searchUserByName(1, 1, (String) jsonParam.get("username"));
        Integer userId = user.getList().get(0).getUserId();
        int bookId = bookService.selectBookIdByName((String) jsonParam.get("bookname"));
        int i = reserveService.deleteReserve(userId,bookId);
        Boolean aBoolean = i>0;
        return new APIResult(aBoolean,200);

    }
}
