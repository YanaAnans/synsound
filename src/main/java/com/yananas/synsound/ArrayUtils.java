package com.yananas.synsound;

import java.util.Arrays;

import org.apache.commons.math3.complex.Complex;

public class ArrayUtils {

    public static double[] clip(double[] array, int start, int end) {
        int n = 0;
        for (int i = 0; i < array.length; i++) {
            if (i > end) {
                n = n + 1;
            }
        }
        double[] array2 = new double[array.length - start - n];
        System.arraycopy(array, start, array2, 0, array.length - start - n);
        return array2;
    }

    public static double[] repeat(double[] array, int times) {
        double[] array2 = new double[array.length * times];
        for (int i = 0; i < times; i++) {
            System.arraycopy(array, 0, array2, array.length * i, array.length);
        }
        return array2;
    }

    public static double max(double[] array) {
        double max = 0;
        for (double anArray : array) {
            max = (max > anArray) ? max : anArray;
        }
        return max;
    }

    public static double min(double[] array) {
        double min = Double.MAX_VALUE;
        for (double anArray : array) {
            min = (min < anArray) ? min : anArray;
        }
        return min;
    }

    public static double[] range(double min, double max, int size) {
        double segment = (max - min) / (size - 1);
        double[] range = new double[size];
        for (int i = 0; i < size; i++) {
            range[i] = min + segment * i;
        }
        return range;
    }

    public static double[] fromComplexAbs(Complex[] complexValues) {
        return Arrays.stream(complexValues).mapToDouble(value -> value.abs()).toArray();
    }

    public static double[] fromComplexRealParts(Complex[] complexValues) {
        return Arrays.stream(complexValues).mapToDouble(value -> value.getReal()).toArray();
    }

    public static double[] fromComplexImaginaryParts(Complex[] complexValues) {
        return Arrays.stream(complexValues).mapToDouble(value -> value.getImaginary()).toArray();
    }

    public static double[] fromComplexArgs(Complex[] complexValues) {
        return Arrays.stream(complexValues).mapToDouble(value -> value.getArgument()).toArray();
    }
}