package com.samsung.interview.service;


import com.samsung.interview.domain.Subscriber;
import com.samsung.interview.domain.exceptions.DuplicateSubscriber;
import com.samsung.interview.domain.exceptions.InvalidTemperatureFormate;
import com.samsung.interview.domain.temperature.AbstractTemperature;
import com.samsung.interview.domain.tempsource.ITemperatureSource;
import com.samsung.interview.domain.threshold.Threshold;
import com.samsung.interview.socket.SocketSender;
import com.samsung.interview.web.SubscriberThresholdDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Component
public class Thermometer {
    final Map<String,Subscriber> subscribers =new ConcurrentHashMap<>();

    @Autowired
    SocketSender socketSender;

    @Autowired
    AsyncProcessTemperature asyncProcessTemperature;

    public void addSubscriber(String name,List<Threshold> threshold) throws DuplicateSubscriber {
        boolean countExisting=this.subscribers.containsKey(name);
        if(countExisting){
            throw new DuplicateSubscriber(name+" subscriber already exists");
        }

        //if(threshold!=null){
            this.subscribers.put(name,new Subscriber(name,threshold));
        //}
    }

    public void addSubscriberThres(String name,Threshold threshold) throws DuplicateSubscriber {
        Subscriber subscriber=this.subscribers.get(name);
        if(subscriber==null){
            throw new DuplicateSubscriber(name+" subscriber not exist");
        }

        subscriber.addThreshold(threshold);
    }

    public List<Threshold> getSubscriberThresholds(String name){
        Subscriber subscriber=this.subscribers.get(name);
        return subscriber.getThresholdManagers().stream()
                .map(tm->tm.getThreshold())
                .collect(Collectors.toList());
    }


    public List<SubscriberThresholdDTO> getSubscriberReachedThreshold(){
        return this.subscribers.entrySet().stream()
                .map(sub->{
                    Subscriber value = sub.getValue();
                    if(value !=null)
                        return new SubscriberThresholdDTO(value.getName(), value.getReachedThreshold().getName());
                    else
                        return new SubscriberThresholdDTO(value.getName(),"N/A");
                })
                .collect(Collectors.toList());
    }

    public Threshold getReachedThreshold(String subscriber_name){
        Subscriber sub=this.subscribers.get(subscriber_name);
        return sub.getReachedThreshold();
    }


    public void processNewTemperature(ITemperatureSource source) throws InvalidTemperatureFormate {
        final Exception e;
        while(source.hasNaxt()){
            AbstractTemperature temperature=source.readNext();
            this.subscribers.entrySet().stream().forEach(entry->{
                Subscriber subscriber=entry.getValue();
                asyncProcessTemperature.processTemperature(temperature,subscriber);
            });
        }
    }


    public Subscriber getSubscriber(String name) {
        return this.subscribers.get(name);
    }


    public void removeSubscriber(String name) {
        this.subscribers.remove(name);
    }
}
