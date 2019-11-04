package com.chinameyer.questionnaire.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.chinameyer.questionnaire.bean.ApiResult;
import com.chinameyer.questionnaire.bean.PageTableRequest;
import com.chinameyer.questionnaire.bean.Question;
import com.chinameyer.questionnaire.service.QuestionService;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author HongYe
 * @Date 2019/10/29 17:25
 */
@RestController
@RequestMapping("/question")
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @GetMapping("/manage")
    public Map<String,Object> getQuestion(PageTableRequest pageTableRequest, HttpServletRequest request){
        pageTableRequest.countOffset();
        String keyword = request.getParameter("keyword");
        List<Question> list = new ArrayList<>();
        Integer count;
        String keywords = null;
        if(keyword == null){
            list = questionService.getAllUserByPage(pageTableRequest.getOffset(),pageTableRequest.getLimit());
            count = questionService.countAllUser();
        }
        else {
            if(keyword == "单选题"){
                keywords = "0";
            }else if(keyword == "多选题"){
                keywords = "1";
            }else{
                keywords = "2";
            }
            list = questionService.getAllUserByPageAndNo(pageTableRequest.getOffset(),pageTableRequest.getLimit(),keywords);
            count = questionService.countUserByKeyword(keywords);
        }

        Map<String,Object> objectMap = new HashMap<>();
        objectMap.put("code",200);
        objectMap.put("msg","");
        objectMap.put("count",count);
        objectMap.put("data",list);
        return objectMap;
    }

    @PostMapping("/delete")
    public ApiResult deleteUser(@RequestParam("id")int id){
        questionService.deleteQuestion(id);
        return new ApiResult(200,"success");
    }

    @GetMapping("/transferQues")
    public ApiResult transferQues() throws JSONException {
        List<Question> singleSelectionList = questionService.getSingleSelection(0);
        List<Question> multSelectionList = questionService.getMultSelection(1);
        List<String> quesAndAnswerList = questionService.getQuesAndAnswer(2);
        List<Object> singleSelectionArrayList = new ArrayList<> ();
        List<Object> multSelectionArrayList = new ArrayList<> ();
        List<Object> quesAndAnswerArrayList = new ArrayList<> ();
        int questionId = 1;
        for (int i = 0;i < singleSelectionList.size(); i++){
            String tempStr = "{"+"value"+":\""+(questionId++)+"\",\"title\":\""+singleSelectionList.get(i).getQuestion()+singleSelectionList.get(i).getOptions()+"\"}";
            JSONObject jsonObj = JSONObject.parseObject(tempStr);
            singleSelectionArrayList.add(jsonObj);
        }
        for (int i = 0;i < multSelectionList.size(); i++){
            String tempStr = "{"+"value"+":\""+(questionId++)+"\",\"title\":\""+multSelectionList.get(i).getQuestion()+multSelectionList.get(i).getOptions()+"\"}";
            JSONObject jsonObj = JSONObject.parseObject(tempStr);
            multSelectionArrayList.add(jsonObj);
        }
        for (int i = 0;i < quesAndAnswerList.size(); i++){
            String tempStr = "{"+"value"+":\""+(questionId++)+"\",\"title\":\""+quesAndAnswerList.get(i)+"\"}";
            JSONObject jsonObj = JSONObject.parseObject(tempStr);
            quesAndAnswerArrayList.add(jsonObj);
        }
        Map<String, Object> map = new HashMap<>();
        map.put("singleSelection",singleSelectionArrayList);
        map.put("multSelection",multSelectionArrayList);
        map.put("quesAndAnswer",quesAndAnswerArrayList);
        return new ApiResult(200,"success",map);
    }

}
