package com.kneelawk.marionette.rt.proxy.template;

import com.google.common.collect.ImmutableList;
import com.kneelawk.marionette.rt.template.MaybeProxied;
import lombok.Builder;
import lombok.Singular;
import lombok.Value;

@Value
@Builder(builderClassName = "Builder")
public class ProxyInterfaceMethodTData {
    String methodName;
    String returnType;
    @Singular
    ImmutableList<String> parameterTypes;
    @Singular
    ImmutableList<String> exceptionTypes;
}
