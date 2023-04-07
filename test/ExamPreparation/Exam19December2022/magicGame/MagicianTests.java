package ExamPreparation.Exam19December2022.magicGame;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class MagicianTests {
    private Magician magician;
    private List<Magic> magics;

    @Before
    public void setup() {
        magician = new Magician("username", 10);
        magics = new ArrayList<>();
        magics.add(new Magic("name1", 1));
        magics.add(new Magic("name2", 1));
        magics.add(new Magic("name3", 1));
        magics.add(new Magic("name4", 1));
        magics.add(new Magic("name5", 1));
    }

    @Test
    public void test_ctor() {
        String username = "username";
        int health = 10;
        Magician magicianTest = new Magician(username, health);

        assertEquals(username,magicianTest.getUsername());
        assertEquals(health,magicianTest.getHealth());
    }

    @Test(expected = NullPointerException.class)
    public void test_ctorWithNullUsername() {
        new Magician(null, 10);
    }

    @Test(expected = NullPointerException.class)
    public void test_ctorWithEmptyUsername() {
        new Magician("   ", 10);
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_ctorWithNegativeHealth() {
        new Magician("username", -10);
    }

    @Test
    public void test_getMagics() {
        for (Magic magic : magics) {
            magician.addMagic(magic);
        }

        List<Magic> magicList = magician.getMagics();

        assertEquals(magics.size(),magicList.size());

        for (int i = 0; i < magicList.size(); i++) {
            Magic expected = magics.get(i);
            Magic actual = magicList.get(i);
            assertEquals(expected.getName(),actual.getName());
            assertEquals(expected.getBullets(),actual.getBullets());
        }
    }

    @Test
    public void test_takeDamage() {
        int health = magician.getHealth();
        int damage = 6;
        magician.takeDamage(damage);
        assertEquals(health - damage, magician.getHealth());
        magician.takeDamage(damage);
        assertEquals(0, magician.getHealth());
    }

    @Test(expected = IllegalStateException.class)
    public void test_takeDamageWhenHealthZero() {
        int health = magician.getHealth();
        int damage = 6;
        magician.takeDamage(damage);
        assertEquals(health - damage, magician.getHealth());
        magician.takeDamage(damage);
        assertEquals(0, magician.getHealth());
        magician.takeDamage(damage);
    }

    @Test
    public void test_addMagic() {
        assertEquals(0, magician.getMagics().size());
        magician.addMagic(magics.get(0));

        assertEquals(1, magician.getMagics().size());
    }

    @Test(expected = NullPointerException.class)
    public void test_addMagicWithNull() {
        magician.addMagic(null);
    }

    @Test
    public void test_removeMagic() {
        magician.addMagic(magics.get(0));

        assertTrue(magician.removeMagic(magics.get(0)));
        assertFalse(magician.removeMagic(magics.get(1)));
    }

    @Test
    public void test_getMagicWithExistingMagic() {
        Magic magic = magics.get(0);
        magician.addMagic(magic);
        Magic actual = magician.getMagic(magic.getName());

        assertEquals(magic.getName(), actual.getName());
        assertEquals(magic.getBullets(), actual.getBullets());
    }

    @Test
    public void test_getMagicWithNonExistingMagic() {
        assertNull(magician.getMagic(magics.get(0).getName()));
    }

}
