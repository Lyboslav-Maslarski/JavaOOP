package ExamPreparation.Exam22Aug2020.easterRaces.entities.cars;

import static ExamPreparation.Exam22Aug2020.easterRaces.common.ExceptionMessages.INVALID_HORSE_POWER;

public class SportsCar extends BaseCar {
    private static final double CUBIC_CENTIMETERS = 3000;

    public SportsCar(String model, int horsePower) {
        super(model, validateHorsePower(horsePower), CUBIC_CENTIMETERS);
    }

    private static int validateHorsePower(int horsePower) {
        if (horsePower < 250 || horsePower > 450) {
            throw new IllegalArgumentException(String.format(INVALID_HORSE_POWER, horsePower));
        }
       return horsePower;
    }
}
