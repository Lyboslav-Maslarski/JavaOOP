package ExamPreparation.Exam09April2022.fairyShop.models;

import java.util.Collection;

public class ShopImpl implements Shop {
    @Override
    public void craft(Present present, Helper helper) {
        Collection<Instrument> instruments = helper.getInstruments();
        for (Instrument instrument : instruments) {
            while (!instrument.isBroken()) {
                instrument.use();
                present.getCrafted();
                helper.work();
                if (present.isDone()) {
                    return;
                }
                if (!helper.canWork()) {
                    return;
                }
            }
        }
    }
}
