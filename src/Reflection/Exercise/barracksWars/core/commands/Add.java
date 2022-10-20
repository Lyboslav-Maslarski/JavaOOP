package Reflection.Exercise.barracksWars.core.commands;

import Reflection.Exercise.barracksWars.interfaces.Repository;
import Reflection.Exercise.barracksWars.interfaces.Unit;
import Reflection.Exercise.barracksWars.interfaces.UnitFactory;
import jdk.jshell.spi.ExecutionControl;

import java.lang.reflect.InvocationTargetException;

public class Add extends Command {
    @Inject
    private UnitFactory unitFactory;
    @Inject
    private Repository repository;

    protected Add(String[] data) {
        super(data);
    }

    @Override
    public String execute() throws ExecutionControl.NotImplementedException, ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        String unitType = super.getData()[1];

        Unit unitToAdd = this.unitFactory.createUnit(unitType);

        this.repository.addUnit(unitToAdd);

        return unitType + " added!";
    }
}
