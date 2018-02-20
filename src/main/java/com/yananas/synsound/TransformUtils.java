package com.yananas.synsound;

import javax.sound.sampled.AudioFormat;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

import com.yananas.synsound.model.WavFormat;

public class TransformUtils {

    public static AudioFormat transformToAudioFormat(WavFormat wavFormat) {
        return new AudioFormat(wavFormat.getSampleRate(), wavFormat.getBitDepth(), wavFormat.getChannelsCount(),
                wavFormat.isSigned(), wavFormat.isBigEndian());
    }

    public static WavFormat transformToWavFormat(AudioFormat audioFormat) {
        WavFormat wavFormat = new WavFormat();
        wavFormat.setSampleRate(audioFormat.getSampleRate());
        wavFormat.setBitDepth(audioFormat.getSampleSizeInBits());
        wavFormat.setChannelsCount(audioFormat.getChannels());
        wavFormat.setSigned(audioFormat.getEncoding() == AudioFormat.Encoding.PCM_SIGNED);
        wavFormat.setBigEndian(audioFormat.isBigEndian());
        return wavFormat;
    }

    /**
     * Converts array of doubles into array of bytes, assuming that doubles are
     * normalized
     */
    public static byte[] transformDoublesToBytes(double[] values) throws IllegalArgumentException {
        if (values == null) {
            throw new IllegalArgumentException("'values' is null");
        }
        byte[] bytes = new byte[2 * values.length];
        for (int i = 0; i < values.length; i++) {
            // TODO: normalization
            if (Double.isNaN(values[i])) {
                throw new IllegalArgumentException("Double " + values[i] + " has invalid value");
            }
            short value = (short) (Short.MAX_VALUE * values[i]);
            bytes[2 * i] = (byte) value;
            bytes[2 * i + 1] = (byte) (value >> 8);
        }
        return bytes;
    }

    /**
     * Converts array of bytes into array of normalized doubles
     */
    public static double[] transformBytesToDoubles(byte[] bytes) throws IllegalArgumentException {
        if (bytes == null) {
            throw new IllegalArgumentException("'bytes' is null");
        }
        int samplesCount = bytes.length / 2;
        double[] samples = new double[samplesCount];
        for (int i = 0; i < samplesCount; i++) {
            ByteBuffer buffer = ByteBuffer.allocate(2);
            buffer.order(ByteOrder.LITTLE_ENDIAN);
            buffer.put(bytes[2 * i]);
            buffer.put(bytes[2 * i + 1]);
            samples[i] = (double) buffer.getShort(0) / Short.MAX_VALUE;
        }
        return samples;
    }
}
