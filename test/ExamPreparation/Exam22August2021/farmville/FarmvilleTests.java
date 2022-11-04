package ExamPreparation.Exam22August2021.farmville;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class FarmvilleTests {
    private Farm farm;
    private List<Animal> animals;

    @Before
    public void setUp() {
        farm = new Farm("SoftUni", 10);
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
}
