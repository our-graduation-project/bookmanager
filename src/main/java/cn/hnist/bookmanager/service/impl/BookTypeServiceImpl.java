package cn.hnist.bookmanager.service.impl;

import cn.hnist.bookmanager.mapper.BooktypeMapper;
import cn.hnist.bookmanager.model.Booktype;
import cn.hnist.bookmanager.model.BooktypeExample;
import cn.hnist.bookmanager.service.BookTypeService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: Zxw
 * @Description:
 * @Date: Created in 19:38 2019/12/10
 * @Modifued By:
 */
@Service
public class BookTypeServiceImpl implements BookTypeService {

    @Autowired
    private BooktypeMapper booktypeMapper;


    /**
     * 添加书类型
     *
     * @param booktype 书类型
     * @return
     */
    @Override
    public int addBook(Booktype booktype) {

        System.out.println(booktypeMapper.toString());
        return booktypeMapper.insert(booktype);
    }

    /**
     * 删除书类型
     *
     * @param id 书类型id
     * @return
     */
    @Override
    public int deleteBookType(int id) {
        return booktypeMapper.deleteByPrimaryKey(id);
    }


    /**
     * 更新书类型
     *
     * @param booktype 书类型
     * @return
     */
    @Override
    public int updateBookType(Booktype booktype) {
        return booktypeMapper.updateByPrimaryKeySelective(booktype);
    }

    /**
     * 通过booktypeId查询书类型
     *
     * @param booktypeId 书类型id
     * @return
     */
    @Override
    public Booktype searchBookTypeById(Integer booktypeId){
        return booktypeMapper.selectByPrimaryKey(booktypeId);
    }

    /**
     * 通过page分页查询书类型
     * 书类型名字传入则对该名字进行模糊查询（要进行模糊查询自己需要加上%）并进行分页
     *
     * @param indexPage 页码
     * @param pageSize 查询数量
     * @param name 书类型名
     * @return
     */
    @Override
    public PageInfo searchBookTypeByName(Integer indexPage, Integer pageSize,String name) {
        BooktypeExample booktypeExample = new BooktypeExample();
        if(name != null){
            booktypeExample.or().andTypeNameLike(name+"%");
        }else{
            booktypeExample.or();
        }
        PageHelper.startPage(indexPage,pageSize);
        List<Booktype> booktypes = booktypeMapper.selectByExample(booktypeExample);
        PageInfo pageInfo = new PageInfo(booktypes);
        return pageInfo;
    }

    /**
     * 通过page分页查询书类型
     *
     * @param indexPage 页码
     * @param pageSize 查询数量
     * @return
     */
    @Override
    public PageInfo searchAllBookType(Integer indexPage, Integer pageSize) {
        BooktypeExample booktypeExample = new BooktypeExample();
        booktypeExample.or();
        PageHelper.startPage(indexPage,pageSize);
        List<Booktype> booktypes = booktypeMapper.selectByExample(booktypeExample);
        PageInfo pageInfo = new PageInfo(booktypes);
        return pageInfo;
    }
}