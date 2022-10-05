package Inheritance.Lab;

import java.util.ArrayList;
import java.util.Random;

public class RandomArrayList extends ArrayList<Object> {
    public Object getRandomElement() {
        Random random = new Random();
        int index = random.nextInt(size() - 1);
        return remove(index);
    }
}
