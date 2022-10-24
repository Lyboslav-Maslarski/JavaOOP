package UnitTesting.Lab;

import java.util.List;

public class Dummy implements Target {

    private int health;
    private final int experience;
    private final List<Weapon> possibleLoot;

    public Dummy(int health, int experience, List<Weapon> possibleLoot) {
        this.health = health;
        this.experience = experience;
        this.possibleLoot = possibleLoot;
    }

    @Override
    public int getHealth() {
        return this.health;
    }

    @Override
    public void takeAttack(int attackPoints) {
        if (this.isDead()) {
            throw new IllegalStateException("Dummy is dead.");
        }

        this.health -= attackPoints;
    }

    @Override
    public int giveExperience() {
        if (!this.isDead()) {
            throw new IllegalStateException("Target is not dead.");
        }

        return this.experience;
    }

    @Override
    public List<Weapon> giveLoot() {
        if (!this.isDead()) {
            throw new IllegalStateException("Target is not dead.");
        }

        return this.possibleLoot;
    }

    @Override
    public boolean isDead() {
        return this.health <= 0;
    }
}
