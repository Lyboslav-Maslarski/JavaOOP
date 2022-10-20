package Reflection.Exercise.barracksWars;


import Reflection.Exercise.barracksWars.core.Engine;
import Reflection.Exercise.barracksWars.core.commands.CommandInterpreterImpl;
import Reflection.Exercise.barracksWars.core.factories.UnitFactoryImpl;
import Reflection.Exercise.barracksWars.data.UnitRepository;
import Reflection.Exercise.barracksWars.interfaces.CommandInterpreter;
import Reflection.Exercise.barracksWars.interfaces.Repository;
import Reflection.Exercise.barracksWars.interfaces.Runnable;
import Reflection.Exercise.barracksWars.interfaces.UnitFactory;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        Repository repository = new UnitRepository();
        UnitFactory unitFactory = new UnitFactoryImpl();
        CommandInterpreter commandInterpreter = new CommandInterpreterImpl(repository, unitFactory);

        Runnable engine = new Engine(commandInterpreter);
        engine.run();
    }
}
