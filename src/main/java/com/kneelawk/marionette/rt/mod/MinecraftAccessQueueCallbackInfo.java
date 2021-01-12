package com.kneelawk.marionette.rt.mod;

import com.google.common.collect.ImmutableList;
import lombok.Builder;
import lombok.Singular;
import lombok.Value;

@Value
@Builder(builderClassName = "Builder")
public class MinecraftAccessQueueCallbackInfo {
    String callbackName;
    String callbackClass;
    @Singular
    ImmutableList<CallbackMaybeProxied> parameterTypes;
}
