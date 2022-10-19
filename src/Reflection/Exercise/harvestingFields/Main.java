package Reflection.Exercise.harvestingFields;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        Class<RichSoilLand> aClass = RichSoilLand.class;

        Field[] fields = aClass.getDeclaredFields();

        String predicate = scan.nextLine();
        while (!"HARVEST".equals(predicate)) {
            switch (predicate) {
                case "private":
                    Stream<Field> privateFields = Arrays.stream(fields).filter(f -> Modifier.isPrivate(f.getModifiers()));
                    printFields(privateFields);
                    break;
                case "protected":
                    Stream<Field> protectedFields = Arrays.stream(fields).filter(f -> Modifier.isProtected(f.getModifiers()));
                    printFields(protectedFields);
                    break;
                case "public":
                    Stream<Field> publicFields = Arrays.stream(fields).filter(f -> Modifier.isPublic(f.getModifiers()));
                    printFields(publicFields);
                    break;
                case "all":
                    Stream<Field> allFields = Arrays.stream(fields);
                    printFields(allFields);
                    break;
                default:
                    throw new IllegalArgumentException("No such command");
            }
            predicate = scan.nextLine();
        }
    }

    public static void printFields(Stream<Field> fieldStream) {
        fieldStream.forEach(f -> System.out.printf("%s %s %s%n", Modifier.toString(f.getModifiers()), f.getType().getSimpleName(), f.getName()));
    }
}
