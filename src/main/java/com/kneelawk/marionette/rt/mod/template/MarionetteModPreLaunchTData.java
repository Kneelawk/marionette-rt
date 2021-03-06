package com.kneelawk.marionette.rt.mod.template;

import com.google.common.collect.ImmutableList;
import lombok.Builder;
import lombok.Singular;
import lombok.Value;

@Value
@Builder(builderClassName = "Builder")
public class MarionetteModPreLaunchTData {
    String packageName;
    String className;
    @Singular
    ImmutableList<String> importNames;
    String clientPreLaunch;
    String serverPreLaunch;
}
