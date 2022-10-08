package InterfacesAndAbstraction.Exercise.MilitaryElite;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class EngineerImpl extends SpecialisedSoldierImpl {
    private List<Repair> repairs;

    public EngineerImpl(int id, String firstName, String lastName, double salary, String corps) {
        super(id, firstName, lastName, salary, corps);
        this.repairs = new ArrayList<>();
    }

    public void addRepair(Repair repair) {
        repairs.add(repair);
    }

    public Collection<Repair> getRepairs() {
        return repairs;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(super.toString()).append(System.lineSeparator());
        stringBuilder.append(String.format("Corps: %s%n",corps));
        stringBuilder.append("Repairs:").append(System.lineSeparator());
        for (Repair repair : repairs) {
            stringBuilder.append(String.format("  %s%n", repair.toString()));
        }
        return stringBuilder.toString().trim();
    }
}
