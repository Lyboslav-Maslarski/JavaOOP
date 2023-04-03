package ExamPreparation.Exam20December2021.christmasRaces.entities.cars;

import ExamPreparation.Exam20December2021.christmasRaces.common.ExceptionMessages;

public class MuscleCar extends BaseCar {

    public static final int CUBIC_CENTIMETERS = 5000;

    public MuscleCar(String model, int horsePower) {
        super(model, horsePower, CUBIC_CENTIMETERS);
    }

    @Override
    public void setHorsePower(int horsePower) {
        if (horsePower < 400 || horsePower > 600) {
            throw new IllegalArgumentException(String.format(ExceptionMessages.INVALID_HORSE_POWER, horsePower));
        }
        super.setHorsePower(horsePower);
    }
}
