package com.yananas.synsound;

import org.junit.Assert;
import org.junit.Test;

import static com.yananas.synsound.TransformUtils.transformBytesToDoubles;
import static com.yananas.synsound.TransformUtils.transformDoublesToBytes;

public class TransformUtilsTest {
    @Test
    public void testShortToByte() {
        double[] samples = { -0.9, -0.51, 0.0, 0.51, 0.9 };
        byte[] bytes = transformDoublesToBytes(samples);
        Assert.assertEquals(bytes.length, samples.length * 2);
        Assert.assertArrayEquals(samples, transformBytesToDoubles(bytes), Constants.EPS);
    }

}