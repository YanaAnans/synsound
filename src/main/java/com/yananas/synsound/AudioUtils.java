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

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.ApplicationFrame;

public class AudioUtils {

    public static final short MAX_SHORT = Short.MAX_VALUE;

    public static byte[] doubles2bytes(double[] values) {
        byte[] bytes = new byte[2 * values.length];
        for (int i = 0; i < values.length; i++) {
            short value = (short) (MAX_SHORT * values[i]);
            bytes[2 * i + 0] = (byte) value;
            bytes[2 * i + 1] = (byte) (value >> 8);
        }
        return bytes;
    }

    public static byte[] short2bytes(short value) {
        return new byte[] { (byte) value, (byte) (value >> 8) };
    }

    public static short bytes2short(byte[] bytes) {
        ByteBuffer buffer = ByteBuffer.allocate(2);
        buffer.order(ByteOrder.LITTLE_ENDIAN);
        buffer.put(bytes[0]);
        buffer.put(bytes[1]);
        return buffer.getShort(0);
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
        JFreeChart chart = ChartFactory.createXYLineChart(title, "Time (s)", "Amplitude", dataset, PlotOrientation.VERTICAL,
                false, false, false);
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new java.awt.Dimension(560, 370));
        chartPanel.setMouseZoomable(true, false);
        ApplicationFrame frame = new ApplicationFrame(title);
        frame.setContentPane(chartPanel);
        frame.pack();
        frame.setVisible(true);
    }
}
