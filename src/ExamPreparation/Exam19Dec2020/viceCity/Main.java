package ExamPreparation.Exam19Dec2020.viceCity;

import ExamPreparation.Exam19Dec2020.viceCity.core.interfaces.Engine;
import ExamPreparation.Exam19Dec2020.viceCity.core.ControllerImpl;
import ExamPreparation.Exam19Dec2020.viceCity.core.EngineImpl;
import ExamPreparation.Exam19Dec2020.viceCity.core.interfaces.Controller;

public class Main {
    public static void main(String[] args) {
        Controller controller = new ControllerImpl();
        Engine engine = new EngineImpl(controller);
        engine.run();
    }
}
