package ExamPreparation.Exam18April2021.spaceStation.models.astronauts;

import ExamPreparation.Exam18April2021.spaceStation.models.bags.Bag;

public interface Astronaut {
    String getName();

    double getOxygen();

    boolean canBreath();

    Bag getBag();

    void breath();
}
