package com.samsung.interview.domain;


import com.samsung.interview.domain.threshold.Threshold;
import com.samsung.interview.domain.threshold.ThresholdManager;

import java.util.ArrayList;
import java.util.List;

public class Subscriber {
    final String name;
    final List<ThresholdManager> thresholdManagers=new ArrayList<ThresholdManager>();
    Threshold reachedThreshold;

    public Subscriber(String name, List<Threshold> thresholds) {
        this.name = name;
        thresholds.forEach(t->this.thresholdManagers.add(new ThresholdManager(t)));
    }

    public void addThreshold(Threshold threshold){
        this.thresholdManagers.add(new ThresholdManager((threshold)));
    }

    public String getName() {
        return name;
    }

    public List<ThresholdManager> getThresholdManagers() {
        return thresholdManagers;
    }

    public Threshold getReachedThreshold(){
        return this.reachedThreshold;
    }

    public void setReachedThreshold(Threshold threshold){
        this.reachedThreshold=threshold;
    }

}
