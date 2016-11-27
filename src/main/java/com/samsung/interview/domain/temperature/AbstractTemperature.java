package com.samsung.interview.domain.temperature;

public abstract class AbstractTemperature {
    Double normalizedValue;

    public AbstractTemperature(Double value){
        this.normalizedValue=this.determineNormalizedValue(value);
    }

    public abstract Double determineNormalizedValue(Double value);

    public Double normalizedValue() {
        return this.normalizedValue;
    }

}
