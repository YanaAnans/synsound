package com.yananas.synsound;

import org.junit.Assert;
import org.junit.Test;

public class AudioUtilsTests {

    @Test
    public void testShortToByte() {
        double[] samples = { -0.9, -0.51, 0.0, 0.51, 0.9 };
        byte[] bytes = AudioUtils.doubles2bytes(samples);
        Assert.assertEquals(bytes.length, samples.length * 2);
        Assert.assertArrayEquals(samples, AudioUtils.bytes2doubles(bytes), AudioUtils.DOUBLE_EPS);
    }

}