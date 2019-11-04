package com.chinameyer.questionnaire.dao;

import com.chinameyer.questionnaire.bean.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;


public interface UserDao {

    @Select("SELECT id,username,password FROM user WHERE username = #{username} AND password = #{password}")
    User findAdminByKeyWords(@Param("username") String username, @Param("password") String password);

    @Update("UPDATE user SET password = #{newPassword} where username = #{username}")
    void changePassword(@Param("username") String username, @Param("newPassword") String newPassword);

    @Select("select id,staffno as staffNo,username,password,create_time as createTime from user order by id limit #{startPosition},#{limit}")
    List<User> findAllUserByPage(Integer startPosition, Integer limit);

    @Select("select count(*) from user")
    Integer countAllUser();

    @Select("select count(*) from user where staffno = #{keyword}")
    Integer countUserByKeyword(String keyword);

    @Select(" select id,staffno as staffNo,username,password,create_time as createTime from user where staffno = #{keyword} order by id limit #{startPosition},#{limit}")
    List<User> getAllUserByPageAndNo(Integer startPosition, Integer limit, String keyword);

    @Delete("delete from user where id = #{id}")
    void deleteUser(int id);
}
