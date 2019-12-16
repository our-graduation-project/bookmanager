package cn.hnist.bookmanager.controller;

import cn.hnist.bookmanager.model.Book;
import cn.hnist.bookmanager.model.Label;
import cn.hnist.bookmanager.service.BookService;
import cn.hnist.bookmanager.service.LabelService;
import cn.hnist.bookmanager.utils.APIResult;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description:
 * @Date: Created in 14:59 2019/12/16
 * @Modifued By:
 */

@Controller
public class BookController {

    @Autowired
    BookService bookService;

    @Autowired
    LabelService labelService;

    @RequestMapping("/searchbook")
    @ResponseBody
    public APIResult searchBook(@RequestParam("keyword") String keyword){

        List<Book> data = new ArrayList<>();

        List<Book> data1 = bookService.selectBookByLabelName(keyword);
        data.addAll(data);

        APIResult apiResult = new APIResult();

        List<Book> data2 = bookService.selectBookByKeyWord(keyword);
        data.addAll(data2);

        apiResult.setData(data);
        return apiResult;
    }

}