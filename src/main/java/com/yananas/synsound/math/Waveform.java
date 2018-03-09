package com.yananas.synsound.math;

import java.util.function.Function;

import lombok.Builder;


@Builder
public class Waveform {
    @Builder.Default private double amplitude = 1.0;
    @Builder.Default private double phase = 0.0;
    @Builder.Default private double frequency = 1.0;

    public Function<Double, Double> fn() {
        return (time) -> amplitude * Math.cos(2 * Math.PI * time * frequency + phase);
    }
}
