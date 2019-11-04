package com.chinameyer.questionnaire.controller;

import com.chinameyer.questionnaire.bean.ApiResult;
import com.chinameyer.questionnaire.bean.EChart;
import com.chinameyer.questionnaire.bean.PageTableRequest;
import com.chinameyer.questionnaire.bean.Result;
import com.chinameyer.questionnaire.service.QuestionService;
import com.chinameyer.questionnaire.service.ResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author HongYe
 * @Date 2019/10/30 15:48
 */
@RestController
@RequestMapping("/result")
public class ResultController {

    @Autowired
    private ResultService resultService;
    @Autowired
    private QuestionService questionService;

    @GetMapping("/showQuesAndAnswer")
    public Map<String,Object> getQuesAndAnswer(PageTableRequest pageTableRequest,HttpServletRequest request){
        String questionnaireIndex = request.getParameter("questionnaireIndex");
        pageTableRequest.countOffset();
        List<Object> list = new ArrayList<Object> ();
        List<Result> listResult = resultService.getAllUserByPage(pageTableRequest.getOffset(),pageTableRequest.getLimit());
        Integer count = resultService.countAllUser();
        for (int i = 0;i < listResult.size();i++) {
            Map<String, Object> map = new HashMap<String, Object> ();
            String question = questionService.getQuesById(listResult.get(i).getQuestionId());
            int id = listResult.get(i).getId();
            String answer = listResult.get(i).getAnswer();
            String type = "问答题";
            map.put("question",question);
            map.put("id",id);
            map.put("answer",answer);
            map.put("type",type);
            list.add(map);
        }

        Map<String,Object> objectMap = new HashMap<>();
        objectMap.put("code",200);
        objectMap.put("msg","");
        objectMap.put("count",count);
        objectMap.put("data",list);
        return objectMap;
    }

    @GetMapping("/showChoiceQues")
    public ApiResult getChoiceQues(HttpServletRequest request) {
        String questionnaireIndex = request.getParameter("questionnaireIndex");
        List<Result> singleResultList = resultService.getSingleResultIdByIndexAndType(questionnaireIndex,0);
        List<Result> multResultList = resultService.getMultResultIdByIndexAndType(questionnaireIndex,1);
        List<Integer> questionIdList = new ArrayList<> ();
        List<Integer> questionIdList1 = new ArrayList<> ();
        String questionNum = null;
        int arraySize = 0;
        questionIdList.add(singleResultList.get(0).getQuestionId());
        questionIdList1.add(multResultList.get(0).getQuestionId());
        //单选题
        for (int i = 1;i < singleResultList.size(); i++){
            for (Integer id:questionIdList) {
                if(singleResultList.get(i).getQuestionId() != id){
                    questionIdList.add(singleResultList.get(i).getQuestionId());
                }
            }
        }
        //多选题
        for(int k = 1;k < multResultList.size();k++){
            for (Integer id:questionIdList1) {
                if(multResultList.get(k).getQuestionId() != id){
                    questionIdList1.add(multResultList.get(k).getQuestionId());
                }
            }
        }

        List<EChart> list = new ArrayList<> ();
        ArrayList <Integer> sizeArray = new ArrayList<> ();
        for(int j = 0;j < questionIdList.size(); j++){
            int optionA = 0;
            int optionB = 0;
            int optionC = 0;
            int optionD = 0;
            int optionE = 0;
            int optionF = 0;
            int questionId = questionIdList.get(j);
            for (Result result:singleResultList) {
                if(questionId == result.getQuestionId()){
                    if("A".equals(result.getAnswer())){
                        optionA++;
                    }
                    if("B".equals(result.getAnswer())){
                        optionB++;
                    }
                    if("C".equals(result.getAnswer())){
                        optionC++;
                    }
                    if("D".equals(result.getAnswer())){
                        optionD++;
                    }
                    if("E".equals(result.getAnswer())){
                        optionE++;
                    }else {
                        optionF++;
                    }
                }
            }
            questionNum = "第"+(j+1)+"题";
            list.add(new EChart(questionNum,optionA,optionB,optionC,optionD,optionE,optionF));
            sizeArray.add(6);
        }

        for(int p = 0;p < questionIdList1.size(); p++){
            int questionId = questionIdList1.get(p);
            int optionA = 0;
            int optionB = 0;
            int optionC = 0;
            int optionD = 0;
            int optionE = 0;
            int optionF = 0;
            for (Result result:multResultList) {
                if(questionId == result.getQuestionId()){
                    if(result.getAnswer().length() == 1){
                        if("A".equals(result.getAnswer())){
                            optionA++;
                        }
                        if("B".equals(result.getAnswer())){
                            optionB++;
                        }
                        if("C".equals(result.getAnswer())){
                            optionC++;
                        }
                        if("D".equals(result.getAnswer())){
                            optionD++;
                        }
                        if("E".equals(result.getAnswer())){
                            optionE++;
                        }else {
                            optionF++;
                        }
                    }else {
                        String[] arrayOptions = result.getAnswer().split(",");
                        for (String option:arrayOptions) {
                            if("A".equals(option)){
                                optionA++;
                            }
                            if("B".equals(option)){
                                optionB++;
                            }
                            if("C".equals(option)){
                                optionC++;
                            }
                            if("D".equals(option)){
                                optionD++;
                            }
                            if("E".equals(option)){
                                optionE++;
                            }else {
                                optionF++;
                            }
                        }
                    }

                }
            }
            questionNum = "第"+(p+1)+"题";
            list.add(new EChart(questionNum,optionA,optionB,optionC,optionD,optionE,optionF));
            sizeArray.add(6);
        }
        int totalNum = singleResultList.size();
        int util = totalNum / 5;
        Map<String, Object> map = new HashMap<> ();
        map.put("list",list);
        map.put("size",sizeArray);
        map.put("totalNum", totalNum);
        map.put("util",util);
        return new ApiResult(200,"success",map);
    }
}
