package cn.hnist.bookmanager.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author whg
 * @date 2019/12/10 15:04
 **/
@Controller
@RequestMapping("/error")
public class ErrorController {

    @RequestMapping("/404")
    public String redirectTo404(){
        return "/error/404";
    }

    @RequestMapping("/500")
    public String redirectTo500(){
        return "/error/500";
    }

    @RequestMapping("/403")
    public String redirectTo403(){
        return "/error/403";
    }

    @RequestMapping("/501")
    public String redirectTo501(){
        return "/error/501";
    }
}
