package com.kneelawk.marionette.rt.mod;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import com.kneelawk.marionette.rt.proxy.template.ImplementationMaybeProxied;
import lombok.Builder;
import lombok.Singular;
import lombok.Value;

@Value
@Builder(builderClassName = "Builder")
public class MinecraftAccessConstructorTData {
    String constructorName;
    ConstructorMaybeProxied constructorClass;
    @Singular
    ImmutableList<ImplementationMaybeProxied> parameters;
    @Singular
    ImmutableSet<String> exceptions;
}
