package TestDrivenDevelopment.Lab;

import org.junit.Before;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

public class InstockTest {
    private Instock instock;

    @Before
    public void setUp() {
        this.instock = new Instock();
    }

    @Test
    public void test_CountShouldBeZeroWhenInstockEmpty() {
        assertEquals(0, instock.getCount());
    }

    @Test
    public void test_CountShouldBeFiveWhenInstockHasFiveProducts() {
        int count = 5;
        addProductsToInstock(count);
        assertEquals(count, instock.getCount());
    }

    @Test
    public void test_ContainShouldReturnFalseWhenProductNotPresentOrTrueWhenPresent() {
        Product product = addProductsToInstock(5)[1];
        assertTrue(instock.contains(product));
        assertFalse(instock.contains(new Product("new product", 1, 1)));
    }

    @Test
    public void test_FindReturnsTheCorrectProduct() {
        Product expected = addProductsToInstock(5)[1];
        Product actual = instock.find(1);
        assertNotNull(actual);
        assertEquals(expected, actual);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void test_FindThrowExceptionWithNegativeIndex() {
        instock.find(-1);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void test_FindThrowExceptionWithGreaterThanLastIndex() {
        addProductsToInstock(5);
        instock.find(instock.getCount());
    }

    @Test
    public void test_ChangeQuantityShouldUpdateProduct() {
        Product product = new Product("test", 1, 10);
        instock.add(product);
        int quantityToAdd = 5;
        int expected = product.getQuantity() + quantityToAdd;
        instock.changeQuantity(product.getLabel(), quantityToAdd);
        assertEquals(expected, product.getQuantity());
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_ChangeQuantityThrowsWhenNoSuchProduct() {
        instock.changeQuantity("test", 5);
    }

    @Test
    public void test_FindByLabelShouldReturnProduct() {
        String label = "test";
        Product expected = new Product(label, 1, 10);
        instock.add(expected);
        Product actual = instock.findByLabel(label);
        assertNotNull(actual);
        assertEquals(expected.getLabel(), actual.getLabel());
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_FindByLabelThrowsWhenNoSuchProduct() {
        instock.findByLabel("test");
    }

    @Test
    public void test_FindFirstByAlphabeticalOrderShouldReturnTheCorrectProducts() {
        int productToTake = 5;
        List<Product> expectedProducts = Arrays.stream(addProductsToInstock(10))
                .sorted(Comparator.comparing(Product::getLabel))
                .limit(productToTake)
                .collect(Collectors.toList());

        Iterable<Product> iterable = instock.findFirstByAlphabeticalOrder(productToTake);
        List<Product> actualProducts = assertIterableNotNullAndConvertToList(iterable);

        assertEquals(expectedProducts, actualProducts);
    }

    @Test
    public void test_FindFirstByAlphabeticalOrderShouldReturnEmptyCollectionIfNotEnoughElements() {
        addProductsToInstock(10);

        Iterable<Product> iterable = instock.findFirstByAlphabeticalOrder(11);
        List<Product> actualProducts = assertIterableNotNullAndConvertToList(iterable);

        assertEquals(0, actualProducts.size());
    }

    @Test
    public void test_FindAllInPriceRangeShouldReturnAllProductsWithinGivenPriceRangeLowExclusiveHighInclusive() {
        Product[] addedProducts = addProductsToInstock(10);

        Product firstProduct = addedProducts[0];
        Product lastProduct = addedProducts[addedProducts.length - 1];
        double firstProductPrice = firstProduct.getPrice();
        double lastProductPrice = lastProduct.getPrice();

        Iterable<Product> iterable = instock.findAllInRange(firstProductPrice, lastProductPrice);
        List<Product> products = assertIterableNotNullAndConvertToList(iterable);

        assertEquals(addedProducts.length - 1, products.size());
    }

    @Test
    public void test_FindAllInPriceRangeShouldReturnEmptyCollectionWhenNoSuchElementIsPresent() {
        Iterable<Product> iterable = instock.findAllInRange(1, 10);
        List<Product> products = assertIterableNotNullAndConvertToList(iterable);

        assertEquals(0, products.size());
    }

    @Test
    public void test_FindAllByPriceShouldReturnAllProductsWithGivenPrice() {
        Product[] addedProducts = addProductsToInstock(10);
        Product expectedProduct = addedProducts[1];
        double expectedProductPrice = expectedProduct.getPrice();

        Iterable<Product> iterable = instock.findAllByPrice(expectedProductPrice);
        List<Product> actualProducts = assertIterableNotNullAndConvertToList(iterable);
        Product actualProduct = actualProducts.get(0);

        assertEquals(expectedProduct.getLabel(), actualProduct.getLabel());
        assertEquals(1, actualProducts.size());
    }

    @Test
    public void test_FindAllByPriceShouldReturnEmptyCollectionWhenNoSuchElementIsPresent() {
        addProductsToInstock(10);

        Iterable<Product> iterable = instock.findAllByPrice(100);
        List<Product> actualProducts = assertIterableNotNullAndConvertToList(iterable);

        assertEquals(0, actualProducts.size());
    }

    @Test
    public void test_FirstMostExpensiveProductsShouldReturnTheCorrectProducts() {
        int productToTake = 5;
        List<Product> expectedProducts = Arrays.stream(addProductsToInstock(10))
                .sorted(Comparator.comparing(Product::getPrice).reversed())
                .limit(productToTake)
                .collect(Collectors.toList());

        Iterable<Product> iterable = instock.findFirstMostExpensiveProducts(productToTake);
        List<Product> actualProducts = assertIterableNotNullAndConvertToList(iterable);

        assertEquals(expectedProducts, actualProducts);
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_FirstMostExpensiveProductsShouldThrowWhenThereAreLessProducts() {
        addProductsToInstock(5);
        instock.findFirstMostExpensiveProducts(10);
    }

    @Test
    public void test_FindAllByQuantityShouldReturnAllProductsWithGiveQuantity() {
        Product[] addedProducts = addProductsToInstock(10);
        Product expectedProduct = addedProducts[1];
        int expectedQuantity = expectedProduct.getQuantity();

        Iterable<Product> iterable = instock.findAllByQuantity(expectedQuantity);
        List<Product> actualProducts = assertIterableNotNullAndConvertToList(iterable);
        Product actualProduct = actualProducts.get(0);

        assertEquals(expectedProduct.getLabel(), actualProduct.getLabel());
        assertEquals(1, actualProducts.size());
    }

    @Test
    public void test_FindAllByQuantityShouldReturnEmptyCollectionWhenNoSuchElementIsPresent() {
        addProductsToInstock(10);

        Iterable<Product> iterable = instock.findAllByQuantity(13);
        List<Product> actualProducts = assertIterableNotNullAndConvertToList(iterable);

        assertEquals(0, actualProducts.size());
    }

    @Test
    public void test_GetIterableShouldReturnAllProductsInStock() {
        List<Product> actualProducts = new ArrayList<>();

        instock.forEach(actualProducts::add);
        assertEquals(0, actualProducts.size());

        Product[] addedProducts = addProductsToInstock(10);

        instock.forEach(actualProducts::add);
        assertEquals(addedProducts.length, actualProducts.size());
    }

    private List<Product> assertIterableNotNullAndConvertToList(Iterable<Product> iterable) {
        assertNotNull(iterable);
        List<Product> products = new ArrayList<>();
        iterable.forEach(products::add);
        return products;
    }

    private Product[] addProductsToInstock(int count) {
        Product[] arr = new Product[count];

        for (int i = 1; i <= count; i++) {
            Product product = new Product("product" + i, 10.00 + i, i);
            instock.add(product);
            arr[i - 1] = product;
        }
        return arr;
    }
}