package com.thundersoft.mqttdemo.thundersoftmqtt.mqtt;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "mqtt")
@Data
public class MqttConfigModel {

    @Value("${mqtt.host}")
    private String host;

    //定义MQTT的ID，消费消息用户id
    @Value("${mqtt.subscribe.clientId}")
    private String subscribeClientid;

    //定义MQTT的ID，发布消息用户id
    @Value("${mqtt.publish.clientId}")
    private String publishClientid;

    @Value("${mqtt.user}")
    private String userName;

    @Value("${mqtt.password}")
    private String passWord;

    @Value("${mqtt.subscribe.topic}")
    private String topic;

    @Value("${mqtt.subscribe.qos}")
    private String qos;
}
