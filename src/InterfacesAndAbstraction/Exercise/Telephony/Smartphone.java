package InterfacesAndAbstraction.Exercise.Telephony;

import java.util.List;

public class Smartphone implements Callable, Browsable {
    private List<String> numbers;
    private List<String> urls;

    public Smartphone(List<String> numbers, List<String> urls) {
        this.numbers = numbers;
        this.urls = urls;
    }

    @Override
    public String browse() {
        StringBuilder stringBuilder = new StringBuilder();
        for (String url : urls) {
            if (!hasNumbers(url)) {
                stringBuilder.append("Browsing: ").append(url).append("!").append(System.lineSeparator());
            } else {
                stringBuilder.append("Invalid URL!").append(System.lineSeparator());
            }
        }
        return stringBuilder.toString().trim();
    }

    @Override
    public String call() {
        StringBuilder stringBuilder = new StringBuilder();
        for (String number : numbers) {
            if (hasOnlyNumbers(number)) {
                stringBuilder.append("Calling... ").append(number).append(System.lineSeparator());
            } else {
                stringBuilder.append("Invalid number!").append(System.lineSeparator());
            }
        }
        return stringBuilder.toString().trim();
    }

    private boolean hasNumbers(String str) {
        for (int i = 0; i < str.length(); i++) {
            if (Character.isDigit(str.charAt(i))) {
                return true;
            }
        }
        return false;
    }

    private boolean hasOnlyNumbers(String str) {
        for (int i = 0; i < str.length(); i++) {
            if (!Character.isDigit(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }
}
