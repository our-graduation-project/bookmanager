package cn.hnist.bookmanager.controller;

import cn.hnist.bookmanager.model.BorrowDetail;
import cn.hnist.bookmanager.model.User;
import cn.hnist.bookmanager.service.impl.BorrowDetailServiceImpl;
import cn.hnist.bookmanager.service.impl.UserServiceImpl;
import cn.hnist.bookmanager.utils.APIResult;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

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
