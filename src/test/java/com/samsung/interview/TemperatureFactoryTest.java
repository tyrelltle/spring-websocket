package com.samsung.interview;

import com.samsung.interview.domain.exceptions.InvalidTemperatureFormate;
import com.samsung.interview.domain.temperature.AbstractTemperature;
import com.samsung.interview.domain.temperature.Celsius;
import com.samsung.interview.domain.temperature.Fahrenheit;
import com.samsung.interview.domain.temperature.TemperatureFactory;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TemperatureFactoryTest {

    @Test
    public void testFactory() throws Exception {
        AbstractTemperature temperature= TemperatureFactory.getTemperatureFromString("30 C");
        assertTrue(temperature instanceof Celsius);
        assertEquals(temperature.normalizedValue(), Double.valueOf(30));

        temperature=TemperatureFactory.getTemperatureFromString("50 F");
        assertEquals(temperature.normalizedValue(), Double.valueOf(10));
        assertTrue(temperature instanceof Fahrenheit);
    }

    @Test(expected = InvalidTemperatureFormate.class)
    public void testInvalidFactoryInput() throws InvalidTemperatureFormate {
        TemperatureFactory.getTemperatureFromString("wow C");
    }

    @Test(expected = InvalidTemperatureFormate.class)
    public void testInvalidFactoryInput2() throws InvalidTemperatureFormate {
        TemperatureFactory.getTemperatureFromString("wow");
    }

    @Test(expected = InvalidTemperatureFormate.class)
    public void testInvalidFactoryInput3() throws InvalidTemperatureFormate {
        TemperatureFactory.getTemperatureFromString("123");
    }
}
