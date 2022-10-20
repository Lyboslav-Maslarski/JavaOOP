package Reflection.Exercise.barracksWars.core.commands;

import Reflection.Exercise.barracksWars.interfaces.CommandInterpreter;
import Reflection.Exercise.barracksWars.interfaces.Executable;
import Reflection.Exercise.barracksWars.interfaces.Repository;
import Reflection.Exercise.barracksWars.interfaces.UnitFactory;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

public class CommandInterpreterImpl implements CommandInterpreter {
    private static final String COMMAND_PACKAGE = "Reflection.Exercise.barracksWars.core.commands.";
    private Repository repository;
    private UnitFactory unitFactory;

    public CommandInterpreterImpl(Repository repository, UnitFactory unitFactory) {
        this.repository = repository;
        this.unitFactory = unitFactory;
    }

    @Override
    @SuppressWarnings("all")
    public Executable interpretCommand(String[] data, String commandName) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        String commandClassName = Character.toUpperCase(commandName.charAt(0)) + commandName.substring(1);

        Executable executable = null;
        try {
            Class<? extends Executable> commandClass = (Class<? extends Executable>) Class.forName(COMMAND_PACKAGE + commandClassName);

            Constructor<? extends Executable> constructor = commandClass.getDeclaredConstructor(String[].class);
            constructor.setAccessible(true);

            executable = constructor.newInstance(new Object[]{data});

            Field[] wantedFields = executable.getClass().getDeclaredFields();

            Field[] currentFields = this.getClass().getDeclaredFields();

            for (Field wantedField : wantedFields) {
                if (wantedField.isAnnotationPresent(Inject.class)) {
                    for (Field currentField : currentFields) {
                        if (wantedField.getType().equals(currentField.getType())) {
                            wantedField.setAccessible(true);
                            wantedField.set(executable, currentField.get(this));
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return executable;
    }
}
