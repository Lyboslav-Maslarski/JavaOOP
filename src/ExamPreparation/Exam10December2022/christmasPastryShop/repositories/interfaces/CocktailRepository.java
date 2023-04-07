package ExamPreparation.Exam10December2022.christmasPastryShop.repositories.interfaces;

public interface CocktailRepository<T> extends Repository<T> {
    T getByName(String name);
}
