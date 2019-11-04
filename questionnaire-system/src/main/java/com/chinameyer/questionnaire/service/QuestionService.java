package com.chinameyer.questionnaire.service;

import com.chinameyer.questionnaire.bean.Question;
import com.chinameyer.questionnaire.dao.QuestionDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author HongYe
 * @Date 2019/10/29 17:27
 */
@Service
public class QuestionService {

    @Autowired
    private QuestionDao questionDao;

    public List<Question> getAllUserByPage(Integer startPosition, Integer limit) {
        return questionDao.findAllUserByPage(startPosition,limit);
    }

    public Integer countAllUser() {
        return questionDao.countAllUser();
    }

    public List<Question> getAllUserByPageAndNo(Integer startPosition, Integer limit, String keyword) {
        return questionDao.getAllUserByPageAndNo(startPosition,limit, keyword);
    }

    public Integer countUserByKeyword(String keyword) {
        return questionDao.countUserByKeyword(keyword);
    }

    public void deleteQuestion(int id) {
        questionDao.deleteQuestion(id);
    }

    public String getQuesById(int questionId) {
        return questionDao.findQuesById(questionId);
    }

    public List<Question> getSingleSelection(int type) {
        return questionDao.findSingleSelection(type);
    }

    public List<Question> getMultSelection(int type) {
        return questionDao.findMultSelection(type);
    }

    public List<String> getQuesAndAnswer(int type) {
        return questionDao.findQuesAndAnswer(type);
    }
}
