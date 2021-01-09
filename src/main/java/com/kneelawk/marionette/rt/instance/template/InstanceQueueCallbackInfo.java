package com.kneelawk.marionette.rt.instance.template;

import lombok.Builder;
import lombok.Value;

@Value
@Builder(builderClassName = "Builder")
public class InstanceQueueCallbackInfo {
    String callbackName;
    String callbackClass;
    String callbackReturnType;
    int parameterCount;
}
