package com.samsung.interview;

import com.samsung.interview.domain.Thermometer;
import com.samsung.interview.domain.tempsource.TextTemperatureSource;
import com.samsung.interview.domain.threshold.Direction;
import com.samsung.interview.domain.threshold.Threshold;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class ThermometerTest {

    @Test
    public void testThrermometerWalkflow() throws Exception {
        Thermometer thermometer=new Thermometer();

        List<Threshold> thresholds=new ArrayList<>();
        thresholds.add(new Threshold("frezing",Double.valueOf(0), Direction.NONE,Double.valueOf(0.5)));
        thermometer.addSubscriber("tyler",thresholds);

        thresholds=new ArrayList<>();
        thresholds.add(new Threshold("boiling",Double.valueOf(100), Direction.NONE,Double.valueOf(0.2)));
        thermometer.addSubscriber("hacker",thresholds);


        ArrayList<String> list = new ArrayList<String>() {{
            add("1.5 C");add("1.0 C");add("0.5 C");add("0.0 C");
        }};

        thermometer.processNewTemperature(new TextTemperatureSource(list));

        assertEquals(thermometer.getReachedThreshold("tyler").getName(),"frezing");
        assertEquals(thermometer.getReachedThreshold("hacker"),null);

        list = new ArrayList<String>() {{
            add("1.5 C");add("1.0 C");add("0.5 C");add("0.0 C");
            add("-0.5 C");add("0.0 C");add("-0.5 C");add("0.0 C");
            add("0.5 C");add("100 C");
        }};

        thermometer.processNewTemperature(new TextTemperatureSource(list));
        assertEquals(thermometer.getReachedThreshold("tyler"),null);
        assertEquals(thermometer.getReachedThreshold("hacker").getName(),"boiling");

    }
}
