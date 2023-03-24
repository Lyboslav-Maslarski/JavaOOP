package ExamPreparation.Exam19Dec2020.viceCity.models.players;

import ExamPreparation.Exam19Dec2020.viceCity.common.ExceptionMessages;
import ExamPreparation.Exam19Dec2020.viceCity.models.guns.Gun;
import ExamPreparation.Exam19Dec2020.viceCity.repositories.GunRepository;
import ExamPreparation.Exam19Dec2020.viceCity.repositories.interfaces.Repository;

public abstract class BasePlayer implements Player {
    private String name;
    private int lifePoints;
    private Repository<Gun> gunRepository;

    public BasePlayer(String name, int lifePoints) {
        setName(name);
        setLifePoints(lifePoints);
        gunRepository = new GunRepository();
    }

    private void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new NullPointerException(ExceptionMessages.PLAYER_NULL_USERNAME);
        }
        this.name = name;
    }

    private void setLifePoints(int lifePoints) {
        if (lifePoints < 0) {
            throw new IllegalArgumentException(ExceptionMessages.PLAYER_LIFE_POINTS_LESS_THAN_ZERO);
        }
        this.lifePoints = lifePoints;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getLifePoints() {
        return lifePoints;
    }

    @Override
    public boolean isAlive() {
        return lifePoints > 0;
    }

    @Override
    public Repository<Gun> getGunRepository() {
        return gunRepository;
    }

    @Override
    public void takeLifePoints(int points) {
        int newLifePoints = getLifePoints() - points;
        if (newLifePoints < 0) {
            newLifePoints = 0;
        }
        setLifePoints(newLifePoints);
    }
}
