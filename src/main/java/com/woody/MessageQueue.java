package com.woody;

import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.jms.Queue;

/**
 * Created with IntelliJ IDEA.
 * User: woody
 * Date: 17-3-10
 * Time: 上午6:46
 * To change this template use File | Settings | File Templates.
 */
@Component
public class MessageQueue {
    @Bean
    public Queue queue(){
        return new ActiveMQQueue("my-message");
    }
}
