package com.yananas.synsound;

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

    /* Задание 1 */
    public static double[] union(double[] array1, double[] array2) {
        double[] result = new double[array1.length + array2.length];
        // #1 Записать в массив result сначала все элементы массива array1, а затем array2
        System.arraycopy(array1, 0, result, 0, array1.length);
        System.arraycopy(array2, 0, result, array1.length, array2.length);
        return result;
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
}