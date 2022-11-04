package ExamPreparation.Exam22August2021.glacialExpedition.repositories;

import ExamPreparation.Exam22August2021.glacialExpedition.models.explorers.Explorer;

import java.util.*;

public class ExplorerRepository implements Repository<Explorer> {
    private Map<String, Explorer> explorers;

    public ExplorerRepository() {
        explorers = new LinkedHashMap<>();
    }

    @Override
    public Collection<Explorer> getCollection() {
        return Collections.unmodifiableCollection(explorers.values());
    }

    @Override
    public void add(Explorer entity) {
        explorers.putIfAbsent(entity.getName(), entity);
    }

    @Override
    public boolean remove(Explorer entity) {
        return explorers.remove(entity.getName()) != null;
    }

    @Override
    public Explorer byName(String name) {
        return explorers.get(name);
    }
}
