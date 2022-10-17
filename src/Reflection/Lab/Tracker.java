package Reflection.Lab;

import java.lang.reflect.Method;

public class Tracker {
    @Author(name = "George")
    public static void main(String[] args) {
        Tracker.printMethodsByAuthor(Tracker.class);
    }

    @Author(name = "Peter")
    public static void printMethodsByAuthor(Class<?> clazz) {
        Method[] methods = clazz.getDeclaredMethods();
        for (Method method : methods) {
            String author = method.getAnnotation(Author.class).name();
            System.out.printf("%s: %s()%n", author, method.getName());
        }
    }
}
