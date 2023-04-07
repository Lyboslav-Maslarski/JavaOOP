package ExamPreparation.Exam10December2022.christmasPastryShop;

import ExamPreparation.Exam10December2022.christmasPastryShop.core.interfaces.Controller;
import ExamPreparation.Exam10December2022.christmasPastryShop.entities.booths.interfaces.Booth;
import ExamPreparation.Exam10December2022.christmasPastryShop.entities.cocktails.interfaces.Cocktail;
import ExamPreparation.Exam10December2022.christmasPastryShop.entities.delicacies.interfaces.Delicacy;
import ExamPreparation.Exam10December2022.christmasPastryShop.repositories.BoothRepositoryImpl;
import ExamPreparation.Exam10December2022.christmasPastryShop.repositories.CocktailRepositoryImpl;
import ExamPreparation.Exam10December2022.christmasPastryShop.repositories.DelicacyRepositoryImpl;
import ExamPreparation.Exam10December2022.christmasPastryShop.repositories.interfaces.BoothRepository;
import ExamPreparation.Exam10December2022.christmasPastryShop.repositories.interfaces.CocktailRepository;
import ExamPreparation.Exam10December2022.christmasPastryShop.repositories.interfaces.DelicacyRepository;
import ExamPreparation.Exam10December2022.christmasPastryShop.core.ControllerImpl;
import ExamPreparation.Exam10December2022.christmasPastryShop.core.EngineImpl;
import ExamPreparation.Exam10December2022.christmasPastryShop.io.ConsoleReader;
import ExamPreparation.Exam10December2022.christmasPastryShop.io.ConsoleWriter;

public class Main {
    public static void main(String[] args) {

        DelicacyRepository<Delicacy> delicacyRepository = new DelicacyRepositoryImpl<>();
        CocktailRepository<Cocktail> cocktailRepository = new CocktailRepositoryImpl<>();
        BoothRepository<Booth> boothRepository = new BoothRepositoryImpl<>();

        Controller controller = new ControllerImpl(delicacyRepository, cocktailRepository, boothRepository);


        ConsoleReader reader = new ConsoleReader();
        ConsoleWriter writer = new ConsoleWriter();
        EngineImpl engine = new EngineImpl(reader, writer, controller);
        engine.run();

    }
}
