package com.kneelawk.marionette.rt.mod;

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
public class CallbackMaybeProxied implements MaybeProxied {
    String unproxiedClass;
    @Nullable
    String proxyImplementationClass;

    public static CallbackMaybeProxied ofUnproxied(String unproxiedClass) {
        return new CallbackMaybeProxied(unproxiedClass, null);
    }

    public static CallbackMaybeProxied ofImplementation(String unproxiedClass,
                                                        String proxyImplementationClass) {
        return new CallbackMaybeProxied(unproxiedClass, proxyImplementationClass);
    }

    @Override
    public String toString() {
        return unproxiedClass;
    }
}
