package com.kneelawk.marionette.rt.mod;

import lombok.Builder;
import lombok.Value;

@Value
@Builder(builderClassName = "Builder")
public class MinecraftAccessQueueCallbackInfo {
    String callbackName;
    String callbackClass;
}
