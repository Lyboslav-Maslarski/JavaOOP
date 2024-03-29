package ExamPreparation.Exam22Aug2020.easterRaces.entities.racers;

import ExamPreparation.Exam22Aug2020.easterRaces.entities.drivers.Driver;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import static ExamPreparation.Exam22Aug2020.easterRaces.common.ExceptionMessages.*;

public class RaceImpl implements Race {
    private String name;
    private int laps;
    private Collection<Driver> drivers;

    public RaceImpl(String name, int laps) {
        this.setName(name);
        this.setLaps(laps);
        this.drivers = new ArrayList<>();
    }

    private void setName(String name) {
        if (name == null || name.trim().isEmpty() || name.length() < 5) {
            throw new IllegalArgumentException(String.format(INVALID_NAME, name, 5));
        }
        this.name = name;
    }

    private void setLaps(int laps) {
        if (laps < 1) {
            throw new IllegalArgumentException(String.format(INVALID_NUMBER_OF_LAPS, 1));
        }
        this.laps = laps;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getLaps() {
        return laps;
    }

    @Override
    public Collection<Driver> getDrivers() {
        return Collections.unmodifiableCollection(drivers);
    }

    @Override
    public void addDriver(Driver driver) {
        if (driver == null) {
            throw new IllegalArgumentException(DRIVER_INVALID);
        }
        String driverName = driver.getName();
        if (!driver.getCanParticipate()) {
            throw new IllegalArgumentException(String.format(DRIVER_NOT_PARTICIPATE, driverName));
        }
        if (drivers.stream().anyMatch(d->d.getName().equals(driverName))) {
            throw new IllegalArgumentException(String.format(DRIVER_ALREADY_ADDED, driverName, getName()));
        }
        drivers.add(driver);
    }
}
