package ExamPreparation.Exam22August2022.goldDigger.models.operation;

import ExamPreparation.Exam22August2022.goldDigger.models.discoverer.Discoverer;
import ExamPreparation.Exam22August2022.goldDigger.models.spot.Spot;

import java.util.Collection;

public class OperationImpl implements Operation {
    @Override
    public void startOperation(Spot spot, Collection<Discoverer> discoverers) {
        Collection<String> exhibits = spot.getExhibits();
        for (Discoverer discoverer : discoverers) {

            while (discoverer.canDig() && exhibits.iterator().hasNext()) {
                discoverer.dig();
                String exhibit = exhibits.iterator().next();
                discoverer.getMuseum().getExhibits().add(exhibit);
                exhibits.remove(exhibit);
            }

        }
    }
}
