package com.samsung.interview.domain.threshold;

public class Threshold {
    String name;
    Double value;
    Direction direction=Direction.NONE;
    Double Fluctuation=Double.valueOf(0);

    public Threshold() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public Double getFluctuation() {
        return Fluctuation;
    }

    public void setFluctuation(Double fluctuation) {
        Fluctuation = fluctuation;
    }

    public Threshold(String name, Double value, Direction direction, Double fluctuation) {
        this.name = name;
        this.value = value;
        this.direction = direction;
        Fluctuation = fluctuation;
    }

}
