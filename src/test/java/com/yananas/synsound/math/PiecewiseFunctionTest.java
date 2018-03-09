package com.yananas.synsound.math;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.function.Function;

import org.junit.Test;

public class PiecewiseFunctionTest {
    private final Function<Double, Double> E_FN = (x) -> Math.E;
    private final int MAX_N = 10;

    @Test
    public void testCreateConst() {
        PiecewiseFunction fn = PiecewiseFunction
            .builder()
            .fn(E_FN)
            .numberOfPieces(MAX_N)
            .build();
        double[] xValues = fn.getXValues();
        double[] yValues = fn.getYValues();
        assertEquals(xValues.length, yValues.length);
        assertEquals(MAX_N + 1, xValues.length);
        assertTrue(Double.compare(0.0, xValues[0]) == 0);
        assertTrue(Double.compare(1.0, xValues[xValues.length-1]) == 0);
        assertTrue((Arrays.stream(yValues).allMatch(
                yValue -> Double.compare(yValue, Math.E) == 0)
        ));
    }

    @Test
    public void testChangeDomain() {
        PiecewiseFunction fn = PiecewiseFunction
                .builder()
                .fn(E_FN)
                .numberOfPieces(MAX_N)
                .left(2.0)
                .right(3.0)
                .build();
            double[] xValues = fn.getXValues();
            assertTrue(Double.compare(2.0, xValues[0]) == 0);
            assertTrue(Double.compare(3.0, xValues[xValues.length-1]) == 0);
    }
}
