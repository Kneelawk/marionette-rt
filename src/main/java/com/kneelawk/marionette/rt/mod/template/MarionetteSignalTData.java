package com.kneelawk.marionette.rt.mod.template;

import com.kneelawk.marionette.rt.mod.MarionetteSignalType;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class MarionetteSignalTData {
    String name;
    MarionetteSignalType type;
}
