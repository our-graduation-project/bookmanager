package cn.hnist.bookmanager.service;

import cn.hnist.bookmanager.model.User;
import cn.hnist.bookmanager.model.UserExample;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface UserService {
    /**
     * 登录
     * @param userName
     * @param userPwd
     * @return
     */
    List<User> loginUser(String userName,String userPwd);

    /**
     * 用户增加
     * @param user
     * @return
     */
    boolean addUser(User user);

    /**
     * 根据ID删除用户
     * @param userId
     * @return
     */
    boolean deleteUser(Integer userId);

    /**
     * 根据ID修改用户
     * @return
     */
    boolean updateUser(User user);

    /**
     * 根据用户名查询用户
     * @param userName
     * @return
     */
    public List<User> searchUserByName(String userName);

    /**
     * 根据用户ID查询用户
     * @param userId
     * @return
     */
    public List<User> searchUserById(Integer userId);

    /**
     * 查询所有用户
     * @return
     */
    public List<User> searchUsers();

    /**
     * 通过page 查询用户
     * @param page 页码
     * @param pageSize 每页数量
     * @return
     */
    PageInfo queryUserByPage(int page, int pageSize);
}
