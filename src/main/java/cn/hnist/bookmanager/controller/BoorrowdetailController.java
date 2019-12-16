package cn.hnist.bookmanager.controller;

import cn.hnist.bookmanager.model.BorrowDetail;
import cn.hnist.bookmanager.model.User;
import cn.hnist.bookmanager.service.impl.BorrowDetailServiceImpl;
import cn.hnist.bookmanager.service.impl.UserServiceImpl;
import cn.hnist.bookmanager.utils.APIResult;
import cn.hnist.bookmanager.utils.TokenUtil;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Map;

/**
 * @author 向清润
 * @createTime 2019.12.12.16:37
 */
@Controller
public class BoorrowdetailController {
    @Autowired
    BorrowDetailServiceImpl borrowDetailService;

    @Autowired
    UserServiceImpl userService;


    /**
     * 后台分页搜索所有借书单信息
     * @param page 搜索页码
     * @param state 目前状态
     * @param userName 用户名（如果有，则搜索该用户的借书单信息）
     * @return 返回页面
     */
    @RequestMapping("/borrowDetailList")
    public ModelAndView borrowDetailList(@RequestParam(value = "page",defaultValue = "1") int page,@RequestParam(value = "state",defaultValue = "0") int state,@RequestParam(value = "userName",defaultValue = "") String userName){
        if(state == 0){
        ModelAndView modelAndView = new ModelAndView("/admin/borrowdetail");
        PageInfo<Map> pageInfo = borrowDetailService.searchBorrowDetail(page, 10);
        modelAndView.addObject("pageInfo",pageInfo);
        return modelAndView;
        }else {
            return usesrBorrowDetail(userName,page);
        }

    }


    /**
     * 前台返回借书单信息
     * @param map 前台传来的页码数据
     * @param request
     * @return 返回状态和用户借书单信息
     */
    @RequestMapping("/personBorrowDetailList")
    @ResponseBody
    public APIResult borrowDetailList(@RequestBody Map map, HttpServletRequest request){
        int page = (int) map.get("page");
        int userId = 0;
        Cookie[] cookies = request.getCookies();
        boolean flag = true;
        for (int i = 0; i < cookies.length; i++) {
            if(cookies[i].getName().equals("token")){
                userId = (int) TokenUtil.getTokenValue(cookies[i].getValue(),"userId");
                flag = false;
                break;
            }
        }
        if(flag){
            return new APIResult("没错",false,200);
        }
        PageInfo<Map> pageInfo = borrowDetailService.searchBorrowDetailByUser(page, 10,userId);

            if(pageInfo.getList().size() > 0){
                return new APIResult("没错",true,200,pageInfo);
            }else {
                return new APIResult("没错",false,200);
            }


    }

    /**
     * 后台用户借书单信息
     * @param userName 用户名
     * @param page 当前页码
     * @return 搜索的借书单信息
     */
    @RequestMapping("/userBorrowdetail")
    public ModelAndView usesrBorrowDetail(@RequestParam(value = "userName",defaultValue = "") String userName,@RequestParam(value = "page",defaultValue = "1") int page){
        ModelAndView modelAndView ;
        System.out.println(userName);
        if(userName!=null&&!"".equals(userName)){
            modelAndView= new ModelAndView("/admin/borrowdetail");
            System.out.println(userName);
            PageInfo<User> pageInfo = userService.searchUserByName(1, 1, userName);
            Integer userId = pageInfo.getList().get(0).getUserId();

            PageInfo<Map> info = borrowDetailService.searchBorrowDetailByUser(page, 10, userId);
            modelAndView.addObject("pageInfo",info);
            modelAndView.addObject("state",1);
            modelAndView.addObject("userName",userName);


        }else {
            System.out.println("开始");
            modelAndView = borrowDetailList(1,0,"");
        }

        return modelAndView;
    }

    /**
     * 还书模块，修改书单状态
     * @param status 目前借书单状态（是否逾期）
     * @param borrowId 借书单的id
     * @return 返回页面
     */
    @RequestMapping("/returnBook")
@ResponseBody
public ModelAndView returnBook(@RequestParam(value = "status",defaultValue = "1") int status,@RequestParam(value = "borrowId",defaultValue = "") int borrowId){
    ModelAndView modelAndView ;
    int len = 0;

    if((status==1||status==4||status==5)&&borrowId!=0){
        BorrowDetail borrowDetail = borrowDetailService.searchOneBorrowDetail(borrowId);
        borrowDetail.setStatus(status);
        borrowDetail.setBorrowId(borrowId);
        borrowDetail.setRealReturnDate(new Date());

        if(status == BorrowDetailServiceImpl.OVERDUE_NO_RETURN_FLAG){
            int day = borrowDetailService.differentDays(borrowDetail.getShouldReturnDate(),borrowDetail.getRealReturnDate());
            borrowDetail.setFine(day*BorrowDetailServiceImpl.FINE_FLAGE);
        }


        len = borrowDetailService.returnBook(borrowDetail);

    }
    System.out.println("len:"+len);
    if(len > 0){
        modelAndView = borrowDetailList(1,0,null);
        return modelAndView;
    }else {
        modelAndView = new ModelAndView("/error/404");
    }
    return modelAndView;
}



}
