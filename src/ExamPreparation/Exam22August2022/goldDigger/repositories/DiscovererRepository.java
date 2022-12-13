package ExamPreparation.Exam22August2022.goldDigger.repositories;

import ExamPreparation.Exam22August2022.goldDigger.models.discoverer.Discoverer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class DiscovererRepository implements Repository<Discoverer> {
    private List<Discoverer> discoverers;

    public DiscovererRepository() {
        this.discoverers = new ArrayList<>();
    }

    @Override
    public Collection<Discoverer> getCollection() {
        return Collections.unmodifiableList(discoverers);
    }

    @Override
    public void add(Discoverer entity) {
        discoverers.add(entity);
    }

    @Override
    public boolean remove(Discoverer entity) {
        return discoverers.remove(entity);
    }

    @Override
    public Discoverer byName(String name) {
        return discoverers.stream().filter(discoverer -> discoverer.getName().equals(name)).findFirst().orElse(null);
    }
}
