package ExamPreparation.Exam18April2021.spaceStation.models.mission;

import ExamPreparation.Exam18April2021.spaceStation.models.astronauts.Astronaut;
import ExamPreparation.Exam18April2021.spaceStation.models.planets.Planet;

import java.util.*;

public class MissionImpl implements Mission {
    @Override
    public void explore(Planet planet, Collection<Astronaut> astronauts) {
        Deque<String> items = new ArrayDeque<>(planet.getItems());
        for (Astronaut astronaut : astronauts) {
            while (!items.isEmpty() && astronaut.canBreath()) {
                String item = items.poll();
                astronaut.getBag().getItems().add(item);
                astronaut.breath();
                planet.getItems().remove(item);
            }
        }
    }
}
