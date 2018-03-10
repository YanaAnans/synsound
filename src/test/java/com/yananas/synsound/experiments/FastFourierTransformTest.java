package com.yananas.synsound.experiments;

import org.apache.commons.math3.complex.Complex;
import org.apache.commons.math3.transform.DftNormalization;
import org.apache.commons.math3.transform.FastFourierTransformer;
import org.apache.commons.math3.transform.TransformType;
import org.junit.Test;

import com.yananas.synsound.ArrayUtils;
import com.yananas.synsound.math.MathPlot;
import com.yananas.synsound.math.PiecewiseFunction;
import com.yananas.synsound.math.Waveform;

public class FastFourierTransformTest {
    /*
     * The Fourier transform of an impulse at the origin
     * is a constant in the transform domain.
     *
     * The test should compute the discrete Fourier
     * transform of an 8-element vector
     * consisting of a real impulse at the origin with expected
     * theoretical results.
     *
     * */
    @Test
    public void testImpulseAtTheOrigin() {
     // TODO: 1) prepare test data, 2) call FFT method and 3) assert results
    }

    /*
     * A shifted impulse alters only the phase of the transform components.
     * The result should have constant magnitude in the transform domain, where magnitude is
     * the square root of 'real part' squared + 'imaginary part' squared.
     *
     * */
    @Test
    public void testShiftedImpulse() {
        // TODO: 1) prepare test data, 2) call FFT method and 3) assert results
    }

    public static void main(String[] args) {
        Waveform wf = Waveform.builder()
                .amplitude(5.0).frequency(1.0)
                .build();

        PiecewiseFunction pf = PiecewiseFunction.builder()
                .left(0.0).right(1.0).numberOfPieces(255).fn(wf.fn())
                .build();

        MathPlot.plot("waveform", "time", "amplitude", pf.getXValues(), pf.getYValues());

        FastFourierTransformer transformer = new FastFourierTransformer(DftNormalization.STANDARD);

        Complex[] result = transformer.transform(pf.getYValues(), TransformType.FORWARD);

        double[] absValues = ArrayUtils.fromComplexAbs(result);
        MathPlot.plot("Absolute values", absValues);

        double[] imaginaryParts = ArrayUtils.fromComplexImaginaryParts(result);
        MathPlot.plot("Imaginary parts", imaginaryParts);

        double[] realParts = ArrayUtils.fromComplexRealParts(result);
        MathPlot.plot("Real parts", realParts);

        double[] angles = ArrayUtils.fromComplexArgs(result);
        MathPlot.plot("Angles", angles);
    }

}
