package Encapsulation.Exercise.FootballTeamGenerator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        Map<String, Team> teamMap = new HashMap<>();

        String input = reader.readLine();

        while (!"END".equals(input)) {

            String[] parts = input.split(";");
            String teamName = parts[1];

            if (parts[0].equals("Team")) {
                try {
                    Team team = new Team(teamName);
                    teamMap.put(teamName, team);
                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                }
            } else if (parts[0].equals("Add")) {
                if (teamMap.containsKey(teamName)) {
                    try {
                        Player player = new Player(parts[2], Integer.parseInt(parts[3]), Integer.parseInt(parts[4])
                                , Integer.parseInt(parts[5]), Integer.parseInt(parts[6]), Integer.parseInt(parts[7]));
                        teamMap.get(teamName).addPlayer(player);
                    } catch (IllegalArgumentException e) {
                        System.out.println(e.getMessage());
                    }

                } else {
                    System.out.println("Team " + teamName + " does not exist.");
                }

            } else if (parts[0].equals("Remove")) {
                try {
                    teamMap.get(teamName).removePlayer(parts[2]);
                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                }

            } else if (parts[0].equals("Rating")) {
                if (teamMap.containsKey(teamName)) {
                    System.out.println(teamName + " - " + Math.round(teamMap.get(teamName).getRating()));
                } else {
                    System.out.println("Team " + teamName + " does not exist.");
                }
            }

            input = reader.readLine();
        }
    }
}
