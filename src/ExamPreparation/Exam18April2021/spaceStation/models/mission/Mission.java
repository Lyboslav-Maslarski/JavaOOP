package ExamPreparation.Exam18April2021.spaceStation.models.mission;

import ExamPreparation.Exam18April2021.spaceStation.models.astronauts.Astronaut;
import ExamPreparation.Exam18April2021.spaceStation.models.planets.Planet;

import java.util.Collection;

public interface Mission {
    void explore(Planet planet, Collection<Astronaut> astronauts);
}
