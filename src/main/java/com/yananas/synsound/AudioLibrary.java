package com.yananas.synsound;

import java.io.File;

import com.yananas.synsound.model.WavData;
import com.yananas.synsound.model.WavFormat;
import com.yananas.synsound.model.WavFormats;

public class AudioLibrary {

    public static final String WAV_DIR = "voicebank";

    public static WavData note(double duration, double frequency) {
        WavData wavData = new WavData();
        WavFormat wavFormat = WavFormats.defaultFormat();
        wavData.setFormat(wavFormat);
        double amplitude = 0.8;
        double[] samples = new double[(int) (duration * wavFormat.getSampleRate())];
        for (int sampleId = 0; sampleId < samples.length; sampleId++) {
            double time = sampleId / wavFormat.getSampleRate();
            samples[sampleId] = amplitude * Math.sin(2 * Math.PI * frequency * time);
        }
        wavData.setSamples(samples);
        return wavData;
    }

    public static WavData voice(String resourceFileName) {
        try {
            ClassLoader classLoader = AudioLibrary.class.getClassLoader();
            String fileName = classLoader.getResource(WAV_DIR + File.separator + resourceFileName).getFile();
            return AudioUtils.load(fileName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new WavData();
    }

    public static void main(String[] args) {
        AudioPlayer player = new AudioPlayer();
        player.play(note(2.0, 440.0));
        player.play(voice("a.wav"));
    }
}
