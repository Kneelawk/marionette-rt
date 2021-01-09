package com.kneelawk.marionette.rt.template;

import com.google.common.base.CaseFormat;
import com.google.common.collect.ImmutableList;
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
    void testCaseFormats() {
        assertEquals(CaseFormat.LOWER_HYPHEN, TemplateUtils.getInstance().getLowerHyphenCase());
        assertEquals(CaseFormat.LOWER_UNDERSCORE, TemplateUtils.getInstance().getLowerUnderscoreCase());
        assertEquals(CaseFormat.LOWER_CAMEL, TemplateUtils.getInstance().getLowerCamelCase());
        assertEquals(CaseFormat.UPPER_CAMEL, TemplateUtils.getInstance().getUpperCamelCase());
        assertEquals(CaseFormat.UPPER_UNDERSCORE, TemplateUtils.getInstance().getUpperUnderscoreCase());
    }
}
