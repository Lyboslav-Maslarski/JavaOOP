package InterfacesAndAbstraction.Exercise.MilitaryElite;

public class SpyImpl extends SoldierImpl {
    private String codeNumber;

    public SpyImpl(int id, String firstName, String lastName, String codeNumber) {
        super(id, firstName, lastName);
        this.codeNumber = codeNumber;
    }

    @Override
    public String toString() {
        return String.format("%s%nCode Number: %s", super.toString(), codeNumber);
    }
}
