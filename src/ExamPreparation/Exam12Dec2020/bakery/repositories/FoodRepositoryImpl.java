package ExamPreparation.Exam12Dec2020.bakery.repositories;

import ExamPreparation.Exam12Dec2020.bakery.entities.bakedFoods.interfaces.BakedFood;
import ExamPreparation.Exam12Dec2020.bakery.repositories.interfaces.FoodRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class FoodRepositoryImpl implements FoodRepository<BakedFood> {
    private Collection<BakedFood> models;

    public FoodRepositoryImpl() {
        this.models = new ArrayList<>();
    }


    @Override
    public BakedFood getByName(String name) {
        return this.models.stream()
                .filter(bakedFood -> bakedFood.getName().equals(name))
                .findFirst()
                .orElse(null);
    }

    @Override
    public Collection<BakedFood> getAll() {
        return Collections.unmodifiableCollection(this.models);
    }

    @Override
    public void add(BakedFood bakedFood) {
        this.models.add(bakedFood);
    }
}
