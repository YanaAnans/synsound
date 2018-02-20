package com.yananas.synsound;

import org.junit.Test;

import static com.yananas.synsound.ArrayUtils.clip;
import static org.junit.Assert.assertArrayEquals;

public class ArrayUtilsTest {
    @Test
    public void clipLast2Test() {
        double[] in = new double[] { 1, 2, 3, 4 };
        double[] expected = new double[] { 3, 4 };
        assertArrayEquals(expected, clip(in, 2, in.length), Constants.EPS);
    }

    @Test
    public void clipLast3Test() {
        double[] in = new double[] { 1, 2, 3, 4, 5 };
        double[] expected = new double[] { 3, 4, 5 };
        assertArrayEquals(expected, clip(in, 2, in.length), Constants.EPS);
    }

    @Test
    public void clipFirst2Test() {
        double[] in = new double[] { 1, 2, 3 };
        double[] expected = new double[] { 1, 2 };
        assertArrayEquals(expected, clip(in, 0, 1), Constants.EPS);
    }

    @Test
    public void clipFirst3Test() {
        double[] in = new double[] { 1, 2, 3, 4 };
        double[] expected = new double[] { 1, 2, 3 };
        assertArrayEquals(expected, clip(in, 0, 2), Constants.EPS);
    }
}
