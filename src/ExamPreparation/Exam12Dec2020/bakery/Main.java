package ExamPreparation.Exam12Dec2020.bakery;

import ExamPreparation.Exam12Dec2020.bakery.core.ControllerImpl;
import ExamPreparation.Exam12Dec2020.bakery.core.interfaces.Controller;
import ExamPreparation.Exam12Dec2020.bakery.entities.bakedFoods.interfaces.BakedFood;
import ExamPreparation.Exam12Dec2020.bakery.entities.drinks.interfaces.Drink;
import ExamPreparation.Exam12Dec2020.bakery.entities.tables.interfaces.Table;
import ExamPreparation.Exam12Dec2020.bakery.repositories.DrinkRepositoryImpl;
import ExamPreparation.Exam12Dec2020.bakery.repositories.FoodRepositoryImpl;
import ExamPreparation.Exam12Dec2020.bakery.repositories.TableRepositoryImpl;
import ExamPreparation.Exam12Dec2020.bakery.repositories.interfaces.DrinkRepository;
import ExamPreparation.Exam12Dec2020.bakery.repositories.interfaces.FoodRepository;
import ExamPreparation.Exam12Dec2020.bakery.repositories.interfaces.TableRepository;
import ExamPreparation.Exam12Dec2020.bakery.core.EngineImpl;
import ExamPreparation.Exam12Dec2020.bakery.io.ConsoleReader;
import ExamPreparation.Exam12Dec2020.bakery.io.ConsoleWriter;

public class Main {
    public static void main(String[] args) {

        FoodRepository<BakedFood> foodRepository = new FoodRepositoryImpl();
        DrinkRepository<Drink> drinkRepository = new DrinkRepositoryImpl();
        TableRepository<Table> tableRepository = new TableRepositoryImpl();

        Controller controller = new ControllerImpl(foodRepository, drinkRepository, tableRepository);


        ConsoleReader reader = new ConsoleReader();
        ConsoleWriter writer = new ConsoleWriter();
        EngineImpl engine = new EngineImpl(reader, writer, controller);
        engine.run();
    }
}
