package ExamPreparation.Exam22Aug2020.easterRaces.entities.drivers;

import ExamPreparation.Exam22Aug2020.easterRaces.entities.cars.Car;

public interface Driver {
    String getName();

    Car getCar();

    int getNumberOfWins();

    void addCar(Car car);

    void winRace();

    boolean getCanParticipate();
}
