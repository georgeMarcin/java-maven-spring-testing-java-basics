package org.mbiczak.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mbiczak.ArraySorter;
import org.mbiczak.CharGrouper;
import org.mbiczak.StringCompressor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;

/**
 * Tests functionality and corner cases for the org.mbiczak.CharGrouper class.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = CharGrouperTest.TestSpringConfig.class)
public class CharGrouperTest {
    @Configuration
    static class TestSpringConfig {
        @Bean
        public CharGrouper charGrouper() { return new CharGrouper(); }

        @Bean
        public StringCompressor stringCompressor() { return new StringCompressor(); }

        @Bean
        public ArraySorter arraySorter() { return new ArraySorter(); }
    }

    @Autowired
    private CharGrouper charGrouper;

    /*****************
        CORNER CASES
     *****************/

    /**
     * Tests null input.
     */
    @Test(expected=NullPointerException.class)
    public void testNullInput() {
        // given
        String input = null;

        // when
        charGrouper.group(input);
    }

    /**
     * Tests empty input.
     */
    @Test
    public void testEmptyInput() {
        // given
        String input = "";

        // when
        String output = charGrouper.group(input);

        // then
        assertEquals("", output);
    }

    /**
     * Tests single element in input.
     */
    @Test
    public void testSingleInput() {
        // given
        String input = "a";

        // when
        String output = charGrouper.group(input);

        // then
        assertEquals("a", output);
    }

    /********************
        FUNCTIONAL TESTS
     ********************/

    /**
     * Tests functionality for alphabetical input.
     */
    @Test
    public void testGrouperAlphabetical() {
        // given
        String input = "abzuaaissna";

        // when
        String output = charGrouper.group(input);

        // then
        assertEquals("a4bins2uz", output);
    }

    /**
     * Tests functionality for numerical input.
     */
    @Test
    public void testGrouperNumerical() {
        // given
        String input = "112223";

        // when
        String output = charGrouper.group(input);

        // then
        assertEquals("12233", output);
    }

    /**
     * Tests functionality for alphanumerical input.
     */
    @Test
    public void testMixedInput() {
        // given
        String input = "aaa111";

        // when
        String output = charGrouper.group(input);

        //then
        assertEquals("13a3", output);
    }

    /**
     * Tests functionality for numerical input with negative sign.
     */
    @Test
    public void testNegativeInput() {
        // given
        String input = "111-2-2";

        // when
        String output = charGrouper.group(input);

        // then
        assertEquals("-21322", output);
    }

    /**
     * Tests functionality for alphanumerical input with negative sign.
     */
    @Test
    public void testMixedNegativeInput() {
        // given
        String input = "aaa111-3-3";

        // when
        String output = charGrouper.group(input);

        // then
        assertEquals("-21332a3", output);
    }
}
