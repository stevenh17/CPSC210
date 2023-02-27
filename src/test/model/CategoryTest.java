package model;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CategoryTest {
    private Category categoryTest;
    private Log logTest;

    Category one = new Category("one", 0);
    Category two = new Category("two", 0);
    Category three = new Category("three", 0);
    Category four = new Category("four", 0);
    Category five = new Category("five", 0);

    @BeforeEach
    void runBefore() {
        categoryTest = new Category("Cooking", 0);
        logTest = new Log(one, two, three, four, five);
    }

    @Test
    void testCategoryConstructor() {
        assertEquals("Cooking", categoryTest.getName());
        assertEquals(0, categoryTest.getValue());
        Category categoryTestOne = new Category("Cooking", -5);
        assertEquals(0, categoryTestOne.getValue());

    }

    @Test
    void testSetValue() {
        assertEquals(0, categoryTest.getValue());
        assertEquals(100, categoryTest.setValue(100));
        assertEquals(500.5, categoryTest.setValue(500.5));
        assertEquals(2.14, categoryTest.setValue(2.14));
    }

    @Test
    void testSubValue() {
        assertEquals(0, categoryTest.getValue());
        assertEquals(100, categoryTest.setValue(100));
        assertEquals(50, categoryTest.subValue(50));
        assertEquals(24.5, categoryTest.subValue(25.5));
        assertEquals(10.3, categoryTest.subValue(14.2));
        assertEquals(0, categoryTest.subValue(10.3));
    }

    @Test
    void testAddValue() {
        assertEquals(0, categoryTest.getValue());
        assertEquals(50, categoryTest.addValue(50));
        assertEquals(70.5, categoryTest.addValue(20.5));
        assertEquals(100, categoryTest.addValue(29.5));
    }

    @Test
    void testLogConstructor() {
        assertEquals(1, logTest.getID());
        assertEquals(5, logTest.getLength());
    }

    @Test
    void testGet() {
        assertEquals(two, logTest.get(1));
        assertEquals(five, logTest.get(4));
    }

    //void replaceCategoryTest() {
        //assertEquals(5, logTest.getLength());
        //assertEquals(5, logTest.replaceCategory(one, two).size());
        //assertEquals(two ,logTest.get(0));
    //}
}