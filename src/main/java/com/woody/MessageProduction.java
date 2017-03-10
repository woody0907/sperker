package com.woody;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.jms.Queue;

/**
 * Created with IntelliJ IDEA.
 * User: woody
 * Date: 17-3-10
 * Time: 上午6:39
 * To change this template use File | Settings | File Templates.
 */
@Component
@EnableScheduling
public class MessageProduction {
    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;//使用JmsMessagingTemplate将消息放入队列
    @Autowired
    private Queue queue;
    @Scheduled(fixedDelay = 3000)//每3s执行1次,将消息放入队列内
    public void send() {
        this.jmsMessagingTemplate.convertAndSend(this.queue, "测试消息队列"+System.currentTimeMillis()/1000);
    }
}
