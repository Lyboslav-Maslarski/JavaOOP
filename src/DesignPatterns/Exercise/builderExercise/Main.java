package DesignPatterns.Exercise.builderExercise;

public class Main {

    public static void main(String[] args) {
        Contact pesho = Contact.builder()
                .withName("pesho")
                .withNumber("12444")
                .build();

        System.out.println(pesho);
    }
}
