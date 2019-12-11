package cn.hnist.bookmanager.service.impl;

import cn.hnist.bookmanager.mapper.BookMapper;
import cn.hnist.bookmanager.model.Book;
import cn.hnist.bookmanager.model.BookExample;
import cn.hnist.bookmanager.model.Booktype;
import cn.hnist.bookmanager.service.BookService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author whg
 * @date 2019/12/9 20:44
 **/
@Service
public class BookServiceImpl implements BookService {

    @Autowired
    @Resource
    private BookMapper bookMapper;

    /**
     * 通过id查询书本
     *
     * @param id
     * @return
     */
    @Override
    public Book findBookById(int id) {
        BookExample bookExample = new BookExample();
        bookExample.or().andBookIdEqualTo(id);
        List<Book> books = bookMapper.selectByExample(bookExample);
        if(books == null || books.isEmpty()){
            return null;
        }
        return books.get(0);
    }

    /**
     * 增加图书
     * @param book
     * @return
     */
    @Override
    public int addBook(Book book) {
        if(book != null && book.getBookName() != null){
            return bookMapper.insert(book);
        }
        return 0;
    }

    /**
     * 根据图书id删除图书
     * @param id
     * @return
     */
    @Override
    public int deleteBook(int id) {
        return bookMapper.deleteByPrimaryKey(id);
    }

    /**
     * 根据图书id修改图书信息
     * @param id
     * @param book
     * @return
     */
    @Override
    public int updateBook(int id, Book book) {
        if(book != null && id ==book.getBookId()){
            BookExample bookExample = new BookExample();
            bookExample.or().andBookIdEqualTo(id);
            return bookMapper.updateByExample(book,bookExample);
        }
        return 0;
    }

    /**
     * 通过图书名查询图书id
     * @param name
     * @return
     */
    @Override
    public int selectBookIdByName(String name) {
        BookExample bookExample = new BookExample();
        bookExample.or().andBookNameEqualTo(name);
        List<Book> books = bookMapper.selectByExample(bookExample);
        if (books != null && books.size() != 0){
            return books.get(0).getBookId();
        }
        return 0;
    }

    /**
     * 通过书名查询图书
     * @param name
     * @return
     */
    @Override
    public Book selectBookByName(String name) {
        BookExample bookExample = new BookExample();
        bookExample.or().andBookNameEqualTo(name);
        List<Book> books = bookMapper.selectByExample(bookExample);
        if(books != null && books.size()>0){
            return books.get(0);
        }
        return null;
    }

    /**
     * 通过图书类型查询图书
     * @param booktype
     * @return
     */
    @Override
    public List<Book> selectBookByType(Booktype booktype) {
        BookExample bookExample = new BookExample();
        bookExample.or().andBookTypeEqualTo(booktype.getTypeId());
        List<Book> books = bookMapper.selectByExample(bookExample);
        if(books != null){
            return books;
        }
        return null;
    }

    /**
     * 通过作者名查询图书
     * @param authorname
     * @return
     */
    @Override
    public List<Book> selectBookByAuthor(String authorname) {
        BookExample bookExample = new BookExample();
        bookExample.or().andAuthorEqualTo(authorname);
        List<Book> books = bookMapper.selectByExample(bookExample);
        if(books != null){
            return books;
        }
        return null;
    }

    /**
     * 通过关键字查询图书
     * @param keyword
     * @return
     */
    @Override
    public List<Book> selectBookByKeyWord(String keyword) {
        BookExample bookExample = new BookExample();
        bookExample.or().andBookNameLike("%"+keyword+"%");
        bookExample.or().andAuthorLike("%"+keyword+"%");
        List<Book> books = bookMapper.selectByExample(bookExample);
        if(books != null){
            return books;
        }
        return null;
    }

    /**
     * 通过标签查询图书
     * @param labelName
     * @return
     */
    @Override
    public List<Book> selectBookByLabelName(String labelName) {
        List<Book> books = bookMapper.selctByLabelName(labelName);
        if(books != null){
            return books;
        }
        return null;
    }

    /**
     * 查询所有图书
     * @return
     */
    @Override
    public List<Book> selectAllBook() {
        BookExample bookExample = new BookExample();
        bookExample.or();
        List<Book> books = bookMapper.selectByExample(bookExample);
        if(books != null){
            return books;
        }
        return null;
    }

    /**
     * 通过page查询图书
     * @param page
     * @param pageSize
     * @return
     */
    @Override
    public PageInfo queryBookByPage(int page, int pageSize) {
        PageHelper.startPage(page,pageSize);
        BookExample bookExample = new BookExample();
        bookExample.or();
        List<Book> books = bookMapper.selectByExample(bookExample);
        PageInfo pageInfo = new PageInfo(books,5);
        return pageInfo;

    }
}
