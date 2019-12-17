package cn.hnist.bookmanager.controller;

import cn.hnist.bookmanager.model.Book;
import cn.hnist.bookmanager.model.BorrowDetail;
import cn.hnist.bookmanager.model.Lose;
import cn.hnist.bookmanager.service.impl.BookServiceImpl;
import cn.hnist.bookmanager.service.impl.BorrowDetailServiceImpl;
import cn.hnist.bookmanager.service.impl.LoseServiceImpl;
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
 * @createTime 2019.12.13.10:51
 */
@Controller
public class LoseController {
    @Autowired
    LoseServiceImpl loseService;

    @Autowired
    BookServiceImpl bookService;

    @Autowired
    BorrowDetailServiceImpl borrowDetailService;


    /**
     * 查看遗失信息
     * @param page 当前页面
     * @return 数据与页面
     */
    @RequestMapping("/loseList")
    public ModelAndView loseList(@RequestParam(value = "page",defaultValue = "1") int page){
        ModelAndView modelAndView = new ModelAndView("/admin/loselist");
        PageInfo<Map> pageInfo = loseService.searchLose(page, 10);
        modelAndView.addObject("pageInfo",pageInfo);
        return modelAndView;
    }



    /**
     * 遗失信息登记
     * @param map 传入进来的信息
     * @return 返回数据
     */
    @RequestMapping("/addLose")
    @ResponseBody
    public APIResult addLose(@RequestBody Map map){
        int borrowId = (int) map.get("borrowId");
        int state = (int) map.get("state");
        if(state!=1&&state!=4&&state!=5){
            return new APIResult("改状态无法遗失登记",false,200);
        }
        BorrowDetail borrowDetail = borrowDetailService.searchOneBorrowDetail(borrowId);
        borrowDetail.setStatus(6);
        borrowDetailService.returnBook(borrowDetail);
        Book book = bookService.findBookById(borrowDetail.getBookId());
        double fine = 0.0;
        if(borrowDetail.getFine() != null){
            fine=borrowDetail.getFine();
        }
        Double price = book.getPrice()+fine;
        Lose lose = new Lose();
        lose.setLoseDate(new Date());
        lose.setUserId(borrowDetail.getUserId());
        lose.setBookId(borrowDetail.getBookId());
        lose.setFine(price);
        int i = loseService.addLose(lose);
        if(i==0){
            return new APIResult("更改状态失败",false,200);
        }else {
            return new APIResult("成功",true,200);
        }
    }
}
