package com.yananas.synsound;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.SeriesException;
import org.jfree.data.time.Second;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.XYDataset;
import org.jfree.ui.ApplicationFrame;

public class SoundSignalView extends ApplicationFrame {

    private static final long serialVersionUID = 6916540658450034477L;

    private static XYDataset createDataset() {
        Second current = new Second();
        TimeSeries series = new TimeSeries("Random Data");
        double[] values = AudioLibrary.SIMPLE_WAVE;
        for (int i = 0; i < values.length; i++) {
            try {
                series.add(current, values[i]);
                // Note: time interval is not correct, 1/44100s should be used instead
                current = (Second) current.next();
            } catch (SeriesException e) {
                e.printStackTrace();
            }
        }
        return new TimeSeriesCollection(series);
    }

    public SoundSignalView(String title, XYDataset model) {
        super(title);
        JFreeChart chart = ChartFactory.createTimeSeriesChart("Sound Signal", "Amplitude", "Time", model, false, false,
                false);
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new java.awt.Dimension(560, 370));
        chartPanel.setMouseZoomable(true, false);
        setContentPane(chartPanel);
    }

    public static void main(String[] args) {
        SoundSignalView view = new SoundSignalView("Sound Signal", createDataset());
        view.pack();
        view.setVisible(true);
    }
}