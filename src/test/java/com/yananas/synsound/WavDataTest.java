package com.yananas.synsound;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import org.apache.commons.math3.exception.OutOfRangeException;
import org.junit.Test;

import com.yananas.synsound.model.WavData;

public class WavDataTest {
    @Test
    public void testDurationFor2SecNote() {
        WavData data = AudioLibrary.note(2.0, 440.1);
        assertEquals(2000.0, data.getDuration(), Constants.MSEC_EPS);
    }

    @Test
    public void testDurationAccuracyPass() {
        WavData data = AudioLibrary.note(3.99990, 440.1);
        assertEquals(3999.9, data.getDuration(), Constants.MSEC_EPS);
    }

    @Test
    public void testDurationAccuracyFails() {
        WavData data = AudioLibrary.note(3.99990, 440.1);
        assertNotEquals(3999.9, data.getDuration(), Constants.MSEC_EPS / 10);
    }

    @Test
    public void testDurationNotExceedReal() {
        WavData data = AudioLibrary.note(3.99990, 440.1);
        assertTrue(data.getDuration() < 3999.9);
    }

    @Test
    public void testSampleIdFor2secAndHalfTime() {
        WavData data = AudioLibrary.note(2, 440.1);
        assertTrue(data.getSampleId(1000) == data.getSamples().length/2);
    }

    @Test
    public void testSampleValueFor2secAndHalfTime() {
        WavData data = AudioLibrary.note(2, 440.1);
        double[] samples = data.getSamples();
        assertTrue(data.getSample(1000) == samples[samples.length/2]);
    }

    @Test(expected = OutOfRangeException.class)
    public void testSampleIdTimeExceedRightBound() {
        WavData data = AudioLibrary.note(2, 440.1);
        data.getSampleId(2000.1);
    }

    @Test(expected = OutOfRangeException.class)
    public void testSampleIdFailsForNegativeInput() {
        WavData data = AudioLibrary.note(2, 440.1);
        data.getSampleId(-0.1);
    }
    
    @Test
    public void testInputTimeBounds() {
        double time = 3333;
        WavData data = AudioLibrary.note(3.5, 440.1);
        double step = data.getDuration() / data.getSamples().length;
        assertTrue(data.getSampleId(time) * step < time);
        assertTrue((data.getSampleId(time) + 1) * step > time);
    }
}
