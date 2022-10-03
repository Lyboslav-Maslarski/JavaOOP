package Encapsulation.Exercise.ShoppingSpree;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Person {
    private String name;
    private double money;
    private List<Product> products;

    public Person(String name, double money) {
        setName(name);
        setMoney(money);
        products = new ArrayList<>();
    }

    private void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be empty");
        }
        this.name = name;
    }

    private void setMoney(double money) {
        if (money < 0) {
            throw new IllegalArgumentException("Money cannot be negative");
        }
        this.money = money;
    }

    public String getName() {
        return name;
    }

    public void buyProduct(Product product) {
        if (this.money < product.getCost()) {
            throw new IllegalArgumentException(getName() + " can't afford " + product.getName());
        } else {
            this.money -= product.getCost();
            this.products.add(product);
        }
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append(getName()).append(" - ");

        if (products.size() == 0) {
            stringBuilder.append("Nothing bought");
        } else {
            List<String> list = products.stream().map(Product::getName).collect(Collectors.toList());
            stringBuilder.append(String.join(", ", list));
        }

        return stringBuilder.toString();
    }
}
