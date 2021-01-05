package com.kneelawk.marionette.rt.template;

import com.google.common.collect.ImmutableList;
import lombok.Builder;
import lombok.Singular;
import lombok.Value;

@Value
@Builder(builderClassName = "Builder")
public class FabricModJsonTData {
    String id;
    String name;
    @Singular
    ImmutableList<String> preLaunchEntryPoints;
}
