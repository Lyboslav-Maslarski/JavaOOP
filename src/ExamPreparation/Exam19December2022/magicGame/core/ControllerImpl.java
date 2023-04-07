package ExamPreparation.Exam19December2022.magicGame.core;

import ExamPreparation.Exam19December2022.magicGame.models.magicians.BlackWidow;
import ExamPreparation.Exam19December2022.magicGame.models.magicians.Magician;
import ExamPreparation.Exam19December2022.magicGame.models.magicians.Wizard;
import ExamPreparation.Exam19December2022.magicGame.models.magics.BlackMagic;
import ExamPreparation.Exam19December2022.magicGame.models.magics.Magic;
import ExamPreparation.Exam19December2022.magicGame.models.magics.RedMagic;
import ExamPreparation.Exam19December2022.magicGame.models.region.Region;
import ExamPreparation.Exam19December2022.magicGame.models.region.RegionImpl;
import ExamPreparation.Exam19December2022.magicGame.repositories.interfaces.MagicRepository;
import ExamPreparation.Exam19December2022.magicGame.repositories.interfaces.MagicRepositoryImpl;
import ExamPreparation.Exam19December2022.magicGame.repositories.interfaces.MagicianRepository;
import ExamPreparation.Exam19December2022.magicGame.repositories.interfaces.MagicianRepositoryImpl;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static ExamPreparation.Exam19December2022.magicGame.common.ExceptionMessages.*;
import static ExamPreparation.Exam19December2022.magicGame.common.OutputMessages.SUCCESSFULLY_ADDED_MAGIC;
import static ExamPreparation.Exam19December2022.magicGame.common.OutputMessages.SUCCESSFULLY_ADDED_MAGICIAN;

public class ControllerImpl implements Controller {
    private MagicRepository<Magic> magics;
    private MagicianRepository<Magician> magicians;
    private Region region;

    public ControllerImpl() {
        this.magics = new MagicRepositoryImpl<>();
        this.magicians = new MagicianRepositoryImpl<>();
        this.region = new RegionImpl();
    }

    @Override
    public String addMagic(String type, String name, int bulletsCount) {
        Magic magic;
        switch (type) {
            case "RedMagic":
                magic = new RedMagic(name, bulletsCount);
                break;
            case "BlackMagic":
                magic = new BlackMagic(name, bulletsCount);
                break;
            default:
                throw new IllegalArgumentException(INVALID_MAGIC_TYPE);
        }

        magics.addMagic(magic);

        return String.format(SUCCESSFULLY_ADDED_MAGIC, name);
    }

    @Override
    public String addMagician(String type, String username, int health, int protection, String magicName) {
        Magic magic = magics.findByName(magicName);
        if (magic == null) {
            throw new NullPointerException(MAGIC_CANNOT_BE_FOUND);
        }

        Magician magician;
        switch (type) {
            case "Wizard":
                magician = new Wizard(username, health, protection, magic);
                break;
            case "BlackWidow":
                magician = new BlackWidow(username, health, protection, magic);
                break;
            default:
                throw new IllegalArgumentException(INVALID_MAGICIAN_TYPE);
        }

        magicians.addMagician(magician);

        return String.format(SUCCESSFULLY_ADDED_MAGICIAN, username);
    }

    @Override
    public String startGame() {
        List<Magician> magicianList = magicians.getData().stream().filter(Magician::isAlive).collect(Collectors.toList());
        return region.start(magicianList);
    }

    @Override
    public String report() {
        return magicians.getData().stream()
                .sorted(Comparator.comparing(Magician::getHealth).thenComparing(Magician::getUsername))
                .map(Magician::toString)
                .collect(Collectors.joining(System.lineSeparator()));
    }
}
