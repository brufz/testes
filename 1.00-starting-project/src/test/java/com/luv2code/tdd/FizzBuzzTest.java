package com.luv2code.tdd;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class FizzBuzzTest {

    //if the number is divisible by 3 -> Fizz
    //if the number is divisible by 5 -> Buzz
    // if the number is divisible by 3 and 5 -> FizzBuzz
    // if the number is not divisible by 3 and 5 -> Print the number

    @Test
    @Order(1)
    @DisplayName("Divisible by 3")
    public void divisibleBy3(){

        String expected = "Fizz";

        assertEquals(expected, FizzBuzz.compute(3), "Should return Fizz");
    }

    @Test
    @Order(2)
    @DisplayName("Divisible by 5")
    public void divisibleBy5(){

        String expected = "Buzz";

        assertEquals(expected, FizzBuzz.compute(5), "Should return Buzz");
    }

    @Test
    @Order(3)
    @DisplayName("Divisible by 3 and 5")
    public void divisibleBy3And5(){

        String expected = "FizzBuzz";

        assertEquals(expected, FizzBuzz.compute(15), "Should return FizzBuzz");
    }

    @Test
    @Order(4)
    @DisplayName("Not Divisible by 3 and 5")
    public void notDivisibleBy3And5(){

        String expected = "1";

        assertEquals(expected, FizzBuzz.compute(1), "Should return 1");
    }

    @ParameterizedTest(name = "value={0}, expected={1}")
    @Order(5)
    @DisplayName("Testing small data file")
    @CsvFileSource(resources = "/small-test-data.csv")
    public void testSmallDataFile(int value, String expected){
        assertEquals(expected, FizzBuzz.compute(value));

    }

    @ParameterizedTest(name = "value={0}, expected={1}")
    @Order(6)
    @DisplayName("Testing medium data file")
    @CsvFileSource(resources = "/medium-test-data.csv")
    public void testMediumDataFile(int value, String expected){
        assertEquals(expected, FizzBuzz.compute(value));

    }

    @ParameterizedTest(name = "value={0}, expected={1}")
    @Order(7)
    @DisplayName("Testing medium data file")
    @CsvFileSource(resources = "/large-test-data.csv")
    public void testLargeDataFile(int value, String expected){
        assertEquals(expected, FizzBuzz.compute(value));

    }



}
