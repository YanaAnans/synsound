package com.yananas.synsound.math;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.ApplicationFrame;
import lombok.Builder;

@Builder
public class MathPlot {

    private String title;
    private String xLabel;
    private String yLabel;

    private static final String windowTitle = "synsound";

    private double[] xValues;
    private double[] yValues;

    @Builder.Default private int width = 560;
    @Builder.Default private int height = 370;

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
