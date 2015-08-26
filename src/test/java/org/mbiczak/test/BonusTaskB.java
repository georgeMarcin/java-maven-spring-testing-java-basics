package org.mbiczak.test;


import org.junit.Test;
import org.mbiczak.TestClass;

import java.lang.reflect.Field;

import static org.junit.Assert.assertEquals;

/**
 * Proof of concept that nearly any object can be modified by external code, unless SecurityManager is used.
 */
public class BonusTaskB {
    @Test
    public void testChangeFooBar() throws NoSuchFieldException, IllegalAccessException {
        // given
        TestClass testClass = new TestClass();
        assertEquals("test", testClass.getFoobar());

        // when
        Field foobar = TestClass.class.getDeclaredField("foobar");
        foobar.setAccessible(true);
        foobar.set(testClass, "SUCCESS");

        // then
        assertEquals("SUCCESS", testClass.getFoobar());
    }
}
