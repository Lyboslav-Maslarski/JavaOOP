package ExamPreparation.Exam10April2021.aquarium.entities.fish;

public class SaltwaterFish extends BaseFish {

    public static final int INITIAL_SIZE = 5;

    public SaltwaterFish(String name, String species, double price) {
        super(name, species, price);
        super.setSize(INITIAL_SIZE);
    }

    @Override
    public void eat() {
        super.setSize(super.getSize() + 2);
    }
}
