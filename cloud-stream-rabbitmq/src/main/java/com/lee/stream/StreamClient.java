package com.lee.stream;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

/**
 * 同一个服务里面绑定的信道名字不能一样，在不同的服务里可以绑定相同名字的信道
 */
public interface StreamClient {

    String INPUT = "input";
    String OUTPUT = "output";
    String INPUT1 = "input1";
    String OUTPUT1 = "output1";

    /**
     * 通过注解的方式绑定了一个名字为input的通道
     */
    @Input(StreamClient.INPUT)
    SubscribableChannel input();

    @Output(StreamClient.OUTPUT)
    MessageChannel output();

    @Input(StreamClient.INPUT1)
    SubscribableChannel input1();

    @Output(StreamClient.OUTPUT1)
    MessageChannel output1();

}
