package ExamPreparation.Exam15August2021.restaurant.entities.drinks;

public class Smoothie extends BaseBeverage {

    public static final double PRICE = 4.50;

    public Smoothie(String name, int counter, String brand) {
        super(name, counter, PRICE, brand);
    }
}
