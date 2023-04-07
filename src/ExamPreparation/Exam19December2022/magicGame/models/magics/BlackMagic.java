package ExamPreparation.Exam19December2022.magicGame.models.magics;

public class BlackMagic extends MagicImpl {

    public int bullets = getBulletsCount();

    public BlackMagic(String name, int bulletsCount) {
        super(name, bulletsCount);
    }

    @Override
    public int fire() {
        if (bullets - 10 < 0) {
            bullets = 0;
            return 0;
        } else {
            bullets -= 10;
            return 10;
        }
    }
}
