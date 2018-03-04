package com.yananas.synsound.experiments;

import com.yananas.synsound.AudioLibrary;
import com.yananas.synsound.AudioPlayer;
import com.yananas.synsound.model.WavData;

public class WavEditor {

	public static void main(String[] args) {
		WavData voice = AudioLibrary.voice("~a~a~.wav");
		AudioPlayer player = new AudioPlayer();
		player.play(voice);
		System.out.println(voice.getDuration());

	}

}
