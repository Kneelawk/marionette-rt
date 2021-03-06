package com.kneelawk.marionette.rt.instance.template;

import com.google.common.collect.ImmutableList;
import lombok.Builder;
import lombok.Singular;
import lombok.Value;

@Value
@Builder(builderClassName = "Builder")
public class MinecraftServerInstanceTData {
    String packageName;
    String className;
    @Singular
    ImmutableList<String> importNames;
    String rmiClass;
    @Singular
    ImmutableList<String> signalNames;
    @Singular
    ImmutableList<InstanceQueueCallbackInfo> queueCallbacks;
}
