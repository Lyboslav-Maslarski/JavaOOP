package Reflection.Exercise.barracksWars.core.commands;

import Reflection.Exercise.barracksWars.interfaces.Executable;

public abstract class Command implements Executable {
    private final String[] data;

    protected Command(String[] data) {
        this.data = data;
    }

    protected String[] getData() {
        return data;
    }
}