package Reflection.Exercise.blackBoxInteger;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchFieldException {
        Scanner scan = new Scanner(System.in);

        Class<BlackBoxInt> aClass = BlackBoxInt.class;

        Constructor<BlackBoxInt> constructor = aClass.getDeclaredConstructor(int.class);
        constructor.setAccessible(true);
        BlackBoxInt blackBoxInt = constructor.newInstance(0);


        Map<String, Method> methods = Arrays.stream(aClass.getDeclaredMethods())
                .peek(method -> method.setAccessible(true))
                .collect(Collectors.toMap(Method::getName, m -> m));

        String command = scan.nextLine();
        while (!"END".equals(command)) {

            String[] commandParts = command.split("_");
            Method method = methods.get(commandParts[0]);
            int value = Integer.parseInt(commandParts[1]);

            method.invoke(blackBoxInt, value);
            Field field = blackBoxInt.getClass().getDeclaredField("innerValue");
            field.setAccessible(true);

            System.out.println(field.get(blackBoxInt));

            command = scan.nextLine();
        }

    }
}
