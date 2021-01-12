package com.kneelawk.marionette.rt.proxy.template;

import com.kneelawk.marionette.rt.template.MaybeProxied;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.jetbrains.annotations.Nullable;

@Getter
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@EqualsAndHashCode
public class ImplementationMaybeProxied implements MaybeProxied {
    @Nullable
    String unproxiedClass;
    @Nullable
    String proxyInterfaceClass;
    @Nullable
    String proxyImplementationClass;

    public static ImplementationMaybeProxied ofUnproxied(String unproxiedClass) {
        return new ImplementationMaybeProxied(unproxiedClass, null, null);
    }

    public static ImplementationMaybeProxied ofImplementation(String proxyInterfaceClass,
                                                              String proxyImplementationClass) {
        return new ImplementationMaybeProxied(null, proxyInterfaceClass, proxyImplementationClass);
    }

    @Override
    public String toString() {
        if (proxyInterfaceClass != null) {
            return proxyInterfaceClass;
        } else {
            return unproxiedClass;
        }
    }
}
