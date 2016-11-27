package com.samsung.interview.socket;

public class TemperatureMessage {
    String current_temperature;
    String threshold_name;

    public TemperatureMessage() {
    }

    public TemperatureMessage(String current_temperature, String threshold_name) {
        this.current_temperature = current_temperature;
        this.threshold_name = threshold_name;
    }

    public String getCurrent_temperature() {
        return current_temperature;
    }

    public void setCurrent_temperature(String current_temperature) {
        this.current_temperature = current_temperature;
    }

    public String getThreshold_name() {
        return threshold_name;
    }

    public void setThreshold_name(String threshold_name) {
        this.threshold_name = threshold_name;
    }

    @Override
    public String toString() {
        return "TemperatureMessage{" +
                "current_temperature='" + current_temperature + '\'' +
                ", threshold_name='" + threshold_name + '\'' +
                '}';
    }
}


