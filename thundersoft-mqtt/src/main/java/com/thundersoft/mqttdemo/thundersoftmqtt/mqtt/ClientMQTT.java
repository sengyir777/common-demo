package com.thundersoft.mqttdemo.thundersoftmqtt.mqtt;

import lombok.Data;
import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.UUID;

/**
 * 模拟一个客户端接收消息
 *
 * @author rao
 */
@Component
@Data
public class ClientMQTT {

    private MqttClient client;

    private MqttConnectOptions options;

    @Autowired
    private MqttConfigModel mqttConfigModel;

    public void initClientMqtt() {
        try {
            // host为主机名，clientid即连接MQTT的客户端ID，一般以唯一标识符表示，MemoryPersistence设置clientid的保存形式，默认为以内存保存
            client = new MqttClient(mqttConfigModel.getHost(), mqttConfigModel.getSubscribeClientid(), new MemoryPersistence());
            // MQTT的连接设置
            options = new MqttConnectOptions();
            // 设置是否清空session,这里如果设置为false表示服务器会保留客户端的连接记录，设置为true表示每次连接到服务器都以新的身份连接
            options.setCleanSession(false);
            // 设置连接的用户名
            options.setUserName(mqttConfigModel.getUserName());
            // 设置连接的密码
            options.setPassword(mqttConfigModel.getPassWord().toCharArray());
            // 设置超时时间 单位为秒
            options.setConnectionTimeout(10);
            // 设置会话心跳时间 单位为秒 服务器会每隔1.5*20秒的时间向客户端发送个消息判断客户端是否在线，但这个方法并没有重连的机制
            options.setKeepAliveInterval(20);
            // 设置回调
            client.setCallback(new PushCallback());
//            MqttTopic mqttTopic = client.getTopic(mqttConfigModel.getTopic());
            //setWill方法，如果项目中需要知道客户端是否掉线可以调用该方法。设置最终端口的通知消息
//            options.setWill(mqttTopic, "close".getBytes(), 1, true);
            //设置自动重连
            options.setAutomaticReconnect(true);
            client.connect(options);
            //订阅消息
            String[] qos = mqttConfigModel.getQos().split(",");
            int[] intQos = Arrays.asList(qos).stream().mapToInt(Integer::parseInt).toArray();
            client.subscribe(mqttConfigModel.getTopic().split(","), intQos);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
