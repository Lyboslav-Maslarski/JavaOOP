package ExamPreparation.Exam08April2023.robotService.entities.services;

import ExamPreparation.Exam08April2023.robotService.entities.robot.Robot;
import ExamPreparation.Exam08April2023.robotService.entities.supplements.Supplement;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.stream.Collectors;

import static ExamPreparation.Exam08April2023.robotService.common.ConstantMessages.NOT_ENOUGH_CAPACITY_FOR_ROBOT;
import static ExamPreparation.Exam08April2023.robotService.common.ExceptionMessages.SERVICE_NAME_CANNOT_BE_NULL_OR_EMPTY;

public abstract class BaseService implements Service {
    private String name;
    private int capacity;
    private Collection<Supplement> supplements;
    private Collection<Robot> robots;

    public BaseService(String name, int capacity) {
        this.setName(name);
        this.capacity = capacity;
        this.supplements = new ArrayList<>();
        this.robots = new ArrayList<>();
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new NullPointerException(SERVICE_NAME_CANNOT_BE_NULL_OR_EMPTY);
        }
        this.name = name;
    }

    @Override
    public Collection<Robot> getRobots() {
        return Collections.unmodifiableCollection(robots);
    }

    @Override
    public Collection<Supplement> getSupplements() {
        return Collections.unmodifiableCollection(supplements);
    }

    @Override
    public void addRobot(Robot robot) {
        if (robots.size() == capacity) {
            throw new IllegalStateException(NOT_ENOUGH_CAPACITY_FOR_ROBOT);
        }
        robots.add(robot);
    }

    @Override
    public void removeRobot(Robot robot) {
        robots.remove(robot);
    }

    @Override
    public void addSupplement(Supplement supplement) {
        supplements.add(supplement);
    }

    @Override
    public void feeding() {
        robots.forEach(Robot::eating);
    }

    @Override
    public int sumHardness() {
        return supplements.stream().mapToInt(Supplement::getHardness).sum();
    }

    @Override
    public String getStatistics() {
        String robotsNames = robots.size() == 0 ? "none" : robots.stream().map(Robot::getName).collect(Collectors.joining(" "));
        return getName() + " " + getClass().getSimpleName() + ":" + System.lineSeparator() +
               "Robots: " + robotsNames + System.lineSeparator() +
               "Supplements: " + supplements.size() + " Hardness: " + sumHardness();
    }
}
