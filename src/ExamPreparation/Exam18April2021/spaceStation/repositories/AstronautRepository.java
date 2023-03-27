package ExamPreparation.Exam18April2021.spaceStation.repositories;

import ExamPreparation.Exam18April2021.spaceStation.models.astronauts.Astronaut;

import java.util.*;

public class AstronautRepository implements Repository<Astronaut> {
    private Map<String, Astronaut> astronauts;

    public AstronautRepository() {
        this.astronauts = new LinkedHashMap<>();
    }

    @Override
    public Collection<Astronaut> getModels() {
        return Collections.unmodifiableCollection(astronauts.values());
    }

    @Override
    public void add(Astronaut model) {
        astronauts.put(model.getName(), model);
    }

    @Override
    public boolean remove(Astronaut model) {
        return astronauts.remove(model.getName(), model);
    }

    @Override
    public Astronaut findByName(String name) {
        return astronauts.get(name);
    }
}
