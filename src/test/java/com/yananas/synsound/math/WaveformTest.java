package com.yananas.synsound.math;

import static org.junit.Assert.*;

import org.junit.Test;

import com.yananas.synsound.Constants;

public class WaveformTest {
    @Test
    public void testBuildWave() {
        Waveform waveform = Waveform.builder().amplitude(1.0).frequency(1.0).phase(Math.PI/2).build();
        assertEquals(0.0, waveform.fn().apply(0.0), Constants.EPS);
        assertEquals(0.0, waveform.fn().apply(1.0), Constants.EPS);
    }
}
