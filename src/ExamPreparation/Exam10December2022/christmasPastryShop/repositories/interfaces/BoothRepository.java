package ExamPreparation.Exam10December2022.christmasPastryShop.repositories.interfaces;

public interface BoothRepository<T> extends Repository<T> {
    T getByNumber(int number);
}
