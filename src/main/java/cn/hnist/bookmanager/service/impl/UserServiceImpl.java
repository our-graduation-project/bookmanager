package cn.hnist.bookmanager.service.impl;

import cn.hnist.bookmanager.mapper.UserMapper;
import cn.hnist.bookmanager.model.User;
import cn.hnist.bookmanager.model.UserExample;
import cn.hnist.bookmanager.service.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper mapper;

    /**
     * 用户登录
     * @param userName
     * @param userPwd
     * @return
     */
    @Override
    public List<User> loginUser(String userName, String userPwd) {
        UserExample userExample = new UserExample();
        userExample.or().andUserNameEqualTo(userName).andUserPwdEqualTo(userPwd);
        List<User> users = mapper.selectByExample(userExample);
        if (users != null && !users.isEmpty()) {
            return users;
        }
        return null;
    }

    /**
     * 用户增加
     * @param user
     * @return
     */
    @Override
    public boolean addUser(User user) {
        if (user!=null && user.getUserName()!=null){
            return mapper.insert(user)>0?true:false;
        }
        return false;
    }

    /**
     * 根据ID删除用户
     * @param userId
     * @return
     */
    @Override
    public boolean deleteUser(Integer userId) {
        return mapper.deleteByPrimaryKey(userId)>0?true:false;
    }

    /**
     * 修改用户
     * @param user
     * @return
     */
    @Override
    public boolean updateUser(User user) {
        int i=mapper.updateByPrimaryKey(user);
        return i>0?true:false;
    }



    /**
     * 根据用户名查询用户
     * @param userName
     * @return
     */
    @Override
    public PageInfo<User> searchUserByName(int page, int pageSize,String userName) {
        UserExample userExample=new UserExample();
        userExample.or().andUserNameEqualTo(userName);
        PageHelper.startPage(page,pageSize);
        List<User> users=mapper.selectByExample(userExample);
        PageInfo pageInfo=new PageInfo(users,1);
        return pageInfo;
    }

    /**
     * 根据用户编号查询用户
     * @param userId
     * @return
     */
    @Override
    public User searchUserById(Integer userId) {
        User user=mapper.selectByPrimaryKey(userId);
        return user;
    }

    /**
     * 查询所有用户
     * @return
     */
    @Override
    public PageInfo<User> searchUsers(int page, int pageSize) {
        UserExample userExample = new UserExample();
        userExample.or();
        PageHelper.startPage(page,pageSize);
        List<User> users = mapper.selectByExample(userExample);
        PageInfo pageInfo=new PageInfo(users,1);
        return pageInfo;
    }

}
