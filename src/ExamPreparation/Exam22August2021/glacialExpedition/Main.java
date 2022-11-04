package ExamPreparation.Exam22August2021.glacialExpedition;

import ExamPreparation.Exam22August2021.glacialExpedition.core.Controller;
import ExamPreparation.Exam22August2021.glacialExpedition.core.ControllerImpl;
import ExamPreparation.Exam22August2021.glacialExpedition.core.Engine;
import ExamPreparation.Exam22August2021.glacialExpedition.core.EngineImpl;

public class Main {

    public static void main(String[] args) {
        Controller controller = new ControllerImpl();
        Engine engine = new EngineImpl(controller);
        engine.run();
    }
}
