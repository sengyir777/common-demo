package com.thundersoft.mqttdemo.thundersoftmqtt.mqtt;


import com.thundersoft.mqttdemo.thundersoftmqtt.mqtt.utils.MqttUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class MqttRunner implements CommandLineRunner {

    @Autowired
    MqttUtil mqttUtil;

    @Autowired
    ClientMQTT clientMQTT;

    @Override
    public void run(String... args) throws Exception {
        clientMQTT.initClientMqtt();
    }
}
