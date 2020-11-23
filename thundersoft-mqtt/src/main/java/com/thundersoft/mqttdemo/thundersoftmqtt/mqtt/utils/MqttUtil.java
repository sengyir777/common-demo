package com.thundersoft.mqttdemo.thundersoftmqtt.mqtt.utils;

import com.thundersoft.mqttdemo.thundersoftmqtt.mqtt.ServerMQTT;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class MqttUtil {

    @Autowired
    private ServerMQTT serverMQTT;

    public void pubMessage(String topic, String message) throws MqttException {
        log.info("push topic is:{},message is:{}", topic, message);
        MqttMessage mqttMessage = new MqttMessage();
        mqttMessage.setQos(2);
        mqttMessage.setRetained(false);
        mqttMessage.setPayload(message.getBytes());
        serverMQTT.publish(topic, mqttMessage);
    }


}
