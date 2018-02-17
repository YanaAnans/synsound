/***************
 * BEGIN-STANDARD-COPYRIGHT      ***************
 * <p>
 * Copyright (c) 2009-2018, Spirent Communications.
 * <p>
 * All rights reserved. Proprietary and confidential information of Spirent Communications.
 * <p>
 * **************        END-STANDARD-COPYRIGHT
 ***************/
package com.yananas.synsound;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class TransformUtils {

	/**
	 * Converts array of doubles into array of bytes, assuming that doubles are
	 * normalized
	 */
	public static byte[] doubles2bytes(double[] values) throws IllegalArgumentException {
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
	public static double[] bytes2doubles(byte[] bytes) throws IllegalArgumentException {
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
