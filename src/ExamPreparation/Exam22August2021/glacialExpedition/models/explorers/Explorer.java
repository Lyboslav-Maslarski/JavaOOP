package ExamPreparation.Exam22August2021.glacialExpedition.models.explorers;

import ExamPreparation.Exam22August2021.glacialExpedition.models.suitcases.Suitcase;

public interface Explorer {
    String getName();

    double getEnergy();

    boolean canSearch();

    Suitcase getSuitcase();

    void search();
}
