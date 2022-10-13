package Polymorphism.Exercise.WildFarm;

import java.text.DecimalFormat;

public abstract class Mammal extends Animal {
    private String livingRegion;
    protected static final DecimalFormat decimalFormat=new DecimalFormat("#.##");

    protected Mammal(String animalType, String animalName, Double animalWeight, String livingRegion) {
        super(animalType, animalName, animalWeight);
        setLivingRegion(livingRegion);
    }

    public String getLivingRegion() {
        return livingRegion;
    }

    private void setLivingRegion(String livingRegion) {
        this.livingRegion = livingRegion;
    }

    @Override
    public String toString() {
        return String.format("%s[%s, %s, %s, %d]",
                getAnimalType(), getAnimalName(), decimalFormat.format(getAnimalWeight()), getLivingRegion(), getFoodEaten());
    }
}
