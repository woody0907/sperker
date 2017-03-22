package com.woody;

/**
 * Created with IntelliJ IDEA.
 * User: woody
 * Date: 17-3-20
 * Time: 上午7:55
 * To change this template use File | Settings | File Templates.
 */

public class SpeakerConfig {


    private Long id;
    private String volume;
    private String  rate;
    private String  url;
    private String queue;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getVolume() {
        return volume;
    }

    public void setVolume(String volume) {
        this.volume = volume;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getQueue() {
        return queue;
    }

    public void setQueue(String queue) {
        this.queue = queue;
    }
}
