package com.kneelawk.marionette.rt.proxy.template;

import com.google.common.collect.ImmutableList;
import lombok.Builder;
import lombok.Singular;
import lombok.Value;
import org.jetbrains.annotations.Nullable;

@Value
@Builder(builderClassName = "Builder")
public class ProxyInterfaceTData {
    String packageName;
    String className;
    @Singular
    ImmutableList<String> importNames;
    @Nullable
    String superClass;
    @Singular
    ImmutableList<ProxyInterfaceMethodTData> methods;
    @Singular
    ImmutableList<PropertyInterfaceTData> getters;
    @Singular
    ImmutableList<PropertyInterfaceTData> setters;
}
