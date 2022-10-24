package UnitTesting.Lab;

import java.util.ArrayList;
import java.util.List;

public class Hero {

    private final String name;
    private int experience;
    private final Weapon weapon;
    private final List<Weapon> inventory;

    public Hero(String name, Weapon weapon) {
        this.name = name;
        this.experience = 0;
        this.weapon = weapon;
        this.inventory = new ArrayList<>();
    }

    public String getName() {
        return this.name;
    }

    public int getExperience() {
        return this.experience;
    }

    public Weapon getWeapon() {
        return this.weapon;
    }

    public List<Weapon> getInventory() {
        return inventory;
    }

    public void attack(Target target) {
        this.weapon.attack(target);

        if (target.isDead()) {
            this.experience += target.giveExperience();
            this.inventory.addAll(target.giveLoot());
        }
    }
}
