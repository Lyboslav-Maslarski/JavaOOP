package ExamPreparation.Exam11December2021.catHouse.entities.cat;

public class ShorthairCat extends BaseCat {

    public static final int KILOGRAMS = 7;

    public ShorthairCat(String name, String breed, double price) {
        super(name, breed, price);
        super.setKilograms(KILOGRAMS);
    }

    @Override
    public void eating() {
        super.setKilograms(getKilograms() + 1);
    }
}
