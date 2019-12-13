package cn.hnist.bookmanager.component;

import cn.hnist.bookmanager.common.QueueEnum;
import cn.hnist.bookmanager.utils.LogUtils;
import com.alibaba.fastjson.JSONObject;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 向对应的交换机发送消息
 * @author whg
 * @date 2019/12/11 12:05
 **/
@Component
public class SenderToQueue {

    @Autowired
    private AmqpTemplate amqpTemplate;

    /**
     * 将消息发送到对应的交换机中
     * @param exchangeName
     * @param routeKey
     * @param message
     */
    public void sendMessage(String exchangeName, String routeKey, Message message){
        //给延迟队列发送消息
        amqpTemplate.convertAndSend(exchangeName, routeKey, message);
        LogUtils.getTimeOutTaskLogger().info("添加" + message + "到消息队列中去");
    }
}
