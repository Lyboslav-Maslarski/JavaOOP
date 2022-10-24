package UnitTesting.rpg_lab;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class DummyTest {
    private static final int ALIVE_HEALTH = 10;
    private static final int DEAD_HEALTH = 0;
    private static final int EXPERIENCE = 100;
    private static final int ATTACK_POINTS = 1;
    private Dummy aliveDummy;
    private Dummy deadDummy;

    @Before
    public void setUp() {
        this.aliveDummy = new Dummy(ALIVE_HEALTH, EXPERIENCE,new ArrayList<>());
        this.deadDummy = new Dummy(DEAD_HEALTH, EXPERIENCE,new ArrayList<>());
    }

    @Test
    public void test_DummyLoosesHealthWhenAttacked() {
        aliveDummy.takeAttack(ATTACK_POINTS);
        assertEquals(ALIVE_HEALTH - ATTACK_POINTS, aliveDummy.getHealth());
    }

    @Test(expected = IllegalStateException.class)
    public void test_DeadDummyCannotBeAttacked() {
        deadDummy.takeAttack(ATTACK_POINTS);
    }

    @Test
    public void test_DeadDummyGivesXP() {
        assertEquals(EXPERIENCE, deadDummy.giveExperience());
    }

    @Test(expected = IllegalStateException.class)
    public void test_AliveDummyCannotGiveXP() {
        aliveDummy.giveExperience();
    }
    @Test
    public void test_AliveDummyIsAlive(){
        assertFalse(aliveDummy.isDead());
    }

    @Test
    public void test_DeadDummyIsDead(){
        assertTrue(deadDummy.isDead());
    }
}