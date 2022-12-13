package ExamPreparation.Exam22August2022.goldDigger.models.museum;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class BaseMuseum implements Museum {
    private List<String> exhibits;

    public BaseMuseum() {
        exhibits = new ArrayList<>();
    }

    @Override
    public Collection<String> getExhibits() {
        return exhibits;
    }
}
