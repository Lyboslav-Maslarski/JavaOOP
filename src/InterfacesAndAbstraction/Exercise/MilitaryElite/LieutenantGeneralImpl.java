package InterfacesAndAbstraction.Exercise.MilitaryElite;

import java.util.ArrayList;
import java.util.List;

public class LieutenantGeneralImpl extends PrivateImpl {

    private List<PrivateImpl> privates;

    public LieutenantGeneralImpl(int id, String firstName, String lastName, double salary) {
        super(id, firstName, lastName, salary);
        this.privates = new ArrayList<>();
    }

    public List<PrivateImpl> getPrivates() {
        return privates;
    }

    public void addPrivate(PrivateImpl priv) {
        privates.add(priv);
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(super.toString()).append(System.lineSeparator());
        stringBuilder.append("Privates:").append(System.lineSeparator());
        privates.sort((p1, p2) -> p2.getId() - p1.getId());
        for (PrivateImpl aPrivate : privates) {
            stringBuilder.append(String.format("  %s%n", aPrivate.toString()));
        }
        return stringBuilder.toString().trim();
    }
}
