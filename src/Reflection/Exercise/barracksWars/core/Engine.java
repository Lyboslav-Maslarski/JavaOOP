package Reflection.Exercise.barracksWars.core;


import Reflection.Exercise.barracksWars.interfaces.CommandInterpreter;
import Reflection.Exercise.barracksWars.interfaces.Executable;
import Reflection.Exercise.barracksWars.interfaces.Runnable;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Engine implements Runnable {

    private final CommandInterpreter commandInterpreter;

    public Engine(CommandInterpreter commandInterpreter) {
        this.commandInterpreter = commandInterpreter;
    }

    @Override
    public void run() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String input;
        while (!"fight".equals(input = reader.readLine())) {
            try {
                String[] data = input.split("\\s+");
                String commandName = data[0];
                Executable command = this.commandInterpreter.interpretCommand(data, commandName);
                System.out.println(command.execute());
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
