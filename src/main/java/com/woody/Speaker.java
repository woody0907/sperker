package com.woody;

import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.Dispatch;
import com.jacob.com.Variant;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

/**
 * Created with IntelliJ IDEA.
 * User: woody
 * Date: 17-3-10
 * Time: 上午6:01
 * To change this template use File | Settings | File Templates.
 */
@Component
public class Speaker {

    @Value("${speaker.volume}")
    private String volume;
    @Value("${speaker.rate}")
    private String rate;
    @Value("${speaker.content}")
    private String content;
    @Value("${speaker.orgcode}")
    private String orgcode;
    @Value("${speaker.level}")
    private String level;

    @JmsListener(destination = "${speaker.queue}")
    public void removeMessage(String msg) {

        //Where is the Sapi.SpVoice? How can I find it in the windows server?
        ActiveXComponent sap = new ActiveXComponent("Sapi.SpVoice");
        Dispatch sapo = sap.getObject();
        try {

            sap.setProperty("Volume", new Variant(Integer.parseInt(volume)));
            sap.setProperty("Rate", new Variant(Integer.parseInt(rate)));

            Variant defalutVoice = sap.getProperty("Voice");

            Dispatch dispdefaultVoice = defalutVoice.toDispatch();
            Variant allVoices = Dispatch.call(sapo, "GetVoices");
            Dispatch dispVoices = allVoices.toDispatch();

            Dispatch setvoice = Dispatch.call(dispVoices, "Item", new Variant(1)).toDispatch();
            ActiveXComponent voiceActivex = new ActiveXComponent(dispdefaultVoice);
            ActiveXComponent setvoiceActivex = new ActiveXComponent(setvoice);

            Variant item = Dispatch.call(setvoiceActivex, "GetDescription");
            // 执行朗读
            if (messageFilter(msg)) {
                Dispatch.call(sapo, "Speak", new Variant(msg.split("@")[1]));
            }
            if (msg.startsWith("测试语音")) {
                Dispatch.call(sapo, "Speak", new Variant(msg));
            }

            //


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sapo.safeRelease();
            sap.safeRelease();
        }
    }

    /**
     * @param message 格式如下：
     *  SGBB@一级告警:PING不通,怎么都PING不通，哎呀！
     * @return
     */
    private boolean messageFilter(String message) {
        boolean isLevle = false;
        boolean isOrgcode = false;
        boolean isContent = false;
        if (message != null) {
            String[] mess = message.split("@");
            if (mess.length > 1) {
                String code = mess[0];
                String messgeBody = mess[1];
                if (orgcode != null && orgcode.equals(code) && messgeBody != null) {
                    isOrgcode = true;
                    if (content != null) {
                        String[] contentArray = content.split(",");
                        for (String filter : contentArray) {
                            String upperBody = messgeBody.toUpperCase();
                            if (upperBody.contains(filter)) {
                                isContent = true;
                            }
                        }
                    }
                    if (level != null) {
                        String[] levelArray = level.split("@");
                        for (String ll : levelArray) {
                            if (messgeBody.contains(ll)) {
                                isLevle = true;
                            }
                        }
                    }
                }
            }
        }
        return isLevle && isOrgcode && isContent;
    }
}
