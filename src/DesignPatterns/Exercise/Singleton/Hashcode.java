package DesignPatterns.Exercise.Singleton;

public class Hashcode {
    private static Hashcode instance;

    private Hashcode() {
    }

    public static Hashcode getInstance() {
        if (instance == null) {
            instance = new Hashcode();
        }
        return instance;
    }
}
