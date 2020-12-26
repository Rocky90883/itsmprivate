package com.koron.web.systemmanger.user;

import com.koron.common.authentication.NoAuth;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
public class IndexAction {

    @NoAuth
    @RequestMapping("/index.htm")
    public ModelAndView index() {
//        ModelAndView mv = new ModelAndView("index");
        ModelAndView mv = new ModelAndView("redirect:/vue/index.html");
        return mv;
    }


    @NoAuth
    @RequestMapping("/err.htm")
    public String error(Model model) {
//        ModelAndView mv = new ModelAndView("err");
////        HttpSession session = request.getSession();
////        String msg =(String) session.getAttribute("msg");
//        mv.addObject("code", HttpStatus.INTERNAL_SERVER_ERROR.value());
//        mv.addObject("msg", HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
//        mv.addObject("errMsg", "woaini");
        model.addAttribute("msg","abc");

        return "err";
    }


}
