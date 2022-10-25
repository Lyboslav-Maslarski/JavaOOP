package UnitTesting.Exercise.p05_CustomLinkedList;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CustomLinkedListTest {
    private CustomLinkedList<String> customLinkedList;

    @Before
    public void setup() {
        customLinkedList = new CustomLinkedList<>();
    }

    @Test
    public void test_AddOperation() {
        customLinkedList.add("Pesho");
        assertEquals("Pesho", customLinkedList.get(0));
        customLinkedList.add("Gosho");
        assertEquals("Gosho", customLinkedList.get(1));
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_TryGetWithNegativeIndex() {
        customLinkedList.get(-1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_TryGetWithTooBigIndex() {
        customLinkedList.get(15);
    }

    @Test
    public void test_SetOperation() {
        customLinkedList.add("Pesho");
        assertEquals("Pesho", customLinkedList.get(0));
        customLinkedList.add("Tosho");
        assertEquals("Tosho", customLinkedList.get(1));
        customLinkedList.set(1, "Gosho");
        assertEquals("Gosho", customLinkedList.get(1));
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_TrySetWithNegativeIndex() {
        customLinkedList.set(-1, "Pesho");
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_TrySetWithTooBigIndex() {
        customLinkedList.set(15, "Pesho");
    }

    @Test
    public void test_RemoveAtOperationChangeTheTail() {
        customLinkedList.add("Pesho");
        assertEquals("Pesho", customLinkedList.get(0));
        customLinkedList.add("Tosho");
        assertEquals("Tosho", customLinkedList.get(1));
        String removedElementOne = customLinkedList.removeAt(1);
        String removedElementTwo = customLinkedList.removeAt(0);
        assertEquals("Tosho", removedElementOne);
        assertEquals("Pesho", removedElementTwo);
    }

    @Test
    public void test_RemoveAtOperationChangeTheHead() {
        customLinkedList.add("Pesho");
        assertEquals("Pesho", customLinkedList.get(0));
        customLinkedList.add("Tosho");
        assertEquals("Tosho", customLinkedList.get(1));
        String removedElementOne = customLinkedList.removeAt(0);
        String removedElementTwo = customLinkedList.removeAt(0);
        assertEquals("Pesho", removedElementOne);
        assertEquals("Tosho", removedElementTwo);
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_TryRemoveAtWithNegativeIndex() {
        customLinkedList.removeAt(-1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_TryRemoveAtWithTooBigIndex() {
        customLinkedList.removeAt(15);
    }

    @Test
    public void test_RemoveExistingElement() {
        customLinkedList.add("Gosho");
        customLinkedList.add("Pesho");
        int index = customLinkedList.remove("Pesho");
        assertEquals(1, index);
    }

    @Test
    public void test_RemoveNonExistingElement() {
        int index = customLinkedList.remove("Pesho");
        assertEquals(-1, index);
    }

    @Test
    public void test_IndexOfExistingElement() {
        customLinkedList.add("Gosho");
        customLinkedList.add("Pesho");
        int index = customLinkedList.indexOf("Pesho");
        assertEquals(1, index);
    }

    @Test
    public void test_IndexOfNonExistingElement() {
        int index = customLinkedList.indexOf("Pesho");
        assertEquals(-1, index);
    }

    @Test
    public void test_Contains() {
        customLinkedList.add("Pesho");
        assertTrue(customLinkedList.contains("Pesho"));
        assertFalse(customLinkedList.contains("Gosho"));
    }
}