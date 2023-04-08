package ExamPreparation.Exam08April2023.robotService.entities.robot;

public class FemaleRobot extends BaseRobot {

    public static final int KILOGRAMS = 7;

    public FemaleRobot(String name, String kind, double price) {
        super(name, kind, KILOGRAMS, price);
    }

    @Override
    public void eating() {
        setKilograms(getKilograms() + 1);
    }
}
