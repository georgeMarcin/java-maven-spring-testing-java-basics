package org.mbiczak.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mbiczak.StringCompressor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;

/**
 * Tests functionality and corner cases for the org.mbiczak.StringCompressor class.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = StringCompressorTest.TestSpringConfig.class)
public class StringCompressorTest {
    @Configuration
    static class TestSpringConfig {
        @Bean
        public StringCompressor stringCompressor() {
            return new StringCompressor();
        }
    }

    @Autowired
    private StringCompressor stringCompressor;

    /*****************
     CORNER CASES
     *****************/

    /**
     * Tests null input.
     */
    @Test(expected=NullPointerException.class)
    public void  testNullInput() {
        // given
        String uncompressed = null;

        // then
        String compressed = stringCompressor.compress(uncompressed);
    }

    /**
     * Tests empty input.
     */
    @Test
    public void testEmptyInput() {
        //given
        String uncompressed = "";

        // when
        String compressed = stringCompressor.compress(uncompressed);

        // then
        assertEquals("", compressed);
    }

    /**
     * Tests single element in input.
     */
    @Test
    public void testSingleInput() {
        // given
        String uncompressed = "a";

        // when
        String compressed = stringCompressor.compress(uncompressed);

        // then
        assertEquals("a", compressed);
    }

    /**
     * Tests corner case when compressed values has bigger or equal size to uncompressed value.
     */
    @Test
    public void testCompressWithNonOptimizedResult() {
        // given
        String uncompressed = "hello";

        // when
        String compressed = stringCompressor.compress(uncompressed);

        // then
        assertEquals(uncompressed, compressed);
    }

    /********************
     FUNCTIONAL TESTS
     ********************/

    /**
     * Simple compressions test for alphabetical input.
     */
    @Test
    public void testCompressSimpleAlphabetical() {
        // given
        String uncompressed = "aaa";

        // when
        String compressed = stringCompressor.compress(uncompressed);

        // then
        assertEquals("a3", compressed);
    }

    /**
     * Complex compression test for alphabetical input.
     */
    @Test
    public void testCompressMoreComplexAlphabetical() {
        // given
        String uncompressed = "aabbbbcaaddddddd";

        // when
        String compressed = stringCompressor.compress(uncompressed);

        // then
        assertEquals("a2b4ca2d7", compressed);
    }

    /**
     * Simple compressions test for numerical input.
     */
    @Test
    public void testCompressSimpleNumerical() {
        // given
        String uncompressed = "111";

        // when
        String compressed = stringCompressor.compress(uncompressed);

        // then
        assertEquals("13", compressed);
    }

    /**
     * Complex compression test for numerical input.
     */
    @Test
    public void testCompressMoreComplexNumerical() {
        // given
        String uncompressed = "1122223114444444";

        // when
        String compressed = stringCompressor.compress(uncompressed);

        // then
        assertEquals("122431247", compressed);
    }

    /**
     * Simple compressions test for mixed input.
     */
    @Test
    public void testCompressSimpleMixed() {
        // given
        String uncompressed = "aaa111";

        // when
        String compressed = stringCompressor.compress(uncompressed);

        // then
        assertEquals("a313", compressed);
    }

    /**
     * Complex compression test for mixed input.
     */
    @Test
    public void testCompressMoreComplexMixed() {
        // given
        String uncompressed = "aabbbbc1123333-4";

        // when
        String compressed = stringCompressor.compress(uncompressed);

        // then
        assertEquals("a2b4c12234-4", compressed);
    }
}