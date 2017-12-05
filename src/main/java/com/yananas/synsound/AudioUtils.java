package com.yananas.synsound;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

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
}
