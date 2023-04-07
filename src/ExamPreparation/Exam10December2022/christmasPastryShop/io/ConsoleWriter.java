package ExamPreparation.Exam10December2022.christmasPastryShop.io;

import ExamPreparation.Exam10December2022.christmasPastryShop.io.interfaces.OutputWriter;

public class ConsoleWriter implements OutputWriter {
    public void writeLine(String text) {
        System.out.println(text);
    }
}
