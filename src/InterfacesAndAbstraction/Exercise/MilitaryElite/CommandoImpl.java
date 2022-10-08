package InterfacesAndAbstraction.Exercise.MilitaryElite;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CommandoImpl extends SpecialisedSoldierImpl {
    private List<Mission> missions;

    public CommandoImpl(int id, String firstName, String lastName, double salary, String corps) {
        super(id, firstName, lastName, salary, corps);
        this.missions = new ArrayList<>();
    }

    public void addMission(Mission mission) {
        missions.add(mission);
    }

    public Collection<Mission> getMissions() {
        return missions;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(super.toString()).append(System.lineSeparator());
        stringBuilder.append(String.format("Corps: %s%n", corps));
        stringBuilder.append("Missions:").append(System.lineSeparator());
        for (Mission mission : missions) {
            stringBuilder.append(String.format("  %s%n", mission.toString()));
        }
        return stringBuilder.toString().trim();
    }
}
