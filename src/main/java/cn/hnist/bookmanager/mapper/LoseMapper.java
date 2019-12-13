package cn.hnist.bookmanager.mapper;

import cn.hnist.bookmanager.model.Lose;
import cn.hnist.bookmanager.model.LoseExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface LoseMapper {
    long countByExample(LoseExample example);

    int deleteByExample(LoseExample example);

    int deleteByPrimaryKey(Integer loseId);

    int insert(Lose record);

    int insertSelective(Lose record);

    List<Lose> selectByExample(LoseExample example);

    Lose selectByPrimaryKey(Integer loseId);

    int updateByExampleSelective(@Param("record") Lose record, @Param("example") LoseExample example);

    int updateByExample(@Param("record") Lose record, @Param("example") LoseExample example);

    int updateByPrimaryKeySelective(Lose record);

    int updateByPrimaryKey(Lose record);

    List<Map>selectLoseList();
}