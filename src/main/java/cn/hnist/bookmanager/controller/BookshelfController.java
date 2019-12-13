package cn.hnist.bookmanager.controller;

import cn.hnist.bookmanager.model.Bookshelf;
import cn.hnist.bookmanager.model.User;
import cn.hnist.bookmanager.service.BookshelfService;
import cn.hnist.bookmanager.utils.APIResult;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Controller
public class BookshelfController {
    @Autowired
    private BookshelfService bookshelfService;

    /**
     * 加载所有书架
     * @param page
     * @param pageSize
     * @return
     */
    @RequestMapping("/loadshelf")
    public ModelAndView searchAllBookshelf(@RequestParam(value = "page",defaultValue = "0") int page,
                                           @RequestParam(value = "pageSize",defaultValue = "3") int pageSize){
        PageInfo<Bookshelf> pageInfo = bookshelfService.searchBookshelf(page, pageSize);
        ModelAndView model=new ModelAndView("admin/bookshelflist");
        model.addObject("pageInfo",pageInfo);
        return model;
    }

    /**
     * 增加书架
     * @param bookshelf
     * @return
     */
    @RequestMapping("/admin/addshelf")
    @ResponseBody
    public APIResult addLabel(@RequestBody Bookshelf bookshelf){

        if(bookshelf == null || bookshelf.getBookshelfName() == null||bookshelf.getBookshelfPosition()==null){
            return new APIResult("参数有误",false,400);
        }

        Boolean aBoolean = bookshelfService.addBookshelf(bookshelf);
        return new APIResult(aBoolean,200);
    }

    /**
     * 根据ID删除书架
     * @param bookshelfId
     * @param page
     * @param response
     * @return
     */
    @RequestMapping("/deleteshelf")
    public ModelAndView deleteBookshelf(@RequestParam("bookshelfId") Integer bookshelfId,
                                        @RequestParam(value = "page",defaultValue = "0") int page,
                                        HttpServletResponse response){
        boolean b = bookshelfService.deleteBookshelf(bookshelfId);
        try {
            response.sendRedirect("loadshelf");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 根据name查询书架
     * @param page
     * @param pageSize
     * @param bookshelfName
     * @return
     */
    @RequestMapping("/toEditshelf")
    public ModelAndView searchBookshelfByName(@RequestParam(value = "page",defaultValue = "0") int page,
                               @RequestParam(value = "pageSize",defaultValue = "3") int pageSize,
            @RequestParam("bookshelfName") String bookshelfName){
        PageInfo<Bookshelf> pageInfo = bookshelfService.searchBookshelfByName(page, pageSize, bookshelfName);
        ModelAndView modelAndView = new ModelAndView("admin/bookshelfedit");
        modelAndView.addObject("pageInfo",pageInfo);
        return modelAndView;
    }

    /**
     * 修改书架
     * @param bookshelf
     * @return
     */
    @RequestMapping("/admin/editshelf")
    @ResponseBody
    public APIResult editLabel(@RequestBody Bookshelf bookshelf){
        if(bookshelf == null || bookshelf.getBookshelfId()==null || bookshelf.
                getBookshelfName()== null||bookshelf.getBookshelfPosition()==null) {
            return new APIResult("参数有误", false, 400);
        }
        boolean b = bookshelfService.updateBookshelf(bookshelf);

        return new APIResult(b,200);
    }

}
