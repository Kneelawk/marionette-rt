package com.kneelawk.marionette.rt.mod.server.template;

import com.google.common.collect.ImmutableList;
import com.kneelawk.marionette.rt.mod.MinecraftAccessQueueCallbackInfo;
import lombok.Builder;
import lombok.Singular;
import lombok.Value;

@Value
@Builder(builderClassName = "Builder")
public class ServerGlobalQueuesTData {
    String packageName;
    String className;
    @Singular
    ImmutableList<String> importNames;
    @Singular
    ImmutableList<MinecraftAccessQueueCallbackInfo> queueCallbacks;
}
