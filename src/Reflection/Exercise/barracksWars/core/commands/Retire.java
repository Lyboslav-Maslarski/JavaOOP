package Reflection.Exercise.barracksWars.core.commands;

import Reflection.Exercise.barracksWars.interfaces.Repository;
import jdk.jshell.spi.ExecutionControl;

public class Retire extends Command {
    @Inject
    private Repository repository;

    protected Retire(String[] data) {
        super(data);
    }

    @Override
    public String execute() throws ExecutionControl.NotImplementedException {
        String unitType = getData()[1];
        this.repository.removeUnit(unitType);
        return unitType + " retired!";
    }
}
