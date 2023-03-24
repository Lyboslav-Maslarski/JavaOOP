package ExamPreparation.Exam19Dec2020.viceCity.repositories;

import ExamPreparation.Exam19Dec2020.viceCity.models.guns.Gun;
import ExamPreparation.Exam19Dec2020.viceCity.repositories.interfaces.Repository;

import java.util.*;

public class GunRepository implements Repository<Gun> {
    private List<Gun> models;

    public GunRepository() {
        this.models = new ArrayList<>();
    }

    @Override
    public List<Gun> getModels() {
        return Collections.unmodifiableList(models);
    }

    @Override
    public void add(Gun model) {
        if (!models.contains(model)) {
            models.add(model);
        }
    }

    @Override
    public boolean remove(Gun model) {
        return models.remove(model);
    }

    @Override
    public Gun find(String name) {
        return models.stream().filter(gun -> gun.getName().equals(name)).findFirst().orElse(null);
    }
}
