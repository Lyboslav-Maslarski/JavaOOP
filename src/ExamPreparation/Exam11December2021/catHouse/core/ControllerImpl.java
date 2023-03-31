package ExamPreparation.Exam11December2021.catHouse.core;

import ExamPreparation.Exam11December2021.catHouse.common.ConstantMessages;
import ExamPreparation.Exam11December2021.catHouse.common.ExceptionMessages;
import ExamPreparation.Exam11December2021.catHouse.entities.cat.Cat;
import ExamPreparation.Exam11December2021.catHouse.entities.cat.LonghairCat;
import ExamPreparation.Exam11December2021.catHouse.entities.cat.ShorthairCat;
import ExamPreparation.Exam11December2021.catHouse.entities.houses.House;
import ExamPreparation.Exam11December2021.catHouse.entities.houses.ShortHouse;
import ExamPreparation.Exam11December2021.catHouse.entities.toys.Ball;
import ExamPreparation.Exam11December2021.catHouse.entities.toys.Mouse;
import ExamPreparation.Exam11December2021.catHouse.entities.toys.Toy;
import ExamPreparation.Exam11December2021.catHouse.repositories.Repository;
import ExamPreparation.Exam11December2021.catHouse.repositories.ToyRepository;
import ExamPreparation.Exam11December2021.catHouse.entities.houses.LongHouse;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

public class ControllerImpl implements Controller {
    private Repository toys;
    private Collection<House> houses;

    public ControllerImpl() {
        this.toys = new ToyRepository();
        this.houses = new ArrayList<>();
    }

    @Override
    public String addHouse(String type, String name) {
        House house;
        switch (type) {
            case "ShortHouse":
                house = new ShortHouse(name);
                break;
            case "LongHouse":
                house = new LongHouse(name);
                break;
            default:
                throw new NullPointerException(ExceptionMessages.INVALID_HOUSE_TYPE);
        }

        houses.add(house);

        return String.format(ConstantMessages.SUCCESSFULLY_ADDED_HOUSE_TYPE, type);
    }

    @Override
    public String buyToy(String type) {
        Toy toy;
        switch (type) {
            case "Ball":
                toy = new Ball();
                break;
            case "Mouse":
                toy = new Mouse();
                break;
            default:
                throw new IllegalArgumentException(ExceptionMessages.INVALID_TOY_TYPE);
        }

        toys.buyToy(toy);

        return String.format(ConstantMessages.SUCCESSFULLY_ADDED_TOY_TYPE, type);
    }

    @Override
    public String toyForHouse(String houseName, String toyType) {
        Toy toy = toys.findFirst(toyType);

        if (toy == null) {
            throw new IllegalArgumentException(String.format(ExceptionMessages.NO_TOY_FOUND, toyType));
        }

        toys.removeToy(toy);
        House house = getHouse(houseName);
        house.buyToy(toy);

        return String.format(ConstantMessages.SUCCESSFULLY_ADDED_TOY_IN_HOUSE, toyType, houseName);
    }

    @Override
    public String addCat(String houseName, String catType, String catName, String catBreed, double price) {
        Cat cat;
        switch (catType) {
            case "ShorthairCat":
                cat = new ShorthairCat(catName, catBreed, price);
                break;
            case "LonghairCat":
                cat = new LonghairCat(catName, catBreed, price);
                break;
            default:
                throw new IllegalArgumentException(ExceptionMessages.INVALID_CAT_TYPE);
        }

        House house = getHouse(houseName);
        if (house.getClass().getSimpleName().substring(0, 4).equals(cat.getClass().getSimpleName().substring(0, 4))) {
            house.addCat(cat);
            return String.format(ConstantMessages.SUCCESSFULLY_ADDED_CAT_IN_HOUSE, catType, houseName);
        }

        return ConstantMessages.UNSUITABLE_HOUSE;
    }

    @Override
    public String feedingCat(String houseName) {
        House house = getHouse(houseName);

        house.feeding();

        return String.format(ConstantMessages.FEEDING_CAT, house.getCats().size());
    }

    @Override
    public String sumOfAll(String houseName) {
        House house = getHouse(houseName);
        double value = house.getCats().stream().mapToDouble(Cat::getPrice).sum() + house.getToys().stream().mapToDouble(Toy::getPrice).sum();
        return String.format(ConstantMessages.VALUE_HOUSE, houseName, value);
    }

    @Override
    public String getStatistics() {
        return houses.stream().map(House::getStatistics).collect(Collectors.joining(System.lineSeparator()));
    }

    private House getHouse(String houseName) {
        return houses.stream().filter(h -> h.getName().equals(houseName)).findFirst().orElse(null);
    }
}
