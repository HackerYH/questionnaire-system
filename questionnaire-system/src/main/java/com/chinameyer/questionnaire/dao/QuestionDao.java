package com.chinameyer.questionnaire.dao;
import	java.lang.instrument.Instrumentation;
import	java.nio.channels.Selector;

import com.chinameyer.questionnaire.bean.Question;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @Author HongYe
 * @Date 2019/10/29 17:27
 */
public interface QuestionDao {

    @Select("select id,question,type,options from question_bank order by id limit #{startPosition},#{limit}")
    List<Question> findAllUserByPage(Integer startPosition, Integer limit);

    @Select("select count(*) from question_bank")
    Integer countAllUser();

    @Select("select count(*) from question_bank where  type = #{keyword}")
    Integer countUserByKeyword(String keyword);

    @Select(" select id,type,options,question from question_bank where type = #{keyword} order by id limit #{startPosition},#{limit}")
    List<Question> getAllUserByPageAndNo(Integer startPosition, Integer limit, String keyword);

    @Delete("delete from question_bank where id = #{id}")
    void deleteQuestion(int id);

    @Select("select question from question_bank where id = #{questionId}")
    String findQuesById(int questionId);

    @Select("select question,options from question_bank where type = #{type}")
    List<Question> findSingleSelection(@Param("type") int type);

    @Select("select question,options from question_bank where type = #{type}")
    List<Question> findMultSelection(int type);

    @Select("select question from question_bank where type = #{type}")
    List<String> findQuesAndAnswer(int type);
}
