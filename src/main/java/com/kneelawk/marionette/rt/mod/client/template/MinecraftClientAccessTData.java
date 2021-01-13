package com.kneelawk.marionette.rt.mod.client.template;

import com.google.common.collect.ImmutableList;
import com.kneelawk.marionette.rt.mod.MinecraftAccessConstructorTData;
import com.kneelawk.marionette.rt.mod.MinecraftAccessQueueCallbackInfo;
import lombok.Builder;
import lombok.Singular;
import lombok.Value;

@Value
@Builder(builderClassName = "Builder")
public class MinecraftClientAccessTData {
    String packageName;
    String className;
    @Singular
    ImmutableList<String> importNames;
    String rmiClass;
    String signalClass;
    @Singular
    ImmutableList<String> signalNames;
    String queueClass;
    @Singular
    ImmutableList<MinecraftAccessQueueCallbackInfo> queueCallbacks;
    @Singular
    ImmutableList<MinecraftAccessConstructorTData> constructors;
}
