package ExamPreparation.Exam22Aug2020.easterRaces.repositories.interfaces;

import ExamPreparation.Exam22Aug2020.easterRaces.entities.drivers.Driver;

import java.util.*;

public class DriverRepository implements Repository<Driver> {
    private Collection<Driver> models;

    public DriverRepository() {
        this.models = new ArrayList<>();
    }

    @Override
    public Driver getByName(String name) {
        return models.stream().filter(driver -> driver.getName().equals(name)).findAny().orElse(null);
    }

    @Override
    public Collection<Driver> getAll() {
        return Collections.unmodifiableCollection(models);
    }

    @Override
    public void add(Driver model) {
        models.add(model);
    }

    @Override
    public boolean remove(Driver model) {
        return models.remove(model);
    }
}
