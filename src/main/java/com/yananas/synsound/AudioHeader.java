package com.yananas.synsound;

public class AudioHeader {
    // sample size in bits
    public static final int BIT_DEPTH = 16;
    // audio CD 44kHz sample rate
    public static final float SAMPLE_RATE = (float) 44100;
    // littleEndian
    public static final boolean BIG_ENDIAN = false;
    // signed PCM
    public static final boolean SIGNED = true;
    // single channel (mono)
    public static final int CHANNELS_COUNT = 1;
}
