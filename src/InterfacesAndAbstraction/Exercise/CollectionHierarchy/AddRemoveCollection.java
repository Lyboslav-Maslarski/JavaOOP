package InterfacesAndAbstraction.Exercise.CollectionHierarchy;

public class AddRemoveCollection extends Collection implements AddRemovable {
    @Override
    public String remove() {
        return this.items.remove(this.items.size() - 1);
    }

    @Override
    public int add(String str) {
        this.items.add(0, str);
        return this.items.indexOf(str);
    }
}
