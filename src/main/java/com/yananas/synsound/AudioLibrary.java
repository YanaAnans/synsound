package com.yananas.synsound;

import java.io.IOException;

public class AudioLibrary {

	private static double[] createNote(double duration, double frequency) {
		double amplitude = 0.8;
		double[] frequencies = new double[(int) (duration * AudioSettings.SAMPLE_RATE)];
		for (int sampleId = 0; sampleId < frequencies.length; sampleId++) {
			duration = sampleId / AudioSettings.SAMPLE_RATE;
			frequencies[sampleId] = amplitude * Math.sin(2 * Math.PI * frequency * duration);
		}
		return frequencies;
	}

	public static void main(String[] args) {
		try {
			String filename = "simple_wave.wav";
			AudioUtils.save(filename, createNote(2.0, 1245));
			AudioPlayer player = new AudioPlayer();
			player.playSound(filename);
		} catch (IllegalArgumentException | IOException e) {
			e.printStackTrace();
		}
	}

}
