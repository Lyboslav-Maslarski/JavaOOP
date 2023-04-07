package ExamPreparation.Exam10December2022.christmasPastryShop.entities.delicacies;

public class Gingerbread extends BaseDelicacy{

    public static final int PORTION = 200;

    public Gingerbread(String name, double price) {
        super(name, PORTION, price);
    }
}
