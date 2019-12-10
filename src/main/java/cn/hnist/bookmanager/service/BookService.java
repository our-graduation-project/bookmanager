package cn.hnist.bookmanager.service;

import cn.hnist.bookmanager.model.Book;

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
}
