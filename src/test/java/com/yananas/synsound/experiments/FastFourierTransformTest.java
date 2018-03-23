package com.yananas.synsound.experiments;

import org.junit.Test;

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

    /*
     * The input is a sequence of real numbers of length 8.
     * The first element is 1, the rest is 0.
     * The output should be a sequence of 1-s of the same length
     * */
    @Test
    public void testConstSequence() {
        // TODO: 1) prepare test data, 2) call FFT method and 3) assert results
    }
}
