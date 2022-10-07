package InterfacesAndAbstraction.Exercise.CollectionHierarchy;

import java.util.ArrayList;
import java.util.List;

public abstract class Collection {
    private final static int MAX_SIZE = 100;
    protected List<String> items;

    public Collection() {
        this.items = new ArrayList<>(MAX_SIZE);
    }
}
