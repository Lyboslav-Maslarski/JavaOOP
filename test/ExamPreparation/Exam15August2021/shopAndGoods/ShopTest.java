package ExamPreparation.Exam15August2021.shopAndGoods;


import org.junit.Before;
import org.junit.Test;

import javax.naming.OperationNotSupportedException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class ShopTest {
    private List<Goods> goods;
    private Shop shop;

    @Before
    public void setUp() {
        goods = new ArrayList<>();
        goods.add(new Goods("name1", "code1"));
        goods.add(new Goods("name2", "code1"));
        goods.add(new Goods("name3", "code2"));
        goods.add(new Goods("name4", "code2"));
        goods.add(new Goods("name5", "code3"));
        shop = new Shop();
    }

    @Test
    public void test_getShelves() {
        Map<String, Goods> stringGoodsMap = shop.getShelves();
        assertEquals(12, shop.getShelves().size());
    }

    @Test
    public void test_addGoods() throws OperationNotSupportedException {
        String expected = String.format("Goods: %s is placed successfully!", goods.get(0).getGoodsCode());
        String actual = shop.addGoods("Shelves1", goods.get(0));

        assertEquals(expected, actual);
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_addGoodsShouldThrowWithNonExistingShelf() throws OperationNotSupportedException {
        shop.addGoods("Shelves13", goods.get(0));
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_addGoodsShouldThrowWithOccupiedShelf() throws OperationNotSupportedException {
        shop.addGoods("Shelves1", goods.get(0));
        shop.addGoods("Shelves1", goods.get(1));
    }

    @Test(expected = OperationNotSupportedException.class)
    public void test_addGoodsShouldThrowWithExistingItem() throws OperationNotSupportedException {
        shop.addGoods("Shelves1", goods.get(0));
        shop.addGoods("Shelves2", goods.get(0));
    }

    @Test
    public void test_removeGoods() throws OperationNotSupportedException {
        String expected = String.format("Goods: %s is removed successfully!", goods.get(0).getGoodsCode());

        shop.addGoods("Shelves1", goods.get(0));

        String actual = shop.removeGoods("Shelves1", goods.get(0));

        assertNull(shop.getShelves().get("Shelves1"));
        assertEquals(expected, actual);
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_removeGoodsShouldThrowWithNonExistingShelf() {
        shop.removeGoods("Shelves13", goods.get(0));
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_removeGoodsShouldThrowWithEmptyShelf() {
        shop.removeGoods("Shelves1", goods.get(0));
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_removeGoodsShouldThrowWithDifferentGoods() throws OperationNotSupportedException {
        shop.addGoods("Shelves1", goods.get(0));
        shop.removeGoods("Shelves1", goods.get(1));
    }
}