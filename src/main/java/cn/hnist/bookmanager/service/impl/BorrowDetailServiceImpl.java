package cn.hnist.bookmanager.service.impl;

import cn.hnist.bookmanager.mapper.BorrowDetailMapper;
import cn.hnist.bookmanager.model.BorrowDetail;
import cn.hnist.bookmanager.model.BorrowDetailExample;
import cn.hnist.bookmanager.service.BorrowDetailService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
 * @author 向清润
 * @createTime 2019.12.10.20:33
 */

@Service
public class BorrowDetailServiceImpl implements BorrowDetailService {

    @Autowired
    @Resource(type = BorrowDetailMapper.class)
    BorrowDetailMapper borrowDetailMapper;

    /**
     * 增加借书单
     *
     * @param borrowDetail 传入借书单数据，实际还书日期和罚金不需要填写
     * @return 影响行数
     */
    @Override
    public int addBrrowDetail(BorrowDetail borrowDetail) {
        int len = 0;
        len = borrowDetailMapper.insert(borrowDetail);
        return len;
    }

    /**
     * 还书，如果逾期，则修改为3逾期归还状态，未逾期则正常修改为2归还状态
     *
     * @param borrowDetail 传入借书单信息，增加实际还书日期，并且修改状态。
     * @return 返回受影响行数
     */
    @Override
    public int returnBook(BorrowDetail borrowDetail) {
        int len = 0;
        if(borrowDetail.getBorrowId()!=null){
            BorrowDetail outborrowDetail = borrowDetailMapper.selectByPrimaryKey(borrowDetail.getBorrowId());
            borrowDetail.setRealReturnDate(new Date());
            if(outborrowDetail.getStatus() == BorrowDetailService.OVERDUE_NO_RETURN_FLAG){
                borrowDetail.setStatus(BorrowDetailService.OVERDUE_RETURN_FLAG);
                int days = differentDays(borrowDetail.getBorrowDate(), borrowDetail.getRealReturnDate());
                double fine = days*BorrowDetailService.FINE_FLAGE;
                borrowDetail.setFine(fine);

            }else {
                borrowDetail.setStatus(BorrowDetailService.RETURN_FLAG);
            }
            len = borrowDetailMapper.updateByPrimaryKeySelective(borrowDetail);

        }

        return len;
    }


    /**
     * 计算两时间之间相差的天数
     * @param date1 借阅时间
     * @param date2 归还时间
     * @return 相差的天数
     */
    public int differentDays (Date date1,Date date2)
    {


        int day = (int)((date2.getTime()-date1.getTime())/(3600*1000*24));
        return day;
    }


    /**
     * 续借，更改应还日期
     *
     * @param borrowDetailId 传入借书单id
     * @return 返回受影响行数
     */
    @Override
    public int renewalBook(int borrowDetailId) {
        int len = 0;
        BorrowDetailExample borrowDetailExample = new BorrowDetailExample();
        borrowDetailExample.or().andBorrowIdEqualTo(borrowDetailId);

        //查询该书单
        BorrowDetail borrowDetail = searchOneBorrowDetail(borrowDetailId);
        if(borrowDetail.getStatus() == BorrowDetailService.NO_RETUEN_FLAG){
            //增加三十天借阅时间

            Date changeDate = addDate(borrowDetail.getShouldReturnDate(),30);
            borrowDetail.setShouldReturnDate(changeDate);
            borrowDetail.setStatus(BorrowDetailService.NO_RENEW_FLAG);
            len = borrowDetailMapper.updateByExample(borrowDetail, borrowDetailExample);
        }

        return len;
    }


    /**
     * 增加天数
     * @param outDate 原来的时间
     * @param day 增加的天数
     * @return 新的时间
     */
    private Date addDate(Date outDate,int day){
        Calendar calendar=Calendar.getInstance();
        calendar.setTime(outDate);
        //long time = borrowDetail.getShouldReturnDate().getTime()+30*24*60*60*1000;
        calendar.add(Calendar.DATE,day);
        Date newDate = calendar.getTime();
        return newDate;
    }


    /**
     * 分页查询所有逾期书单
     * @param page 当前页码名字
     * @param pageSize 页码大小
     * @return 返回分页信息
     */
    @Override
    public PageInfo<BorrowDetail> searchOverdueBorrowDetail(int page, int pageSize) {
        BorrowDetailExample borrowDetailExample = new BorrowDetailExample();
        borrowDetailExample.or().andStatusEqualTo(BorrowDetailService.OVERDUE_NO_RETURN_FLAG);
        PageHelper.startPage(page,pageSize);
        List<BorrowDetail> borrowDetails = borrowDetailMapper.selectByExample(borrowDetailExample);
        PageInfo pageInfo = new PageInfo(borrowDetails,5);
        return pageInfo;
    }

    /**
     * 查询所有逾期书单
     *
     * @return 返回借书单信息
     */
    @Override
    public List<BorrowDetail> searchOverdueBorrowDetail() {
        BorrowDetailExample borrowDetailExample = new BorrowDetailExample();
        borrowDetailExample.or().andStatusEqualTo(BorrowDetailService.OVERDUE_NO_RETURN_FLAG);
        List<BorrowDetail> borrowDetails = borrowDetailMapper.selectByExample(borrowDetailExample);

        return borrowDetails;
    }

    /**
     * 分页查询所有借书单
     *
     * @param page     当前的页码
     * @param pageSize 页码大小
     * @return 返回所有借书单, 为空则返回空
     */
    @Override
    public PageInfo<Map> searchBorrowDetail(int page, int pageSize) {
        BorrowDetailExample borrowDetailExample = new BorrowDetailExample();
        borrowDetailExample.or();
        PageHelper.startPage(page,pageSize);

        List<Map> borrowDetails = borrowDetailMapper.selectBorrowDetal();
        for ( Map m:
             borrowDetails) {
            if(m.get("realReturnDate") == null){
                m.put("realReturnDate","未归还");
            }
            if(m.get("fine") == null){
                m.put("fine",0.0);
            }
        }

        PageInfo pageInfo = new PageInfo(borrowDetails,5);
        return pageInfo;
    }

    /**
     * 查询单个用户的所有借书单
     *
     * @param page     当前页码
     * @param pageSize 页码大小
     * @param userId   用户ID
     * @return 返回查询到的所有借书单, 为空则返回空
     */
    @Override
    public PageInfo<Map> searchBorrowDetailByUser(int page, int pageSize, int userId) {

        PageHelper.startPage(page,pageSize);

        Map map = new HashMap();
        map.put("userId",userId);
        List<Map> borrowDetails = borrowDetailMapper.selectUserBorrowDetal(map);
        for ( Map m:
                borrowDetails) {
            if(m.get("realReturnDate") == null){
                m.put("realReturnDate","未归还");
            }
            if(m.get("fine") == null){
                m.put("fine",0.0);
            }
        }
        PageInfo pageInfo = new PageInfo(borrowDetails,5);
        return pageInfo;
    }

    /**
     * 通过Id查询单个的借书单
     *
     * @param borrowDetailId 借书单Id
     * @return 借书单所有信息, 没有则返回null
     */
    @Override
    public BorrowDetail searchOneBorrowDetail(int borrowDetailId) {
        BorrowDetail borrowDetail = borrowDetailMapper.selectByPrimaryKey(borrowDetailId);
        return borrowDetail;
    }

    /**
     * 检索所有即将到期的借书单(三天)
     *
     * @return 所有即将到期的借书单列表
     */
    @Override
    public List<BorrowDetail> AboutToExpire() {
        BorrowDetailExample borrowDetailExample = new BorrowDetailExample();
        Date aboutDate = new Date();
        Date date = addDate(aboutDate, 3);
        System.out.println(date);
        borrowDetailExample.or().andShouldReturnDateLessThanOrEqualTo(date).andStatusEqualTo(BorrowDetailService.NO_RETUEN_FLAG);
        List<BorrowDetail> borrowDetails = borrowDetailMapper.selectByExample(borrowDetailExample);
        return borrowDetails;
    }

    /**
     * 分页检索所有即将到期的书籍
     *
     * @param page     当前页
     * @param pageSize 页面大小
     * @return 书记列表分页信息
     */
    @Override
    public PageInfo<BorrowDetail> AboutToExpire(int page, int pageSize) {
        BorrowDetailExample borrowDetailExample = new BorrowDetailExample();
        Date aboutDate = new Date();
        Date date = addDate(aboutDate, 3);
        borrowDetailExample.or().andShouldReturnDateLessThanOrEqualTo(date).andStatusEqualTo(BorrowDetailService.NO_RETUEN_FLAG);

        PageHelper.startPage(page,pageSize);
        List<BorrowDetail> borrowDetails = borrowDetailMapper.selectByExample(borrowDetailExample);
       PageInfo pageInfo = new PageInfo(borrowDetails,3);
       return pageInfo;
    }

    /**
     * 将所有到期的借书单修改状态
     *
     * @return 受影响行数
     */
    @Override
    public int updateOverdue() {
        BorrowDetailExample borrowDetailExample = new BorrowDetailExample();
        Date date = new Date();
        borrowDetailExample.or().andStatusEqualTo(BorrowDetailService.NO_RETUEN_FLAG).andShouldReturnDateLessThanOrEqualTo(date);
        borrowDetailExample.or().andStatusEqualTo(BorrowDetailService.NO_RENEW_FLAG).andShouldReturnDateLessThanOrEqualTo(date);
        BorrowDetail borrowDetail = new BorrowDetail();
        borrowDetail.setStatus(BorrowDetailService.OVERDUE_NO_RETURN_FLAG);
        int len = borrowDetailMapper.updateByExampleSelective(borrowDetail, borrowDetailExample);
        return len;
    }
}
