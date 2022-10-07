package InterfacesAndAbstraction.Exercise.CollectionHierarchy;

public class MyListImpl extends Collection implements MyList {
    @Override
    public String remove() {
        return this.items.remove(0);
    }

    @Override
    public int add(String str) {
        this.items.add(0, str);
        return this.items.indexOf(str);
    }

    @Override
    public int getUsed() {
        return this.items.size();
    }
}
