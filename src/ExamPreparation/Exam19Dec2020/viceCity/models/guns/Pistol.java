package ExamPreparation.Exam19Dec2020.viceCity.models.guns;

public class Pistol extends BaseGun {

    public static final int BULLETS_PER_BARREL = 10;
    public static final int TOTAL_BULLETS = 100;
    public static final int BULLET_PER_SHOOT = 1;

    public Pistol(String name) {
        super(name, BULLETS_PER_BARREL, TOTAL_BULLETS);
    }

    @Override
    public int fire() {
        if (canFire()) {
            int currentBullets = getBulletsPerBarrel() - BULLET_PER_SHOOT;
            if (currentBullets == 0 && getTotalBullets() > 0) {
                setBulletsPerBarrel(BULLETS_PER_BARREL);
                setTotalBullets(getTotalBullets() - BULLETS_PER_BARREL);
            } else {
                setBulletsPerBarrel(currentBullets);
            }
            return BULLET_PER_SHOOT;
        }
        return 0;
    }
}
