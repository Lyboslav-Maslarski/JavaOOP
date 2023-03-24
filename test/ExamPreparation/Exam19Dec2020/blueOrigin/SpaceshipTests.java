package ExamPreparation.Exam19Dec2020.blueOrigin;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class SpaceshipTests {
    private Spaceship spaceship;
    private List<Astronaut> astronauts;

    @Before
    public void setUp() {
        spaceship = new Spaceship("name", 3);
        astronauts = new ArrayList<>();
        astronauts.add(new Astronaut("name1", 10));
        astronauts.add(new Astronaut("name2", 10));
        astronauts.add(new Astronaut("name3", 10));
        astronauts.add(new Astronaut("name4", 10));
        astronauts.add(new Astronaut("name5", 10));
    }

    @Test
    public void test_constructor() {
        String name = "name";
        Spaceship spaceship1 = new Spaceship(name, 3);
        assertEquals(name,spaceship1.getName());
    }

    @Test(expected = NullPointerException.class)
    public void test_constructorShouldThrowWithNullName() {
        new Spaceship(null, 3);
    }

    @Test(expected = NullPointerException.class)
    public void test_constructorShouldThrowWithEmptyNameName() {
        new Spaceship("  ", 3);
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_constructorShouldThrowWithNegativeCapacity() {
        new Spaceship("name", -3);
    }

    @Test
    public void test_add() {
        assertEquals(0,spaceship.getCount());
        assertEquals(3,spaceship.getCapacity());
        spaceship.add(astronauts.get(0));
        assertEquals(1,spaceship.getCount());
        spaceship.add(astronauts.get(1));
        assertEquals(2, spaceship.getCount());
    }
    @Test(expected = IllegalArgumentException.class)
    public void test_addShouldThrowWithExistingAstronaut() {
        spaceship.add(astronauts.get(0));
        spaceship.add(astronauts.get(0));
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_addShouldThrowIfNoMoreCapacity() {
        spaceship.add(astronauts.get(0));
        spaceship.add(astronauts.get(1));
        spaceship.add(astronauts.get(2));
        spaceship.add(astronauts.get(3));
    }
    @Test
    public void test_remove() {
        assertEquals(0,spaceship.getCount());
        spaceship.add(astronauts.get(0));
        assertEquals(1,spaceship.getCount());
        spaceship.remove(astronauts.get(0).getName());
        assertEquals(0, spaceship.getCount());
    }
}
