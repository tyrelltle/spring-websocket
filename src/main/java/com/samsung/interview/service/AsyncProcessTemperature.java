package com.samsung.interview.service;

import com.samsung.interview.domain.Subscriber;
import com.samsung.interview.domain.temperature.AbstractTemperature;
import com.samsung.interview.domain.threshold.Threshold;
import com.samsung.interview.socket.SocketSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class AsyncProcessTemperature{

    @Autowired
    SocketSender socketSender;

    @Async
    public void processTemperature(AbstractTemperature temperature,Subscriber subscriber){
            subscriber.setReachedThreshold(null);
            subscriber.getThresholdManagers().forEach(mngr->{
                if(mngr.reach(temperature)){
                    subscriber.setReachedThreshold(mngr.getThreshold());
                }
            });
            Threshold reachedt = subscriber.getReachedThreshold();
            socketSender.notifyThresholdReachClient(subscriber.getName(), temperature,reachedt);

    }
}