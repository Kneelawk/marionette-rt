package com.kneelawk.marionette.rt.callback.template;

import com.google.common.collect.ImmutableList;
import lombok.Builder;
import lombok.Singular;
import lombok.Value;

@Value
@Builder(builderClassName = "Builder")
public class CallbackTData {
    String packageName;
    String className;
    @Singular
    ImmutableList<String> importNames;
    boolean remote;
    String returnType;
    @Singular
    ImmutableList<String> parameterTypes;
    @Singular
    ImmutableList<String> exceptionTypes;
}
