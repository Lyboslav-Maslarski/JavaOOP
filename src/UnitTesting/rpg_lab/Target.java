package UnitTesting.rpg_lab;

import java.util.List;

public interface Target {
    int getHealth();

    void takeAttack(int attackPoints);

    int giveExperience();

    List<Weapon> giveLoot();

    boolean isDead();
}
