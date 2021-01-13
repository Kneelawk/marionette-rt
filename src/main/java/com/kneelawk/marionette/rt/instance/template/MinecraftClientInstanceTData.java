package com.kneelawk.marionette.rt.instance.template;

import com.google.common.collect.ImmutableList;
import com.kneelawk.marionette.rt.rmi.template.RMIMinecraftAccessConstructorTData;
import lombok.Builder;
import lombok.Singular;
import lombok.Value;

@Value
@Builder(builderClassName = "Builder")
public class MinecraftClientInstanceTData {
    String packageName;
    String className;
    @Singular
    ImmutableList<String> importNames;
    String rmiClass;
    @Singular
    ImmutableList<String> signalNames;
    @Singular
    ImmutableList<InstanceQueueCallbackInfo> queueCallbacks;
    @Singular
    ImmutableList<RMIMinecraftAccessConstructorTData> constructors;
}
