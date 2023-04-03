package ExamPreparation.Exam20December2021.garage;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class GarageTests {
    private Garage garage;
    private List<Car> cars;

    @Before
    public void setup() {
        this.garage = new Garage();
        this.cars = new ArrayList<>();
        this.cars.add(new Car("brand1", 1, 1));
        this.cars.add(new Car("brand1", 10, 10));
        this.cars.add(new Car("brand2", 100, 100));
        this.cars.add(new Car("brand2", 100, 100));
        this.cars.add(new Car("brand2", 100, 100));
    }

    @Test
    public void test_addCar() {
        assertEquals(0, garage.getCount());
        Car expected = cars.get(0);
        garage.addCar(expected);
        assertEquals(1, garage.getCount());
        Car actual = garage.getCars().get(0);
        assertEquals(expected.getBrand(), actual.getBrand());
        assertEquals(expected.getMaxSpeed(), actual.getMaxSpeed());
        assertEquals(expected.getPrice(), actual.getPrice(), 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_addCarWithNullShouldThrow() {
        garage.addCar(null);
    }

    @Test
    public void test_findAllCarsWithMaxSpeedAbove() {
        int speed = 10;
        List<Car> expected = cars.stream().filter(c -> c.getMaxSpeed() > speed).collect(Collectors.toList());
        this.garage.addCar(cars.get(0));
        this.garage.addCar(cars.get(1));
        this.garage.addCar(cars.get(2));
        this.garage.addCar(cars.get(3));
        this.garage.addCar(cars.get(4));

        List<Car> actual = garage.findAllCarsWithMaxSpeedAbove(speed);
        assertEquals(expected.size(), actual.size());

        for (int i = 0; i < expected.size(); i++) {
            Car expectedCar = expected.get(i);
            Car actualCar = actual.get(i);
            assertEquals(expectedCar.getBrand(), actualCar.getBrand());
            assertEquals(expectedCar.getMaxSpeed(), actualCar.getMaxSpeed());
            assertEquals(expectedCar.getPrice(), actualCar.getPrice(), 0);
        }
    }

    @Test
    public void test_getTheMostExpensiveCar() {
        Car first = garage.getTheMostExpensiveCar();
        assertNull(first);
        this.garage.addCar(cars.get(0));
        this.garage.addCar(cars.get(1));
        this.garage.addCar(cars.get(2));
        this.garage.addCar(cars.get(3));
        this.garage.addCar(cars.get(4));
        Car expected = cars.stream().sorted((c1, c2) -> Double.compare(c2.getPrice(), c1.getPrice()))
                .limit(1).findFirst().get();

        Car actual = garage.getTheMostExpensiveCar();

        assertEquals(expected.getBrand(), actual.getBrand());
        assertEquals(expected.getMaxSpeed(), actual.getMaxSpeed());
        assertEquals(expected.getPrice(), actual.getPrice(), 0);
    }

    @Test
    public void test_findAllCarsByBrand() {
        String brand = "brand1";
        List<Car> expected = cars.stream().filter(c -> c.getBrand().equals(brand)).collect(Collectors.toList());
        this.garage.addCar(cars.get(0));
        this.garage.addCar(cars.get(1));
        this.garage.addCar(cars.get(2));
        this.garage.addCar(cars.get(3));
        this.garage.addCar(cars.get(4));

        List<Car> actual = garage.findAllCarsByBrand(brand);
        assertEquals(expected.size(), actual.size());

        for (int i = 0; i < expected.size(); i++) {
            Car expectedCar = expected.get(i);
            Car actualCar = actual.get(i);
            assertEquals(expectedCar.getBrand(), actualCar.getBrand());
            assertEquals(expectedCar.getMaxSpeed(), actualCar.getMaxSpeed());
            assertEquals(expectedCar.getPrice(), actualCar.getPrice(), 0);
        }
    }
}