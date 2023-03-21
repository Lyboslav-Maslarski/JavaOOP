package ExamPreparation.Exam22Aug2020.easterRaces.repositories.interfaces;

import ExamPreparation.Exam22Aug2020.easterRaces.entities.racers.Race;

import java.util.*;

public class RaceRepository implements Repository<Race> {
    private Collection<Race> models;

    public RaceRepository() {
        this.models = new ArrayList<>();
    }

    @Override
    public Race getByName(String name) {
        return models.stream().filter(race -> race.getName().equals(name)).findAny().orElse(null);
    }

    @Override
    public Collection<Race> getAll() {
        return Collections.unmodifiableCollection(models);
    }

    @Override
    public void add(Race model) {
        models.add(model);
    }

    @Override
    public boolean remove(Race model) {
        return models.remove(model);
    }
}
