package cn.hnist.bookmanager.service;

import cn.hnist.bookmanager.model.Booktype;
import com.github.pagehelper.PageInfo;
import io.swagger.models.auth.In;
import org.omg.CORBA.INTERNAL;

import java.util.List;

/**
 * @Author: Zxw
 * @Description:
 * @Date: Created in 19:37 2019/12/10
 * @Modifued By:
 */
public interface BookTypeService {

    /**
     * 添加书籍类型
     * @param booktype
     * @return boolean
     */
    int addBook(Booktype booktype);

    /**
     *
     * @param id
     * @return
     */
    int deleteBookType(int id);

    /**
     * 通过书籍类型id修改书籍类型
     * @param booktype
     * @return
     */
    int updateBookType(Booktype booktype);

    /**
     * 通过书籍类型名分页查询查找书籍类型
     * @param name
     * @return
     */
    Booktype searchBookTypeById(Integer booktypeId);

    /**
     * 通过书籍类型名分页查询查找书籍类型
     * @param name
     * @return
     */
    PageInfo searchBookTypeByName(Integer indexPage,Integer pageSize,String name);

    /**
     * 分页查询书籍类型
     * @param
     * @return
     */
    PageInfo searchAllBookType(Integer indexPage,Integer pageSize);

}
