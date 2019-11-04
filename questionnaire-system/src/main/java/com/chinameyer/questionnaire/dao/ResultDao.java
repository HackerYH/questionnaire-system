package com.chinameyer.questionnaire.dao;
import	java.lang.reflect.Parameter;

import com.chinameyer.questionnaire.bean.Result;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @Author HongYe
 * @Date 2019/10/30 15:54
 */
public interface ResultDao {

    @Select("select id,question_id as questionId,answer from results_collected order by id limit #{startPosition},#{limit}")
    List<Result> findAllUserByPage(@Param("startPosition") Integer startPosition, @Param("limit") Integer limit);

    @Select("select count(*) from results_collected")
    Integer countAllUser();

    @Select("select id,question_id as questionId,answer,result_index as resultIndex,type from results_collected where result_index = #{index} and type = #{type}")
    List<Result> findSingleResultIdByIndexAndType(@Param("index") String index,@Param("type")int type);

    @Select("select id,question_id as questionId,answer,result_index as resultIndex,type from results_collected where result_index = #{index} and type = #{type}")
    List<Result> findMultResultIdByIndexAndType(@Param("index")String index,@Param("type")int type);
}
