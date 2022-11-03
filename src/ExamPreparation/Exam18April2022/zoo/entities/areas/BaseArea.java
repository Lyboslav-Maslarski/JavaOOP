package ExamPreparation.Exam18April2022.zoo.entities.areas;

import ExamPreparation.Exam18April2022.zoo.entities.animals.Animal;
import ExamPreparation.Exam18April2022.zoo.entities.foods.Food;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

import static ExamPreparation.Exam18April2022.zoo.common.ExceptionMessages.AREA_NAME_NULL_OR_EMPTY;
import static ExamPreparation.Exam18April2022.zoo.common.ExceptionMessages.NOT_ENOUGH_CAPACITY;

public abstract class BaseArea implements Area {
    private String name;
    private int capacity;
    private Collection<Food> foods;
    private Collection<Animal> animals;

    public BaseArea(String name, int capacity) {
        setName(name);
        this.capacity = capacity;
        this.foods = new ArrayList<>();
        this.animals = new ArrayList<>();
    }

    private void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new NullPointerException(AREA_NAME_NULL_OR_EMPTY);
        }
        this.name = name;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public Collection<Animal> getAnimals() {
        return this.animals;
    }

    @Override
    public Collection<Food> getFoods() {
        return this.foods;
    }

    @Override
    public int sumCalories() {
        return foods.stream().mapToInt(Food::getCalories).sum();
    }

    @Override
    public void addAnimal(Animal animal) {
        if (this.capacity == animals.size()) {
            throw new IllegalStateException(NOT_ENOUGH_CAPACITY);
        }
        animals.add(animal);
    }

    @Override
    public void removeAnimal(Animal animal) {
        animals.remove(animal);
    }

    @Override
    public void addFood(Food food) {
        foods.add(food);
    }

    @Override
    public void feed() {
        animals.forEach(Animal::eat);
    }

    @Override
    public String getInfo() {
        String animalsNames = animals.isEmpty() ? "none" : animals.stream().map(Animal::getName).collect(Collectors.joining(" "));
        return String.format("%s (%s):%n" +
                             "Animals: %s%n" +
                             "Foods: %d%n" +
                             "Calories: %d", this.name, this.getClass().getSimpleName(), animalsNames, foods.size(), sumCalories());
    }
}
