package com.samsung.interview.socket;

import com.samsung.interview.domain.temperature.AbstractTemperature;
import com.samsung.interview.domain.threshold.Threshold;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

@Component
public class SocketSender {

    static final String NO_REACHED="$no_reached$";

    @Autowired
    private SimpMessagingTemplate template;


    public void notifyThresholdReachClient(String client_name,AbstractTemperature temperature, Threshold threshold){
        TemperatureMessage thresholdReachedMessage=new TemperatureMessage();
        if(threshold!=null)
            thresholdReachedMessage.setThreshold_name(threshold.getName());
        else
            thresholdReachedMessage.setThreshold_name(NO_REACHED);
        thresholdReachedMessage.setCurrent_temperature(temperature.stringify());
        this.template.convertAndSend("/topic/"+client_name, thresholdReachedMessage);
    }
}
