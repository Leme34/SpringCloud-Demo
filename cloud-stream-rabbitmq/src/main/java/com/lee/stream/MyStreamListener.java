package com.lee.stream;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

@Slf4j
@EnableBinding({StreamClient.class})
public class MyStreamListener {

    @StreamListener(value=StreamClient.INPUT)
    @SendTo(StreamClient.OUTPUT1)
    public String process(String msg){
        log.info("MyStreamListener1: {}",msg);
        return "MyStreamListener1已接收消息: "+msg;
    }

    @StreamListener(value=StreamClient.OUTPUT1)
    public void process1(String msg){
        log.info("MyStreamListener2收到回应: {}",msg);
    }

}
