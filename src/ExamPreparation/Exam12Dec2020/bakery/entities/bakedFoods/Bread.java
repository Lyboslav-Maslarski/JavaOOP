package ExamPreparation.Exam12Dec2020.bakery.entities.bakedFoods;

public class Bread extends BaseFood {
    public static final double PORTION = 200;

    public Bread(String name, double price) {
        super(name, PORTION, price);
    }
}
