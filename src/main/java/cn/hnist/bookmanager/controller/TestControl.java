package cn.hnist.bookmanager.controller;

import cn.hnist.bookmanager.model.Book;
import cn.hnist.bookmanager.service.BookService;
import cn.hnist.bookmanager.utils.LogUtils;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author whg
 * @date 2019/12/9 23:42
 **/
@Controller
public class TestControl {

    @Autowired
    private BookService bookService;

    @RequestMapping("/helloworld")
    public ModelAndView helloWorld(){
        //System.out.println("come in");
//        Logger log = LogUtils.getExceptionLogger();
//        Logger log1 = LogUtils.getBussinessLogger();
//        Logger log2 = LogUtils.getDBLogger();
        Book bookById = bookService.findBookById(1);
        ModelAndView model = new ModelAndView("/admin/main");
        model.addObject("book",bookById);
//        log1.info("getBussinessLogger===日志测试");
//        LogUtils.getControllerLogger().info("getControllerLogger===日志测试");
//        System.out.println(1/0);
//        log2.debug("getDBLogger===日志测试");
        return model;
    }
}
