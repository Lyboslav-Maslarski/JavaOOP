package ExamPreparation.Exam09April2022.fairyShop.core;

import ExamPreparation.Exam09April2022.fairyShop.models.*;
import ExamPreparation.Exam09April2022.fairyShop.repositories.HelperRepository;
import ExamPreparation.Exam09April2022.fairyShop.repositories.PresentRepository;
import ExamPreparation.Exam09April2022.fairyShop.repositories.Repository;

import java.util.List;
import java.util.stream.Collectors;

import static ExamPreparation.Exam09April2022.fairyShop.common.ConstantMessages.*;
import static ExamPreparation.Exam09April2022.fairyShop.common.ExceptionMessages.*;

public class ControllerImpl implements Controller {
    private Repository<Helper> helperRepository;
    private Repository<Present> presentRepository;
    private int presentsDone;

    public ControllerImpl() {
        this.helperRepository = new HelperRepository();
        this.presentRepository = new PresentRepository();
    }

    @Override
    public String addHelper(String type, String helperName) {
        Helper helper;
        switch (type) {
            case "Happy":
                helper = new Happy(helperName);
                break;
            case "Sleepy":
                helper = new Sleepy(helperName);
                break;
            default:
                throw new IllegalArgumentException(HELPER_TYPE_DOESNT_EXIST);
        }

        helperRepository.add(helper);

        return String.format(ADDED_HELPER, type, helperName);
    }

    @Override
    public String addInstrumentToHelper(String helperName, int power) {
        Helper helper = helperRepository.findByName(helperName);
        if (helper == null) {
            throw new IllegalArgumentException(HELPER_DOESNT_EXIST);
        }

        helper.addInstrument(new InstrumentImpl(power));

        return String.format(SUCCESSFULLY_ADDED_INSTRUMENT_TO_HELPER, power, helperName);
    }

    @Override
    public String addPresent(String presentName, int energyRequired) {
        presentRepository.add(new PresentImpl(presentName, energyRequired));

        return String.format(SUCCESSFULLY_ADDED_PRESENT, presentName);
    }

    @Override
    public String craftPresent(String presentName) {
        List<Helper> helpers = helperRepository.getModels().stream().filter(h -> h.getEnergy() > 50).collect(Collectors.toList());
        if (helpers.size() == 0) {
            throw new IllegalArgumentException(NO_HELPER_READY);
        }

        Present present = presentRepository.findByName(presentName);

        Shop shop = new ShopImpl();
        int countBrokenInstruments = 0;

        for (Helper helper : helpers) {
            while (helper.canWork() && !present.isDone() && helper.getInstruments().stream().anyMatch(i -> !i.isBroken())) {
                shop.craft(present, helper);
            }
            countBrokenInstruments += helper.getInstruments().stream().filter(Instrument::isBroken).count();
        }

        String result = "not done";
        if (present.isDone()) {
            presentsDone++;
            result = "done";
        }
        return String.format(PRESENT_DONE, presentName, result) +
               String.format(COUNT_BROKEN_INSTRUMENTS, countBrokenInstruments);
    }

    @Override
    public String report() {
        StringBuilder sb = new StringBuilder();
        sb.append(presentsDone).append(" presents are done!").append(System.lineSeparator());
        sb.append("Helpers info:").append(System.lineSeparator());

        for (Helper helper : helperRepository.getModels()) {
            sb.append("Name: ").append(helper.getName()).append(System.lineSeparator());
            sb.append("Energy: ").append(helper.getEnergy()).append(System.lineSeparator());
            sb.append("Instruments: ").append(helper.getInstruments().stream().filter(i -> !i.isBroken()).count());
            sb.append(" not broken left").append(System.lineSeparator());
        }

        return sb.toString().trim();
    }
}
