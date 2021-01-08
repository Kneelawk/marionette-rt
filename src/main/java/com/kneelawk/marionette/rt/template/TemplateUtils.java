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
        StringBuilder tmp = new StringBuilder();
        int i = 0;
        for (Object o : parameterTypes) {
            if (i != 0) {
                tmp.append(", ");
            }
            tmp.append(o).append(" p").append(i);
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
