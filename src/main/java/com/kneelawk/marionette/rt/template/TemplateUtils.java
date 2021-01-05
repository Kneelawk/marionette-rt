package com.kneelawk.marionette.rt.template;

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
}
