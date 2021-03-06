package cn.hnist.bookmanager.component;

import cn.hnist.bookmanager.common.QueueEnum;
import cn.hnist.bookmanager.model.Book;
import cn.hnist.bookmanager.model.BorrowDetail;
import cn.hnist.bookmanager.model.User;
import cn.hnist.bookmanager.service.BookService;
import cn.hnist.bookmanager.service.BorrowDetailService;
import cn.hnist.bookmanager.service.UserService;
import cn.hnist.bookmanager.utils.LogUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageDeliveryMode;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import java.util.List;


/**
 * 借书逾期定时器
 * @author whg
 * @date 2019/12/11 10:35
 **/
@Component
public class BorrowerBookTimeOutTask {

    @Autowired
    private BorrowDetailService borrowDetailService;

    @Autowired
    private UserService userService;

    @Autowired
    private BookService bookService;

    @Autowired
    private SenderToQueue senderToQueue;

    @Autowired
    private SendRegisterToQueue sendRegisterToQueue;

    /**
     * 向快要过期或过期的书单发送邮件
     * cron表达式：Seconds Minutes Hours DayofMonth Month DayofWeek [Year]
     * 每XXX扫描一次，扫描设定超时时间之前的借书订单，如果到了约定时间没有归还
     * 则发送邮件给用户，并且改变数据库中借书订单的状态变为逾期未还
     */
//    @Scheduled(cron = "0 0/30 * ? * ?")//每小时运行一次
    @Scheduled(cron = "0 0 0 ? * ? ") //每天运行一次
//    @Scheduled(cron = "0 1-2 * * * ? ")//每分钟运行一次
    private void sentEmailToTimeOutOrder() {
        System.out.println("添加入队列");
        List<BorrowDetail> borrowDetails = borrowDetailService.AboutToExpire();
        for (BorrowDetail b : borrowDetails) {
            User user = userService.searchUserById(b.getUserId());
            Book bookById = bookService.findBookById(b.getBookId());
            JSONObject jsonObject = (JSONObject) JSON.toJSON(user);
            jsonObject.put("shouldReturnTime",b.getShouldReturnDate().toString());
            jsonObject.put("bookName", bookById.getBookName());
            System.out.println(jsonObject);
            //转换为json的字符串然后发送
            Message message = sendRegisterToQueue.setMessage(jsonObject.toJSONString());
            senderToQueue.sendMessage(QueueEnum.QUEUE_SENT_EAILL.getExchange(),QueueEnum.QUEUE_SENT_EAILL.getRouteKey(),message);
            LogUtils.getTimeOutTaskLogger().info("向队列中添加向" + user.getUserName() + "发送邮件的任务");
        }

//        /**
//         * 修改到了归还日期未还的书
//         */
//        borrowDetailService.updateOverdue();

//        验证码测试
//        System.out.println("发送验证码");
//        sendRegisterToQueue.sendToQueue("1234","1547527394@qq.com");
    }


}
