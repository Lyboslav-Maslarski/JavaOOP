package ExamPreparation.Exam10December2022.christmasPastryShop.repositories.interfaces;

import java.util.Collection;

public interface Repository<T> {

    Collection<T> getAll();

    void add(T t);

}
