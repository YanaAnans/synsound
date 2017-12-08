package com.yananas.synsound;

import java.io.IOException;

public class AudioLibrary {

    public static double[] SIMPLE_WAVE;
    public static double[] TWO_WAVES;

    static {
        initSimpleWave();
        initTwoWaves();
    }

    private static void initSimpleWave() {
        double seconds = 2.0;
        double frequency = 440.0;
        double amplitude = 0.8;
        SIMPLE_WAVE = new double[(int) (seconds * AudioSettings.SAMPLE_RATE)];
        for (int sampleId = 0; sampleId < SIMPLE_WAVE.length; sampleId++) {
            double time = sampleId / AudioSettings.SAMPLE_RATE;
            SIMPLE_WAVE[sampleId] = amplitude * Math.sin(2 * Math.PI * frequency * time);
        }
    }

    private static void initTwoWaves() {
        double seconds = 2.0;
        double f0 = 440.0;
        double f1 = 6 * f0;
        double amplitude0 = 0.8;
        double amplitude1 = 0.2;
        TWO_WAVES = new double[(int) (seconds * AudioSettings.SAMPLE_RATE)];
        for (int sampleId = 0; sampleId < TWO_WAVES.length; sampleId++) {
            double time = sampleId / AudioSettings.SAMPLE_RATE;
            TWO_WAVES[sampleId] = amplitude0 * Math.sin(2 * Math.PI * f0 * time)
                    + amplitude1 * Math.sin(2 * Math.PI * f1 * time);
        }
    }

    public static void main(String[] args) {
        try {
            AudioUtils.save("simple_wave.wav", SIMPLE_WAVE);
            double[] samples = AudioUtils.load("simple_wave.wav");
            AudioUtils.plot("Simple wave", samples);
        } catch (IllegalArgumentException | IOException e) {
            e.printStackTrace();
        }
    }

}
