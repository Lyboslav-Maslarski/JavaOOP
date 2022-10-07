package InterfacesAndAbstraction.Exercise.CollectionHierarchy;

public class AddCollection extends Collection implements Addable{

    @Override
    public int add(String str) {
        this.items.add(str);
        return this.items.indexOf(str);
    }
}
