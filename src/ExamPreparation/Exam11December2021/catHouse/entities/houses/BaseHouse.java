package ExamPreparation.Exam11December2021.catHouse.entities.houses;

import ExamPreparation.Exam11December2021.catHouse.common.ConstantMessages;
import ExamPreparation.Exam11December2021.catHouse.common.ExceptionMessages;
import ExamPreparation.Exam11December2021.catHouse.entities.cat.Cat;
import ExamPreparation.Exam11December2021.catHouse.entities.toys.Toy;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

public abstract class BaseHouse implements House {
    private String name;
    private int capacity;
    private Collection<Toy> toys;
    private Collection<Cat> cats;

    public BaseHouse(String name, int capacity) {
        this.setName(name);
        this.capacity = capacity;
        this.toys = new ArrayList<>();
        this.cats = new ArrayList<>();
    }

    @Override
    public int sumSoftness() {
        return toys.stream().mapToInt(Toy::getSoftness).sum();
    }

    @Override
    public void addCat(Cat cat) {
        if (cats.size() == capacity) {
            throw new IllegalStateException(ConstantMessages.NOT_ENOUGH_CAPACITY_FOR_CAT);
        }
        cats.add(cat);
    }

    @Override
    public void removeCat(Cat cat) {
        cats.remove(cat);
    }

    @Override
    public void buyToy(Toy toy) {
        toys.add(toy);
    }

    @Override
    public void feeding() {
        cats.forEach(Cat::eating);
    }

    @Override
    public String getStatistics() {
        return getName() + " " + getClass().getSimpleName() + ":" + System.lineSeparator() +
               "Cats: " + (cats.size() == 0 ? "none" :
                cats.stream().map(Cat::getName).collect(Collectors.joining(" "))) +
               System.lineSeparator() +
               "Toys: " + toys.size() + " Softness: " + sumSoftness();
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new NullPointerException(ExceptionMessages.HOUSE_NAME_CANNOT_BE_NULL_OR_EMPTY);
        }
        this.name = name;
    }

    @Override
    public Collection<Cat> getCats() {
        return cats;
    }

    @Override
    public Collection<Toy> getToys() {
        return toys;
    }
}
