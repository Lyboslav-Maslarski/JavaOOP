package UnitTesting.Exercise.p03_IteratorTest;

import org.junit.Before;
import org.junit.Test;

import javax.naming.OperationNotSupportedException;

import static org.junit.Assert.*;

public class ListIteratorTest {
    private ListIterator listIterator;
    private static final String[] STRINGS = new String[]{"Pesho", "Gosho", "Tosho"};

    @Before
    public void setUp() throws OperationNotSupportedException {
        this.listIterator = new ListIterator(STRINGS);
    }

    @Test
    public void test_Constructor() throws OperationNotSupportedException {
        setUp();
    }

    @Test(expected = OperationNotSupportedException.class)
    public void test_ConstructorWithNull() throws OperationNotSupportedException {
        new ListIterator(null);
    }

    @Test
    public void test_hasNextIfThereIsNextElement() {
        assertTrue(listIterator.hasNext());
    }

    @Test
    public void test_hasNextIfThereIsNoNextElement() {
        for (int i = 0; i < STRINGS.length - 1; i++) {
            listIterator.move();
        }
        assertFalse(listIterator.hasNext());
    }

    @Test
    public void test_move() {
        for (int i = 0; i < STRINGS.length - 1; i++) {
            assertTrue(listIterator.move());
        }
        assertFalse(listIterator.move());
    }

    @Test(expected = IllegalStateException.class)
    public void test_printEmptyList() throws OperationNotSupportedException {
        new ListIterator().print();
    }

    @Test
    public void test_printOperation() {
        for (String string : STRINGS) {
            assertEquals(string, listIterator.print());
            listIterator.move();
        }
    }
}