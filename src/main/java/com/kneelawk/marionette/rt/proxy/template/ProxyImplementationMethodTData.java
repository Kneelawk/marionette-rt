package com.kneelawk.marionette.rt.proxy.template;

import com.google.common.collect.ImmutableList;
import lombok.Builder;
import lombok.Singular;
import lombok.Value;

@Value
@Builder(builderClassName = "Builder")
public class ProxyImplementationMethodTData {
    String methodName;
    ImplementationMaybeProxied returnType;
    @Singular
    ImmutableList<ImplementationMaybeProxied> parameterTypes;
    @Singular
    ImmutableList<String> exceptionTypes;
}
