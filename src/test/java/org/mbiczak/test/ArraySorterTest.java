package org.mbiczak.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mbiczak.ArraySorter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertArrayEquals;

/**
 * Tests functionality and corner cases for the org.mbiczak.ArraySorter class.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ArraySorterTest.TestSpringConfig.class)
public class ArraySorterTest {
    @Configuration
    static class TestSpringConfig {
        @Bean
        public ArraySorter arraySorter() {
            return new ArraySorter();
        }
    }

    @Autowired
    private ArraySorter sorter;

    /*****************
        CORNER CASES
     *****************/

    /**
     * Tests null input.
     *
     * This test tests how the method will behave with invalid input. Since sort() method does not perform null checks,
     * it is important to test how the method will behave for such input.
     */
    @Test(expected=NullPointerException.class)
    public void testNullInput() {
        // given
        int[] test = null;

        // when
        sorter.sort(test); // result do not matter as we are testing Exception
    }

    /**
     * Tests empty input.
     *
     * This test test a "valid" input, one which does not hold any elements ("values").
     * This is a corner case test, it is important to test how the method will behave for such input,
     * as the method itself does not check this case.
     */
    @Test
    public void testEmptyInput() {
        // given
        int[] test = {};

        // when
        int[] sorted = sorter.sort(test);

        // then
        assertArrayEquals(new int[]{}, sorted);
    }

    /**
     * Tests single element in input.
     *
     * This test test a "valid" input, one which holds only one element ("value").
     * This is a corner case test, it is important to test how the method will behave for such input,
     * as the method itself does not check this case.
     */
    @Test
    public void  testSingleInput() {
        // given
        int[] test = {1};

        // when
        int[] sorted = sorter.sort(test);

        // then
        assertArrayEquals(new int[]{1}, sorted);
    }

    /**
     * Tests duplications in input.
     *
     * This test tests how the method will behave when given as part of input duplicated values/elements.
     * As the method has only sort functionality, no duplication should be removed, the whole input should be
     * sorted accordingly.
     */
    @Test
    public void testDuplicationInInput() {
        // given
        int[] test = {4, 3, 3, 2, 1, 1};

        // when
        int[] sorted = sorter.sort(test);

        // then
        assertArrayEquals(new int[]{1, 1, 2, 3, 3, 4}, sorted);
    }

    /********************
        FUNCTIONAL TESTS
     ********************/

    /**
     * Tests sort functionality.
     *
     * This test tests if the method provides correct functionality. It is a basic test, if in future
     * for whatever reasons method would fail this test, the method should be marked as "bugged".
     */
    @Test
    public void testSort() {
        // given
        int[] test = {4, 3, 2, 1};

        // when
        int[] sorted = sorter.sort(test);

        // then
        assertArrayEquals(new int[]{1, 2, 3, 4}, sorted);
    }

    /**
     * Tests already sorted input.
     *
     * This test tests how the method will behave when receiving correct input, one which is already sorted.
     * Upon receiving already sorted input, method should not change the input.
     */
    @Test
    public void testSortedInput() {
        // given
        int[] test = {1, 2, 3, 4};

        // when
        int[] sorted = sorter.sort(test);

        // then
        assertArrayEquals(new int[]{1, 2, 3, 4}, sorted);
    }

    /**
     * Tests single negative in input.
     *
     * This test tests the behaviour of the method for both positive and negative integers in input.
     * Tests if the method correctly distincts positive from negative integers.
     */
    @Test
    public void testSingleNegativeInput() {
        // given
        int[] test = {1, 2, -3, 4};

        // when
        int[] sorted = sorter.sort(test);

        // then
        assertArrayEquals(new int[]{-3, 1, 2, 4}, sorted);
    }
}
