package cn.hnist.bookmanager.service;

import cn.hnist.bookmanager.model.BorrowDetail;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * 借书单Service,里面包含了多借书单的所有操作
 * @author 向清润
 * @createTime 2019.12.10.19:36
 */
public interface BorrowDetailService {
    //正常借出状态
    public static final int NO_RETUEN_FLAG = 1;
    //正常归还状态
    public static final int RETURN_FLAG = 2;
    //逾期归还
    public static final int OVERDUE_RETURN_FLAG = 3;
    //逾期未归还
    public static final int OVERDUE_NO_RETURN_FLAG = 4;

    public static final int NO_RENEW_FLAG = 5;
    //罚金每天一块钱
    public static final double FINE_FLAGE = 1.0;

    /**
     * 增加借书单
     * @param borrowDetail 传入借书单数据，实际还书日期和罚金不需要填写
     * @return 影响行数
     */
    int addBrrowDetail(BorrowDetail borrowDetail);

    /**
     * 还书，如果逾期，则修改为3逾期归还状态，未逾期则正常修改为2归还状态
     * @param borrowDetail 传入借书单信息，增加实际还书日期，并且修改状态。
     * @return 返回受影响行数
     */
    int returnBook(BorrowDetail borrowDetail);

    /**
     * 续借，更改应还日期
     * @param borrowDetailId 传入借书单id
     * @return 返回受影响行数
     */
    int renewalBook(int borrowDetailId);


    /**
     * 分页查询所有逾期书单
     * @param page 当前页码名字
     * @param pageSize 页码大小
     * @return 返回分页信息
     */
    PageInfo<BorrowDetail> searchOverdueBorrowDetail(int page, int pageSize);


    /**
     * 查询所有逾期书单
     * @return 返回借书单信息
     */
    List<BorrowDetail> searchOverdueBorrowDetail();


    /**
     * 分页查询所有借书单
     * @param page 当前的页码
     * @param pageSize 页码大小
     * @return 返回所有借书单,为空则返回空
     */
   PageInfo<BorrowDetail> searchBorrowDetail(int page, int pageSize);

    /**
     *查询单个用户的所有借书单
     * @param page 当前页码
     * @param pageSize 页码大小
     * @param userId 用户ID
     * @return 返回查询到的所有借书单,为空则返回空
     */
    PageInfo<BorrowDetail> searchBorrowDetailByUser(int page, int pageSize,int userId);

    /**
     * 通过Id查询单个的借书单
     * @param borrowDetailId 借书单Id
     * @return 借书单所有信息,没有则返回null
     */
    BorrowDetail searchOneBorrowDetail(int borrowDetailId);


    /**
     * 检索所有即将到期的借书单（三天）
     * @return 所有即将到期的借书单列表
     */
    List<BorrowDetail> AboutToExpire();


    /**
     * 分页检索所有即将到期的书籍
     * @param page 当前页
     * @param pageSize 页面大小
     * @return 书记列表分页信息
     */
    PageInfo<BorrowDetail> AboutToExpire(int page, int pageSize);


    /**
     * 将所有到期的借书单修改状态
     * @return 受影响行数
     */
    int updateOverdue();
}
