package com.samsung.interview.domain.temperature;

public abstract class AbstractTemperature {
    Double normalizedValue;
    Double value;
    public AbstractTemperature(Double value){
        this.normalizedValue=this.determineNormalizedValue(value);
        this.value=value;
    }

    public abstract Double determineNormalizedValue(Double value);

    public Double normalizedValue() {
        return this.normalizedValue;
    }

    public abstract String stringify();

}
