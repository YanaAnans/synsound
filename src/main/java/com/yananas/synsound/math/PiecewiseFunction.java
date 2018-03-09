package com.yananas.synsound.math;

import java.util.Arrays;
import java.util.function.Function;
import java.util.stream.IntStream;

import lombok.Builder;

@Builder
public class PiecewiseFunction {
    @Builder.Default private double left = 0;
    @Builder.Default private double right = 1;
    @Builder.Default private int numberOfPieces = 256;
    @Builder.Default private Function<Double, Double> fn = (x) -> x;

    public double[] getXValues() {
        return IntStream.rangeClosed(0, numberOfPieces).mapToDouble(k -> (right - left) * ((double)k/numberOfPieces) + left).toArray();
    }

    public double[] getYValues() {
        return Arrays.stream(getXValues()).map(xValue -> fn.apply(xValue)).toArray();
    }

}
