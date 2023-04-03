package ExamPreparation.Exam20December2021.christmasRaces.io;

import ExamPreparation.Exam20December2021.christmasRaces.io.interfaces.OutputWriter;

public class ConsoleWriter implements OutputWriter {
    @Override
    public void writeLine(String text) {
        System.out.println(text);
    }
}
