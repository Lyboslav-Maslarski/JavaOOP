package ExamPreparation.Exam15August2021.restaurant;

import ExamPreparation.Exam15August2021.restaurant.core.ControllerImpl;
import ExamPreparation.Exam15August2021.restaurant.core.EngineImpl;
import ExamPreparation.Exam15August2021.restaurant.core.interfaces.Controller;
import ExamPreparation.Exam15August2021.restaurant.entities.drinks.interfaces.Beverages;
import ExamPreparation.Exam15August2021.restaurant.entities.healthyFoods.interfaces.HealthyFood;
import ExamPreparation.Exam15August2021.restaurant.entities.tables.interfaces.Table;
import ExamPreparation.Exam15August2021.restaurant.io.ConsoleReader;
import ExamPreparation.Exam15August2021.restaurant.io.ConsoleWriter;
import ExamPreparation.Exam15August2021.restaurant.repositories.interfaces.*;

public class Main {
    public static void main(String[] args) {

        HealthFoodRepository<HealthyFood> healthFoodRepository = new HealthFoodRepositoryImpl();
        BeverageRepository<Beverages> beverageRepository = new BeverageRepositoryImpl();
        TableRepository<Table> tableRepository = new TableRepositoryImpl();


        Controller controller = new ControllerImpl(healthFoodRepository, beverageRepository, tableRepository);

        ConsoleReader reader = new ConsoleReader();
        ConsoleWriter writer = new ConsoleWriter();
        EngineImpl engine = new EngineImpl(reader, writer, controller);
        engine.run();

    }
}
