package com.samsung.interview.service;

import com.samsung.interview.domain.exceptions.InvalidTemperatureFormate;
import com.samsung.interview.domain.tempsource.TextTemperatureSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
@EnableScheduling
public class AutoTemperature {
    @Autowired
    Thermometer thermometer;

    int index=0;
    int MAX=10;
    @Scheduled(fixedRate = 2000)
    public void reportCurrentTime() throws InvalidTemperatureFormate {
        String tmp=index+" C";
        index++;
        if(index>=MAX) {
            index = 0;
        }
        System.out.println("[AutoTemperature] sending temperature "+tmp);
        thermometer.processNewTemperature(new TextTemperatureSource(Arrays.asList(tmp)));
    }
}
