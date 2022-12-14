package Polymorphism.Exercise.WildFarm;

public class Tiger extends Feline {

    public Tiger(String animalType, String animalName, Double animalWeight, String livingRegion) {
        super(animalType, animalName, animalWeight, livingRegion);
    }

    @Override
    public void makeSound() {
        System.out.println("ROAAR!!!");
    }

    @Override
    public void eat(Food food) {
        if (food instanceof Meat) {
            setFoodEaten(getFoodEaten() + food.getQuantity());
        } else {
            System.out.println(getAnimalType() + "s are not eating that type of food!");
        }
    }
}
