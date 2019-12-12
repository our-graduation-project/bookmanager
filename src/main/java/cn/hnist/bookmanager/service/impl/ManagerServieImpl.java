package cn.hnist.bookmanager.service.impl;

import cn.hnist.bookmanager.mapper.ManagerMapper;
import cn.hnist.bookmanager.model.Manager;
import cn.hnist.bookmanager.model.ManagerExample;
import cn.hnist.bookmanager.service.ManagerService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: Zxw
 * @Description:
 * @Date: Created in 19:36 2019/12/10
 * @Modifued By:
 */
@Service
public class ManagerServieImpl implements ManagerService {

    @Autowired
    ManagerMapper managerMapper;

    /**
     * 管理员登录验证
     *
     * @param manager 管理员
     * @return
     */
    @Override
    public Manager login(Manager manager) {
        ManagerExample managerExample = new ManagerExample();
        managerExample.or().andMailboxEqualTo(manager.getMailbox())
                .andManagerPwdEqualTo(manager.getManagerPwd());
        List<Manager> managers = managerMapper.selectByExample(managerExample);
        if(managers == null || managers.size() != 1  ){
            return null;
        }
        return managers.get(0);
    }

    /**
     * 增加管理员
     *
     * @param manager 管理员
     * @return
     */
    @Override
    public int addManager(Manager manager) {
        return managerMapper.insert(manager);
    }

    /**
     * 更新管理员信息
     *
     * @param manager 管理员
     * @return
     */

    @Override
    public int updateManager(Manager manager) {
        return managerMapper.updateByPrimaryKeySelective(manager);
    }

    /**
     * 通过managerId查询管理员
     * @param managerId 管理员名
     * @return
     */
    @Override
    public Manager searchManagerById(Integer managerId){
        return managerMapper.selectByPrimaryKey(managerId);
    }

    /**
     * 通过page分页查询管理员
     * 如果页码，页面大小未传入则默认indexPage = 1 ，indexSize = 10
     * 管理员名字传入则对该名字进行模糊查询（要进行模糊查询自己需要加上%）并进行分页
     *
     * @param indexPage 页码
     * @param pageSize 查询数量
     * @param managerName 管理员名
     * @return
     */
    @Override
    public PageInfo searchManagerByName(Integer indexPage, Integer pageSize,String managerName) {
        ManagerExample managerExample = new ManagerExample();
        if(managerName != null) {
            managerExample.or().andManagerNameLike(managerName + "%");
        }else{
            managerExample.or();
        }
        PageHelper.startPage(indexPage,pageSize);
        List<Manager> managers = managerMapper.selectByExample(managerExample);
        PageInfo pageInfo = new PageInfo(managers);
        return pageInfo;
    }


    /**
     * 通过page分页查询管理员
     *
     * @param indexPage 页码
     * @param pageSize 查询数量
     * @return
     */
    @Override
    public PageInfo searchAllManager(Integer indexPage, Integer pageSize) {
        ManagerExample managerExample = new ManagerExample();
        managerExample.or();
        PageHelper.startPage(indexPage,pageSize);
        List<Manager> managers = managerMapper.selectByExample(managerExample);
        PageInfo pageInfo = new PageInfo(managers);
        return pageInfo;
    }

    @Override
    public Manager searchByMailBox(String mailBox) {
        ManagerExample managerExample = new ManagerExample();
        managerExample.or().andMailboxEqualTo(mailBox);
        List<Manager> managers = managerMapper.selectByExample(managerExample);
        if(managers == null || managers.size() == 1){
            return managers.get(0);
        }
        return null;
    }

    @Override
    public Manager searchByPhone(String phone) {
        ManagerExample managerExample = new ManagerExample();
        managerExample.or().andPhoneEqualTo(phone);
        List<Manager> managers = managerMapper.selectByExample(managerExample);
        if(managers == null || managers.size() == 1){
            return managers.get(0);
        }
        return null;
    }

    @Override
    public Manager searchByManagerNickname(String managerNickname) {
        ManagerExample managerExample = new ManagerExample();
        managerExample.or().andManagerNicknameEqualTo(managerNickname);
        List<Manager> managers = managerMapper.selectByExample(managerExample);
        if(managers == null || managers.size() == 1){
            return managers.get(0);
        }
        return null;
    }

}