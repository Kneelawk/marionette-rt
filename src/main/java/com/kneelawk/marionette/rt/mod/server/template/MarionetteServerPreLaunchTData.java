package com.kneelawk.marionette.rt.mod.server.template;

import com.google.common.collect.ImmutableList;
import lombok.Builder;
import lombok.Singular;
import lombok.Value;

@Value
@Builder(builderClassName = "Builder")
public class MarionetteServerPreLaunchTData {
    String packageName;
    String className;
    @Singular
    ImmutableList<String> importNames;
    String accessClass;
    String rmiClass;
}
