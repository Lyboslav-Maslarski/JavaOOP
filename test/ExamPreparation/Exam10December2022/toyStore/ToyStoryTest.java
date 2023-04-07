package ExamPreparation.Exam10December2022.toyStore;

import org.junit.Before;
import org.junit.Test;

import javax.naming.OperationNotSupportedException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

public class ToyStoryTest {
    private ToyStore toyStore;
    private List<Toy> toys;

    @Before
    public void setup() {
        toyStore = new ToyStore();
        toys = new ArrayList<>();
        toys.add(new Toy("manufacturer1", "id1"));
        toys.add(new Toy("manufacturer1", "id2"));
        toys.add(new Toy("manufacturer2", "id3"));
        toys.add(new Toy("manufacturer2", "id4"));
        toys.add(new Toy("manufacturer2", "id5"));
    }

    @Test
    public void test_getToyShelf() {
        Map<String, Toy> toyShelf = toyStore.getToyShelf();
        assertEquals(7,toyShelf.size());
        for (Toy toy : toyShelf.values()) {
            assertNull(toy);
        }
    }

    @Test
    public void test_addToy() throws OperationNotSupportedException {
        Toy toy = toys.get(0);
        String expected = String.format("Toy:%s placed successfully!", toy.getToyId());

        String shelf = "A";
        String actual = toyStore.addToy(shelf, toy);
        assertEquals(toy.getManufacturer(),toyStore.getToyShelf().get(shelf).getManufacturer());
        assertEquals(toy.getToyId(),toyStore.getToyShelf().get(shelf).getToyId());
        assertEquals(expected,actual);
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_addToyWithNonExistingShelf() throws OperationNotSupportedException {
        toyStore.addToy("non-existing",toys.get(0));
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_addToyWithOccupiedShelf() throws OperationNotSupportedException {
        String shelf = "A";
        toyStore.addToy(shelf,toys.get(0));
        toyStore.addToy(shelf,toys.get(1));
    }

    @Test(expected = OperationNotSupportedException.class)
    public void test_addToyWithExistingToy() throws OperationNotSupportedException {
        toyStore.addToy("A",toys.get(0));
        toyStore.addToy("B",toys.get(0));
    }

    @Test
    public void test_removeToy() throws OperationNotSupportedException {
        String shelf = "A";
        assertNull(toyStore.getToyShelf().get(shelf));

        Toy toy = toys.get(0);
        String expected = String.format("Remove toy:%s successfully!", toy.getToyId());

        toyStore.addToy(shelf, toy);

        assertNotNull(toyStore.getToyShelf().get(shelf));
        String actual = toyStore.removeToy(shelf, toy);

        assertNull(toyStore.getToyShelf().get(shelf));
        assertEquals(expected,actual);
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_removeToyWithNonExistingShelf() {
        toyStore.removeToy("non-existing",toys.get(0));
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_removeToyWithDifferentToy() throws OperationNotSupportedException {
        String shelf = "A";
        toyStore.addToy(shelf,toys.get(0));
        toyStore.removeToy(shelf,toys.get(1));
    }
}