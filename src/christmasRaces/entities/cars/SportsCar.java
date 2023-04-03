package christmasRaces.entities.cars;

import static christmasRaces.common.ExceptionMessages.INVALID_HORSE_POWER;

public class SportsCar extends BaseCar {

    public static final int CUBIC_CENTIMETERS = 3000;

    public SportsCar(String model, int horsePower) {
        super(model, horsePower, CUBIC_CENTIMETERS);
    }

    @Override
    public void setHorsePower(int horsePower) {
        if (horsePower < 250 || horsePower > 450) {
            throw new IllegalArgumentException(String.format(INVALID_HORSE_POWER, horsePower));
        }
        super.setHorsePower(horsePower);
    }
}