package com.yananas.synsound.model;

public class WavFormats {
    public static WavFormat defaultFormat() {
        WavFormat wavFormat = new WavFormat();
        wavFormat.setBigEndian(false);
        wavFormat.setBitDepth(16);
        wavFormat.setSampleRate((float) 44100);
        wavFormat.setSigned(true);
        wavFormat.setChannelsCount(1);
        return wavFormat;
    }
}
