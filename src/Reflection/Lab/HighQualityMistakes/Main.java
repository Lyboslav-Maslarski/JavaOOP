package Reflection.Lab.HighQualityMistakes;

import Reflection.Lab.Reflection;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.Comparator;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        Class<Reflection> aClass = Reflection.class;
        Method[] methods = aClass.getDeclaredMethods();
        Field[] fields = aClass.getDeclaredFields();
        Arrays.sort(fields, Comparator.comparing(Field::getName));
        TreeSet<Method> getters = filterMethodsByName(methods, "get");
        TreeSet<Method> setters = filterMethodsByName(methods, "set");
        Arrays.stream(fields)
                .filter(field -> !Modifier.isPrivate(field.getModifiers()))
                .forEach(field -> System.out.printf("%s must be private!%n", field.getName()));
        getters.stream()
                .filter(method -> !Modifier.isPublic(method.getModifiers()))
                .forEach(method -> System.out.printf("%s have to be public!%n", method.getName()));
        setters.stream()
                .filter(method -> !Modifier.isPrivate(method.getModifiers()))
                .forEach(method -> System.out.printf("%s have to be private!%n", method.getName()));
    }

    private static TreeSet<Method> filterMethodsByName(Method[] methods, String predicate) {
        return Arrays.stream(methods)
                .filter(m -> m.getName().contains(predicate))
                .collect(Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(Method::getName))));
    }
}
