package cn.hnist.bookmanager.mapper;

import cn.hnist.bookmanager.model.LabelRelation;
import cn.hnist.bookmanager.model.LabelRelationExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface LabelRelationMapper {
    long countByExample(LabelRelationExample example);

    int deleteByExample(LabelRelationExample example);

    int deleteByPrimaryKey(Integer labelRelationId);

    int insert(LabelRelation record);

    int insertSelective(LabelRelation record);

    List<LabelRelation> selectByExample(LabelRelationExample example);

    LabelRelation selectByPrimaryKey(Integer labelRelationId);

    int updateByExampleSelective(@Param("record") LabelRelation record, @Param("example") LabelRelationExample example);

    int updateByExample(@Param("record") LabelRelation record, @Param("example") LabelRelationExample example);

    int updateByPrimaryKeySelective(LabelRelation record);

    int updateByPrimaryKey(LabelRelation record);
}