package cn.hnist.bookmanager.service.impl;

import cn.hnist.bookmanager.mapper.BookshelfMapper;
import cn.hnist.bookmanager.model.Bookshelf;
import cn.hnist.bookmanager.model.BookshelfExample;
import cn.hnist.bookmanager.model.User;
import cn.hnist.bookmanager.service.BookshelfService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookshelfServiceImpl implements BookshelfService {

    @Autowired
    private BookshelfMapper mapper;

    /**
     * 增加书架
     * @param shelf
     * @return
     */
    @Override
    public boolean addBookshelf(Bookshelf shelf) {
        if (shelf!=null&&shelf.getBookshelfName()!=null){
            return mapper.insert(shelf)>0?true:false;
        }
        return false;
    }

    /**
     * 根据ID删除书架
     * @param bookshelfId
     * @return
     */
    @Override
    public boolean deleteBookshelf(Integer bookshelfId) {
        return mapper.deleteByPrimaryKey(bookshelfId)>0?true:false;
    }

    /**
     * 修改书架
     * @param shelf
     * @return
     */
    @Override
    public boolean updateBookshelf(Bookshelf shelf) {
        int i=mapper.updateByPrimaryKey(shelf);
        return i>0?true:false;
    }

    /**
     * 根据书架名查询书架
     * @param bookshelfName
     * @return
     */
    @Override
    public List<Bookshelf> searchBookshelfByName(String bookshelfName) {
        BookshelfExample example=new BookshelfExample();
        example.or().andBookshelfNameEqualTo(bookshelfName);
        List<Bookshelf> bookshelf=mapper.selectByExample(example);
        if (bookshelf!=null&&!bookshelf.isEmpty()){
            return bookshelf;
        }
        return null;
    }

    /**
     * 查询所有书架
     * @return
     */
    @Override
    public List<Bookshelf> searchBookshelf() {
        BookshelfExample example=new BookshelfExample();
        example.or();
        List<Bookshelf> bookshelf=mapper.selectByExample(example);
        return bookshelf;
    }

    /**
     * 通过page分页查询用户
     *
     * @param page     页码
     * @param pageSize 每页数量
     * @return
     */
    @Override
    public PageInfo queryBookshelfByPage(int page, int pageSize) {
        PageHelper.startPage(page,pageSize);
        BookshelfExample bookshelfExample=new BookshelfExample();
        bookshelfExample.or();
        List<Bookshelf> bookshelf=mapper.selectByExample(bookshelfExample);
        PageInfo pageInfo=new PageInfo(bookshelf,2);
        return pageInfo;
    }
}