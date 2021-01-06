package com.kneelawk.marionette.rt.mod.client.template;

import com.google.common.collect.ImmutableList;
import com.kneelawk.marionette.rt.mod.template.MarionetteSignalTData;
import lombok.Builder;
import lombok.Singular;
import lombok.Value;

@Value
@Builder(builderClassName = "Builder")
public class ClientGlobalSignalsTData {
    String packageName;
    String className;
    @Singular
    ImmutableList<String> importNames;
    @Singular
    ImmutableList<MarionetteSignalTData> signals;
}
