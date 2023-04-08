package ExamPreparation.Exam08April2023.robots;

import ExamPreparation.Exam08April2023.robots.Robot;
import ExamPreparation.Exam08April2023.robots.Service;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class ServiceTests {
    public static final String NAME = "name" ;
    public static final int CAPACITY = 3;
    private Service service;
    private List<Robot> robots;

    @Before
    public void setup() {
        this.service = new Service(NAME, CAPACITY);
        this.robots = new ArrayList<>();
        this.robots.add(new Robot("name1"));
        this.robots.add(new Robot("name2"));
        this.robots.add(new Robot("name3"));
        this.robots.add(new Robot("name4"));
        this.robots.add(new Robot("name5"));
    }

    @Test
    public void test_ctor() {
        assertEquals(NAME, service.getName());
        assertEquals(CAPACITY, service.getCapacity());
    }

    @Test(expected = NullPointerException.class)
    public void test_ctorWithNullName() {
        new Service(null, CAPACITY);
    }

    @Test(expected = NullPointerException.class)
    public void test_ctorWithEmptyName() {
        new Service("  ", CAPACITY);
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_ctorWithNegativeCapacity() {
        new Service(NAME, -1);
    }

    @Test
    public void test_add() {
        assertEquals(0, service.getCount());
        service.add(robots.get(0));
        assertEquals(1, service.getCount());
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_addWhenCapacityReached() {
        for (int i = 0; i < CAPACITY + 1; i++) {
            service.add(robots.get(i));
        }
    }


    @Test
    public void test_remove() {
        assertEquals(0, service.getCount());
        service.add(robots.get(0));
        assertEquals(1, service.getCount());
        service.remove(robots.get(0).getName());
        assertEquals(0, service.getCount());
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_removeWithNonExistingName() {
        assertEquals(0, service.getCount());
        service.add(robots.get(0));
        assertEquals(1, service.getCount());
        service.remove(robots.get(1).getName());
        assertEquals(1, service.getCount());
    }

    @Test
    public void test_forSale() {
        assertEquals(0, service.getCount());
        Robot expected = robots.get(0);
        service.add(expected);
        assertEquals(1, service.getCount());
        Robot actual = service.forSale(expected.getName());
        assertEquals(expected.getName(), actual.getName());
        assertFalse(actual.isReadyForSale());
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_forSaleWithNonExistingName() {
        assertEquals(0, service.getCount());
        service.add(robots.get(0));
        assertEquals(1, service.getCount());
        service.forSale(robots.get(1).getName());
    }

    @Test
    public void test_report() {
        String names = robots.stream().limit(3).map(Robot::getName).collect(Collectors.joining(", "));

        String expected = String.format("The robot %s is in the service %s!", names, NAME);
        service.add(robots.get(0));
        service.add(robots.get(1));
        service.add(robots.get(2));
        String actual = service.report();

        assertEquals(expected, actual);
    }

}
