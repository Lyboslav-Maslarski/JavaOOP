package Polymorphism.Exercise.calculator;

import java.util.ArrayDeque;

public class MemoryRecallOperation implements Operation {
    private ArrayDeque<Integer> memory;

    public MemoryRecallOperation(ArrayDeque<Integer> memory) {
        this.memory = memory;
    }

    @Override
    public void addOperand(int operand) {

    }

    @Override
    public boolean isCompleted() {
        return this.memory.size() != 0;
    }

    @Override
    public int getResult() {
        return memory.pop();
    }
}
