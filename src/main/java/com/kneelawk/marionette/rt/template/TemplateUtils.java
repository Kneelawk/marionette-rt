package com.kneelawk.marionette.rt.template;

import com.google.common.base.CaseFormat;

public class TemplateUtils {
    private static final TemplateUtils INSTNACE = new TemplateUtils();

    public static TemplateUtils getInstance() {
        return INSTNACE;
    }

    private TemplateUtils() {
    }

    public boolean isNull(Object object) {
        return object == null;
    }

    public String quoted(Object str) {
        return "\"" + str + "\"";
    }

    public String join(Iterable<?> iterable, String joiner) {
        return join(iterable, joiner, "", "");
    }

    public String join(Iterable<?> iterable, String joiner, String prefix, String suffix) {
        StringBuilder tmp = new StringBuilder(prefix);
        String joinerTmp = "";
        for (Object o : iterable) {
            tmp.append(joinerTmp).append(o);
            joinerTmp = joiner;
        }
        tmp.append(suffix);
        return tmp.toString();
    }

    public String joinQuoted(Iterable<?> iterable, String joiner) {
        return joinQuoted(iterable, joiner, "", "");
    }

    public String joinQuoted(Iterable<?> iterable, String joiner, String prefix, String suffix) {
        StringBuilder tmp = new StringBuilder(prefix);
        String joinerTmp = "";
        for (Object o : iterable) {
            tmp.append(joinerTmp).append(quoted(o));
            joinerTmp = joiner;
        }
        tmp.append(suffix);
        return tmp.toString();
    }

    public String joinParameters(Iterable<?> parameterTypes) {
        return joinParameters(parameterTypes, false);
    }

    public String joinParameters(Iterable<?> parameterTypes, boolean firstComma) {
        StringBuilder tmp = new StringBuilder();
        int i = 0;
        for (Object o : parameterTypes) {
            if (i != 0 || firstComma) {
                tmp.append(", ");
            }
            tmp.append(o).append(" p").append(i);
            i++;
        }
        return tmp.toString();
    }

    public String parameters(int count) {
        return parameters(count, false);
    }

    public String parameters(int count, boolean firstComma) {
        StringBuilder tmp = new StringBuilder();
        for (int i = 0; i < count; i++) {
            if (i != 0 || firstComma) {
                tmp.append(", ");
            }
            tmp.append("p").append(i);
        }
        return tmp.toString();
    }

    public String unwrapStart(Object o) {
        if (o instanceof MaybeProxied && ((MaybeProxied) o).getProxyImplementationClass() != null) {
            return "((" + ((MaybeProxied) o).getProxyImplementationClass() + ") RMIUtils.requireOriginal(";
        } else {
            return "";
        }
    }

    public String unwrapEnd(Object o) {
        if (o instanceof MaybeProxied && ((MaybeProxied) o).getProxyImplementationClass() != null) {
            return ")).getProxy()";
        } else {
            return "";
        }
    }

    public String unwrapParameters(Iterable<?> parameterTypes, boolean firstComma) {
        StringBuilder tmp = new StringBuilder();
        int i = 0;
        for (Object o : parameterTypes) {
            if (i != 0 || firstComma) {
                tmp.append(", ");
            }
            tmp.append(unwrapStart(o)).append("p").append(i).append(unwrapEnd(o));
            i++;
        }
        return tmp.toString();
    }

    public String wrapStart(Object o) {
        if (o instanceof MaybeProxied && ((MaybeProxied) o).getProxyImplementationClass() != null) {
            return "RMIUtils.export(new " + ((MaybeProxied) o).getProxyImplementationClass() + "(";
        } else {
            return "";
        }
    }

    public String wrapEnd(Object o) {
        if (o instanceof MaybeProxied && ((MaybeProxied) o).getProxyImplementationClass() != null) {
            return "))";
        } else {
            return "";
        }
    }

    public String wrapParameters(Iterable<?> parameterTypes, boolean firstComma) {
        StringBuilder tmp = new StringBuilder();
        int i = 0;
        for (Object o : parameterTypes) {
            if (i != 0 || firstComma) {
                tmp.append(", ");
            }
            tmp.append(wrapStart(o)).append("p").append(i).append(wrapEnd(o));
            i++;
        }
        return tmp.toString();
    }

    public CaseFormat getLowerHyphenCase() {
        return CaseFormat.LOWER_HYPHEN;
    }

    public CaseFormat getLowerUnderscoreCase() {
        return CaseFormat.LOWER_UNDERSCORE;
    }

    public CaseFormat getLowerCamelCase() {
        return CaseFormat.LOWER_CAMEL;
    }

    public CaseFormat getUpperCamelCase() {
        return CaseFormat.UPPER_CAMEL;
    }

    public CaseFormat getUpperUnderscoreCase() {
        return CaseFormat.UPPER_UNDERSCORE;
    }
}
