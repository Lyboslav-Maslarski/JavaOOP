package ExamPreparation.Exam22August2021.farmville;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class FarmvilleTests {
    private Farm farm;
    private List<Animal> animals;

    @Before
    public void setUp() {
        farm = new Farm("SoftUni", 3);
        this.animals = new ArrayList<>();
        animals.add(new Animal("chick", 1));
        animals.add(new Animal("cow", 5));
        animals.add(new Animal("dog", 10));
        animals.add(new Animal("goat", 15));
    }

    @Test
    public void test_CreateFarm() {
        Farm farm = new Farm("SoftUni", 10);
        assertEquals("SoftUni", farm.getName());
        assertEquals(10, farm.getCapacity());
        assertEquals(0, farm.getCount());
    }

    @Test(expected = NullPointerException.class)
    public void test_CreateFarmWithNullForName() {
        Farm farm = new Farm(null, 10);
    }

    @Test(expected = NullPointerException.class)
    public void test_CreateFarmWithEmptyName() {
        Farm farm = new Farm("   ", 10);
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_CreateFarmWithNegativeCapacity() {
        Farm farm = new Farm("SoftUni", -10);
    }

    @Test
    public void test_AddShouldAddAnimalToFarm() {
        assertEquals(0, farm.getCount());
        farm.add(animals.get(0));
        assertEquals(1, farm.getCount());
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_AddExistingAnimalShouldThrow() {
        farm.add(animals.get(0));
        farm.add(animals.get(0));
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_AddShouldThrowIfNoMoreCapacity() {
        farm.add(animals.get(0));
        farm.add(animals.get(1));
        farm.add(animals.get(2));
        farm.add(animals.get(3));
    }

    @Test
    public void test_Remove() {
        String type1 = animals.get(0).getType();
        String type2 = animals.get(1).getType();
        farm.add(animals.get(0));
        assertEquals(1, farm.getCount());
        assertTrue(farm.remove(type1));
        assertEquals(0, farm.getCount());
        assertFalse(farm.remove(type2));
    }
}
