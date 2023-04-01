package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LogTest {
    private Log logTest;

    Category one = new Category("one", 0);
    Category two = new Category("two", 0);
    Category three = new Category("three", 0);

    @BeforeEach
    void runBefore() {
        logTest = new Log();
    }

    @Test
    void testLogConstructor() {
        assertEquals(1, logTest.getID());
        assertEquals(0, logTest.getLength());
        Log logTest2 = new Log();
        assertEquals(2, logTest2.getID());
    }

    @Test
    void testGet() {
        logTest.add(one);
        logTest.add(two);
        logTest.add(three);
        assertEquals(two, logTest.get(1));
        assertEquals(three, logTest.get(2));
    }

    @Test
    void testAdd() {
        assertEquals(0 ,logTest.getLength());
        logTest.add(one);
        assertEquals(1 ,logTest.getLength());
    }

    @Test
    void testRemoveLast() {
        logTest.add(one);
        logTest.add(two);
        assertEquals(2 ,logTest.getLength());
        logTest.removeLast();
        assertEquals(1 ,logTest.getLength());
    }

//    @Test
//    void testDisplayCurrentLog() {
//        logTest.add(one);
//        assertEquals(System.out.println("one"), logTest.displayCurrentLog());
//    }


}
