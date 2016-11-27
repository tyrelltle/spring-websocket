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

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class Thermometer {
    final List<Subscriber> subscribers =new ArrayList<Subscriber>();

    @Autowired
    SocketSender socketSender;

    public void addSubscriber(String name,List<Threshold> threshold) throws DuplicateSubscriber {
        long countExisting=this.subscribers.stream()
                .filter(s->s.getName().equals(name))
                .count();
        if(countExisting>0){
            throw new DuplicateSubscriber(name+" subscriber already exists");
        }

        //if(threshold!=null){
            this.subscribers.add(new Subscriber(name,threshold));
        //}
    }

    public void addSubscriberThres(String name,Threshold threshold) throws DuplicateSubscriber {
        Subscriber subscriber=this.subscribers.stream()
                .filter(s->s.getName().equals(name))
                .findAny().get();
        if(subscriber==null){
            throw new DuplicateSubscriber(name+" subscriber not exist");
        }

        subscriber.addThreshold(threshold);
    }

    public List<Threshold> getSubscriberThresholds(String name){
        Subscriber subscriber=this.subscribers.stream()
                .filter(s->s.getName().equals(name))
                .findAny().get();
        return subscriber.getThresholdManagers().stream()
                .map(tm->tm.getThreshold())
                .collect(Collectors.toList());
    }


    public List<SubscriberThresholdDTO> getSubscriberReachedThreshold(){
        return this.subscribers.stream()
                .map(sub->{
                    if(sub.getReachedThreshold()!=null)
                        return new SubscriberThresholdDTO(sub.getName(),sub.getReachedThreshold().getName());
                    else
                        return new SubscriberThresholdDTO(sub.getName(),"N/A");
                })
                .collect(Collectors.toList());
    }

    public Threshold getReachedThreshold(String subscriber_name){
        Subscriber sub=this.subscribers.stream()
                .filter(s->s.getName().equals(subscriber_name))
                .findAny().get();
        return sub.getReachedThreshold();
    }


    public void processNewTemperature(ITemperatureSource source) throws InvalidTemperatureFormate {
        final Exception e;
        while(source.hasNaxt()){
            AbstractTemperature temperature=source.readNext();
            for(Subscriber subscriber : this.subscribers){
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
    }



}
