package cn.hnist.bookmanager.service;


import cn.hnist.bookmanager.model.Bookshelf;
import cn.hnist.bookmanager.model.User;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface BookshelfService {
    /**
     * 增加书架
     * @param shelf
     * @return
     */
    boolean addBookshelf(Bookshelf shelf);

    /**
     * 删除书架
     * @param bookshelfId
     * @return
     */
    boolean deleteBookshelf(Integer bookshelfId);

    /**
     * 修改书架
     * @param shelf
     * @return
     */
    boolean updateBookshelf(Bookshelf shelf);

    /**
     * 根据书架名查询书架
     * @param bookshelfName
     * @return
     */
    PageInfo<Bookshelf> searchBookshelfByName(int page, int pageSize,String bookshelfName);

    /**
     * 查询所有书架
     * @return
     */
    PageInfo<Bookshelf> searchBookshelf(int page, int pageSize);

}
