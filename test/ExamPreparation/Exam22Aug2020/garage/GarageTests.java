package ExamPreparation.Exam22Aug2020.garage;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;

public class GarageTests {
    private Garage garage;
    private List<Car> cars;

    @Before
    public void setUp() {
        garage = new Garage();
        this.cars = new ArrayList<>();
        cars.add(new Car("brand1", 10, 10));
        cars.add(new Car("brand1", 100, 100));
        cars.add(new Car("brand2", 100, 100));
        cars.add(new Car("brand2", 1000, 1000));
        cars.add(new Car("brand2", 1000, 10000));
    }

    @Test
    public void test_constructor() {
        assertEquals(garage.getCount(), 0);
    }

    @Test
    public void test_addCar() {
        assertEquals(garage.getCount(), 0);
        garage.addCar(cars.get(0));
        assertEquals(garage.getCount(), 1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_addCarShouldThrowWithNull() {
        garage.addCar(null);
    }

    @Test
    public void test_getCars() {
        assertEquals(garage.getCount(), 0);
        garage.addCar(cars.get(0));
        garage.addCar(cars.get(1));
        garage.addCar(cars.get(2));
        assertEquals(garage.getCars().size(), 3);
    }

    @Test
    public void test_findAllCarsWithMaxSpeedAbove() {
        int speed = 100;
        List<Car> expected = this.cars.stream().filter(c -> c.getMaxSpeed() > speed).collect(Collectors.toList());
        garage.addCar(cars.get(0));
        garage.addCar(cars.get(1));
        garage.addCar(cars.get(2));
        garage.addCar(cars.get(3));
        garage.addCar(cars.get(4));

        List<Car> actual = garage.findAllCarsWithMaxSpeedAbove(speed);

        assertEquals(expected, actual);
    }

    @Test
    public void test_getTheMostExpensiveCar() {
        Car expected = cars.stream().sorted((c1, c2) -> Double.compare(c2.getPrice(), c1.getPrice()))
                .limit(1).findFirst().orElse(null);

        garage.addCar(cars.get(0));
        garage.addCar(cars.get(1));
        garage.addCar(cars.get(2));
        garage.addCar(cars.get(3));
        garage.addCar(cars.get(4));

        Car actual = garage.getTheMostExpensiveCar();

        assertEquals(expected, actual);
    }

    @Test
    public void test_findAllCarsByBrand() {
        String brand = "brand2";
        List<Car> expected = cars.stream().filter(c -> c.getBrand().equals(brand)).collect(Collectors.toList());

        garage.addCar(cars.get(0));
        garage.addCar(cars.get(1));
        garage.addCar(cars.get(2));
        garage.addCar(cars.get(3));
        garage.addCar(cars.get(4));

        List<Car> actual = garage.findAllCarsByBrand(brand);

        assertEquals(expected, actual);
    }
}