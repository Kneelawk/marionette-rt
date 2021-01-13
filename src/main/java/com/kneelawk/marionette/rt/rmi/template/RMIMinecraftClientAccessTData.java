package com.kneelawk.marionette.rt.rmi.template;

import com.google.common.collect.ImmutableList;
import lombok.Builder;
import lombok.Singular;
import lombok.Value;

@Value
@Builder(builderClassName = "Builder")
public class RMIMinecraftClientAccessTData {
    String packageName;
    String className;
    @Singular
    ImmutableList<String> importNames;
    @Singular
    ImmutableList<String> signalNames;
    @Singular
    ImmutableList<RMIMinecraftAccessQueueCallbackInfo> queueCallbacks;
    @Singular
    ImmutableList<RMIMinecraftAccessConstructorTData> constructors;
}
