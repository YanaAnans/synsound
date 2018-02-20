package com.yananas.synsound.model;

import lombok.Data;

@Data
public class WavData {
    private double[] samples;
    private WavFormat format;
}
