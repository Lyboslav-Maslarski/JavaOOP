package Reflection.Lab.Reflect;

import Reflection.Lab.Reflection;

import java.lang.reflect.InvocationTargetException;

public class Main {
    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Class<Reflection> aClass = Reflection.class;
        System.out.println(aClass);
        Class<? super Reflection> superclass = aClass.getSuperclass();
        System.out.println(superclass);
        Class<?>[] interfaces = aClass.getInterfaces();
        for (Class<?> anInterface : interfaces) {
            System.out.println(anInterface);
        }
        Reflection reflection = aClass.getDeclaredConstructor().newInstance();
        System.out.println(reflection);
    }
}
