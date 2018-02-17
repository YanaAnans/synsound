package com.yananas.synsound;

public class AudioLibrary {

    private static double[] createNote(double duration, double frequency) {
        double amplitude = 0.8;
        double[] frequencies = new double[(int) (duration * Constants.SAMPLE_RATE)];
        for (int sampleId = 0; sampleId < frequencies.length; sampleId++) {
            double time = sampleId / Constants.SAMPLE_RATE;
            frequencies[sampleId] = amplitude * Math.sin(2 * Math.PI * frequency * time);
        }
        return frequencies;
    }

    public static void main(String[] args) {
        AudioPlayer player = new AudioPlayer();
        player.play(createNote(2.0, 440.0));
    }
}
