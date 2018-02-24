package com.yananas.synsound;

import com.yananas.synsound.model.WavData;

public class AudioEditor {
    /**
     *
     * Задание 2:
     * Метод clip должен обрезать зуковой файл wavData слева и справа, метод должен возвращать результат
     *
     * Параметры offset и cutoff считаются заданными в миллисекундах
     *
     */
    public WavData clip(WavData wavData, double offset, double cutoff) {
        @SuppressWarnings("unused")
        WavData result = new WavData();
        result.setFormat(wavData.getFormat());
        // #1 Вывести в консоль информацию о wav-файле: частоту дискретизации, длительность, кол-во сэмплов
        // #2 Пусть h - это временной интервал, через который берутся отсчеты по времени = (double) 1 / sampleRate
        // #3 Посчитать наибольший индекс массива offsetId, такой что offsetId * h < offset
        //              Заметьте, что вещественный параметр offset будет зажат между offsetId * h и (offsetId + 1) * h
        // #3 По заданному параметру cutoff посчитать наибольший индекс массива cutoffId, который не превосходит h * (totalTime - cutoff)
        // #4 Воспользоваться одноименным методом ArrayUtils, чтобы получить новый массив сэмплов
        // #5 Записать новый массив сэмплов в переменную result;
        return result;
    }

    /**
     *
     * Задание 3:
     * Метод join должен объединять звуковые файлы и возвращать результат
     *
     */
    public WavData join(WavData wavData1, WavData wavData2) {
        @SuppressWarnings("unused")
        WavData result = new WavData();
        // #1 Воспользоваться соответствующим методом ArrayUtils
        return result;
    }
}
