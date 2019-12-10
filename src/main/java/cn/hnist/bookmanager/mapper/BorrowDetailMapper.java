package cn.hnist.bookmanager.mapper;

import cn.hnist.bookmanager.model.BorrowDetail;
import cn.hnist.bookmanager.model.BorrowDetailExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BorrowDetailMapper {
    long countByExample(BorrowDetailExample example);

    int deleteByExample(BorrowDetailExample example);

    int deleteByPrimaryKey(Integer borrowId);

    int insert(BorrowDetail record);

    int insertSelective(BorrowDetail record);

    List<BorrowDetail> selectByExample(BorrowDetailExample example);

    BorrowDetail selectByPrimaryKey(Integer borrowId);

    int updateByExampleSelective(@Param("record") BorrowDetail record, @Param("example") BorrowDetailExample example);

    int updateByExample(@Param("record") BorrowDetail record, @Param("example") BorrowDetailExample example);

    int updateByPrimaryKeySelective(BorrowDetail record);

    int updateByPrimaryKey(BorrowDetail record);
}