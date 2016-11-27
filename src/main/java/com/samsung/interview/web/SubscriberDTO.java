package com.samsung.interview.web;

import com.samsung.interview.domain.threshold.Threshold;

import java.util.ArrayList;
import java.util.List;

public class SubscriberDTO {
    public String name;
    public List<Threshold> thresholds =new ArrayList<>();

    public SubscriberDTO() {
    }

    public SubscriberDTO(String name, List<Threshold> subscribers) {
        this.name = name;
        this.thresholds = subscribers;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Threshold> getThresholds() {
        return thresholds;
    }

    public void setThresholds(List<Threshold> thresholds) {
        this.thresholds = thresholds;
    }

    @Override
    public String toString() {
        return "SubscriberDTO{" +
                "name='" + name + '\'' +
                ", thresholds=" + thresholds +
                '}';
    }
}
