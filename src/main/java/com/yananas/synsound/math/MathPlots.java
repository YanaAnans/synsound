package com.yananas.synsound.math;

import java.util.stream.IntStream;

import com.yananas.synsound.model.WavData;

public class MathPlots {
    public final static String LABEL_AMPLITUDE = "Amplitude";
    public final static String LABEL_TIME = "Time (s)";
    public final static String LABEL_MISSING = "-";

    public static void plot2d(String title, String xLabel, String yLabel, double[] xVals, double[] yVals) {
        MathPlot plot = MathPlot.builder().xLabel(xLabel).yLabel(yLabel).title(title).xValues(xVals).yValues(yVals)
                .build();
        plot.plot();
    }

    public static void plot2d(String title, double[] yVals) {
        double[] xVals = IntStream.range(0, yVals.length).mapToDouble(id -> id / yVals.length).toArray();
        plot2d(title, LABEL_MISSING, LABEL_MISSING, xVals, yVals);
    }

    public static void plot2d(String title, WavData wavData) {
        double[] yVals = wavData.getSamples();
        double[] xVals = IntStream.range(0, yVals.length).mapToDouble(id -> id / wavData.getFormat().getSampleRate())
                .toArray();
        plot2d(title, LABEL_TIME, LABEL_AMPLITUDE, xVals, yVals);
    }

}
