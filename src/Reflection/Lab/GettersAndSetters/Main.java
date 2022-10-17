package Reflection.Lab.GettersAndSetters;

import Reflection.Lab.Reflection;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Comparator;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        Class<Reflection> clazz = Reflection.class;
        Method[] methods = clazz.getDeclaredMethods();
        TreeSet<Method> getters = filterMethodsByName(methods, "get");
        TreeSet<Method> setters = filterMethodsByName(methods, "set");
        getters.forEach(m -> System.out.printf("%s will return class %s%n", m.getName(), m.getReturnType().getName()));
        setters.forEach(m -> System.out.printf("%s and will set field of class %s%n", m.getName(), m.getParameterTypes()[0].getName()));
    }

    private static TreeSet<Method> filterMethodsByName(Method[] methods, String predicate) {
        return Arrays.stream(methods)
                .filter(m -> m.getName().contains(predicate))
                .collect(Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(Method::getName))));
    }
}
