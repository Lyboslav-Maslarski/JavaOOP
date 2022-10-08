package InterfacesAndAbstraction.Exercise.MilitaryElite;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        Map<Integer, PrivateImpl> privates = new HashMap<>();
        String input = scan.nextLine();

        while (!input.equals("End")) {
            String[] data = input.split("\\s+");
            int id = Integer.parseInt(data[1]);
            String firstName = data[2];
            String lastName = data[3];
            switch (data[0]) {
                case "Private":
                    double privateSalary = Double.parseDouble(data[4]);
                    PrivateImpl priv = new PrivateImpl(id, firstName, lastName, privateSalary);
                    privates.put(id, priv);
                    System.out.println(priv);
                    break;
                case "Commando":
                    try {
                        double commandoSalary = Double.parseDouble(data[4]);
                        String commandoCorps = data[5];
                        CommandoImpl commando = new CommandoImpl(id, firstName, lastName, commandoSalary, commandoCorps);
                        for (int i = 6; i < data.length; i += 2) {
                            String missionName = data[i];
                            String missionState = data[i + 1];
                            try {
                                Mission mission = new Mission(missionName, missionState);
                                commando.addMission(mission);
                            } catch (IllegalStateException e) {
                            }
                        }
                        System.out.println(commando);
                    } catch (IllegalStateException e) {
                        break;
                    }
                    break;
                case "LieutenantGeneral":
                    double lieutenantGeneralSalary = Double.parseDouble(data[4]);
                    LieutenantGeneralImpl lieutenantGeneral = new LieutenantGeneralImpl(id, firstName, lastName, lieutenantGeneralSalary);
                    for (int i = 5; i < data.length; i++) {
                        int privateId = Integer.parseInt(data[i]);
                        PrivateImpl aPrivate = privates.get(privateId);
                        lieutenantGeneral.addPrivate(aPrivate);
                    }
                    System.out.println(lieutenantGeneral);
                    break;
                case "Engineer":
                    double engineerSalary = Double.parseDouble(data[4]);
                    String engineerCorps = data[5];
                    try {
                        EngineerImpl engineer = new EngineerImpl(id, firstName, lastName, engineerSalary, engineerCorps);
                        for (int i = 6; i < data.length; i += 2) {
                            String partName = data[i];
                            int hours = Integer.parseInt(data[i + 1]);
                            Repair repair = new Repair(partName, hours);
                            engineer.addRepair(repair);
                        }
                        System.out.println(engineer);
                    } catch (IllegalStateException e) {
                        break;
                    }
                    break;
                case "Spy":
                    String codeNumber = data[4];
                    SpyImpl spy = new SpyImpl(id, firstName, lastName, codeNumber);
                    System.out.println(spy);
                    break;
            }
            input = scan.nextLine();
        }
    }
}
