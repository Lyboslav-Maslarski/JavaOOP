package ExamPreparation.Exam09April2022.gifts;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

public class GiftFactoryTests {
    private GiftFactory giftFactory;
    private List<Gift> gifts;

    @Before
    public void setup() {
        this.giftFactory = new GiftFactory();
        this.gifts = new ArrayList<>();
        this.gifts.add(new Gift("type1", 1));
        this.gifts.add(new Gift("type2", 2));
        this.gifts.add(new Gift("type3", 3));
        this.gifts.add(new Gift("type4", 4));
        this.gifts.add(new Gift("type5", 5));
    }

    @Test
    public void test_createGift() {
        Gift gift = gifts.get(0);
        String expected = String.format("Successfully added gift %s with magic %.2f.", gift.getType(), gift.getMagic());

        assertEquals(0, giftFactory.getCount());
        String actual = giftFactory.createGift(gift);

        assertEquals(1, giftFactory.getCount());
        assertEquals(expected, actual);
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_createGiftWithExistingGiftShouldThrow() {
        giftFactory.createGift(gifts.get(0));
        giftFactory.createGift(gifts.get(0));
    }

    @Test
    public void test_removeGift() {
        giftFactory.createGift(gifts.get(0));

        assertEquals(1, giftFactory.getCount());
        assertTrue(giftFactory.removeGift(gifts.get(0).getType()));
        assertFalse(giftFactory.removeGift(gifts.get(1).getType()));

        assertEquals(0, giftFactory.getCount());
        assertFalse(giftFactory.removeGift(gifts.get(0).getType()));
    }

    @Test(expected = NullPointerException.class)
    public void test_removeGiftWithNullShouldThrow() {
        giftFactory.removeGift(null);
    }

    @Test(expected = NullPointerException.class)
    public void test_removeGiftWithEmptyTypeShouldThrow() {
        giftFactory.removeGift("  ");
    }

    @Test
    public void test_getPresentWithLeastMagic() {
        assertNull(giftFactory.getPresentWithLeastMagic());

        Gift expected = gifts.stream().min(Comparator.comparingDouble(Gift::getMagic)).orElse(null);
        giftFactory.createGift(gifts.get(0));
        giftFactory.createGift(gifts.get(1));
        giftFactory.createGift(gifts.get(2));
        giftFactory.createGift(gifts.get(3));
        giftFactory.createGift(gifts.get(4));
        Gift actual = giftFactory.getPresentWithLeastMagic();

        assertEquals(expected.getType(), actual.getType());
        assertEquals(expected.getMagic(), actual.getMagic(), 0);
    }

    @Test
    public void test_getPresent() {
        Gift expected = gifts.get(0);
        giftFactory.createGift(expected);
        Gift actual = giftFactory.getPresent(expected.getType());

        assertEquals(expected.getType(), actual.getType());
        assertEquals(expected.getMagic(), actual.getMagic(), 0);

        assertNull(giftFactory.getPresent(gifts.get(1).getType()));
    }

    @Test
    public void test_getPresents() {
        giftFactory.createGift(gifts.get(0));
        giftFactory.createGift(gifts.get(1));
        giftFactory.createGift(gifts.get(2));
        giftFactory.createGift(gifts.get(3));
        giftFactory.createGift(gifts.get(4));

        List<Gift> actualList = giftFactory.getPresents().stream().collect(Collectors.toList());
        assertEquals(gifts.size(), actualList.size());

        for (int i = 0; i < gifts.size(); i++) {
            Gift expected = gifts.get(i);
            Gift actual = actualList.get(i);
            assertEquals(expected.getType(), actual.getType());
            assertEquals(expected.getMagic(), actual.getMagic(), 0);
        }
    }
}
