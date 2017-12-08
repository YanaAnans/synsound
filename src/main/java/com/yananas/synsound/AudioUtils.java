package com.yananas.synsound;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.UnsupportedAudioFileException;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.ApplicationFrame;

public class AudioUtils {

    public static final double DOUBLE_EPS = 1e-5;

    /**
     * Converts array of doubles into array of bytes, assuming that doubles are
     * normalized
     */
    public static byte[] doubles2bytes(double[] values) {
        if (values == null) {
            throw new IllegalArgumentException("'values' is null");
        }
        byte[] bytes = new byte[2 * values.length];
        for (int i = 0; i < values.length; i++) {
            // TODO: normalization
            if (Double.isNaN(values[i])) {
                throw new IllegalArgumentException("Double " + values[i] + " has invalid value");
            }
            short value = (short) (Short.MAX_VALUE * values[i]);
            bytes[2 * i + 0] = (byte) value;
            bytes[2 * i + 1] = (byte) (value >> 8);
        }
        return bytes;
    }

    /**
     * Converts array of bytes into array of normalized doubles
     */
    public static double[] bytes2doubles(byte[] bytes) {
        if (bytes == null) {
            throw new IllegalArgumentException("'bytes' is null");
        }
        int samplesCount = bytes.length / 2;
        double[] samples = new double[samplesCount];
        for (int i = 0; i < samplesCount; i++) {
            ByteBuffer buffer = ByteBuffer.allocate(2);
            buffer.order(ByteOrder.LITTLE_ENDIAN);
            buffer.put(bytes[2 * i]);
            buffer.put(bytes[2 * i + 1]);
            samples[i] = (double) buffer.getShort(0) / Short.MAX_VALUE;
        }
        return samples;
    }

    public static void save(String filename, double[] samples) throws IllegalArgumentException, IOException {
        if (samples == null) {
            throw new IllegalArgumentException("'samples' is null");
        }
        try {
            ByteArrayInputStream bais = new ByteArrayInputStream(doubles2bytes(samples));
            AudioFormat format = new AudioFormat(AudioSettings.SAMPLE_RATE, AudioSettings.BIT_DEPTH,
                    AudioSettings.CHANNELS_COUNT, AudioSettings.SIGNED, AudioSettings.BIG_ENDIAN);

            AudioInputStream ais = new AudioInputStream(bais, format, samples.length);
            if (filename.endsWith(".wav") || filename.endsWith(".WAV")) {
                AudioSystem.write(ais, AudioFileFormat.Type.WAVE, new File(filename));
            } else {
                throw new IllegalArgumentException("Audio format: '" + filename + "' is not supported");
            }
        } catch (IOException e) {
            throw new IOException("Not able to save the file '" + filename + "'", e);
        }
    }

    public static double[] load(String filename) {
        if (filename == null) {
            throw new IllegalArgumentException("'filename' is null");
        }
        File file = new File(filename);
        if (!file.exists()) {
            throw new IllegalArgumentException("File with name '" + filename + "' is not existing");
        }
        try {
            AudioInputStream stream = AudioSystem.getAudioInputStream(file);
            int allBytes = stream.available();
            byte[] bytes = new byte[allBytes];
            int bytesRead = stream.read(bytes);
            if (bytesRead != allBytes) {
                throw new IllegalArgumentException("Number of read bytes != number of all bytes");
            }
            return bytes2doubles(bytes);
        } catch (UnsupportedAudioFileException e) {
            throw new IllegalArgumentException("'" + filename + "' has wrong format", e);
        } catch (IOException e) {
            throw new IllegalArgumentException("'" + filename + "' couldn't be read", e);
        }
    }

    public static void plot(String title, double[] samples) throws IllegalArgumentException {
        if (samples == null) {
            throw new IllegalArgumentException("'samples' is null");
        }
        XYSeries series = new XYSeries(title);
        for (int sampleId = 0; sampleId < samples.length; sampleId++) {
            try {
                series.add(sampleId / AudioSettings.SAMPLE_RATE, samples[sampleId]);
            } catch (Exception e) {
                throw new IllegalArgumentException("Not able to plot 'smaples' array");
            }
        }
        XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(series);
        JFreeChart chart = ChartFactory.createXYLineChart(title, "Time (s)", "Amplitude", dataset,
                PlotOrientation.VERTICAL, false, false, false);
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new java.awt.Dimension(560, 370));
        chartPanel.setMouseZoomable(true, false);
        ApplicationFrame frame = new ApplicationFrame(title);
        frame.setContentPane(chartPanel);
        frame.pack();
        frame.setVisible(true);
    }
}
