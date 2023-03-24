package ExamPreparation.Exam12Dec2020.bankSafe;


import org.junit.Before;
import org.junit.Test;

import javax.naming.OperationNotSupportedException;
import java.util.LinkedHashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class BankVaultTest {

    public static final String CELL_1 = "A1";
    public static final String CELL_2 = "A2";
    public static final String CELL_3 = "A3";
    public static final String CELL_4 = "A4";
    public static final String CELL_5 = "B1";
    private BankVault bankVault;
    private Map<String, Item> items;

    @Before
    public void setUp() {
        bankVault = new BankVault();
        items = new LinkedHashMap<>();
        items.put(CELL_1, new Item("owner1", "id1"));
        items.put(CELL_2, new Item("owner1", "id2"));
        items.put(CELL_3, new Item("owner2", "id3"));
        items.put(CELL_4, new Item("owner2", "id4"));
        items.put(CELL_5, new Item("owner2", "id5"));
    }

    @Test
    public void test_constructor() {
        Map<String, Item> vaultCells = bankVault.getVaultCells();
        assertEquals(12, vaultCells.size());
    }

    @Test
    public void test_addItem() throws OperationNotSupportedException {
        String result1 = bankVault.addItem(CELL_1, items.get(CELL_1));
        String result2 = bankVault.addItem(CELL_2, items.get(CELL_2));
        String result3 = bankVault.addItem(CELL_3, items.get(CELL_3));

        Map<String, Item> actualCells = bankVault.getVaultCells();

        for (Map.Entry<String, Item> entry : actualCells.entrySet()) {
            Item expected = items.get(entry.getKey());
            Item actual = entry.getValue();
            if (actual != null) {
                assertEquals(expected.getOwner(), actual.getOwner());
                assertEquals(expected.getItemId(), actual.getItemId());
            }
        }

        assertEquals(String.format("Item:%s saved successfully!", items.get(CELL_1).getItemId()), result1);
        assertEquals(String.format("Item:%s saved successfully!", items.get(CELL_2).getItemId()), result2);
        assertEquals(String.format("Item:%s saved successfully!", items.get(CELL_3).getItemId()), result3);
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_addItemShouldThrowWithNonExistingCell() throws OperationNotSupportedException {
        bankVault.addItem("D1", items.get(CELL_1));
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_addItemShouldThrowWithOccupiedCell() throws OperationNotSupportedException {
        bankVault.addItem(CELL_1, items.get(CELL_1));
        bankVault.addItem(CELL_1, items.get(CELL_1));
    }

    @Test(expected = OperationNotSupportedException.class)
    public void test_addItemShouldThrowWithExistingItem() throws OperationNotSupportedException {
        bankVault.addItem(CELL_1, items.get(CELL_1));
        bankVault.addItem(CELL_2, items.get(CELL_1));
    }

    @Test
    public void test_removeItem() throws OperationNotSupportedException {
        bankVault.addItem(CELL_1, items.get(CELL_1));
        bankVault.addItem(CELL_2, items.get(CELL_2));
        bankVault.addItem(CELL_3, items.get(CELL_3));

        String result = bankVault.removeItem(CELL_1, items.get(CELL_1));

        assertEquals(String.format("Remove item:%s successfully!", items.get(CELL_1).getItemId()), result);
        assertNull(bankVault.getVaultCells().get(CELL_1));
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_removeItemShouldThrowWithNonExistingCell() throws OperationNotSupportedException {
        bankVault.removeItem("D1", items.get(CELL_1));
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_removeItemShouldThrowWithDifferentItem() throws OperationNotSupportedException {
        bankVault.addItem(CELL_1, items.get(CELL_1));
        bankVault.removeItem(CELL_1, items.get(CELL_2));
    }
}