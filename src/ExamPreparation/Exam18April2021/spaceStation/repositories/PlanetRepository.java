package ExamPreparation.Exam18April2021.spaceStation.repositories;

import ExamPreparation.Exam18April2021.spaceStation.models.planets.Planet;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class PlanetRepository implements Repository<Planet> {
    private Map<String, Planet> planets;

    public PlanetRepository() {
        this.planets = new HashMap<>();
    }

    @Override
    public Collection<Planet> getModels() {
        return Collections.unmodifiableCollection(planets.values());
    }

    @Override
    public void add(Planet model) {
        planets.put(model.getName(), model);
    }

    @Override
    public boolean remove(Planet model) {
        return planets.remove(model.getName(), model);
    }

    @Override
    public Planet findByName(String name) {
        return planets.get(name);
    }
}
