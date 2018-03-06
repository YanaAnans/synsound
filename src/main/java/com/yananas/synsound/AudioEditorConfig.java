package com.yananas.synsound;

import lombok.Data;

@Data
public class AudioEditorConfig {
    private String alias;
    private String fileName;
    private int offset;
    private int cutoff;
}
