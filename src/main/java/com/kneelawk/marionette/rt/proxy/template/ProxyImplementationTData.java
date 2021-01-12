package com.kneelawk.marionette.rt.proxy.template;

import com.google.common.collect.ImmutableList;
import lombok.Builder;
import lombok.Singular;
import lombok.Value;

@Value
@Builder(builderClassName = "Builder")
public class ProxyImplementationTData {
    String packageName;
    String className;
    @Singular
    ImmutableList<String> importNames;
    String rmiClass;
    String proxiedClass;
    @Singular
    ImmutableList<ProxyImplementationMethodTData> methods;
}
