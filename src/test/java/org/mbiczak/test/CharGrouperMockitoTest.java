package org.mbiczak.test;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mbiczak.ArraySorter;
import org.mbiczak.CharGrouper;
import org.mbiczak.StringCompressor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import static org.hamcrest.core.IsNot.not;
import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;

/*
    Test proper execution of CharGrouper class for given test scenario:
     - return "SUCCESS" for input "abcdef"
     - return "" for any other input
 */
@RunWith(MockitoJUnitRunner.class)
public class CharGrouperMockitoTest {
    // exception input
    private final static String EXCEPTION_TEST_STRING = "abcdef";
    private final static int[] EXCEPTION_TEST_STRING_AS_INT_ARRAY = CharGrouper.castStringToIntArray(EXCEPTION_TEST_STRING);
    private final static String EXPECTED_EXCEPTION_TEST_STRING_RESULT = "SUCCESS";
    private final static int[] EXPECTED_EXCEPTION_TEST_STRING_RESULT_AS_INT_ARRAY = CharGrouper.castStringToIntArray(EXPECTED_EXCEPTION_TEST_STRING_RESULT);

    // expected result for any input
    private final static String EXPECTED_RESULT = "";
    private final static int[] EXPECTED_RESULT_AS_INT_ARRAY = CharGrouper.castStringToIntArray(EXPECTED_RESULT);

    @Mock
    private ArraySorter sorter;

    @Spy
    private StringCompressor compressor;

    @InjectMocks
    private CharGrouper grouper;

    /**
     * Setup test.
     */
    @Before
    public void initMocks() {
        when(sorter.sort(EXCEPTION_TEST_STRING_AS_INT_ARRAY)).thenReturn(EXPECTED_EXCEPTION_TEST_STRING_RESULT_AS_INT_ARRAY);   // calls group method
        doReturn(EXPECTED_RESULT_AS_INT_ARRAY).when(sorter).sort(argThat(not(EXCEPTION_TEST_STRING_AS_INT_ARRAY)));   // mocks group method call
    }

    /**
     * Test exception input "abcdef"
     */
    @Test
    public void testEceptionTestString() {
        assertEquals(EXPECTED_EXCEPTION_TEST_STRING_RESULT, grouper.group(EXCEPTION_TEST_STRING));
        verify(compressor, times(1)).compress(anyString()); // verify single call of compress method
        verify(sorter, times(1)).sort(any((int[].class)));  // verify single call of sort method
    }

    /**
     * Tests other inputs.
     */
    @Test
    public void testWithSomeBogusString() {
        assertEquals("", grouper.group("foo"));
        assertEquals("", grouper.group("abc"));
        assertEquals("", grouper.group("123"));
        assertEquals("", grouper.group("abc123"));
    }
}