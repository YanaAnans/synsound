package com.yananas.synsound;


import com.yananas.synsound.model.WavData;

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

    public WavData join(WavData wavData1, WavData wavData2) {
        WavData result = new WavData();
        // #1 Воспользоваться соответствующим методом ArrayUtils
        double[] joinedSamples = ArrayUtils.union(wavData1.getSamples(), wavData2.getSamples());
        result.setSamples(joinedSamples);
        result.setFormat(wavData1.getFormat());
        return result;
    }

    public static void main(String[] args) {
        AudioEditor audioEditor = new AudioEditor();
        AudioPlayer player = new AudioPlayer();
        // test1
        WavData wavData = AudioLibrary.note(2, 440.1);
        WavData clippedData = audioEditor.clip(wavData, 500, 500);
        System.out.println(clippedData.getDuration());
        //test2
        WavData wavData1 = AudioLibrary.note(2, 440.1);
        WavData wavData2 = AudioLibrary.note(2, 660.1);
        WavData joinedData = audioEditor.join(wavData1, wavData2);
        player.play(joinedData);
        player.play(audioEditor.clip(joinedData, 1000, 1000));
    }
}
