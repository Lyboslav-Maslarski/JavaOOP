package ExamPreparation.Exam22August2022.goldDigger.repositories;

import ExamPreparation.Exam22August2022.goldDigger.models.spot.Spot;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class SpotRepository implements Repository<Spot> {
    private List<Spot> spots;

    public SpotRepository() {
        this.spots = new ArrayList<>();
    }

    @Override
    public Collection<Spot> getCollection() {
        return Collections.unmodifiableList(spots);
    }

    @Override
    public void add(Spot entity) {
        spots.add(entity);
    }

    @Override
    public boolean remove(Spot entity) {
        return spots.remove(entity);
    }

    @Override
    public Spot byName(String name) {
        return spots.stream().filter(discoverer -> discoverer.getName().equals(name)).findFirst().orElse(null);
    }
}
