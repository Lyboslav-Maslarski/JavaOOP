package ExamPreparation.Exam08April2023.robotService.entities.robot;

public class MaleRobot extends BaseRobot {

    public static final int KILOGRAMS = 9;

    public MaleRobot(String name, String kind, double price) {
        super(name, kind, KILOGRAMS, price);
    }

    @Override
    public void eating() {
        setKilograms(getKilograms() + 3);
    }
}
