package com.chinameyer.questionnaire.controller;
import java.text.SimpleDateFormat;
import java.util.*;
import	java.lang.annotation.Retention;
import java.util.Date;

import ch.qos.logback.core.joran.spi.ElementSelector;
import com.alibaba.fastjson.JSONObject;
import com.chinameyer.questionnaire.bean.ApiResult;
import com.chinameyer.questionnaire.bean.Event;
import com.chinameyer.questionnaire.bean.PageTableRequest;
import com.chinameyer.questionnaire.bean.Question;
import com.chinameyer.questionnaire.service.QuestionnaireService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * @Author HongYe
 * @Date 2019/11/1 9:19
 */
@RestController
@RequestMapping("questionnaire")
public class QuestionnaireController {

    @Autowired
    private QuestionnaireService questionnaireService;

    @PostMapping("/addQuestionnaire")
    public ApiResult addQuestionnaire(@RequestParam("transferMsg") String transferMsg) {
        JSONObject jsonObj = JSONObject.parseObject(transferMsg);
        String questionnaireIndex = new SimpleDateFormat("yyyy/MM/dd/HH/mm/ss").format(new Date());
        String confirmStr = "{\"0\":\"[]\",\"1\":\"[]\",\"2\":\"[]\"}";
        String title = null;
        String value = null;
        ApiResult apiResult = null;

        if(!transferMsg.equals(confirmStr)){
            //fastjson解析方法
            for (Map.Entry<String, Object> entry : jsonObj.entrySet()) {
                if (entry.getKey() == "0" || entry.getKey() == "1") {
                    String strValue0 = (String) entry.getValue();
                    String[] valueArray = strValue0.replace("[", "").replace("]", "").split(",");
                    for (int j = 0; j < valueArray.length; j += 2) {
                        String jsonStr = valueArray[j] + "," + valueArray[j + 1];
                        JSONObject jsonObj0 = JSONObject.parseObject(jsonStr);
                        for (Map.Entry<String, Object> entry0 : jsonObj0.entrySet()) {
                            if (entry0.getKey() == "title") {
                                title = entry0.getValue().toString();
                            }
                            if (entry0.getKey().equals("value")) {
                                value = entry0.getValue().toString();
                            }
                        }
                        questionnaireService.addQuestionToSelectedList(title, value, entry.getKey());
                        String strTitle = "需要用户输入";
                        questionnaireService.addEvent(questionnaireIndex,strTitle);
                    }

                }
                if (entry.getKey() == "2") {
                    String strValue2 = (String) entry.getValue();
                    String[] valueArray = strValue2.replace("[", "").replace("]", "").split(",");
                    for (int j = 0; j < valueArray.length; j += 2) {
                        String jsonStr = valueArray[j] + "," + valueArray[j + 1];
                        JSONObject jsonObj2 = JSONObject.parseObject(jsonStr);
                        for (Map.Entry<String, Object> entry2 : jsonObj2.entrySet()) {
                            if (entry2.getKey() == "title") {
                                title = entry2.getValue().toString();
                            }
                            if (entry2.getKey() == "value") {
                                value = entry2.getValue().toString();
                            }
                        }
                        questionnaireService.addQuestionToSelectedList(title, value, entry.getKey());
                        String strTitle = "需要用户输入";
                        questionnaireService.addEvent(questionnaireIndex,strTitle);
                    }
                }
            }
            apiResult = new ApiResult(200,"success");
        }else {
            apiResult = new ApiResult(400,"未选择题目");
        }
        return apiResult;
    }

    @GetMapping("/analysis")
    public Map<String,Object> resultAnalysis(PageTableRequest pageTableRequest){

        pageTableRequest.countOffset();
        Map<String,Object> objectMap = new HashMap<>();
        List<Event> list = new ArrayList<>();
        Integer count = null;

        list = questionnaireService.getAllQuestionnaireIndexByPage(pageTableRequest.getOffset(),pageTableRequest.getLimit());
        count = questionnaireService.countAllQuestionnaireIndex();

        objectMap.put("code",200);
        objectMap.put("msg","");
        objectMap.put("count",count);
        objectMap.put("data",list);

        return objectMap;
    }

    @PostMapping("delete")
    public ApiResult deleteQuestionnaire(@RequestParam("id")int id){
        //删除event表中的数据
        questionnaireService.deleteEventById(id);
        //删除与event表关联的表的数据
        questionnaireService.deleteQuestionnaireByEventId(id);
        return new ApiResult(200,"success");
    }

    @GetMapping("/result")
    public String getResult(Model model) {
        model.addAttribute("Result","");
        return "redirect:/result/questionnaire-analysis";
    }
}
