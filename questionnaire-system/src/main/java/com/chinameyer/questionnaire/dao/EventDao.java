package com.chinameyer.questionnaire.dao;

import com.chinameyer.questionnaire.bean.Event;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @Author HongYe
 * @Date 2019/11/1 15:14
 */
public interface EventDao {

    @Select("select id,questionnaire_index as questionnaireIndex,title from event order by id limit #{startPosition},#{limit}")
    List<Event> findAllQuestionnaireIndexByPage(Integer startPosition, Integer limit);

    @Select("select count(*) from event")
    Integer countAllQuestionnaireIndex();

    @Select("select questionnaire_index as questionnaireIndex from event where id = #{id}")
    String findQuestionnaireIndexById(@Param("id") int id);

    @Delete("delete from event where id = #{id}")
    void deleteEventById(@Param("id") int id);
}
