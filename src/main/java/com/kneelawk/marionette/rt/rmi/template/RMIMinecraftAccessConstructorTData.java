package com.kneelawk.marionette.rt.rmi.template;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import lombok.Builder;
import lombok.Singular;
import lombok.Value;

@Value
@Builder(builderClassName = "Builder")
public class RMIMinecraftAccessConstructorTData {
    String constructorName;
    String constructorClass;
    @Singular
    ImmutableList<String> parameters;
    @Singular
    ImmutableSet<String> exceptions;
}
