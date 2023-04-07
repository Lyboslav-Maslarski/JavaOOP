package ExamPreparation.Exam19December2022.magicGame.models.magicians;

import ExamPreparation.Exam19December2022.magicGame.models.magics.Magic;

import static ExamPreparation.Exam19December2022.magicGame.common.ExceptionMessages.*;

public abstract class MagicianImpl implements Magician {
    private String username;
    private int health;
    private int protection;
    private boolean isAlive;
    private Magic magic;

    public void setUsername(String username) {
        if (username == null || username.trim().isEmpty()) {
            throw new NullPointerException(INVALID_MAGICIAN_NAME);
        }
        this.username = username;
    }

    public void setHealth(int health) {
        if (health < 0) {
            throw new IllegalArgumentException(INVALID_MAGICIAN_HEALTH);
        }
        this.health = health;
    }

    public void setProtection(int protection) {
        if (protection < 0) {
            throw new IllegalArgumentException(INVALID_MAGICIAN_PROTECTION);
        }
        this.protection = protection;
    }

    public void setMagic(Magic magic) {
        if (magic == null) {
            throw new NullPointerException(INVALID_MAGIC);
        }
        this.magic = magic;
    }

    public MagicianImpl(String username, int health, int protection, Magic magic) {
        this.setUsername(username);
        this.setHealth(health);
        this.setProtection(protection);
        this.setMagic(magic);
        this.isAlive = true;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public int getHealth() {
        return health;
    }

    @Override
    public int getProtection() {
        return protection;
    }

    @Override
    public Magic getMagic() {
        return magic;
    }

    @Override
    public boolean isAlive() {
        return isAlive;
    }

    @Override
    public void takeDamage(int points) {
        this.protection -= points;
        if (this.protection < 0) {
            health += this.protection;
        }
        if (health <= 0) {
            isAlive = false;
            health = 0;
            protection = 0;
        }
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + ": " + getUsername() + System.lineSeparator() +
               "Health: " + getHealth() + System.lineSeparator() +
               "Protection: " + getProtection() + System.lineSeparator() +
               "Magic: " + getMagic().getName();
    }
}
