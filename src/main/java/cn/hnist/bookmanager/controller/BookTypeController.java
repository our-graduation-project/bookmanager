package cn.hnist.bookmanager.controller;

import cn.hnist.bookmanager.model.Booktype;
import cn.hnist.bookmanager.service.BookTypeService;
import cn.hnist.bookmanager.utils.APIResult;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;


/**
 * @Author: Zxw
 * @Description:
 * @Date: Created in 19:07 2019/12/12
 * @Modifued By:
 */
@Controller
public class BookTypeController {

    @Autowired
    BookTypeService bookTypeService;

    /**
     * 跳入booktypelist页面并加载页面数据
     * @param indexPage
     * @param pageSize
     * @return
     */
    @RequestMapping("/booktypelist")
    public ModelAndView bookTypeList(@RequestParam(value = "indexPage" ,defaultValue = "0") Integer indexPage,
                                     @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize){
        PageInfo pageInfo = bookTypeService.searchAllBookType(indexPage, pageSize);
        ModelAndView modelAndView = new ModelAndView("/admin/booktypelist");
        modelAndView.addObject( "pageInfo", pageInfo);
        return modelAndView;
    }

    /**
     * 跳入tobooktypemodify页面并加载页面数据
     * @param typeId
     * @return
     */
    @RequestMapping("/tobooktypemodify")
    public ModelAndView toBookTypeModify(@RequestParam("typeId") int typeId){
        Booktype booktype = bookTypeService.searchBookTypeById(typeId);
        ModelAndView modelAndView = new ModelAndView("admin/booktypeedit");
        modelAndView.addObject( "booktype", booktype);
        return modelAndView;
    }

    /**
     * 修改booktype
     * @param booktype
     * @return
     */
    @RequestMapping("/admin/booktypemodify")
    @ResponseBody
    public APIResult bookTypeModify(@RequestBody Booktype booktype){
        int i = bookTypeService.updateBookType(booktype);
        boolean flg = false;
        if(i > 0){
            flg = true;
        }
        return new APIResult(flg,200);
    }

    /**
     * 跳入booktypeadd页面
     * @return
     */
    @RequestMapping("/tobooktypeadd")
    public String toBookTypeAdd(){
        return "admin/booktypeadd";
    }


    /**
     * 增加booktype记录
     * @param booktype
     * @return
     */
    @RequestMapping("/admin/booketypeadd")
    @ResponseBody
    public APIResult bookeTypeAdd(@RequestBody Booktype booktype){
        //判断这种书的类型是否已经存在
        Booktype booktypeTypeName = bookTypeService.searchBookTypeByTypeName(booktype.getTypeName());
        if(booktypeTypeName != null){
            return new APIResult("这种书的类型已存在",false,400);
        }
        int i =  bookTypeService.addBook(booktype);
        boolean flg = false;
        if(i > 0){
            flg = true;
        }
        return new APIResult(flg,200);
    }

    /**
     * 删除booktype记录
     * @param booktype
     * @return
     */
    @RequestMapping("/admin/booktypedel")
    @ResponseBody
    public APIResult bookTypeDel(@RequestBody Booktype booktype){
        int i = bookTypeService.deleteBookType(booktype.getTypeId());
        boolean flg = false;
        if(i > 0){
            flg = true;
        }
        return new APIResult(flg,200);
    }
}