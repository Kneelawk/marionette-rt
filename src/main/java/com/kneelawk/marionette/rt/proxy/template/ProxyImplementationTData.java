package com.kneelawk.marionette.rt.proxy.template;

import com.google.common.collect.ImmutableList;
import lombok.Builder;
import lombok.Singular;
import lombok.Value;
import org.jetbrains.annotations.Nullable;

@Value
@Builder(builderClassName = "Builder")
public class ProxyImplementationTData {
    String packageName;
    String className;
    @Singular
    ImmutableList<String> importNames;
    @Nullable
    String superClass;
    String rmiClass;
    String proxiedClass;
    @Singular
    ImmutableList<ProxyImplementationMethodTData> methods;
    @Singular
    ImmutableList<PropertyImplementationTData> getters;
    @Singular
    ImmutableList<PropertyImplementationTData> setters;
}
