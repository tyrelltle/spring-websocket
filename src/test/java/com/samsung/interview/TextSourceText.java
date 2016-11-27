package com.samsung.interview;

import com.samsung.interview.domain.temperature.AbstractTemperature;
import com.samsung.interview.domain.tempsource.TextTemperatureSource;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class TextSourceText {

    @Test
    public void testGetFromText() throws Exception {
        List<String> lines=new ArrayList();
        lines.add("5 C");
        lines.add("200 F");
        lines.add("14 C");
        lines.add("882 F");

        TextTemperatureSource textSourceText=new TextTemperatureSource(lines);
        assertTrue(textSourceText.hasNaxt());
        AbstractTemperature temperature=textSourceText.readNext();
        assertEquals(temperature.normalizedValue(),Double.valueOf(5));


        assertTrue(textSourceText.hasNaxt());
        temperature=textSourceText.readNext();
        assertEquals(temperature.normalizedValue(),Double.valueOf(93.33));


        assertTrue(textSourceText.hasNaxt());
        temperature=textSourceText.readNext();
        assertEquals(temperature.normalizedValue(),Double.valueOf(14));


        assertTrue(textSourceText.hasNaxt());
        temperature=textSourceText.readNext();
        assertEquals(temperature.normalizedValue(),Double.valueOf(472.22));

        assertFalse(textSourceText.hasNaxt());
    }
}
