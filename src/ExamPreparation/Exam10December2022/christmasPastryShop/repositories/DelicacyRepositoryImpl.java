package ExamPreparation.Exam10December2022.christmasPastryShop.repositories;

import ExamPreparation.Exam10December2022.christmasPastryShop.entities.delicacies.interfaces.Delicacy;
import ExamPreparation.Exam10December2022.christmasPastryShop.repositories.interfaces.DelicacyRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class DelicacyRepositoryImpl<T> implements DelicacyRepository<Delicacy> {
    private Collection<Delicacy> models;

    public DelicacyRepositoryImpl() {
        this.models = new ArrayList<>();
    }

    @Override
    public Delicacy getByName(String name) {
        return models.stream().filter(delicacy -> delicacy.getName().equals(name)).findFirst().orElse(null);
    }

    @Override
    public Collection<Delicacy> getAll() {
        return Collections.unmodifiableCollection(models);
    }

    @Override
    public void add(Delicacy delicacy) {
        models.add(delicacy);
    }
}
