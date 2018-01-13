package com.yananas.synsound;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.SourceDataLine;

public class AudioPlayer {

    private final int BUFFER_SIZE = 1280000;

    private File soundFile;
    private AudioInputStream audioStream;
    private AudioFormat audioFormat;
    private SourceDataLine sourceLine;

    /**
     * Play sound represented by an array of samples
     * @param samples
     *          an array of samples that is going to be played
     * */
    public void play(double[] samples) {
        try {
            new File("tmp").mkdir();
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
        try {
            String filename = "tmp/sound.wav";
            AudioUtils.save(filename, samples);
            play(filename);
        } catch (IllegalArgumentException|IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Play sound file given by its filename
     * @param filename
     *            the name of the file that is going to be played
     */
    public void play(String filename) {
        try {
            soundFile = new File(filename);
            audioStream = AudioSystem.getAudioInputStream(soundFile);
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }

        audioFormat = audioStream.getFormat();
        DataLine.Info info = new DataLine.Info(SourceDataLine.class, audioFormat);
        try {
            sourceLine = (SourceDataLine) AudioSystem.getLine(info);
            sourceLine.open(audioFormat);
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }

        sourceLine.start();
        int nBytesRead = 0;
        byte[] abData = new byte[BUFFER_SIZE];
        while (nBytesRead != -1) {
            try {
                nBytesRead = audioStream.read(abData, 0, abData.length);
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (nBytesRead >= 0) {
                @SuppressWarnings("unused")
                int nBytesWritten = sourceLine.write(abData, 0, nBytesRead);
            }
        }
        sourceLine.drain();
        sourceLine.close();
    }
}