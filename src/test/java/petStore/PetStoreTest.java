package petStore;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

public class PetStoreTest {
    private PetStore petStore;
    private List<Animal> animals;

    @Before
    public void setUp() {
        petStore = new PetStore();
        animals = new ArrayList<>();
        animals.add(new Animal("dog", 30, 150.00));
        animals.add(new Animal("cat", 10, 244.00));
        animals.add(new Animal("cat", 11, 524.00));
    }

    @Test(expected = UnsupportedOperationException.class)
    public void test_GetAnimalsShouldReturnUnmodifiableList() {
        List<Animal> animals = petStore.getAnimals();
        animals.remove(1);
    }

    @Test
    public void test_GetCount() {
        assertEquals(0, petStore.getCount());
        petStore.addAnimal(animals.get(0));
        assertEquals(1, petStore.getCount());
    }

    @Test
    public void test_FindAllAnimalsWithMaxKilogramsReturnOnlyTheSearchedAnimals() {
        petStore.addAnimal(animals.get(0));
        petStore.addAnimal(animals.get(1));
        petStore.addAnimal(animals.get(2));
        List<Animal> expected1 = animals.stream().filter(animal -> animal.getMaxKilograms() > 50).collect(Collectors.toList());
        List<Animal> expected2 = animals.stream().filter(animal -> animal.getMaxKilograms() > 10).collect(Collectors.toList());
        List<Animal> expected3 = animals.stream().filter(animal -> animal.getMaxKilograms() > 5).collect(Collectors.toList());
        List<Animal> allAnimalsWithMaxKilogramsMoreThan50 = petStore.findAllAnimalsWithMaxKilograms(50);
        List<Animal> allAnimalsWithMaxKilogramsMoreThan10 = petStore.findAllAnimalsWithMaxKilograms(10);
        List<Animal> allAnimalsWithMaxKilogramsMoreThan5 = petStore.findAllAnimalsWithMaxKilograms(5);
        assertTrue(allAnimalsWithMaxKilogramsMoreThan50.isEmpty());
        assertEquals(expected1, allAnimalsWithMaxKilogramsMoreThan50);
        assertEquals(expected2, allAnimalsWithMaxKilogramsMoreThan10);
        assertEquals(expected3, allAnimalsWithMaxKilogramsMoreThan5);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddAnimalWithNullShouldThrow() {
        petStore.addAnimal(null);
    }

    @Test
    public void test_GetTheMostExpensiveAnimalShouldReturnNullWhenPetStoreIsEmpty() {
        Animal theMostExpensiveAnimal = petStore.getTheMostExpensiveAnimal();
        assertNull(theMostExpensiveAnimal);
    }

    @Test
    public void test_GetTheMostExpensiveAnimalShouldReturnTheMostExpansiveAnimal() {
        petStore.addAnimal(animals.get(0));
        petStore.addAnimal(animals.get(1));
        petStore.addAnimal(animals.get(2));
        Animal expected = animals.stream()
                .sorted((c1, c2) -> Double.compare(c2.getPrice(), c1.getPrice()))
                .limit(1)
                .findFirst()
                .get();
        Animal theMostExpensiveAnimal = petStore.getTheMostExpensiveAnimal();
        assertEquals(expected.getPrice(), theMostExpensiveAnimal.getPrice(), 0);
    }

    @Test
    public void test_FindAllAnimalBySpecieReturnEmptyListWhenNoSuchSpeciePresent() {
        petStore.addAnimal(animals.get(0));
        petStore.addAnimal(animals.get(1));
        petStore.addAnimal(animals.get(2));
        List<Animal> expected = animals.stream().filter(animal -> animal.getSpecie().equals("fish")).collect(Collectors.toList());
        List<Animal> actual = petStore.findAllAnimalBySpecie("fish");
        assertEquals(expected, actual);
    }

    @Test
    public void test_FindAllAnimalBySpecieReturnOnlyAnimalsWithTheCorrectSpecie() {
        petStore.addAnimal(animals.get(0));
        petStore.addAnimal(animals.get(1));
        petStore.addAnimal(animals.get(2));
        List<Animal> expected = animals.stream().filter(animal -> animal.getSpecie().equals("cat")).collect(Collectors.toList());
        List<Animal> actual = petStore.findAllAnimalBySpecie("cat");
        assertEquals(expected, actual);
    }
}