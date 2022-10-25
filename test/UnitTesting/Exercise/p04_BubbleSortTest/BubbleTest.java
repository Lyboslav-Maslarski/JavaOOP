package UnitTesting.Exercise.p04_BubbleSortTest;

import org.junit.Test;

import static org.junit.Assert.*;

public class BubbleTest {
    @Test
    public void test_Sort() {
        int[] number = new int[]{1, 5, -3, 100, 13, -5};
        Bubble.sort(number);
        int[] sortedNumbers = new int[]{-5, -3, 1, 5, 13, 100};
        assertArrayEquals(number, sortedNumbers);
    }
}