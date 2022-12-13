package ExamPreparation.Exam22August2022.goldDigger.models.spot;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static ExamPreparation.Exam22August2022.goldDigger.common.ExceptionMessages.SPOT_NAME_NULL_OR_EMPTY;

public class SpotImpl implements Spot {
    private String name;
    private List<String> exhibits;

    public SpotImpl(String name) {
        setName(name);
        exhibits = new ArrayList<>();
    }

    public void setName(String name) {
        if (name==null||name.trim().isEmpty()){
            throw new NullPointerException(SPOT_NAME_NULL_OR_EMPTY);
        }
        this.name = name;
    }

    @Override
    public Collection<String> getExhibits() {
        return exhibits;
    }

    @Override
    public String getName() {
        return name;
    }
}
