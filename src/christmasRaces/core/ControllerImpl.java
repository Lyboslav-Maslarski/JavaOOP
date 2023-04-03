package christmasRaces.core;

import christmasRaces.core.interfaces.Controller;
import christmasRaces.entities.cars.Car;
import christmasRaces.entities.drivers.Driver;
import christmasRaces.entities.drivers.DriverImpl;
import christmasRaces.entities.races.Race;
import christmasRaces.repositories.interfaces.Repository;

import static christmasRaces.common.ExceptionMessages.DRIVER_EXISTS;
import static christmasRaces.common.OutputMessages.DRIVER_CREATED;

public class ControllerImpl implements Controller {
    private final Repository<Driver> driverRepository;
    private final Repository<Car> carRepository;
    private final Repository<Race> raceRepository;

    public ControllerImpl(Repository<Driver> driverRepository, Repository<Car> carRepository, Repository<Race> raceRepository) {
        this.driverRepository = driverRepository;
        this.carRepository = carRepository;
        this.raceRepository = raceRepository;
    }

    @Override
    public String createDriver(String driver) {
        Driver driver1 = driverRepository.getByName(driver);
        if (driver1 != null) {
            throw new IllegalArgumentException(String.format(DRIVER_EXISTS, driver));
        }
        driver1 = new DriverImpl(driver);

        driverRepository.add(driver1);

        return String.format(DRIVER_CREATED, driver);
    }

    @Override
    public String createCar(String type, String model, int horsePower) {
        return null;
    }

    @Override
    public String addCarToDriver(String driverName, String carModel) {
        return null;
    }

    @Override
    public String addDriverToRace(String raceName, String driverName) {
        return null;
    }

    @Override
    public String startRace(String raceName) {
        return null;
    }

    @Override
    public String createRace(String name, int laps) {
        return null;
    }
}
