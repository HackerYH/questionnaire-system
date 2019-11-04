package com.chinameyer.questionnaire.dao;

import com.chinameyer.questionnaire.bean.Question;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @Author HongYe
 * @Date 2019/11/1 9:21
 */
public interface QuestionnaireDao {

    @Insert("insert into selected_questions (question,question_order,type) values (#{key},#{questionOrder},#{questionType})")
    void InsertQuestionToSelectedList(String key, int questionOrder, int questionType);

    @Delete("delete from selected_questions where event_id = #{eventId}")
    void deleteQuestionnaireByEventId(@Param("eventId") int eventId);

    @Insert("insert into event (questionnaire_index,title) values (#{questionnaireIndex},#{strTitle})")
    void InsertEvent(String questionnaireIndex, String strTitle);
}
