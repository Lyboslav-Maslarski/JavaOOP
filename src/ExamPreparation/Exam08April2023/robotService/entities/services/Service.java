package ExamPreparation.Exam08April2023.robotService.entities.services;

import ExamPreparation.Exam08April2023.robotService.entities.robot.Robot;
import ExamPreparation.Exam08April2023.robotService.entities.supplements.Supplement;

import java.util.Collection;

public interface Service {
    String getName();

    void setName(String name);

    Collection<Robot> getRobots();

    Collection<Supplement> getSupplements();

    void addRobot(Robot robot);

    void removeRobot(Robot robot);

    void addSupplement(Supplement supplement);

    void feeding();

    int sumHardness();

    String getStatistics();
}
