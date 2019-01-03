package com.lee.controller;

import com.lee.stream.StreamClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
public class TestMsgController {

    @Autowired
    private StreamClient streamClient;

    @RequestMapping("/send")
    public void send(){
        String msg = "喵喵喵";
        Message<String> message = MessageBuilder.withPayload(msg).build();
        streamClient.output().send(message);
    }

}
