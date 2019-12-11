package cn.hnist.bookmanager.service;

import cn.hnist.bookmanager.model.User;
import cn.hnist.bookmanager.model.UserExample;
import com.github.pagehelper.PageInfo;

import javax.jws.soap.SOAPBinding;
import java.util.List;

public interface UserService {
    /**
     * 登录
     * @return
     */
    boolean loginUser(User user);

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
    PageInfo<User> searchUserByName(int page, int pageSize,String userName);

    /**
     * 根据用户ID查询用户
     * @param userId
     * @return
     */
    User searchUserById(Integer userId);

    /**
     * 查询所有用户
     * @return
     */
    PageInfo<User> searchUsers(int page, int pageSize);

}
