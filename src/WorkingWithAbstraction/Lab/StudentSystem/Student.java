package WorkingWithAbstraction.Lab.StudentSystem;

public class Student {
    private String name;
    private int age;
    private double grade;

    public Student(String name, int age, double grade) {
        this.name = name;
        this.age = age;
        this.grade = grade;
    }

    public double getGrade() {
        return grade;
    }

    public String getInfo() {
        return String.format("%s is %d years old.", this.name, this.age);
    }
}
