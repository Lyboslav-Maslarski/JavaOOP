package UnitTesting.Lab;

import org.junit.Test;
import org.mockito.Mockito;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class HeroTest {
    private static final int XP = 100;

    private static class FakeWeapon implements Weapon {

        @Override
        public int getAttackPoints() {
            return 10;
        }

        @Override
        public int getDurabilityPoints() {
            return 0;
        }

        @Override
        public void attack(Target target) {

        }
    }

    @Test
    public void test_HeroGainXpWhenKillTarget() {
        Hero hero = new Hero("Javarcho", new FakeWeapon());

        assertEquals(0, hero.getExperience());

        Target target = new Target() {
            @Override
            public int getHealth() {
                return 0;
            }

            @Override
            public void takeAttack(int attackPoints) {
            }

            @Override
            public int giveExperience() {
                return XP;
            }

            @Override
            public List<Weapon> giveLoot() {
                return null;
            }

            @Override
            public boolean isDead() {
                return true;
            }
        };

        hero.attack(target);

        assertEquals(XP, hero.getExperience());
    }

    @Test
    public void test_HeroGainXpWhenKillTarget_Mocking() {
        Weapon weapon = Mockito.mock(Weapon.class);

        Hero hero = new Hero("Javarcho", weapon);

        assertEquals(0, hero.getExperience());

        Target target = Mockito.mock(Target.class);

        Mockito.when(target.isDead()).thenReturn(true);
        Mockito.when(target.giveExperience()).thenReturn(XP);

        hero.attack(target);

        assertEquals(XP, hero.getExperience());

    }

    @Test
    public void test_TargetGivesItemsWhenKilled() {
        Hero hero = new Hero("Javarcho", new FakeWeapon());

        assertEquals(0, hero.getInventory().size());

        Target target = Mockito.mock(Target.class);

        Mockito.when(target.isDead()).thenReturn(true);
        Mockito.when(target.giveLoot()).thenReturn(List.of(new FakeWeapon(), new FakeWeapon()));

        hero.attack(target);

        assertEquals(2, hero.getInventory().size());

    }
}