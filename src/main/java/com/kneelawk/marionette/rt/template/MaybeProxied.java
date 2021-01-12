package com.kneelawk.marionette.rt.template;

import org.jetbrains.annotations.Nullable;

public interface MaybeProxied {
    @Nullable
    String getProxyImplementationClass();
}
