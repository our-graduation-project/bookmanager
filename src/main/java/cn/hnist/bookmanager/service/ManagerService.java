package cn.hnist.bookmanager.service;

import cn.hnist.bookmanager.model.Manager;
import com.github.pagehelper.PageInfo;


/**
 * @Author: Zxw
 * @Description:
 * @Date: Created in 19:36 2019/12/10
 * @Modifued By:
 */
public interface ManagerService {

    /**
     * 管理员登录
     * @param manager 管理员
     * @return boolean
     */
    Manager login(Manager manager);


    /**
     * 添加管理员
     * @param manager
     * @return
     */
    int addManager(Manager manager);

    /**
     * 修改管理员信息
     * @param manager
     * @return
     */
    int updateManager(Manager manager);

    /**
     * 通过managerId查询管理员
     * @param managerId
     * @return
     */
    Manager searchManagerById(Integer managerId);

    /**
     * 通过managerName查询管理员
     * @param managerName
     * @return
     */
    PageInfo searchManagerByName(Integer indexPage, Integer pageSize,String managerName);

    /**
     * 分页查询所有管理员
     * @param indexPage
     * @param pageSize
     * @return
     */
    PageInfo searchAllManager(Integer indexPage, Integer pageSize);

    /**
     * 通过邮箱查询管理员
     * @param
     * @param mailBox
     * @return
     */
    Manager searchByMailBox(String mailBox);

    /**
     * 通过邮箱查询管理员
     * @param
     * @param phone
     * @return
     */
    Manager searchByPhone(String phone);

    /**
     * 通过邮箱查询管理员
     * @param
     * @param managerNickname
     * @return
     */
    Manager searchByManagerNickname(String managerNickname);

}