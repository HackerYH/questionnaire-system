package com.chinameyer.questionnaire.service;

import com.chinameyer.questionnaire.bean.Result;
import com.chinameyer.questionnaire.dao.ResultDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author HongYe
 * @Date 2019/10/30 15:49
 */
@Service
public class ResultService {

    @Autowired
    private ResultDao resultDao;

    public List<Result> getAllUserByPage(Integer startPosition, Integer limit) {
        return resultDao.findAllUserByPage(startPosition,limit);
    }

    public Integer countAllUser() {
        return resultDao.countAllUser();
    }

    public List<Result> getSingleResultIdByIndexAndType(String index,int type) {
        return resultDao.findSingleResultIdByIndexAndType(index,type);
    }

    public List<Result> getMultResultIdByIndexAndType(String questionnaireIndex, int type) {
        return resultDao.findMultResultIdByIndexAndType(questionnaireIndex,type);
    }
}
