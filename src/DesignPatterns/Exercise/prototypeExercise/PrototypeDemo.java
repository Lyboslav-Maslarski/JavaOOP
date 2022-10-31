package DesignPatterns.Exercise.prototypeExercise;

public class PrototypeDemo {
    public static void main(String[] args) {
        EmployeeRecord employee1 = new EmployeeRecord(0, "Pesho", "Golemiq", 1500, "Sofia");
        employee1.showRecord();
        EmployeeRecord employee2 = employee1;
        employee2.setId(1);
        employee1.showRecord();
        employee2.showRecord();
        EmployeeRecord clone = employee1.getClone();
        clone.setId(2);
        employee1.showRecord();
        employee2.showRecord();
        clone.showRecord();
    }
}
