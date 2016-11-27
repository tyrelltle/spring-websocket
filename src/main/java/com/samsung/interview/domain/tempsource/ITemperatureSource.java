package com.samsung.interview.domain.tempsource;

import com.samsung.interview.domain.exceptions.InvalidTemperatureFormate;
import com.samsung.interview.domain.temperature.AbstractTemperature;

public interface ITemperatureSource {
    boolean hasNaxt();
    AbstractTemperature readNext() throws InvalidTemperatureFormate;
}
