package com.luv2code.junitdemo.test;

import com.luv2code.junitdemo.DemoUtils;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;

import java.time.Duration;
import java.util.List;

@DisplayNameGeneration(DisplayNameGenerator.Simple.class)
@TestMethodOrder(MethodOrderer.Random.class)
public class DemoUtilsTest {

    DemoUtils demoUtils;


    @BeforeEach
    void beforeEach(){
        System.out.println("@BeforeEach executes before the execution of each test method");
        demoUtils = new DemoUtils();
    }

    @Test
    public void testMultiply(){
        assertEquals(12, demoUtils.multiply(3,4));
    }

    @Test
    public void testIfIsEqualsAndNotEquals(){
        assertEquals(6, demoUtils.add(2,4));
        assertNotEquals(8, demoUtils.add(2,4));
    }

    @Test
    public void testIfIsNullAndNotNull(){
        assertNull(demoUtils.checkNull(null));
        assertNotNull(demoUtils.checkNull("luvCode"));
    }

    @Test
    public void testIfIsSameOrNotSame(){
        String str = "Love2Code";
        assertSame(demoUtils.getAcademy(), demoUtils.getAcademyDuplicate(), "Must be the same");
        assertNotSame(str, demoUtils.getAcademy(), "Must not be the same");
    }

    @Test
    public void testIfIsTrueOrIsFalse(){
        int n1 = 10;
        int n2 = 5;
        assertTrue(demoUtils.isGreater(n1,n2));
        assertFalse(demoUtils.isGreater(n2,n1));

    }

    @Test
    public void testIfArraysAreEquals(){
        String[] array = {"A", "B", "C"};
        assertArrayEquals(array, demoUtils.getFirstThreeLettersOfAlphabet(), "arrays should be equal");
    }

    @Test
    public void testIfListAreEquals(){
        List<String> listTest = List.of("luv", "2", "code");
        assertIterableEquals(listTest, demoUtils.getAcademyInList(), "list should be equal");
    }

    @Test
    public void testIfLinesMatch(){
        List<String> listTest = List.of("luv", "2", "code");
        assertLinesMatch(listTest, demoUtils.getAcademyInList(), "lists should match");
    }

    @Test
    public void testIfMethodThrowsOrNotThrowsException(){
        assertThrows(Exception.class, () -> {demoUtils.throwException(-10);});
        assertDoesNotThrow(() -> demoUtils.throwException(10));
    }

    @Test
    public void testTimeOut(){
        assertTimeoutPreemptively(Duration.ofSeconds(3), () -> {demoUtils.checkTimeout();});
    }







/*    @AfterEach
    void afterEach(){
        System.out.println("@AfterEach executes after the execution of each test method ");
    }

    @BeforeAll
    static void beforeAll(){
        System.out.println("@BeforeAll executes only once before start the tests");
    }

    @AfterAll
    static void afterAll(){
        System.out.println("@AfterAll executes only once after finish the tests");
    }*/


}
