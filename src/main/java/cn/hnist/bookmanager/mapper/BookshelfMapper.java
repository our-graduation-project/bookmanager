package cn.hnist.bookmanager.mapper;

import cn.hnist.bookmanager.model.Bookshelf;
import cn.hnist.bookmanager.model.BookshelfExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface BookshelfMapper {
    long countByExample(BookshelfExample example);

    int deleteByExample(BookshelfExample example);

    int deleteByPrimaryKey(Integer bookshelfId);

    int insert(Bookshelf record);

    int insertSelective(Bookshelf record);

    List<Bookshelf> selectByExample(BookshelfExample example);

    Bookshelf selectByPrimaryKey(Integer bookshelfId);

    int updateByExampleSelective(@Param("record") Bookshelf record, @Param("example") BookshelfExample example);

    int updateByExample(@Param("record") Bookshelf record, @Param("example") BookshelfExample example);

    int updateByPrimaryKeySelective(Bookshelf record);

    int updateByPrimaryKey(Bookshelf record);
}