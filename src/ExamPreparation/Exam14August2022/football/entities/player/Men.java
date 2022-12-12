package ExamPreparation.Exam14August2022.football.entities.player;

public class Men extends BasePlayer {
    private final static double KGs = 85.50;
    private final static int INCREASING_STRENGTH_VALUE = 145;

    public Men(String name, String nationality,int strength) {
        super(name, nationality, strength);
        setKg(KGs);
    }

    @Override
    public void stimulation() {
        setStrength(getStrength() + INCREASING_STRENGTH_VALUE);
    }
}
