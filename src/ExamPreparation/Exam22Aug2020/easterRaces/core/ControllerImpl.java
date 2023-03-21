package ExamPreparation.Exam22Aug2020.easterRaces.core;

import ExamPreparation.Exam22Aug2020.easterRaces.entities.cars.Car;
import ExamPreparation.Exam22Aug2020.easterRaces.entities.cars.SportsCar;
import ExamPreparation.Exam22Aug2020.easterRaces.entities.drivers.Driver;
import ExamPreparation.Exam22Aug2020.easterRaces.entities.drivers.DriverImpl;
import ExamPreparation.Exam22Aug2020.easterRaces.entities.racers.Race;
import ExamPreparation.Exam22Aug2020.easterRaces.entities.racers.RaceImpl;
import ExamPreparation.Exam22Aug2020.easterRaces.repositories.interfaces.Repository;
import ExamPreparation.Exam22Aug2020.easterRaces.core.interfaces.Controller;
import ExamPreparation.Exam22Aug2020.easterRaces.entities.cars.MuscleCar;

import java.util.List;
import java.util.stream.Collectors;

import static ExamPreparation.Exam22Aug2020.easterRaces.common.ExceptionMessages.*;
import static ExamPreparation.Exam22Aug2020.easterRaces.common.OutputMessages.*;

public class ControllerImpl implements Controller {
    private Repository<Driver> riderRepository;
    private Repository<Car> motorcycleRepository;
    private Repository<Race> raceRepository;

    public ControllerImpl(Repository<Driver> riderRepository, Repository<Car> motorcycleRepository, Repository<Race> raceRepository) {

        this.riderRepository = riderRepository;
        this.motorcycleRepository = motorcycleRepository;
        this.raceRepository = raceRepository;
    }

    @Override
    public String createDriver(String driver) {
        Driver rider = riderRepository.getByName(driver);
        if (rider != null) {
            throw new IllegalArgumentException(String.format(DRIVER_EXISTS, driver));
        }
        DriverImpl newDriver = new DriverImpl(driver);
        riderRepository.add(newDriver);
        return String.format(DRIVER_CREATED, driver);
    }

    @Override
    public String createCar(String type, String model, int horsePower) {
        boolean exist = motorcycleRepository.getByName(model) != null;
        if (exist) {
            throw new IllegalArgumentException(String.format(CAR_EXISTS, model));
        }
        Car car = null;
        switch (type) {
            case "Muscle":
                car = new MuscleCar(model, horsePower);
                break;
            case "Sports":
                car = new SportsCar(model, horsePower);
                break;
            default:
                throw new IllegalArgumentException("Invalid car type");
        }

        motorcycleRepository.add(car);

        return String.format(CAR_CREATED, car.getClass().getSimpleName(), model);
    }

    @Override
    public String addCarToDriver(String driverName, String carModel) {
        Driver driver = riderRepository.getByName(driverName);
        if (driver == null) {
            throw new IllegalArgumentException(String.format(DRIVER_NOT_FOUND, driverName));
        }

        Car car = motorcycleRepository.getByName(carModel);
        if (car == null) {
            throw new IllegalArgumentException(String.format(CAR_NOT_FOUND, carModel));
        }

        driver.addCar(car);

        return String.format(CAR_ADDED, driverName, carModel);
    }

    @Override
    public String addDriverToRace(String raceName, String driverName) {
        Race race = raceRepository.getByName(raceName);
        if (race == null) {
            throw new IllegalArgumentException(String.format(RACE_NOT_FOUND, raceName));
        }

        Driver driver = riderRepository.getByName(driverName);
        if (driver == null) {
            throw new IllegalArgumentException(String.format(DRIVER_NOT_FOUND, driverName));
        }

        race.addDriver(driver);

        return String.format(DRIVER_ADDED, driverName, raceName);
    }

    @Override
    public String startRace(String raceName) {
        Race race = raceRepository.getByName(raceName);
        if (race == null) {
            throw new IllegalArgumentException(String.format(RACE_NOT_FOUND, raceName));
        }

        if (race.getDrivers().size() < 3) {
            throw new IllegalArgumentException(String.format(RACE_INVALID, raceName, 3));
        }

        List<Driver> drivers = race.getDrivers().stream().sorted((l, r) -> {
            return Double.compare(r.getCar().calculateRacePoints(race.getLaps()), l.getCar().calculateRacePoints(race.getLaps()));
        }).limit(3).collect(Collectors.toList());

        raceRepository.remove(race);

        return String.format(DRIVER_FIRST_POSITION, drivers.get(0).getName(), raceName) +
               System.lineSeparator() +
               String.format(DRIVER_SECOND_POSITION, drivers.get(1).getName(), raceName) +
               System.lineSeparator() +
               String.format(DRIVER_THIRD_POSITION, drivers.get(2).getName(), raceName);
    }

    @Override
    public String createRace(String name, int laps) {
        Race race = raceRepository.getByName(name);
        if (race != null) {
            throw new IllegalArgumentException(String.format(RACE_EXISTS, name));
        }

        race = new RaceImpl(name, laps);
        raceRepository.add(race);

        return String.format(RACE_CREATED, name);
    }
}
