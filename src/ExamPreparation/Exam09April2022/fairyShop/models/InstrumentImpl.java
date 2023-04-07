package ExamPreparation.Exam09April2022.fairyShop.models;

import ExamPreparation.Exam09April2022.fairyShop.common.ExceptionMessages;

public class InstrumentImpl implements Instrument {
    private int power;

    public InstrumentImpl(int power) {
        this.setPower(power);
    }

    public void setPower(int power) {
        if (power < 0) {
            throw new IllegalArgumentException(ExceptionMessages.INSTRUMENT_POWER_LESS_THAN_ZERO);
        }
        this.power = power;
    }

    @Override
    public int getPower() {
        return power;
    }

    @Override
    public void use() {
        int power = getPower() - 10;
        setPower(Math.max(power, 0));
    }

    @Override
    public boolean isBroken() {
        return power == 0;
    }
}
