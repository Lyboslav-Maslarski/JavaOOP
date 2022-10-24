package UnitTesting.Lab;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class AxeTest {
    private static final int ATTACK = 13;
    private static final int DURABILITY = 10;
    private static final int HEALTH = 13;
    private static final int EXPERIENCE = 100;
    private Axe axe;
    private Dummy dummy;

    @Before
    public void setUp() {
        this.axe = new Axe(ATTACK, DURABILITY);
        this.dummy = new Dummy(HEALTH, EXPERIENCE,new ArrayList<>());

    }

    @Test
    public void test_AxeCreation() {

        assertEquals(13, axe.getAttackPoints());
        assertEquals(10, axe.getDurabilityPoints());
    }

    @Test
    public void test_WeaponLoosesDurabilityAfterAttack() {
        axe.attack(dummy);
        assertEquals(DURABILITY - 1, axe.getDurabilityPoints());
    }

    @Test(expected = IllegalStateException.class)
    public void test_CannotAttackWithBrokenWeapon() {
        Axe axe = new Axe(ATTACK, 0);
        axe.attack(dummy);
    }
}