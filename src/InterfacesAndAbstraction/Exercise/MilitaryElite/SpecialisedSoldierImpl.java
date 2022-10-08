package InterfacesAndAbstraction.Exercise.MilitaryElite;

public abstract class SpecialisedSoldierImpl extends PrivateImpl {
    protected Corps corps;

    public SpecialisedSoldierImpl(int id, String firstName, String lastName, double salary, String corps) {
        super(id, firstName, lastName, salary);
        setCorps(corps);
    }

    public void setCorps(String corps) {
        if ("Airforces".equals(corps) || "Marines".equals(corps)) {
            this.corps = Corps.valueOf(corps);
        }else {
            throw new IllegalStateException();
        }
    }
}
