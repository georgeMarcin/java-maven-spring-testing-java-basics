package org.mbiczak.test;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Simple test for generic add method.
 */
public class BonusTaskA {
    /**
     * Test passes both String and Integer object to generic method add.
     */
    @Test
    public void test() {
        // given
        String s = "1";
        Integer i = 1;

        // when
        s = add(s, 1);
        // then
        assertEquals("2", s);

        // when
        s = add(s, 5);
        // then
        assertEquals("7", s);

        // when
        i = add(i, 2);
        // then
        assertEquals((Integer) 3, i);

        // when
        i = add(i, 1);
        // then
        assertEquals((Integer) 4, i);
    }

    /**
     * Generic method that works for String and Integer as input. Adds add value to input.
     * In case of String object as input, object is transformed to Integer, add operation is performed
     * and result is returned as String.
     *
     * @param input object to be modified, either String or Integer.
     * @param add value to be added to input object.
     * @param <T> generic object type.
     * @return result of the operation.
     */
    private <T> T add(T input, int add) {
        Integer result = Integer.parseInt(input.toString()) + add;
        if(input instanceof String) {
            return (T)result.toString();
        }

        return (T)result;
    }
}
