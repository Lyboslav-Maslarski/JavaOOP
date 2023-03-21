package ExamPreparation.Exam22Aug2020.easterRaces.repositories.interfaces;

import ExamPreparation.Exam22Aug2020.easterRaces.entities.cars.Car;

import java.util.*;

public class CarRepository implements Repository<Car> {
    private Collection<Car> models;

    public CarRepository() {
        this.models = new ArrayList<>();
    }

    @Override
    public Car getByName(String name) {
        return models.stream().filter(car -> car.getModel().equals(name)).findAny().orElse(null);
    }

    @Override
    public Collection<Car> getAll() {
        return Collections.unmodifiableCollection(models);
    }

    @Override
    public void add(Car model) {
        models.add(model);
    }

    @Override
    public boolean remove(Car model) {
        return models.remove(model);
    }
}
