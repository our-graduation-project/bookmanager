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
    public PageInfo<Bookshelf> searchBookshelfByName(int page, int pageSize,String bookshelfName) {
        BookshelfExample example=new BookshelfExample();
        example.or().andBookshelfNameEqualTo(bookshelfName);
        PageHelper.startPage(page,pageSize);
        List<Bookshelf> bookshelfList=mapper.selectByExample(example);
        PageInfo pageInfo=new PageInfo(bookshelfList,1);
        return pageInfo;
    }

    /**
     * 查询所有书架
     * @return
     */
    @Override
    public PageInfo<Bookshelf> searchBookshelf(int page, int pageSize) {
        BookshelfExample example=new BookshelfExample();
        example.or();
        List<Bookshelf> bookshelf=mapper.selectByExample(example);
        PageInfo pageInfo=new PageInfo(bookshelf,1);
        return pageInfo;
    }

}
