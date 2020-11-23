package com.thundersoft.mqttdemo.thundersoftmqtt.mqtt;

import lombok.Data;
import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Data
public class ServerMQTT {


    private MqttClient client;

    @Autowired
    private MqttConfigModel mqttConfigModel;


    /**
     * 用来连接服务器
     */
    public void connect() throws MqttException {
        client = new MqttClient(mqttConfigModel.getHost(), mqttConfigModel.getPublishClientid(), new MemoryPersistence());
        MqttConnectOptions options = new MqttConnectOptions();
        options.setCleanSession(false);
        options.setUserName(mqttConfigModel.getUserName());
        options.setPassword(mqttConfigModel.getPassWord().toCharArray());
        options.setAutomaticReconnect(true);
        // 设置超时时间
        options.setConnectionTimeout(10);
        // 设置会话心跳时间
        options.setKeepAliveInterval(20);
        try {
            client.setCallback(new PushCallback());
            client.connect(options);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @param topic
     * @param message
     * @throws MqttPersistenceException
     * @throws MqttException
     */
    public void publish(String topic, MqttMessage message) throws MqttPersistenceException, MqttException {
        connect();
        MqttTopic mqttTopic = client.getTopic(topic);
        MqttDeliveryToken token = mqttTopic.publish(message);
        token.waitForCompletion();
    }

}
