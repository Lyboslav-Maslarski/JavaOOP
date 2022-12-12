package ExamPreparation.Exam14August2022.football.entities.player;

public class Women extends BasePlayer {
    private final static double KGs = 60.0;
    private final static int INCREASING_STRENGTH_VALUE = 115;

    public Women(String name, String nationality, int strength) {
        super(name, nationality, strength);
        setKg(KGs);
    }

    @Override
    public void stimulation() {
        setStrength(getStrength() + INCREASING_STRENGTH_VALUE);
    }
}
