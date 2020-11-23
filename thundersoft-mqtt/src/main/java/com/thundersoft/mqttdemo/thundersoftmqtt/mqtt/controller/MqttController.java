package com.thundersoft.mqttdemo.thundersoftmqtt.mqtt.controller;

import com.thundersoft.mqttdemo.thundersoftmqtt.mqtt.utils.MqttUtil;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MqttController {

    @Autowired
    MqttUtil mqttUtil;

    @RequestMapping(value = "/pushMessage")
    public void pushMessage(String topic, String message) throws MqttException {
        mqttUtil.pubMessage(topic, message);
    }

}
