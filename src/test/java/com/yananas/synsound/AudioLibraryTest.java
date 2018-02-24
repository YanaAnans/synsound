package com.yananas.synsound;

import com.yananas.synsound.model.WavData;
import com.yananas.synsound.model.WavFormat;
import org.junit.Assert;
import org.junit.Test;

public class AudioLibraryTest {
    @Test
    public void testWavDataSizeEqualsSampleRateTimesDuration() {
        WavData wavData = AudioLibrary.note(1, 550);
        WavFormat wavFormat = wavData.getFormat();
        Assert.assertEquals((int)wavFormat.getSampleRate(), wavData.getSamples().length);

        wavData = AudioLibrary.note(3, 550);
        Assert.assertEquals(3 * (int)wavFormat.getSampleRate(), wavData.getSamples().length);
    }

}
