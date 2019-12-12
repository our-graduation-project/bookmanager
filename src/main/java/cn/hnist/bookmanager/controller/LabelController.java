package cn.hnist.bookmanager.controller;

import cn.hnist.bookmanager.model.Label;
import cn.hnist.bookmanager.service.LabelService;
import cn.hnist.bookmanager.utils.APIResult;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * @author whg
 * @date 2019/12/11 19:47
 **/
@Controller
public class LabelController {

    @Autowired
    private LabelService labelService;

    /**
     * 跳入labellist页面
     * @param page
     * @param limit
     * @return
     */
    @RequestMapping("/toLabelList")
    public ModelAndView toLabelList(@RequestParam(value = "page" ,defaultValue = "0") int page,
                                    @RequestParam(value = "limit", defaultValue = "10") int limit){

        System.out.println("page = " + page);
        System.out.println("limit = " + limit);
        PageInfo pageInfo = labelService.queryLabelByPage(page, limit);
        ModelAndView modelAndView = new ModelAndView("admin/labellist");
        modelAndView.addObject( "pageInfo", pageInfo);
        return modelAndView;
    }

    /**
     * 跳入修改页面
     * @param labelId
     * @return
     */
    @RequestMapping("/toEditLabel")
    public ModelAndView toEditLabel(@RequestParam("labelId") int labelId){
        Label label = labelService.selectLabelNameById(labelId);
        ModelAndView modelAndView = new ModelAndView("admin/labeledit");
        modelAndView.addObject("label",label);
        return modelAndView;
    }

    /**
     * 修改标签信息
     * @param label
     * @return
     */
    @RequestMapping("/admin/editLabel")
    @ResponseBody
    public APIResult editLabel(@RequestBody Label label){
        System.out.println("come in editLabel");
        System.out.println("label = " + label);
        if(label == null || label.getLabelId() == null || label.getLabelName() == null){
            return new APIResult("参数有误",false,400);
        }
        Integer id = labelService.selectLabelIdByName(label.getLabelName());
        if(id != null && id > 0){
            return new APIResult("此标签名已存在", false, 500);
        }
        Boolean aBoolean = labelService.updateLabel(label.getLabelId(),label.getLabelName());
        return new APIResult(aBoolean,200);
    }

    @RequestMapping("/deleteLabel")
    public void deleteLabel(@RequestParam("labelId") int labelId,
                              @RequestParam(value = "page",defaultValue = "0") int page,
                              HttpServletResponse response){

        Boolean aBoolean = labelService.deleteLabel(labelId);
        try {
            response.sendRedirect("toLabelList");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ;
    }

    @RequestMapping("/admin/addLabel")
    @ResponseBody
    public APIResult addLabel(@RequestBody Label label){

        if(label == null || label.getLabelName() == null){
            return new APIResult("参数有误",false,400);
        }

        Boolean aBoolean = labelService.addLabel(label);
        return new APIResult(aBoolean,200);
    }
}
