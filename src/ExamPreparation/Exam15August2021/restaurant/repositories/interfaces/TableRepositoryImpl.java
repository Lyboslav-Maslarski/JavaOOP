package ExamPreparation.Exam15August2021.restaurant.repositories.interfaces;

import ExamPreparation.Exam15August2021.restaurant.entities.tables.interfaces.Table;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class TableRepositoryImpl implements TableRepository<Table> {
    private Collection<Table> entities;

    public TableRepositoryImpl() {
        this.entities = new ArrayList<>();
    }

    @Override
    public Collection<Table> getAllEntities() {
        return Collections.unmodifiableCollection(entities);
    }

    @Override
    public void add(Table entity) {
        this.entities.add(entity);
    }

    @Override
    public Table byNumber(int number) {
        return this.entities.stream().filter(t -> t.getTableNumber() == number).findAny().orElse(null);
    }
}
