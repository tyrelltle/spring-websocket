package com.samsung.interview.web;

public class SubscriberThresholdDTO {
    public String name;
    public String reachedThreasholdName;

    public SubscriberThresholdDTO(String name, String reachedThreasholdName) {
        this.name = name;
        this.reachedThreasholdName = reachedThreasholdName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getReachedThreasholdName() {
        return reachedThreasholdName;
    }

    public void setReachedThreasholdName(String reachedThreasholdName) {
        this.reachedThreasholdName = reachedThreasholdName;
    }

    @Override
    public String toString() {
        return "SubscriberThresholdDTO{" +
                "name='" + name + '\'' +
                ", reachedThreasholdName='" + reachedThreasholdName + '\'' +
                '}';
    }
}
