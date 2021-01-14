package com.kneelawk.marionette.rt.template;

import com.google.common.base.CaseFormat;
import com.google.common.collect.ImmutableList;
import com.kneelawk.marionette.rt.proxy.template.ImplementationMaybeProxied;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TemplateUtilsTests {
    @Test
    void testIsNull() {
        assertTrue(TemplateUtils.getInstance().isNull(null));
        assertFalse(TemplateUtils.getInstance().isNull(new Object()));
    }

    @Test
    void testQuoted() {
        assertEquals("\"hello\"", TemplateUtils.getInstance().quoted("hello"));
        assertEquals("\"\"", TemplateUtils.getInstance().quoted(""));
    }

    @Test
    void testJoin() {
        assertEquals("he, ll, o , world, !",
                TemplateUtils.getInstance().join(ImmutableList.of("he", "ll", "o ", "world", "!"), ", ", "", ""));
        assertEquals("[foo:bar:baz]",
                TemplateUtils.getInstance().join(ImmutableList.of("foo", "bar", "baz"), ":", "[", "]"));
    }

    @Test
    void testJoinQuoted() {
        assertEquals("\"hello\", \"world\", \"!\"",
                TemplateUtils.getInstance().joinQuoted(ImmutableList.of("hello", "world", "!"), ", ", "", ""));
        assertEquals("[\"foo\"|\"bar\"|\"baz\"]",
                TemplateUtils.getInstance().joinQuoted(ImmutableList.of("foo", "bar", "baz"), "|", "[", "]"));
    }

    @Test
    void testJoinParameters() {
        assertEquals("Foo p0, Bar p1", TemplateUtils.getInstance().joinParameters(ImmutableList.of("Foo", "Bar")));
        assertEquals("Foo p0", TemplateUtils.getInstance().joinParameters(ImmutableList.of("Foo")));
        assertEquals("", TemplateUtils.getInstance().joinParameters(ImmutableList.of()));
        assertEquals(", Foo p0, Bar p1",
                TemplateUtils.getInstance().joinParameters(ImmutableList.of("Foo", "Bar"), true));
        assertEquals(", Foo p0", TemplateUtils.getInstance().joinParameters(ImmutableList.of("Foo"), true));
        assertEquals("", TemplateUtils.getInstance().joinParameters(ImmutableList.of(), true));
    }

    @Test
    void testParameters() {
        assertEquals("p0, p1", TemplateUtils.getInstance().parameters(2));
        assertEquals("p0", TemplateUtils.getInstance().parameters(1));
        assertEquals("", TemplateUtils.getInstance().parameters(0));
        assertEquals(", p0, p1", TemplateUtils.getInstance().parameters(2, true));
        assertEquals(", p0", TemplateUtils.getInstance().parameters(1, true));
        assertEquals("", TemplateUtils.getInstance().parameters(0, true));
    }

    @Test
    void testUnwrapStart() {
        assertEquals("", TemplateUtils.getInstance().unwrapStart("String"));
        assertEquals("",
                TemplateUtils.getInstance().unwrapStart(ImplementationMaybeProxied.ofUnproxied("String")));
        assertEquals("((MinecraftClientProxy) RMIUtils.requireOriginal(", TemplateUtils.getInstance().unwrapStart(
                ImplementationMaybeProxied.ofImplementation("RMIMinecraftClientProxy", "MinecraftClientProxy")));
    }

    @Test
    void testUnwrapEnd() {
        assertEquals("", TemplateUtils.getInstance().unwrapEnd("String"));
        assertEquals("",
                TemplateUtils.getInstance().unwrapEnd(ImplementationMaybeProxied.ofUnproxied("String")));
        assertEquals(")).getProxy()", TemplateUtils.getInstance().unwrapEnd(
                ImplementationMaybeProxied.ofImplementation("RMIMinecraftClientProxy", "MinecraftClientProxy")));
    }

    @Test
    void testWrapStart() {
        assertEquals("", TemplateUtils.getInstance().wrapStart("String"));
        assertEquals("",
                TemplateUtils.getInstance().wrapStart(ImplementationMaybeProxied.ofUnproxied("String")));
        assertEquals("RMIUtils.export(new MinecraftClientProxy(", TemplateUtils.getInstance().wrapStart(
                ImplementationMaybeProxied.ofImplementation("RMIMinecraftClientProxy", "MinecraftClientProxy")));
    }

    @Test
    void testWrapEnd() {
        assertEquals("", TemplateUtils.getInstance().wrapEnd("String"));
        assertEquals("", TemplateUtils.getInstance().wrapEnd(ImplementationMaybeProxied.ofUnproxied("String")));
        assertEquals("))", TemplateUtils.getInstance().wrapEnd(
                ImplementationMaybeProxied.ofImplementation("RMIMinecraftClientProxy", "MinecraftClientProxy")));
    }

    @Test
    void testUnwrapParameters() {
        assertEquals("p0, p1", TemplateUtils.getInstance().unwrapParameters(ImmutableList.of("Foo", "Bar"), false));
        assertEquals("p0", TemplateUtils.getInstance().unwrapParameters(ImmutableList.of("Foo"), false));
        assertEquals("p0, p1", TemplateUtils.getInstance().unwrapParameters(ImmutableList
                        .of(ImplementationMaybeProxied.ofUnproxied("Foo"), ImplementationMaybeProxied.ofUnproxied("Bar")),
                false));
        assertEquals("p0", TemplateUtils.getInstance()
                .unwrapParameters(ImmutableList.of(ImplementationMaybeProxied.ofUnproxied("Foo")), false));
        assertEquals(
                "((MinecraftClientProxy) RMIUtils.requireOriginal(p0)).getProxy(), ((TitleScreenProxy) RMIUtils.requireOriginal(p1)).getProxy()",
                TemplateUtils.getInstance().unwrapParameters(ImmutableList.of(ImplementationMaybeProxied
                                .ofImplementation("RMIMinecraftClientProxy", "MinecraftClientProxy"),
                        ImplementationMaybeProxied.ofImplementation("RMITitleScreenProxy", "TitleScreenProxy")),
                        false));
        assertEquals("((MinecraftClientProxy) RMIUtils.requireOriginal(p0)).getProxy()", TemplateUtils.getInstance()
                .unwrapParameters(ImmutableList.of(ImplementationMaybeProxied
                        .ofImplementation("RMIMinecraftClientProxy", "MinecraftClientProxy")), false));
        assertEquals("", TemplateUtils.getInstance().unwrapParameters(ImmutableList.of(), false));
        assertEquals(", p0, p1", TemplateUtils.getInstance().unwrapParameters(ImmutableList.of("Foo", "Bar"), true));
        assertEquals(", p0", TemplateUtils.getInstance().unwrapParameters(ImmutableList.of("Foo"), true));
        assertEquals(", p0, p1", TemplateUtils.getInstance().unwrapParameters(ImmutableList
                        .of(ImplementationMaybeProxied.ofUnproxied("Foo"), ImplementationMaybeProxied.ofUnproxied("Bar")),
                true));
        assertEquals(", p0", TemplateUtils.getInstance()
                .unwrapParameters(ImmutableList.of(ImplementationMaybeProxied.ofUnproxied("Foo")), true));
        assertEquals(
                ", ((MinecraftClientProxy) RMIUtils.requireOriginal(p0)).getProxy(), ((TitleScreenProxy) RMIUtils.requireOriginal(p1)).getProxy()",
                TemplateUtils.getInstance().unwrapParameters(ImmutableList.of(ImplementationMaybeProxied
                                .ofImplementation("RMIMinecraftClientProxy", "MinecraftClientProxy"),
                        ImplementationMaybeProxied.ofImplementation("RMITitleScreenProxy", "TitleScreenProxy")), true));
        assertEquals(", ((MinecraftClientProxy) RMIUtils.requireOriginal(p0)).getProxy()", TemplateUtils.getInstance()
                .unwrapParameters(ImmutableList.of(ImplementationMaybeProxied
                        .ofImplementation("RMIMinecraftClientProxy", "MinecraftClientProxy")), true));
        assertEquals("", TemplateUtils.getInstance().unwrapParameters(ImmutableList.of(), true));
    }

    @Test
    void testWrapParameters() {
        assertEquals("p0, p1", TemplateUtils.getInstance().wrapParameters(ImmutableList.of("Foo", "Bar"), false));
        assertEquals("p0", TemplateUtils.getInstance().wrapParameters(ImmutableList.of("Foo"), false));
        assertEquals("p0, p1", TemplateUtils.getInstance().wrapParameters(ImmutableList
                        .of(ImplementationMaybeProxied.ofUnproxied("Foo"), ImplementationMaybeProxied.ofUnproxied("Bar")),
                false));
        assertEquals("p0", TemplateUtils.getInstance()
                .wrapParameters(ImmutableList.of(ImplementationMaybeProxied.ofUnproxied("Foo")), false));
        assertEquals("RMIUtils.export(new MinecraftClientProxy(p0)), RMIUtils.export(new TitleScreenProxy(p1))",
                TemplateUtils.getInstance().wrapParameters(
                        ImmutableList.of(ImplementationMaybeProxied
                                        .ofImplementation("RMIMinecraftClientProxy", "MinecraftClientProxy"),
                                ImplementationMaybeProxied.ofImplementation("RMITitleScreenProxy", "TitleScreenProxy")),
                        false));
        assertEquals("RMIUtils.export(new MinecraftClientProxy(p0))",
                TemplateUtils.getInstance().wrapParameters(ImmutableList
                                .of(ImplementationMaybeProxied
                                        .ofImplementation("RMIMinecraftClientProxy", "MinecraftClientProxy")),
                        false));
        assertEquals("", TemplateUtils.getInstance().wrapParameters(ImmutableList.of(), false));
        assertEquals(", p0, p1", TemplateUtils.getInstance().wrapParameters(ImmutableList.of("Foo", "Bar"), true));
        assertEquals(", p0", TemplateUtils.getInstance().wrapParameters(ImmutableList.of("Foo"), true));
        assertEquals(", p0, p1", TemplateUtils.getInstance().wrapParameters(ImmutableList
                        .of(ImplementationMaybeProxied.ofUnproxied("Foo"), ImplementationMaybeProxied.ofUnproxied("Bar")),
                true));
        assertEquals(", p0", TemplateUtils.getInstance()
                .wrapParameters(ImmutableList.of(ImplementationMaybeProxied.ofUnproxied("Foo")), true));
        assertEquals(", RMIUtils.export(new MinecraftClientProxy(p0)), RMIUtils.export(new TitleScreenProxy(p1))",
                TemplateUtils.getInstance().wrapParameters(
                        ImmutableList.of(ImplementationMaybeProxied
                                        .ofImplementation("RMIMinecraftClientProxy", "MinecraftClientProxy"),
                                ImplementationMaybeProxied.ofImplementation("RMITitleScreenProxy", "TitleScreenProxy")),
                        true));
        assertEquals(", RMIUtils.export(new MinecraftClientProxy(p0))",
                TemplateUtils.getInstance().wrapParameters(ImmutableList
                                .of(ImplementationMaybeProxied
                                        .ofImplementation("RMIMinecraftClientProxy", "MinecraftClientProxy")),
                        true));
        assertEquals("", TemplateUtils.getInstance().wrapParameters(ImmutableList.of(), true));
    }

    @Test
    void testCaseFormats() {
        assertEquals(CaseFormat.LOWER_HYPHEN, TemplateUtils.getInstance().getLowerHyphenCase());
        assertEquals(CaseFormat.LOWER_UNDERSCORE, TemplateUtils.getInstance().getLowerUnderscoreCase());
        assertEquals(CaseFormat.LOWER_CAMEL, TemplateUtils.getInstance().getLowerCamelCase());
        assertEquals(CaseFormat.UPPER_CAMEL, TemplateUtils.getInstance().getUpperCamelCase());
        assertEquals(CaseFormat.UPPER_UNDERSCORE, TemplateUtils.getInstance().getUpperUnderscoreCase());
    }

    @Test
    void testDetectCase() {
        assertEquals(CaseFormat.LOWER_HYPHEN, TemplateUtils.getInstance().detectCase("foo-bar"));
        assertEquals(CaseFormat.LOWER_UNDERSCORE, TemplateUtils.getInstance().detectCase("foo_bar"));
        assertEquals(CaseFormat.LOWER_CAMEL, TemplateUtils.getInstance().detectCase("fooBar"));
        assertEquals(CaseFormat.LOWER_CAMEL, TemplateUtils.getInstance().detectCase("fooBAR"));
        assertEquals(CaseFormat.LOWER_CAMEL, TemplateUtils.getInstance().detectCase("foo"));
        assertEquals(CaseFormat.UPPER_CAMEL, TemplateUtils.getInstance().detectCase("FooBar"));
        assertEquals(CaseFormat.UPPER_CAMEL, TemplateUtils.getInstance().detectCase("FOOBar"));
        assertEquals(CaseFormat.UPPER_CAMEL, TemplateUtils.getInstance().detectCase("FooBAR"));
        assertEquals(CaseFormat.UPPER_UNDERSCORE, TemplateUtils.getInstance().detectCase("FOO_BAR"));
        assertEquals(CaseFormat.UPPER_UNDERSCORE, TemplateUtils.getInstance().detectCase("FOO"));
    }
}
