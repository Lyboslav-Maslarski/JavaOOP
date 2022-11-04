package ExamPreparation.Exam22August2021.glacialExpedition.models.mission;

import ExamPreparation.Exam22August2021.glacialExpedition.models.explorers.Explorer;
import ExamPreparation.Exam22August2021.glacialExpedition.models.states.State;


import java.util.Collection;

public interface Mission {
    void explore(State state, Collection<Explorer> explorers);
}
