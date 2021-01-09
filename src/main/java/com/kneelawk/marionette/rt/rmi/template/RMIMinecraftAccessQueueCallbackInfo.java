package com.kneelawk.marionette.rt.rmi.template;

import lombok.Builder;
import lombok.Value;

@Value
@Builder(builderClassName = "Builder")
public class RMIMinecraftAccessQueueCallbackInfo {
    String callbackName;
    String callbackClass;
}
