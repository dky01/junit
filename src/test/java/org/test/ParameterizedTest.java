package org.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.Stream;

public class ParameterizedTest {
    @org.junit.jupiter.params.ParameterizedTest
//    @ValueSource(ints = {1, 2, 3, 4, 5})
    @ValueSource(strings = {"1", "2"})
    // @ValueSource(doubles = {"1", "2"})
    // @ValueSource(floats = {"1", "2"})
    void testSample(int n) {
        System.out.println(doubleIt(n));
    }

    int doubleIt(int n) {
        return n * n;
    }

    // enum source
    @org.junit.jupiter.params.ParameterizedTest
    @EnumSource(Animal.class)
    void testAnimal(Animal animal) {
        Assertions.assertNotNull(animal);
    }

    // method source
    @org.junit.jupiter.params.ParameterizedTest
    @MethodSource("provideAnimals")
    void testAnimalMethodSource(String animal) {
        Assertions.assertNotNull(animal);
    }

    static Stream<String> provideAnimals() {
        return Stream.of("CAT", "DOG");
    }

    // csv source
    @org.junit.jupiter.params.ParameterizedTest
    @CsvSource({ "one, 1", "two, 2", "'foo, bar', 3" })
    void testWithCsvSource(String first, int second) {
        Assertions.assertNotNull(first);
        Assertions.assertNotEquals(0, second);
    }


    // csv file source
    @org.junit.jupiter.params.ParameterizedTest
    @CsvFileSource(resources = "/capitals.csv", numLinesToSkip = 1)
    void testWithCsvFileSource(String country, String capital) {
        Assertions.assertNotNull(country);
        Assertions.assertNotNull(capital);
    }
}
