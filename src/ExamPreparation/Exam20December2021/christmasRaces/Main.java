package ExamPreparation.Exam20December2021.christmasRaces;

import ExamPreparation.Exam20December2021.christmasRaces.entities.drivers.Driver;
import ExamPreparation.Exam20December2021.christmasRaces.entities.races.Race;
import ExamPreparation.Exam20December2021.christmasRaces.io.ConsoleReader;
import ExamPreparation.Exam20December2021.christmasRaces.core.ControllerImpl;
import ExamPreparation.Exam20December2021.christmasRaces.core.EngineImpl;
import ExamPreparation.Exam20December2021.christmasRaces.core.interfaces.Controller;
import ExamPreparation.Exam20December2021.christmasRaces.entities.cars.Car;
import ExamPreparation.Exam20December2021.christmasRaces.io.ConsoleWriter;
import ExamPreparation.Exam20December2021.christmasRaces.repositories.CarRepository;
import ExamPreparation.Exam20December2021.christmasRaces.repositories.DriverRepository;
import ExamPreparation.Exam20December2021.christmasRaces.repositories.RaceRepository;
import ExamPreparation.Exam20December2021.christmasRaces.repositories.interfaces.Repository;

public class Main {
    public static void main(String[] args) {
        Repository<Car> carRepository = new CarRepository();
        Repository<Race> raceRepository = new RaceRepository();
        Repository<Driver> driverRepository = new DriverRepository();

        Controller controller = new ControllerImpl(driverRepository, carRepository, raceRepository);

        ConsoleReader reader = new ConsoleReader();
        ConsoleWriter writer = new ConsoleWriter();
        EngineImpl engine = new EngineImpl(reader, writer, controller);
        engine.run();
    }
}
