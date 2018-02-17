package com.yananas.synsound;

import org.junit.Assert;
import org.junit.Test;

import static com.yananas.synsound.TransformUtils.bytes2doubles;
import static com.yananas.synsound.TransformUtils.doubles2bytes;

public class TransformUtilsTests {

    @Test
    public void testShortToByte() {
        double[] samples = { -0.9, -0.51, 0.0, 0.51, 0.9 };
        byte[] bytes = doubles2bytes(samples);
        Assert.assertEquals(bytes.length, samples.length * 2);
        Assert.assertArrayEquals(samples, bytes2doubles(bytes), Constants.DOUBLE_EPS);
    }

}