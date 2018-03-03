package com.yananas.synsound.model;

import org.apache.commons.math3.exception.OutOfRangeException;

import com.yananas.synsound.Constants;

import lombok.Data;

@Data
public class WavData {

    private double[] samples;
    private WavFormat format;

    /*
     * Retrieves duration of the WAV file in milliseconds
     */
    public double getDuration() {
        return (double) samples.length / format.getSampleRate() * Constants.MILLSEC_IN_SEC;
    }

    /*
     * Retrieves sample id at a given time moment
     * @param time
     *        time moment given in milliseconds
     * @return index of the sample at that time
     */
    public int getSampleId(double time) throws OutOfRangeException {
        if (Double.compare(time, getDuration()) > 0 || Double.compare(time, 0.0d) < 0) {
            throw new OutOfRangeException(time, 0.0d, getDuration());
        }
        return (int) (time * getFormat().getSampleRate() / Constants.MILLSEC_IN_SEC);
    }

    /*
     * Retrieves sample value at a given time moment
     * @param time
     *        time moment given in milliseconds
     * @return value of the sample i.e. amplitude at that time
     */
    public double getSample(double time) throws OutOfRangeException {
        return samples[getSampleId(time)];
    }
}
