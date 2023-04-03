package ExamPreparation.Exam11December2021.catHouse.entities.cat;

public class LonghairCat extends BaseCat {

    public static final int KILOGRAMS = 9;

    public LonghairCat(String name, String breed, double price) {
        super(name, breed, price);
        super.setKilograms(KILOGRAMS);
    }

    @Override
    public void eating() {
        super.setKilograms(getKilograms() + 3);
    }
}
