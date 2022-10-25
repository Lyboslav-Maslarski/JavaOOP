package TestDrivenDevelopment.Lab;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public class Instock implements ProductStock {

    private final List<Product> products;

    public Instock() {
        this.products = new ArrayList<>();
    }

    @Override
    public int getCount() {
        return products.size();
    }

    @Override
    public boolean contains(Product product) {
        return products.stream().anyMatch(p -> p.getLabel().equals(product.getLabel()));
    }

    @Override
    public void add(Product product) {
        products.add(product);
    }

    @Override
    public void changeQuantity(String label, int quantity) {
        Product product = findByLabel(label);

        product.setQuantity(product.getQuantity() + quantity);
    }

    @Override
    public Product find(int index) {
        return products.get(index);
    }

    @Override
    public Product findByLabel(String label) {
        return products.stream().filter(p -> p.getLabel().equals(label)).findAny().orElseThrow(IllegalArgumentException::new);

    }

    @Override
    public Iterable<Product> findFirstByAlphabeticalOrder(int count) {
        if (count > getCount()) {
            return new ArrayList<>();
        }
        return products.stream().sorted(Comparator.comparing(Product::getLabel))
                .limit(count)
                .collect(Collectors.toList());
    }

    @Override
    public Iterable<Product> findAllInRange(double low, double high) {
        return products.stream()
                .filter(p -> p.getPrice() > low && p.getPrice() <= high)
                .collect(Collectors.toList());
    }

    @Override
    public Iterable<Product> findAllByPrice(double price) {
        return products.stream()
                .filter(p -> p.getPrice() == price)
                .collect(Collectors.toList());
    }

    @Override
    public Iterable<Product> findFirstMostExpensiveProducts(int count) {
        if (count > getCount()) {
            throw new IllegalArgumentException();
        }
        return products.stream()
                .sorted(Comparator.comparing(Product::getPrice).reversed())
                .limit(count)
                .collect(Collectors.toList());
    }

    @Override
    public Iterable<Product> findAllByQuantity(int quantity) {
        return products.stream()
                .filter(p -> p.getQuantity() == quantity)
                .collect(Collectors.toList());
    }

    @Override
    public Iterator<Product> iterator() {
        return new Iterator<>() {
            int index = 0;

            @Override
            public boolean hasNext() {
                return index < getCount();
            }

            @Override
            public Product next() {
                return products.get(index++);
            }
        };
    }
}
