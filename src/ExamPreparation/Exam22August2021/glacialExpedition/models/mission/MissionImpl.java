package ExamPreparation.Exam22August2021.glacialExpedition.models.mission;

import ExamPreparation.Exam22August2021.glacialExpedition.models.explorers.Explorer;
import ExamPreparation.Exam22August2021.glacialExpedition.models.states.State;

import java.util.Collection;

public class MissionImpl implements Mission {
    @Override
    public void explore(State state, Collection<Explorer> explorers) {
        Collection<String> exhibits = state.getExhibits();

        for (Explorer explorer : explorers) {

            while (explorer.canSearch() && exhibits.iterator().hasNext()) {
                String currentExhibit = exhibits.iterator().next();
                explorer.search();
                explorer.getSuitcase().getExhibits().add(currentExhibit);
                exhibits.remove(currentExhibit);
            }

        }

    }
}
