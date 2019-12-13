package cn.hnist.bookmanager.service.impl;

import cn.hnist.bookmanager.mapper.BookMapper;
import cn.hnist.bookmanager.model.Book;
import cn.hnist.bookmanager.model.BookExample;
import cn.hnist.bookmanager.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author whg
 * @date 2019/12/9 20:44
 **/
@Service
public class BookServiceImpl implements BookService {

    @Autowired
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
}
