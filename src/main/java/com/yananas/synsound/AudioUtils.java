package com.yananas.synsound;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;

public class AudioUtils {

    public static final short MAX_SHORT = Short.MAX_VALUE;

    public static byte[] doubles2bytes(double[] values) {
        byte[] bytes = new byte[2 * values.length];
        for (int i = 0; i < values.length; i++) {
            short value = (short) (MAX_SHORT * values[i]);
            bytes[2 * i + 0] = (byte) value;
            bytes[2 * i + 1] = (byte) (value >> 8);
        }
        return bytes;
    }

    public static byte[] short2bytes(short value) {
        return new byte[] { (byte) value, (byte) (value >> 8) };
    }

    public static short bytes2short(byte[] bytes) {
        ByteBuffer buffer = ByteBuffer.allocate(2);
        buffer.order(ByteOrder.LITTLE_ENDIAN);
        buffer.put(bytes[0]);
        buffer.put(bytes[1]);
        return buffer.getShort(0);
    }

    public static void save(String filename, double[] samples) throws IllegalArgumentException, IOException{
        if (samples == null) {
            throw new IllegalArgumentException("Array with samples is null");
        }
        try {
            ByteArrayInputStream bais = new ByteArrayInputStream(doubles2bytes(samples));
            AudioFormat format = new AudioFormat(
                    AudioHeader.SAMPLE_RATE,
                    AudioHeader.BIT_DEPTH,
                    AudioHeader.CHANNELS_COUNT,
                    AudioHeader.SIGNED,
                    AudioHeader.BIG_ENDIAN
                );
            AudioInputStream ais = new AudioInputStream(bais, format, samples.length);
            if (filename.endsWith(".wav") || filename.endsWith(".WAV")) {
                AudioSystem.write(ais, AudioFileFormat.Type.WAVE, new File(filename));
            } else {
                throw new IllegalArgumentException("Audio format: '" + filename + "' is not supported");
            }
        } catch (IOException e) {
            throw new IOException("Not able to save the file '" + filename + "'", e);
        }
    }
}
