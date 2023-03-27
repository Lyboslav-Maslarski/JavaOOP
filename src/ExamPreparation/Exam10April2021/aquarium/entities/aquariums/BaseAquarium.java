package ExamPreparation.Exam10April2021.aquarium.entities.aquariums;

import ExamPreparation.Exam10April2021.aquarium.common.ConstantMessages;
import ExamPreparation.Exam10April2021.aquarium.entities.decorations.Decoration;
import ExamPreparation.Exam10April2021.aquarium.entities.fish.Fish;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.stream.Collectors;

import static ExamPreparation.Exam10April2021.aquarium.common.ExceptionMessages.AQUARIUM_NAME_NULL_OR_EMPTY;

public abstract class BaseAquarium implements Aquarium {
    private String name;
    private int capacity;
    private Collection<Decoration> decorations;
    private Collection<Fish> fish;

    public BaseAquarium(String name, int capacity) {
        this.name = name;
        this.capacity = capacity;
        this.decorations = new ArrayList<>();
        this.fish = new ArrayList<>();
    }

    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new NullPointerException(AQUARIUM_NAME_NULL_OR_EMPTY);
        }
        this.name = name;
    }

    @Override
    public int calculateComfort() {
        return decorations.stream().mapToInt(Decoration::getComfort).sum();
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void addFish(Fish fish) {
        if (this.fish.size() == this.capacity) {
            throw new IllegalStateException(ConstantMessages.NOT_ENOUGH_CAPACITY);
        }
        this.fish.add(fish);
    }

    @Override
    public void removeFish(Fish fish) {
        this.fish.remove(fish);
    }

    @Override
    public void addDecoration(Decoration decoration) {
        this.decorations.add(decoration);
    }

    @Override
    public void feed() {
        this.fish.forEach(Fish::eat);
    }

    @Override
    public String getInfo() {
        return String.format("%s (%s):", getName(), getClass().getSimpleName()) +
               System.lineSeparator() +
               String.format("Fish: %s", fish.size() == 0 ? "none" :
                       fish.stream().map(Fish::getName).collect(Collectors.joining(" "))) +
               System.lineSeparator() +
               String.format("Decorations: %d", decorations.size()) +
               System.lineSeparator() +
               String.format("Comfort: %d", calculateComfort());
    }

    @Override
    public Collection<Fish> getFish() {
        return Collections.unmodifiableCollection(fish);
    }

    @Override
    public Collection<Decoration> getDecorations() {
        return Collections.unmodifiableCollection(decorations);
    }
}
