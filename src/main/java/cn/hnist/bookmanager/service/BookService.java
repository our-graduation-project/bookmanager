package cn.hnist.bookmanager.service;

import cn.hnist.bookmanager.model.Book;
import cn.hnist.bookmanager.model.Booktype;
import cn.hnist.bookmanager.model.Label;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @author whg
 * @date 2019/12/9 20:44
 **/
public interface BookService {

    /**
     * 通过id查询书本
     * @param id
     * @return
     */
    Book findBookById(int id);

    /**
     * 增加图书
     * @param book
     * @return
     */
    int addBook(Book book);


    /**
     * 根据图书id删除图书
     * @param id
     * @return
     */
    int deleteBook(int id);


    /**
     * 根据图书id修改图书信息
     * @param id
     * @param book
     * @return
     */
    int updateBook(int id,Book book);

    /**
     * 通过图书名查询图书id
     * @param name
     * @return
     */
    int selectBookIdByName(String name);

    /**
     * 通过书名查询图书
     * @param name
     * @return
     */
    Book selectBookByName(String name);

    /**
     * 通过图书类型查询图书
     * @param booktype
     * @return
     */
    List<Book> selectBookByType(Booktype booktype);

    /**
     * 通过作者名查询图书
     * @param authorname
     * @return
     */
    List<Book> selectBookByAuthor(String authorname);

    /**
     * 通过关键字查询图书
     * @param keyword
     * @return
     */
    List<Book> selectBookByKeyWord(String keyword);

    /**
     * 通过标签查询图书
     * @param labelName
     * @return
     */
    List<Book> selectBookByLabelName(String labelName);

    /**
     * 查询所有图书
     * @return
     */
    List<Book> selectAllBook();

    /**
     * 通过page查询图书
     * @param page
     * @param pageSize
     * @return
     */
    PageInfo queryBookByPage(int page, int pageSize);
}
