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
    void testCaseFormats() {
        assertEquals(CaseFormat.LOWER_HYPHEN, TemplateUtils.getInstance().getLowerHyphenCase());
        assertEquals(CaseFormat.LOWER_UNDERSCORE, TemplateUtils.getInstance().getLowerUnderscoreCase());
        assertEquals(CaseFormat.LOWER_CAMEL, TemplateUtils.getInstance().getLowerCamelCase());
        assertEquals(CaseFormat.UPPER_CAMEL, TemplateUtils.getInstance().getUpperCamelCase());
        assertEquals(CaseFormat.UPPER_UNDERSCORE, TemplateUtils.getInstance().getUpperUnderscoreCase());
    }
}
