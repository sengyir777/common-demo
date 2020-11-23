package com.thundersoft.mqttdemo.thundersoftmqtt.mqtt;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class MqttConsumerHadnler {
    public static void handler(String message) {
        log.info("MqttConsumerService ======>>>>>>>>>>>>>");
        log.info("接收到的消息：{}", message);
    }
}
