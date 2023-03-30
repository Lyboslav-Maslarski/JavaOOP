package ExamPreparation.Exam15August2021.restaurant.entities.healthyFoods;

public class Salad extends Food{

    public static final int PORTION = 150;

    public Salad(String name, double price) {
        super(name, PORTION, price);
    }
}
