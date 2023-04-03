package ExamPreparation.Exam11December2021.cats;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class HouseTests {
    private House house;
    private List<Cat> cats;

    @Before
    public void setup() {
        this.house = new House("name", 3);
        this.cats = new ArrayList<>();
        this.cats.add(new Cat("name1"));
        this.cats.add(new Cat("name2"));
        this.cats.add(new Cat("name3"));
        this.cats.add(new Cat("name4"));
        this.cats.add(new Cat("name5"));
    }

    @Test
    public void test_ctor() {
        String name = "name";
        int capacity = 3;
        int count = 0;
        House houseForCats = new House(name, capacity);
        assertEquals(name, houseForCats.getName());
        assertEquals(capacity, houseForCats.getCapacity());
        assertEquals(count, houseForCats.getCount());
    }

    @Test(expected = NullPointerException.class)
    public void test_ctorWithNullNameShouldThrow() {
        new House(null, 3);
    }

    @Test(expected = NullPointerException.class)
    public void test_ctorWithEmptyNameShouldThrow() {
        new House("  ", 3);
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_ctorWithNegativeCapacityShouldThrow() {
        new House("name", -3);
    }

    @Test
    public void test_addCat() {
        house.addCat(cats.get(0));
        assertEquals(1, house.getCount());
        house.addCat(cats.get(1));
        assertEquals(2, house.getCount());
        house.addCat(cats.get(2));
        assertEquals(3, house.getCount());
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_addCatOnMaxCapacityHouseShouldThrow() {
        house.addCat(cats.get(0));
        house.addCat(cats.get(1));
        house.addCat(cats.get(2));
        house.addCat(cats.get(3));
    }

    @Test
    public void test_removeCat() {
        house.addCat(cats.get(0));
        assertEquals(1, house.getCount());
        house.removeCat(cats.get(0).getName());
        assertEquals(0, house.getCount());
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_removeCatWithNonExistingCatShouldThrow() {
        house.addCat(cats.get(0));
        house.removeCat(cats.get(1).getName());
    }

    @Test
    public void test_catForSale() {
        house.addCat(cats.get(0));
        assertEquals(1, house.getCount());
        Cat cat = house.catForSale(cats.get(0).getName());
        assertEquals(1, house.getCount());
        assertFalse(cat.isHungry());
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_catForSaleWithNonExistingCatShouldThrow() {
        house.addCat(cats.get(0));
        house.catForSale(cats.get(1).getName());
    }

    @Test
    public void test_statistics() {
        String names = cats.stream().limit(3).map(Cat::getName).collect(Collectors.joining(", "));
        String expected = String.format("The cat %s is in the house %s!", names, house.getName());

        house.addCat(cats.get(0));
        house.addCat(cats.get(1));
        house.addCat(cats.get(2));

        String actual = house.statistics();

        assertEquals(expected, actual);
    }
}
