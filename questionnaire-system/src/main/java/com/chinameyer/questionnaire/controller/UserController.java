package com.chinameyer.questionnaire.controller;

import com.chinameyer.questionnaire.bean.ApiResult;
import com.chinameyer.questionnaire.bean.PageTableRequest;
import com.chinameyer.questionnaire.bean.User;
import com.chinameyer.questionnaire.service.UserService;
import com.chinameyer.questionnaire.utils.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

    String encodeRules = "美亚光电";
    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ApiResult login(@RequestParam("username")String username, @RequestParam("password")String password, HttpServletRequest request){
        ApiResult apiResult = null;
        password = MD5Utils.stringToMD5(password);
        User admin = userService.getAdminByKeyWords(username, password);
        if (admin == null) {
            apiResult = new ApiResult(400, "用户名或密码输入错误");
        } else {
            HttpSession session = request.getSession();
            session.setAttribute("username",username);
            String[] str = {username,password};
            apiResult = new ApiResult(200, "success",str);
        }
        return apiResult;
    }

    @GetMapping("/manage")
    public Map<String,Object> getUser(PageTableRequest pageTableRequest, HttpServletRequest request){
        pageTableRequest.countOffset();
        String keyword = request.getParameter("keyword");
        List<User> list = new ArrayList<>();
        Integer count;
        if(keyword == null){
            list = userService.getAllUserByPage(pageTableRequest.getOffset(),pageTableRequest.getLimit());
            count = userService.countAllUser();
        }
        else {
            list = userService.getAllUserByPageAndNo(pageTableRequest.getOffset(),pageTableRequest.getLimit(),keyword);
            count = userService.countUserByKeyword(keyword);
        }

        Map<String,Object> objectMap = new HashMap<>();
        objectMap.put("code",200);
        objectMap.put("msg","");
        objectMap.put("count",count);
        objectMap.put("data",list);
        return objectMap;
    }

    @GetMapping(value = "/add")
    public String addUser(Model model) {
        model.addAttribute("User",new User());
        return "user-add";
    }

    @PostMapping("/delete")
    public ApiResult deleteUser(@RequestParam("id")int id){
        userService.deleteUser(id);
        return new ApiResult(200,"success");
    }

    @PostMapping("/changePassword")
    public void changePassword(HttpServletRequest request){
        String username = request.getParameter("username");
        String newPassword1 = request.getParameter("newPassword1");
        newPassword1 = MD5Utils.stringToMD5(newPassword1);
        userService.changePassword(username, newPassword1);
    }

    @GetMapping("/logout")
    public String logout(HttpServletResponse response, HttpServletRequest request) throws IOException {
        response.sendRedirect("login");
        return "login";
    }
}
