package ExamPreparation.Exam18April2021.spaceStation.models.astronauts;

import ExamPreparation.Exam18April2021.spaceStation.common.ExceptionMessages;
import ExamPreparation.Exam18April2021.spaceStation.models.bags.Bag;
import ExamPreparation.Exam18April2021.spaceStation.models.bags.Backpack;

public abstract class BaseAstronaut implements Astronaut {
    private String name;
    private double oxygen;
    private Bag bag;

    public BaseAstronaut(String name, double oxygen) {
        this.setName(name);
        this.setOxygen(oxygen);
        this.bag = new Backpack();
    }

    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new NullPointerException(ExceptionMessages.ASTRONAUT_NAME_NULL_OR_EMPTY);
        }
        this.name = name;
    }

    public void setOxygen(double oxygen) {
        if (oxygen < 0) {
            throw new IllegalArgumentException(ExceptionMessages.ASTRONAUT_OXYGEN_LESS_THAN_ZERO);
        }
        this.oxygen = oxygen;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public double getOxygen() {
        return oxygen;
    }

    @Override
    public boolean canBreath() {
        return oxygen > 0;
    }

    @Override
    public Bag getBag() {
        return bag;
    }

    @Override
    public void breath() {
        double oxygen = getOxygen() - 10;
        if (oxygen < 0) {
            setOxygen(0);
        } else {
            setOxygen(oxygen);
        }
    }
}
