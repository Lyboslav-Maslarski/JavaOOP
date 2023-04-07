package ExamPreparation.Exam10December2022.christmasPastryShop.repositories;

import ExamPreparation.Exam10December2022.christmasPastryShop.entities.cocktails.interfaces.Cocktail;
import ExamPreparation.Exam10December2022.christmasPastryShop.repositories.interfaces.CocktailRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class CocktailRepositoryImpl<T> implements CocktailRepository<Cocktail> {
    private Collection<Cocktail> models;

    public CocktailRepositoryImpl() {
        this.models = new ArrayList<>();
    }

    @Override
    public Cocktail getByName(String name) {
        return models.stream().filter(cocktail -> cocktail.getName().equals(name)).findFirst().orElse(null);
    }

    @Override
    public Collection<Cocktail> getAll() {
        return Collections.unmodifiableCollection(models);
    }

    @Override
    public void add(Cocktail cocktail) {
        models.add(cocktail);
    }
}
