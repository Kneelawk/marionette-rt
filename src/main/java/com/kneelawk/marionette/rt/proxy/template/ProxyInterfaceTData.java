package com.kneelawk.marionette.rt.proxy.template;

import com.google.common.collect.ImmutableList;
import lombok.Builder;
import lombok.Singular;
import lombok.Value;

@Value
@Builder(builderClassName = "Builder")
public class ProxyInterfaceTData {
    String packageName;
    String className;
    @Singular
    ImmutableList<String> importNames;
    @Singular
    ImmutableList<ProxyInterfaceMethodTData> methods;
}
