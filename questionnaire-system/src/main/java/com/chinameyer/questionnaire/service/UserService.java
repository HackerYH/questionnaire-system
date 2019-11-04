package com.chinameyer.questionnaire.service;

import com.chinameyer.questionnaire.bean.User;
import com.chinameyer.questionnaire.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    public User getAdminByKeyWords(String username, String password){
        return userDao.findAdminByKeyWords(username,password);
    }

    public void changePassword(String username,String newPassword){
        userDao.changePassword(username, newPassword);
    }

    public List<User> getAllUserByPage(Integer startPosition, Integer limit) {
        return userDao.findAllUserByPage(startPosition,limit);
    }

    public Integer countAllUser() {
        return userDao.countAllUser();
    }

    public List<User> getAllUserByPageAndNo(Integer startPosition, Integer limit, String keyword) {
        return userDao.getAllUserByPageAndNo(startPosition,limit, keyword);
    }

    public Integer countUserByKeyword(String keyword) {
        return userDao.countUserByKeyword(keyword);
    }

    public void deleteUser(int id) {
        userDao.deleteUser(id);
    }
}
