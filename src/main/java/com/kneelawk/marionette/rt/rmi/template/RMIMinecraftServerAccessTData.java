package com.kneelawk.marionette.rt.rmi.template;

import com.google.common.collect.ImmutableList;
import lombok.Builder;
import lombok.Singular;
import lombok.Value;

@Value
@Builder(builderClassName = "Builder")
public class RMIMinecraftServerAccessTData {
    String packageName;
    String className;
    @Singular
    ImmutableList<String> importNames;
}
