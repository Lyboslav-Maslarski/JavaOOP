package ExamPreparation.Exam12Dec2020.bakery.repositories.interfaces;

public interface DrinkRepository<T> extends Repository<T> {
    T getByNameAndBrand(String drinkName,String drinkBrand);
}
