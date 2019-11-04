package com.chinameyer.questionnaire.service;

import com.chinameyer.questionnaire.bean.Event;
import com.chinameyer.questionnaire.bean.Question;
import com.chinameyer.questionnaire.dao.EventDao;
import com.chinameyer.questionnaire.dao.QuestionDao;
import com.chinameyer.questionnaire.dao.QuestionnaireDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author HongYe
 * @Date 2019/11/1 9:21
 */
@Service
public class QuestionnaireService {

    @Autowired
    private QuestionnaireDao questionnaireDao;
    @Autowired
    private EventDao eventDao;

    public void addQuestionToSelectedList( String key, String value, String type) {
        int questionOrder = Integer.parseInt(value);
        int questionType = Integer.parseInt(type);
        questionnaireDao.InsertQuestionToSelectedList(key,questionOrder,questionType);
    }

    public List<Event> getAllQuestionnaireIndexByPage(Integer startPosition, Integer limit) {
        return eventDao.findAllQuestionnaireIndexByPage(startPosition,limit);
    }

    public Integer countAllQuestionnaireIndex() {
        return eventDao.countAllQuestionnaireIndex();
    }

    public String getQuestionnaireIndexById(int id) {
        return eventDao.findQuestionnaireIndexById(id);
    }

    public void deleteEventById(int id) {
        eventDao.deleteEventById(id);
    }

    public void deleteQuestionnaireByEventId(int eventId) {
        questionnaireDao.deleteQuestionnaireByEventId(eventId);
    }

    public void addEvent(String questionnaireIndex, String strTitle) {
        questionnaireDao.InsertEvent(questionnaireIndex,strTitle);
    }
}
