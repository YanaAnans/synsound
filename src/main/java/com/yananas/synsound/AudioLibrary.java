package com.yananas.synsound;

public class AudioLibrary {

    public final static double SAMPLE_RATE = 44100.0;

    public final static byte[] SIMPLE_TONE_DATA;
    static {
        SIMPLE_TONE_DATA = initSimpleTone();
    }

    public static byte[] initSimpleTone() {
        double[] samples = {};
        // TODO: fill in samples array with simple tone raw data;
        return AudioUtils.doubles2bytes(samples);
    }

    public static void main(String[] args) {
        System.out.println("wav file raw data bytes number: " + SIMPLE_TONE_DATA.length);
    }

}
