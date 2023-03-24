package ExamPreparation.Exam12Dec2020.bakery.io;


import ExamPreparation.Exam12Dec2020.bakery.io.interfaces.OutputWriter;

public class ConsoleWriter implements OutputWriter {
    public void writeLine(String text) {
        System.out.println(text);
    }
}
