package ExamPreparation.Exam08April2023.robotService;

import ExamPreparation.Exam08April2023.robotService.core.Engine;
import ExamPreparation.Exam08April2023.robotService.core.EngineImpl;

public class Main {
    public static void main(String[] args) {

        Engine engine = new EngineImpl();
        engine.run();
    }
}
