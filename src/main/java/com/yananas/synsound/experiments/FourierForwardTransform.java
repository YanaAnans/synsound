package com.yananas.synsound.experiments;

import java.util.Arrays;
import java.util.stream.IntStream;

import org.jtransforms.fft.DoubleFFT_1D;

import com.yananas.synsound.math.MathPlot;
import com.yananas.synsound.math.PiecewiseFunction;

public class FourierForwardTransform {
	
	private static int sz = 100000;
	private static double a = 0;
	private static double b = 1;
	private static DoubleFFT_1D fft = new DoubleFFT_1D(sz);

	
	private static PiecewiseFunction fn = PiecewiseFunction.builder()
			.left(a)
			.right(b)
			.numberOfPieces(sz-1)
			.fn((s) -> Math.cos(2 * Math.PI * s * 50)
				).build();
	
	private static double[] abs(double[] vals) {
		double[] abs = new double[vals.length/2];
		Arrays.setAll(abs, i -> Math.sqrt(
				Math.pow(vals[2 * i], 2) + Math.pow(vals[2 * i + 1], 2))
			);
		return abs;
	}
	
	private static double[] re(double[] vals) {
		double[] abs = new double[vals.length/2];
		Arrays.setAll(abs, i -> vals[2 * i]);
		return abs;
	}
	
	private static double[] im(double[] vals) {
		double[] abs = new double[vals.length/2];
		Arrays.setAll(abs, i -> vals[2 * i + 1]);
		return abs;
	}
	
	private static double sampling_rate() {
		return sz / (b-a);
	}
	
	private static double[] freq_scale() {
		return IntStream.range(0, sz)
				.mapToDouble(i -> i * sampling_rate() / sz).toArray();
		}
	
	public static void main(String[] args) {
		System.out.println(Arrays.toString(freq_scale()));
		MathPlot.plot("Original", "Time", "Db", fn.getXValues(), fn.getYValues());
		double[] original = fn.getYValues();
		double[] copy = Arrays.copyOf(original, original.length * 2);	
		fft.realForwardFull(copy);
		MathPlot.plot("Abs", "Herz", "Db", freq_scale(), abs(copy));
		
	}

}
