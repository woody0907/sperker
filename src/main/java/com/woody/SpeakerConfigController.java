package com.woody;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.jms.Queue;

/**
 * Created with IntelliJ IDEA.
 * User: woody
 * Date: 17-3-20
 * Time: 上午8:12
 * To change this template use File | Settings | File Templates.
 */
@Controller
@RequestMapping("/config")
public class SpeakerConfigController {
    @Value("${speaker.volume}")
    private  String volume;
    @Value("${speaker.rate}")
    private String rate;
    @Value("${spring.activemq.broker-url}")
    private String url;
    @Value("${speaker.queue}")
    private String queuename;
    @Value("${speaker.content}")
    private String content;
    @Value("${speaker.orgcode}")
    private String orgcode;
    @Value("${speaker.level}")
    private String level;

    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;//使用JmsMessagingTemplate将消息放入队列
    @Autowired
    private Queue queue;



    @RequestMapping(method = RequestMethod.GET)
    public String readConfig(Model model){
      SpeakerConfig sc = new SpeakerConfig();
        sc.setRate(rate);
        sc.setUrl(url);
        sc.setVolume(volume);
        sc.setQueue(queuename);
        sc.setContent(content);
        sc.setLevel(level);
        sc.setOrgcode(orgcode);
        model.addAttribute("config",sc);
        return "config";
    }


    @RequestMapping(params = {"test"}, method = RequestMethod.POST)
    public String configTest(Model model){

        jmsMessagingTemplate.convertAndSend(this.queue, "测试语音:恭喜您测试语音正常");
        return "redirect:/config";
    }


}
