package WorkingWithAbstraction.Lab.StudentSystem;

import java.util.HashMap;
import java.util.Map;

public class StudentSystem {
    private Map<String, Student> students;

    public StudentSystem() {
        this.students = new HashMap<>();
    }

    public Map<String, Student> getStudents() {
        return this.students;
    }

    public void ParseCommand(String[] args) {

        String name = args[1];
        if (args[0].equals("Create")) {
            int age = Integer.parseInt(args[2]);
            double grade = Double.parseDouble(args[3]);
            if (!students.containsKey(name)) {
                Student student = new Student(name, age, grade);
                students.put(name, student);
            }
        } else if (args[0].equals("Show")) {
            if (students.containsKey(name)) {
                Student student = students.get(name);
                String studentInfo = student.getInfo();

                if (student.getGrade() >= 5.00) {
                    studentInfo += " Excellent student.";
                } else if (student.getGrade() < 5.00 && student.getGrade() >= 3.50) {
                    studentInfo += " Average student.";
                } else {
                    studentInfo += " Very nice person.";
                }

                System.out.println(studentInfo);
            }
        }
    }
}
