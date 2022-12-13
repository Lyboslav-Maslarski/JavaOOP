package ExamPreparation.Exam22August2022.goldDigger;

import ExamPreparation.Exam22August2022.goldDigger.core.Controller;
import ExamPreparation.Exam22August2022.goldDigger.core.ControllerImpl;
import ExamPreparation.Exam22August2022.goldDigger.core.Engine;
import ExamPreparation.Exam22August2022.goldDigger.core.EngineImpl;

public class Main {

    public static void main(String[] args) {
        Controller controller = new ControllerImpl();
        Engine engine = new EngineImpl(controller);
        engine.run();
    }
}
