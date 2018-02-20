package com.yananas.synsound.model;

import lombok.Data;

@Data
public class WavFormat {
    private int bitDepth;
    private float sampleRate;
    private boolean bigEndian;
    private boolean signed;
    private int channelsCount;
}
