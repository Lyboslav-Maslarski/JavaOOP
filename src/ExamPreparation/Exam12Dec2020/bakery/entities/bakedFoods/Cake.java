package ExamPreparation.Exam12Dec2020.bakery.entities.bakedFoods;

public class Cake extends BaseFood {
    public static final double PORTION = 245;

    public Cake(String name, double price) {
        super(name, PORTION, price);
    }
}
