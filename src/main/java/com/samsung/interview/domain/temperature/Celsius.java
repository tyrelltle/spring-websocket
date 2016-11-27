package com.samsung.interview.domain.temperature;

public class Celsius extends AbstractTemperature{


    public Celsius(Double value) {
        super(value);
    }

    @Override
    public Double determineNormalizedValue(Double value) {
        return value;
    }
}
