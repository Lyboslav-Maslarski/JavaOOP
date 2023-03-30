package ExamPreparation.Exam15August2021.restaurant.io;

import ExamPreparation.Exam15August2021.restaurant.io.interfaces.OutputWriter;

public class ConsoleWriter implements OutputWriter {
    public void writeLine(String text) {
        System.out.println(text);
    }
}
