package ExamPreparation.Exam10December2022.christmasPastryShop.core;

import ExamPreparation.Exam10December2022.christmasPastryShop.common.ExceptionMessages;
import ExamPreparation.Exam10December2022.christmasPastryShop.common.OutputMessages;
import ExamPreparation.Exam10December2022.christmasPastryShop.core.interfaces.Controller;
import ExamPreparation.Exam10December2022.christmasPastryShop.entities.booths.OpenBooth;
import ExamPreparation.Exam10December2022.christmasPastryShop.entities.booths.PrivateBooth;
import ExamPreparation.Exam10December2022.christmasPastryShop.entities.booths.interfaces.Booth;
import ExamPreparation.Exam10December2022.christmasPastryShop.entities.cocktails.Hibernation;
import ExamPreparation.Exam10December2022.christmasPastryShop.entities.cocktails.MulledWine;
import ExamPreparation.Exam10December2022.christmasPastryShop.entities.cocktails.interfaces.Cocktail;
import ExamPreparation.Exam10December2022.christmasPastryShop.entities.delicacies.Gingerbread;
import ExamPreparation.Exam10December2022.christmasPastryShop.entities.delicacies.Stolen;
import ExamPreparation.Exam10December2022.christmasPastryShop.entities.delicacies.interfaces.Delicacy;
import ExamPreparation.Exam10December2022.christmasPastryShop.repositories.interfaces.BoothRepository;
import ExamPreparation.Exam10December2022.christmasPastryShop.repositories.interfaces.CocktailRepository;
import ExamPreparation.Exam10December2022.christmasPastryShop.repositories.interfaces.DelicacyRepository;

public class ControllerImpl implements Controller {

    private DelicacyRepository<Delicacy> delicacyRepository;
    private CocktailRepository<Cocktail> cocktailRepository;
    private BoothRepository<Booth> boothRepository;
    private double totalIncome;

    public ControllerImpl(DelicacyRepository<Delicacy> delicacyRepository, CocktailRepository<Cocktail> cocktailRepository, BoothRepository<Booth> boothRepository) {
        this.delicacyRepository = delicacyRepository;
        this.cocktailRepository = cocktailRepository;
        this.boothRepository = boothRepository;
    }


    @Override
    public String addDelicacy(String type, String name, double price) {
        Delicacy delicacy = delicacyRepository.getByName(name);
        if (delicacy != null) {
            throw new IllegalArgumentException(String.format(ExceptionMessages.FOOD_OR_DRINK_EXIST, type, name));
        }

        switch (type) {
            case "Gingerbread":
                delicacy = new Gingerbread(name, price);
                break;
            case "Stolen":
                delicacy = new Stolen(name, price);
                break;
        }

        delicacyRepository.add(delicacy);

        return String.format(OutputMessages.DELICACY_ADDED, name, type);
    }

    @Override
    public String addCocktail(String type, String name, int size, String brand) {
        Cocktail cocktail = cocktailRepository.getByName(name);
        if (cocktail != null) {
            throw new IllegalArgumentException(String.format(ExceptionMessages.FOOD_OR_DRINK_EXIST, type, name));
        }

        switch (type) {
            case "MulledWine":
                cocktail = new MulledWine(name, size, brand);
                break;
            case "Hibernation":
                cocktail = new Hibernation(name, size, brand);
                break;
        }

        cocktailRepository.add(cocktail);

        return String.format(OutputMessages.COCKTAIL_ADDED, name, brand);
    }

    @Override
    public String addBooth(String type, int boothNumber, int capacity) {
        Booth booth = boothRepository.getByNumber(boothNumber);
        if (booth != null) {
            throw new IllegalArgumentException(String.format(ExceptionMessages.BOOTH_EXIST, boothNumber));
        }

        switch (type) {
            case "OpenBooth":
                booth = new OpenBooth(boothNumber, capacity);
                break;
            case "PrivateBooth":
                booth = new PrivateBooth(boothNumber, capacity);
                break;
        }

        boothRepository.add(booth);

        return String.format(OutputMessages.BOOTH_ADDED, boothNumber);
    }

    @Override
    public String reserveBooth(int numberOfPeople) {
        Booth booth = boothRepository.getAll().stream()
                .filter(b -> !b.isReserved() && b.getCapacity() >= numberOfPeople)
                .findFirst().orElse(null);

        if (booth == null) {
            return String.format(OutputMessages.RESERVATION_NOT_POSSIBLE, numberOfPeople);
        }

        booth.reserve(numberOfPeople);

        return String.format(OutputMessages.BOOTH_RESERVED, booth.getBoothNumber(), numberOfPeople);
    }

    @Override
    public String leaveBooth(int boothNumber) {
        Booth booth = boothRepository.getByNumber(boothNumber);
        double bill = booth.getBill();
        booth.clear();
        totalIncome += bill;
        return String.format(OutputMessages.BILL, boothNumber, bill);
    }

    @Override
    public String getIncome() {
        return String.format(OutputMessages.TOTAL_INCOME, totalIncome);
    }
}
