package org.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.fail;

public class TestAssertionsSample {

    @Test
    void sampleTest() {
        Assertions.assertEquals(1, 1);
    }

    @Test
    void sampleTest2() {
        Assertions.assertNotEquals(1, 2);
        String s = null;
        Assertions.assertNull(s);
        s = "name";
        Assertions.assertNotNull(s);
    }

    /**
     * fail()
     *
     * public static void fail()
     * public static void fail(String message)
     * public static void fail(Supplier<String> messageSupplier)
     * public static void fail(Throwable throwable)
     * public static void fail(String message, Throwable throwable)
     */

    @Test
    public void testActualExceptionThrown() {
        try {
            methodThatShouldThrowException();
            fail("Exception not thrown !!!");
        } catch (UnsupportedOperationException e) {
            // test case passed
        }
    }

    private void methodThatShouldThrowException() {
        throw new UnsupportedOperationException();
    }

    @Test
    public void testAssertTrueWithFalseConditionAndMessageSupplier() {
        boolean falseValue = false;
        // Assertions.assertTrue(falseValue, () -> "The actual value is false bruhh");
        Assertions.assertFalse(false);
    }

    // assertSame, assertNotSame

    @Test
    public void testAssertSameWithDifferentObjectAndMessageSupplier() {
        String actual = "hello";
        String expected = "hello";
        Assertions.assertSame(expected, actual, () -> "The actual value is not same as expected value");
    }

    // assertArrayEquals
    @Test
    public void testAssertArrayEqualsForEqualArraysWithDifferentOrder() {
        int[] expected = {1,2,4,3};
        int[] actual = {1,2,4,3};
        Assertions.assertArrayEquals(expected, actual, () -> "Arrays order is different");
    }

    // assertIterabeEquals
    @Test
    public void testAssertIterableEqualsForEqualIterablesWithDifferentOrder() {
        Iterable<Integer> expected = new ArrayList<>(Arrays.asList(1,2,3,4));
        Iterable<Integer> actual = new ArrayList<>(Arrays.asList(1,2,3,4));
        List<Integer> sampleList = new ArrayList<>();
        sampleList.add(1); sampleList.add(2); sampleList.add(3); sampleList.add(4);
        Assertions.assertIterableEquals(sampleList, actual, () -> "Iterables order is different");
    }

    // assertThrows

    @Test
    public void testAssertThrows() {
        Assertions.assertThrows(ArithmeticException.class, () -> divide(1, 0));
    }

    @Test
    public void testAssertThrowsWithMessageSupplier() {
        Assertions.assertThrows(IOException.class, () -> divide(1, 0), () -> "Division by zero !!!");
    }

    private int divide(int a, int b) {
        return a / b;
    }


    // assertTimeout

    @Test
    void timeoutNotExceededWithMethod() {
        // The following assertion invokes a method reference and returns an object.
        String actualGreeting = Assertions.assertTimeout(Duration.ofMinutes(3), () -> "Hello, World!", "timeout");
        Assertions.assertEquals("Hello, World!", actualGreeting);
    }

    @Test
    void timeoutExceeded() {
        Assertions.assertTimeout(Duration.ofMillis(10), () -> {
            // Simulate task that takes more than 10 ms.
            Thread.sleep(9);
        });

        Assertions.assertTimeoutPreemptively(Duration.ofMillis(10), () -> {
            // Simulate task that takes more than 10 ms.
            Thread.sleep(100);
        });
    }

}
