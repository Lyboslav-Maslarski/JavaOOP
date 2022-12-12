package ExamPreparation.Exam14August2022.football.entities.supplement;

public abstract class BaseSupplement implements Supplement {
    private int energy;
    private double price;

    public BaseSupplement(int energy, double price) {
        this.energy = energy;
        this.price = price;
    }

    @Override
    public int getEnergy() {
        return energy;
    }


    @Override
    public double getPrice() {
        return price;
    }

    public void setEnergy(int energy) {
        this.energy = energy;
    }

    public void setPrice(double price) {
        this.price = price;
    }

}
