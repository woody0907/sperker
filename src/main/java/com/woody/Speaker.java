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
    private  String volume;
    @Value("${speaker.rate}")
    private String rate;

    @JmsListener(destination="${speaker.queue}")
    public void removeMessage(String msg){

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
                Dispatch.call(sapo, "Speak", new Variant(msg));


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sapo.safeRelease();
            sap.safeRelease();
        }
    }
}
