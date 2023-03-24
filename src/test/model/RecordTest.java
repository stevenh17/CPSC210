package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class RecordTest {
    private Record rTest;

    Log log1 = new Log();
    Log log2 = new Log();
    Log log3 = new Log();

    @BeforeEach
    void runBefore() {
        rTest = new Record();
    }

    @Test
    void testAddLog() {
        assertEquals(0, rTest.getLength());
        rTest.addLog(log1);
        assertEquals(1, rTest.getLength());
    }

    @Test
    void testGetLog() {
        assertEquals(0, rTest.getLength());
        rTest.addLog(log1);
        assertEquals(log1, rTest.getLog(0));
        rTest.addLog(log2);
        assertEquals(log2, rTest.getLog(1));
    }

    @Test
    void testGetMostRecentLog() {
        assertEquals(0, rTest.getLength());
        rTest.addLog(log1);
        rTest.addLog(log2);
        assertEquals(log2, rTest.getMostRecentLog());
        rTest.addLog(log3);
        assertEquals(log3, rTest.getMostRecentLog());
    }

    @Test
    void testIsEmpty() {
        assertEquals(0, rTest.getLength());
        assertTrue(rTest.isEmpty());
        rTest.addLog(log1);
        assertFalse(rTest.isEmpty());
    }

    @Test
    void testGetLength() {
        assertEquals(0, rTest.getLength());
        rTest.addLog(log1);
        assertEquals(1, rTest.getLength());
        rTest.addLog(log2);
        rTest.addLog(log3);
        assertEquals(3, rTest.getLength());
    }
}
