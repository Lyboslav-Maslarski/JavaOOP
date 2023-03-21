package ExamPreparation.Exam22Aug2020.easterRaces.entities.cars;

import ExamPreparation.Exam22Aug2020.easterRaces.common.ExceptionMessages;

public class MuscleCar extends BaseCar {
    private static final double CUBIC_CENTIMETERS = 5000;

    public MuscleCar(String model, int horsePower) {
        super(model, validateHorsePower(horsePower), CUBIC_CENTIMETERS);
    }

    private static int validateHorsePower(int horsePower) {
        if (horsePower < 400 || horsePower > 600) {
            throw new IllegalArgumentException(String.format(ExceptionMessages.INVALID_HORSE_POWER, horsePower));
        }
        return horsePower;
    }
}
