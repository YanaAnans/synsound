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
			for (int j = 0; j < array.length; j++) {
				array2[array.length*i + j] = array[j];
			}
		}
		return array2;
	}

	public static double max(double[] array) {
		double max = 0;
		for (int i = 0; i < array.length; i++) {
			max = (max > array[i]) ? max : array[i];
		}
		return max;
	}

	public static double min(double[] array) {
		double min = Double.MAX_VALUE;
		for (int i = 0; i < array.length; i++) {
			min = (min < array[i]) ? min : array[i];
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