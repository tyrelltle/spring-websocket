package com.samsung.interview.web;

public class TemperatureInputDTO {
    public String name;

    public TemperatureInputDTO(String name) {
        this.name = name;
    }

    public TemperatureInputDTO() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "TemperatureInputDTO{" +
                "name='" + name + '\'' +
                '}';
    }
}
