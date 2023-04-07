package ExamPreparation.Exam10December2022.christmasPastryShop.repositories;

import ExamPreparation.Exam10December2022.christmasPastryShop.entities.booths.interfaces.Booth;
import ExamPreparation.Exam10December2022.christmasPastryShop.repositories.interfaces.BoothRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class BoothRepositoryImpl<T> implements BoothRepository<Booth> {
    private Collection<Booth> models;

    public BoothRepositoryImpl() {
        models = new ArrayList<>();
    }

    @Override
    public Booth getByNumber(int number) {
        return models.stream().filter(booth -> booth.getBoothNumber() == number).findFirst().orElse(null);
    }

    @Override
    public Collection<Booth> getAll() {
        return Collections.unmodifiableCollection(models);
    }

    @Override
    public void add(Booth booth) {
        models.add(booth);
    }
}
