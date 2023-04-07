package ExamPreparation.Exam19December2022.magicGame.repositories.interfaces;

import ExamPreparation.Exam19December2022.magicGame.models.magicians.Magician;

import java.util.Collection;

public interface MagicianRepository<T> {
    Collection<T> getData();

    void addMagician(Magician model);

    boolean removeMagician(Magician model);

    T findByUsername(String name);
}
