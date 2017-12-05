package com.yananas.synsound;

import org.junit.Assert;
import org.junit.Test;

public class AudioUtilsTests {

    @Test
    public void testShortToByte() {
        short value = 9; // TODO: check more input data
        byte[] bytes = AudioUtils.short2bytes(value);
        Assert.assertEquals(value, AudioUtils.bytes2short(bytes));
    }

}