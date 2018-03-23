package com.yananas.synsound.experiments;

import java.util.Arrays;

import com.yananas.synsound.AudioEditor;
import com.yananas.synsound.AudioLibrary;
import com.yananas.synsound.AudioPlayer;
import com.yananas.synsound.model.WavData;

public class Words {

    public static void main(String[] args) {
        WavData wavData1 = AudioLibrary.voice("papap.wav");
        wavData1 = AudioEditor.clip(wavData1, 350, 2052);
        WavData wavData2 = AudioLibrary.voice("r_ir_ir_.wav");
        wavData2 = AudioEditor.clip(wavData2, 1610, 1310);
        WavData wavData3 = AudioLibrary.voice("v_iv_iv_.wav");
        wavData3 = AudioEditor.clip(wavData3, 1331, 1500);
        WavData wavData4 = AudioLibrary.voice("v_ev_ev_.wav");
        wavData4 = AudioEditor.clip(wavData4, 1261, 1100);
        WavData wavData5 = AudioLibrary.voice("tetet.wav");
        wavData5 = AudioEditor.clip(wavData5, 2300, 350);
        WavData wavData6 = AudioLibrary.voice("m_im_im_.wav");
        wavData6 = AudioEditor.clip(wavData6, 460, 1950);
        WavData wavData7 = AudioLibrary.voice("ryr_ir.wav");
        wavData7 = AudioEditor.clip(wavData7, 2490, 80);
        WavData joinedData = AudioEditor
                .union(Arrays.asList(wavData1, wavData2, wavData3, wavData4, wavData5, wavData6, wavData7));
        AudioPlayer.play(joinedData);

    }

}
