package ExamPreparation.Exam10April2021.aquarium;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class AquariumTests {
    private Aquarium aquarium;
    private List<Fish> fishList;

    @Before
    public void setUp() {
        aquarium = new Aquarium("name", 3);
        fishList = new ArrayList<>();
        fishList.add(new Fish("name1"));
        fishList.add(new Fish("name2"));
        fishList.add(new Fish("name3"));
        fishList.add(new Fish("name4"));
        fishList.add(new Fish("name5"));
    }

    @Test
    public void test_constructor() {
        int capacity = 3;
        String name = "name";
        Aquarium aquarium1 = new Aquarium(name, capacity);
        assertEquals(capacity, aquarium1.getCapacity());
        assertEquals(name, aquarium1.getName());
        assertEquals(0, aquarium1.getCount());
        aquarium1.add(fishList.get(0));
        assertEquals(1, aquarium1.getCount());
    }

    @Test(expected = NullPointerException.class)
    public void test_constructorShouldThrowWithNullName() {
        new Aquarium(null, 3);
    }

    @Test(expected = NullPointerException.class)
    public void test_constructorShouldThrowWithEmptyName() {
        new Aquarium("  ", 3);
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_constructorShouldThrowWithNegativeCapacity() {
        new Aquarium("name", -3);
    }

    @Test
    public void test_addFish() {
        aquarium.add(fishList.get(0));
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_addFishShouldThrowWhenExceededCapacity() {
        aquarium.add(fishList.get(0));
        aquarium.add(fishList.get(1));
        aquarium.add(fishList.get(2));
        aquarium.add(fishList.get(3));
    }

    @Test
    public void test_removeFish() {
        aquarium.add(fishList.get(0));
        assertEquals(1, aquarium.getCount());
        aquarium.remove(fishList.get(0).getName());
        assertEquals(0, aquarium.getCount());

    }

    @Test(expected = IllegalArgumentException.class)
    public void test_removeFishShouldThrowWithNonExistingFish() {
        aquarium.add(fishList.get(0));
        aquarium.remove(fishList.get(1).getName());
    }

    @Test
    public void test_sellFish() {
        Fish expected = fishList.get(0);
        aquarium.add(expected);
        assertEquals(1, aquarium.getCount());
        Fish actual = aquarium.sellFish(expected.getName());
        assertEquals(1, aquarium.getCount());
        assertEquals(expected, actual);
        assertFalse(actual.isAvailable());
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_sellFishShouldThrowWithNonExistingFish() {
        aquarium.add(fishList.get(0));
        aquarium.sellFish(fishList.get(1).getName());
    }

    @Test
    public void test_report() {
        String expected = String.format(String.format("Fish available at %s: %s", this.aquarium.getName(),
                this.fishList.stream().map(Fish::getName).limit(3).collect(Collectors.joining(", "))));

        aquarium.add(fishList.get(0));
        aquarium.add(fishList.get(1));
        aquarium.add(fishList.get(2));

        String actual = aquarium.report();

        assertEquals(expected, actual);
    }
}

