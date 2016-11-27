package com.samsung.interview;

import com.samsung.interview.domain.temperature.Celsius;
import com.samsung.interview.domain.threshold.Direction;
import com.samsung.interview.domain.threshold.Threshold;
import com.samsung.interview.domain.threshold.ThresholdManager;
import org.junit.Test;

import static junit.framework.TestCase.*;

public class ThresholdTest {

    @Test
    public void testThresehold() throws Exception {
        Threshold threshold=new Threshold("testthres",Double.valueOf(10), Direction.NONE,Double.valueOf(0));
        ThresholdManager thresholdManager=new ThresholdManager(threshold);
        assertFalse(thresholdManager.reach(new Celsius(Double.valueOf(23))));
        assertTrue(thresholdManager.reach(new Celsius(Double.valueOf(10))));
        assertFalse(thresholdManager.reach(new Celsius(Double.valueOf(0))));
        assertTrue(thresholdManager.reach(new Celsius(Double.valueOf(10))));
    }

    @Test
    public void testThreseholdWithDirection() throws Exception {
        Threshold threshold=new Threshold("testthres",Double.valueOf(10), Direction.ASC,Double.valueOf(0));
        ThresholdManager thresholdManager=new ThresholdManager(threshold);
        assertFalse(thresholdManager.reach(new Celsius(Double.valueOf(23))));
        assertFalse(thresholdManager.reach(new Celsius(Double.valueOf(10))));
        assertFalse(thresholdManager.reach(new Celsius(Double.valueOf(0))));
        assertTrue(thresholdManager.reach(new Celsius(Double.valueOf(10))));
    }

    @Test
    public void testThreseholdWithFluctuation() throws Exception {
        Threshold threshold=new Threshold("testthres",Double.valueOf(10), Direction.NONE,Double.valueOf(0.5));
        ThresholdManager thresholdManager=new ThresholdManager(threshold);
        assertFalse(thresholdManager.reach(new Celsius(Double.valueOf(10.5))));
        assertTrue(thresholdManager.reach(new Celsius(Double.valueOf(10)))); //first time reach
        assertFalse(thresholdManager.reach(new Celsius(Double.valueOf(10)))); //second time not reach because difference < 0.5
        assertFalse(thresholdManager.reach(new Celsius(Double.valueOf(9.5))));
        assertFalse(thresholdManager.reach(new Celsius(Double.valueOf(10)))); //not reach because difference < 0.5
        assertFalse(thresholdManager.reach(new Celsius(Double.valueOf(11))));
        assertTrue(thresholdManager.reach(new Celsius(Double.valueOf(10))));  //reach because difference > 0.5
        assertFalse(thresholdManager.reach(new Celsius(Double.valueOf(12))));
        assertTrue(thresholdManager.reach(new Celsius(Double.valueOf(10))));  //reach because difference > 0.5
    }

    @Test
    public void testThreseholdWithFluctuationAndDirection() throws Exception {
        Threshold threshold=new Threshold("testthres",Double.valueOf(10), Direction.DESC,Double.valueOf(0.5));
        ThresholdManager thresholdManager=new ThresholdManager(threshold);
        assertFalse(thresholdManager.reach(new Celsius(Double.valueOf(10.5))));
        assertTrue(thresholdManager.reach(new Celsius(Double.valueOf(10)))); //first time reach
        assertFalse(thresholdManager.reach(new Celsius(Double.valueOf(10)))); //second time not reach because difference < 0.5
        assertFalse(thresholdManager.reach(new Celsius(Double.valueOf(9.5))));
        assertFalse(thresholdManager.reach(new Celsius(Double.valueOf(10)))); //not reach because difference < 0.5
        assertFalse(thresholdManager.reach(new Celsius(Double.valueOf(11))));
        assertTrue(thresholdManager.reach(new Celsius(Double.valueOf(10))));  //reach because difference > 0.5 and its desc
        assertFalse(thresholdManager.reach(new Celsius(Double.valueOf(8))));
        assertFalse(thresholdManager.reach(new Celsius(Double.valueOf(10))));  //not reach even if difference > 0.5, because direction is asc
    }
}
