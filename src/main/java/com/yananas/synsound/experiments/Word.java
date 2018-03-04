package com.yananas.synsound.experiments;

import java.util.Arrays;

import com.yananas.synsound.AudioEditor;
import com.yananas.synsound.AudioLibrary;
import com.yananas.synsound.AudioPlayer;
import com.yananas.synsound.model.WavData;

public class Word {

    public static void main(String[] args) {
        AudioEditor audioEditor = new AudioEditor();
        WavData wavData1 = AudioLibrary.voice("papap.wav");
        wavData1 = AudioEditor.clip(wavData1, 354, 2020);
        WavData wavData2 = AudioLibrary.voice("r_ir_ir_.wav");
        wavData2 = AudioEditor.clip(wavData2, 1615, 750);
        WavData wavData3 = AudioLibrary.voice("v_iv_iv_.wav");
        wavData3 = AudioEditor.clip(wavData3, 1237, 1480);
        WavData wavData4 = AudioLibrary.voice("v_ev_ev_.wav");
        wavData4 = AudioEditor.clip(wavData4, 1225, 750);
        WavData wavData5 = AudioLibrary.voice("tetet.wav");
        wavData5 = AudioEditor.clip(wavData5, 2260, 50);
        WavData joinedData = audioEditor.union(Arrays.asList(wavData1, wavData2, wavData3, wavData4, wavData5));
        AudioPlayer player = new AudioPlayer();
        player.play(joinedData);

    }

}
