package ExamPreparation.Exam09April2022.fairyShop.models;

public class Sleepy extends BaseHelper {

    public static final int ENERGY = 50;

    public Sleepy(String name) {
        super(name, ENERGY);
    }

    @Override
    public void work() {
        super.setEnergy(getEnergy() - 15);
    }
}
