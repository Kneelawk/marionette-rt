package com.kneelawk.marionette.rt.mod;

import com.kneelawk.marionette.rt.template.MaybeProxied;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@AllArgsConstructor
@EqualsAndHashCode
public class ConstructorMaybeProxied implements MaybeProxied {
    String unproxiedClass;
    String proxyInterfaceClass;
    String proxyImplementationClass;

    @Override
    public String toString() {
        return proxyInterfaceClass;
    }
}
