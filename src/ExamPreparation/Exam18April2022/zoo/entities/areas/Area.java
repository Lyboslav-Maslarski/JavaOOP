package ExamPreparation.Exam18April2022.zoo.entities.areas;

import ExamPreparation.Exam18April2022.zoo.entities.animals.Animal;
import ExamPreparation.Exam18April2022.zoo.entities.foods.Food;

import java.util.Collection;

public interface Area {
    String getName();

    Collection<Animal> getAnimals();

    Collection<Food> getFoods();

    int sumCalories();

    void addAnimal(Animal animal);

    void removeAnimal(Animal animal);

    void addFood(Food food);

    void feed();

    String getInfo();
}
