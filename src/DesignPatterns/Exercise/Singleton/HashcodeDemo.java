package DesignPatterns.Exercise.Singleton;

public class HashcodeDemo {
    public static void main(String[] args) {
        Hashcode hashcode = Hashcode.getInstance();
        System.out.println(hashcode.hashCode());
        Hashcode hashcode1 = Hashcode.getInstance();
        System.out.println(hashcode1.hashCode());
        Hashcode hashcode2 = Hashcode.getInstance();
        System.out.println(hashcode2.hashCode());
    }
}
