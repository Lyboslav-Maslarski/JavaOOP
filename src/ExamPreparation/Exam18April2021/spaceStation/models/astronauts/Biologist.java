package ExamPreparation.Exam18April2021.spaceStation.models.astronauts;

public class Biologist extends BaseAstronaut {

    public static final int OXYGEN = 70;

    public Biologist(String name) {
        super(name, OXYGEN);
    }

    @Override
    public void breath() {
        double oxygen = getOxygen() - 5;
        if (oxygen < 0) {
            setOxygen(0);
        } else {
            setOxygen(oxygen);
        }
    }
}
