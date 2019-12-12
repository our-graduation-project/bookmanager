package cn.hnist.bookmanager.controller;

import cn.hnist.bookmanager.model.BorrowDetail;
import cn.hnist.bookmanager.service.impl.BorrowDetailServiceImpl;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author 向清润
 * @createTime 2019.12.12.16:37
 */
@Controller
public class boorrowdetailController {
    @Autowired
    BorrowDetailServiceImpl borrowDetailService;

    @RequestMapping("/borrowDetailList")
    public ModelAndView borrowDetailList(@RequestParam(value = "page",defaultValue = "1") int page){
        ModelAndView modelAndView = new ModelAndView("/admin/borrowdetail");
        PageInfo<BorrowDetail> pageInfo = borrowDetailService.searchOverdueBorrowDetail(page, 10);
        modelAndView.addObject("pageInfo",pageInfo);
        return modelAndView;

    }

}
