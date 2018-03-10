package com.yananas.synsound.math;

import java.util.stream.IntStream;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.ApplicationFrame;

import com.yananas.synsound.model.WavData;

import lombok.Data;

@Data
public class MathPlot {

    private String title;
    private String xLabel;
    private String yLabel;

    private static final String windowTitle = "synsound";

    private double[] xValues;
    private double[] yValues;

    private int width = 560;
    private int height = 370;

    public final static String LABEL_AMPLITUDE = "Amplitude";
    public final static String LABEL_TIME = "Time (s)";
    public final static String LABEL_MISSING = "-";

    public static void plot(String title, String xLabel, String yLabel, double[] xVals, double[] yVals) {
        MathPlot mathPlot = new MathPlot();
        mathPlot.setTitle(title);
        mathPlot.setXLabel(xLabel);
        mathPlot.setYLabel(yLabel);
        mathPlot.setXValues(xVals);
        mathPlot.setYValues(yVals);
        mathPlot.plot();
    }

    public static void plot(String title, double[] yVals) {
        double[] xVals = IntStream.range(0, yVals.length).mapToDouble(id -> id / yVals.length).toArray();
        plot(title, LABEL_MISSING, LABEL_MISSING, xVals, yVals);
    }

    public static void plot2d(String title, WavData wavData) {
        double[] yVals = wavData.getSamples();
        double[] xVals = IntStream.range(0, yVals.length).mapToDouble(id -> id / wavData.getFormat().getSampleRate())
                .toArray();
        plot(title, LABEL_TIME, LABEL_AMPLITUDE, xVals, yVals);
    }

    public void plot() {
        if (xValues == null || yValues == null) {
            throw new IllegalArgumentException("'xVals' or 'yVals' is null");
        }
        if (xValues.length != yValues.length) {
            throw new IllegalArgumentException("dimmension missmatch");
        }
        XYSeries series = new XYSeries(title);
        for (int valueId = 0; valueId < xValues.length; valueId++) {
            try {
                series.add(xValues[valueId], yValues[valueId]);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(series);
        JFreeChart chart = ChartFactory.createXYLineChart(title, xLabel, yLabel, dataset,
                PlotOrientation.VERTICAL, false, false, false);
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new java.awt.Dimension(width, height));
        chartPanel.setMouseZoomable(true, false);
        ApplicationFrame frame = new ApplicationFrame(windowTitle);
        frame.setContentPane(chartPanel);
        frame.pack();
        frame.setVisible(true);
    }
}
