package com.chinameyer.questionnaire.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
public class IndexController {

    @RequestMapping("/")
    public String toLogin(HttpServletResponse response,HttpSession session) {
        return response.encodeRedirectURL("login");
    }

    @RequestMapping("/index")
    public String index(HttpServletResponse response,HttpSession session) {
        Object username = session.getAttribute("username");
        if(username == null){
            return response.encodeRedirectURL("login");
        }else {
            return response.encodeRedirectURL("index");
        }

    }

    @GetMapping(value = "/logout")
    public String toLogout(HttpSession session,HttpServletResponse response) {
        session.removeAttribute("username");
        Object username = session.getAttribute("username");
        if (username == null){
            return response.encodeRedirectURL("login");
        }
        else
            return response.encodeRedirectURL("index");
    }
}
