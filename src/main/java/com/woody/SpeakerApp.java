package com.woody;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * Created with IntelliJ IDEA.
 * User: woody
 * Date: 17-3-10
 * Time: 上午6:47
 * To change this template use File | Settings | File Templates.
 */
@SpringBootApplication
@ComponentScan("com.woody")
public class SpeakerApp {
    public static void main(String[] args) {
        SpringApplication.run(SpeakerApp.class, args);
    }
}
