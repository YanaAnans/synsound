package com.yananas.synsound;


import java.util.Arrays;
import java.util.List;

import com.yananas.synsound.model.WavData;
import com.yananas.synsound.model.WavFormat;

public class AudioEditor {
    public WavData clip(WavData wavData, double offset, double cutoff) {
        WavData result = new WavData();

        System.out.println(wavData.getFormat().getSampleRate() + " - частота дискретизации");
        System.out.println(wavData.getDuration() + " - длительность (мс)");
        System.out.println(wavData.getSamples().length + " - кол-во сэмплов");

        int firstIndex = wavData.getSampleId(offset);
        double totalTime = wavData.getDuration();
        int lastIndex = wavData.getSampleId(totalTime - cutoff);

        double[] samples = wavData.getSamples();
        double[] clippedSamples = ArrayUtils.clip(samples, firstIndex, lastIndex);

        result.setSamples(clippedSamples);
        result.setFormat(wavData.getFormat());
        return result;
    }

    public WavData union(List<WavData> wavDatas) throws IllegalArgumentException {
        int size = 0;
        for (int wavDataId = 0; wavDataId < wavDatas.size(); wavDataId++) {
            size += wavDatas.get(wavDataId).getSamples().length;
        }
        int destPos = 0;
        double[] copyTo = new double[size];
        WavFormat wavFormat = wavDatas.get(0).getFormat();
        for (int wavDataId = 0; wavDataId < wavDatas.size(); wavDataId++) {
            WavFormat nextFormat = wavDatas.get(wavDataId).getFormat();
            if (!wavFormat.equals(nextFormat)) {
                throw new IllegalArgumentException("Wav files are given in different formats");
            }
            double[] copyFrom = wavDatas.get(wavDataId).getSamples();
            System.arraycopy(copyFrom, 0, copyTo, destPos, copyFrom.length);
            destPos += copyFrom.length;
        }
        WavData wavData = new WavData();
        wavData.setFormat(wavFormat);
        wavData.setSamples(copyTo);
        return wavData;
    }

    @Deprecated
    public WavData join(WavData wavData1, WavData wavData2) {
        WavData result = new WavData();
        double[] joinedSamples = ArrayUtils.union(wavData1.getSamples(), wavData2.getSamples());
        result.setSamples(joinedSamples);
        result.setFormat(wavData1.getFormat());
        return result;
    }

    public static void main(String[] args) {
        AudioEditor audioEditor = new AudioEditor();
        AudioPlayer player = new AudioPlayer();
        WavData wavData1 = AudioLibrary.note(1, 440.1);
        WavData wavData2 = AudioLibrary.note(1, 660.1);
        WavData wavData3 = AudioLibrary.note(1, 880.1);
        WavData joinedData = audioEditor.union(Arrays.asList(wavData1, wavData2, wavData3));
        player.play(joinedData);
        player.play(audioEditor.clip(joinedData, 1000, 1000));
    }
}
