package com.yananas.synsound;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;

import com.yananas.synsound.model.WavData;
import com.yananas.synsound.model.WavFormat;
import com.yananas.synsound.model.WavFormats;

public class AudioLibrary {

    public static final String WAV_DIR = "/voicebank/";

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

    private static AudioEditorConfig loadConfig(String alias) throws IllegalArgumentException, IOException {
        URL url = AudioLibrary.class.getResource("/settings.ini");
        File file;
        try {
            file = Paths.get(url.toURI()).toFile();
            AudioEditorConfig result = Files.lines(file.toPath()).map((line) -> {
                String[] parts = line.split("[=,]");
                if (parts == null || parts.length < 2) {
                    throw new IllegalArgumentException("Bad config format: " + line);
                }
                AudioEditorConfig config = new AudioEditorConfig();
                config.setFileName(parts[0]);
                config.setAlias(parts[1]);
                config.setOffset(Integer.parseInt(parts[2]));
                config.setCutoff(Integer.parseInt(parts[4]));
                return config;
            }).filter((config) -> {
                return config.getAlias().equals(alias);
            }).findAny().orElseThrow(
                    () -> new IllegalArgumentException("Not able to find foname with name: " + alias));
            return result;
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
            throw new IOException("Not able to read from 'settings.ini' file", e);
        }
    }

    public static WavData phoneme(String alias) {
        WavData wavData = new WavData();
        try {
            AudioEditorConfig config = loadConfig(alias);
            wavData =  voice(config.getFileName());
            return AudioEditor.clip(wavData, config.getOffset(), config.getCutoff());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return wavData;
    }

    public static WavData voice(String resourceFileName) {
        try {
            URL url = AudioLibrary.class.getResource(WAV_DIR + resourceFileName);
            File file = Paths.get(url.toURI()).toFile();
            return AudioUtils.load(file.getAbsolutePath());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new WavData();
    }

    public static void main(String[] args) {
        AudioPlayer player = new AudioPlayer();
        player.play(phoneme("a"));
        player.play(note(2.0, 440.0));
        player.play(voice("a.wav"));
    }
}
