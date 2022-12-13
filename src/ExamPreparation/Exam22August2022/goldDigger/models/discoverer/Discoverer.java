package ExamPreparation.Exam22August2022.goldDigger.models.discoverer;

import ExamPreparation.Exam22August2022.goldDigger.models.museum.Museum;

public interface Discoverer {
    String getName();

    double getEnergy();

    boolean canDig();

    Museum getMuseum();

    void dig();
}
