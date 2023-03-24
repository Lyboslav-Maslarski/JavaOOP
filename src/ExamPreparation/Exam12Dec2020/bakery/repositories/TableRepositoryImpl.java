package ExamPreparation.Exam12Dec2020.bakery.repositories;

import ExamPreparation.Exam12Dec2020.bakery.entities.tables.interfaces.Table;
import ExamPreparation.Exam12Dec2020.bakery.repositories.interfaces.TableRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class TableRepositoryImpl implements TableRepository<Table> {
    private Collection<Table> models;

    public TableRepositoryImpl() {
        this.models = new ArrayList<>();
    }


    @Override
    public Collection<Table> getAll() {
        return Collections.unmodifiableCollection(this.models);
    }

    @Override
    public void add(Table table) {
        this.models.add(table);
    }

    @Override
    public Table getByNumber(int number) {
        return this.models.stream()
                .filter(table -> table.getTableNumber() == number)
                .findFirst()
                .orElse(null);
    }
}
