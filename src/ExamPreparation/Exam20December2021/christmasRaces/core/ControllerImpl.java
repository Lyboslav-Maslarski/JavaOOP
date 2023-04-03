package ExamPreparation.Exam20December2021.christmasRaces.core;

import ExamPreparation.Exam20December2021.christmasRaces.common.ExceptionMessages;
import ExamPreparation.Exam20December2021.christmasRaces.common.OutputMessages;
import ExamPreparation.Exam20December2021.christmasRaces.entities.cars.Car;
import ExamPreparation.Exam20December2021.christmasRaces.entities.drivers.Driver;
import ExamPreparation.Exam20December2021.christmasRaces.entities.drivers.DriverImpl;
import ExamPreparation.Exam20December2021.christmasRaces.entities.races.Race;
import ExamPreparation.Exam20December2021.christmasRaces.entities.races.RaceImpl;
import ExamPreparation.Exam20December2021.christmasRaces.repositories.interfaces.Repository;
import ExamPreparation.Exam20December2021.christmasRaces.core.interfaces.Controller;
import ExamPreparation.Exam20December2021.christmasRaces.entities.cars.MuscleCar;
import ExamPreparation.Exam20December2021.christmasRaces.entities.cars.SportsCar;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

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
            throw new IllegalArgumentException(String.format(ExceptionMessages.DRIVER_EXISTS, driver));
        }
        driver1 = new DriverImpl(driver);

        driverRepository.add(driver1);

        return String.format(OutputMessages.DRIVER_CREATED, driver);
    }

    @Override
    public String createCar(String type, String model, int horsePower) {
        Car car = carRepository.getByName(model);
        if (car != null) {
            throw new IllegalArgumentException(String.format(ExceptionMessages.CAR_EXISTS, model));
        }

        switch (type) {
            case "Muscle":
                car = new MuscleCar(model, horsePower);
                break;
            case "Sports":
                car = new SportsCar(model, horsePower);
                break;
            default:
                throw new IllegalArgumentException();
        }
        carRepository.add(car);

        return String.format(OutputMessages.CAR_CREATED, car.getClass().getSimpleName(), model);
    }

    @Override
    public String addCarToDriver(String driverName, String carModel) {
        Driver driver = driverRepository.getByName(driverName);
        if (driver == null) {
            throw new IllegalArgumentException(String.format(ExceptionMessages.DRIVER_NOT_FOUND, driverName));
        }
        Car car = carRepository.getByName(carModel);
        if (car == null) {
            throw new IllegalArgumentException(String.format(ExceptionMessages.CAR_NOT_FOUND, carModel));
        }
        driver.addCar(car);

        return String.format(OutputMessages.CAR_ADDED, driverName, carModel);
    }

    @Override
    public String addDriverToRace(String raceName, String driverName) {
        Race race = raceRepository.getByName(raceName);
        if (race == null) {
            throw new IllegalArgumentException(String.format(ExceptionMessages.RACE_NOT_FOUND, raceName));
        }

        Driver driver = driverRepository.getByName(driverName);
        if (driver == null) {
            throw new IllegalArgumentException(String.format(ExceptionMessages.DRIVER_NOT_FOUND, driverName));
        }

        race.addDriver(driver);

        return String.format(OutputMessages.DRIVER_ADDED, driverName, raceName);
    }

    @Override
    public String startRace(String raceName) {
        Race race = raceRepository.getByName(raceName);
        if (race == null) {
            throw new IllegalArgumentException(String.format(ExceptionMessages.RACE_NOT_FOUND, raceName));
        }

        Collection<Driver> drivers = race.getDrivers();
        if (drivers.size() < 3) {
            throw new IllegalArgumentException(String.format(ExceptionMessages.RACE_INVALID, raceName, 3));
        }
        List<Driver> podium = drivers.stream()
                .sorted((d1, d2) -> Double.compare(
                        d2.getCar().calculateRacePoints(race.getLaps()),
                        d1.getCar().calculateRacePoints(race.getLaps())))
                .limit(3).collect(Collectors.toList());

        return String.format(OutputMessages.DRIVER_FIRST_POSITION, podium.get(0).getName(), raceName) + System.lineSeparator() +
               String.format(OutputMessages.DRIVER_SECOND_POSITION, podium.get(1).getName(), raceName) + System.lineSeparator() +
               String.format(OutputMessages.DRIVER_THIRD_POSITION, podium.get(2).getName(), raceName);
    }

    @Override
    public String createRace(String name, int laps) {
        Race race = raceRepository.getByName(name);
        if (race != null) {
            throw new IllegalArgumentException(String.format(ExceptionMessages.RACE_EXISTS, name));
        }

        race = new RaceImpl(name, laps);
        raceRepository.add(race);

        return String.format(OutputMessages.RACE_CREATED, name);
    }
}
