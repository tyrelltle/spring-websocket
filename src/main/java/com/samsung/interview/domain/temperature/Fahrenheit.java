package com.samsung.interview.domain.temperature;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Fahrenheit extends AbstractTemperature {

    public Fahrenheit(Double value) {
        super(value);
    }

    @Override
    public Double determineNormalizedValue(Double value) {
        double val= (value-32)/1.8;
        return new BigDecimal(val).setScale(2, RoundingMode.HALF_UP).doubleValue();
    }

    @Override
    public String stringify() {
        return this.value+" F";
    }
}
