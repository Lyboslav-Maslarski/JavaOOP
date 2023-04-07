package ExamPreparation.Exam19December2022.magicGame.models.magics;

public class RedMagic extends MagicImpl {

    public int bullets = getBulletsCount();

    public RedMagic(String name, int bulletsCount) {
        super(name, bulletsCount);
    }

    @Override
    public int fire() {
        if (bullets - 1 < 0) {
            bullets = 0;
            return 0;
        } else {
            bullets -= 1;
            return 1;
        }
    }
}
