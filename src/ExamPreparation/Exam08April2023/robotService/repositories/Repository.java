package ExamPreparation.Exam08April2023.robotService.repositories;

import ExamPreparation.Exam08April2023.robotService.entities.supplements.Supplement;

public interface Repository {

    void addSupplement(Supplement supplement);

    boolean removeSupplement(Supplement supplement);

    Supplement findFirst(String type);
}
