//package cn.hnist.bookmanager.utils;
//
//import cn.hnist.bookmanager.component.SendRegisterToQueue;
//import cn.hnist.bookmanager.service.RedisStringService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
///**
// * 注册使用的工具类
// * @author whg
// * @date 2019/12/12 23:26
// **/
//@Component
//public class RegisterUtil {
//
//    @Autowired
//    private RedisStringService redisStringService;
//
//    @Autowired
//    private SendRegisterToQueue sendRegisterToQueue;
//
//    /**
//     * 向邮箱发送一个随机email
//     * @param email
//     * @return
//     */
//    public String sendCodeToEmail(String email){
//        String code = (int)(Math.random() * 1000) + "";
//        sendRegisterToQueue.sendToQueue(code, email);
//        redisStringService.set("code",code);
//        redisStringService.expire("code",300);
//        return code;
//    }
//
//    /**
//     * 通过邮箱获得发送的验证码
//     * 验证码过期返回null
//     * @param email
//     * @return
//     */
//    public String getCodeByEmail(String email){
//        String code = redisStringService.get("code");
//        return code;
//    }
//}
