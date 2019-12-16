package cn.hnist.bookmanager.controller;

import cn.hnist.bookmanager.service.impl.LoseServiceImpl;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

/**
 * @author 向清润
 * @createTime 2019.12.13.10:51
 */
@Controller
public class LoseController {
    @Autowired
    LoseServiceImpl loseService;


    /**
     * 查看遗失信息
     * @param page 当前页面
     * @return 数据与页面
     */
    @RequestMapping("/loseList")
    public ModelAndView loseList(@RequestParam(value = "page",defaultValue = "1") int page){
        ModelAndView modelAndView = new ModelAndView("/admin/loseList");
        PageInfo<Map> pageInfo = loseService.searchLose(page, 10);
        modelAndView.addObject("pageInfo",pageInfo);
        return modelAndView;
    }
}
