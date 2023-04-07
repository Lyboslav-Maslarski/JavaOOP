package ExamPreparation.Exam10December2022.christmasPastryShop.entities.booths;

import ExamPreparation.Exam10December2022.christmasPastryShop.entities.booths.interfaces.Booth;
import ExamPreparation.Exam10December2022.christmasPastryShop.entities.cocktails.interfaces.Cocktail;
import ExamPreparation.Exam10December2022.christmasPastryShop.entities.delicacies.interfaces.Delicacy;

import java.util.ArrayList;
import java.util.Collection;

import static ExamPreparation.Exam10December2022.christmasPastryShop.common.ExceptionMessages.INVALID_NUMBER_OF_PEOPLE;
import static ExamPreparation.Exam10December2022.christmasPastryShop.common.ExceptionMessages.INVALID_TABLE_CAPACITY;

public abstract class BaseBooth implements Booth {
    private Collection<Delicacy> delicacyOrders;
    private Collection<Cocktail> cocktailOrders;
    private int boothNumber;
    private int capacity;
    private int numberOfPeople;
    private double pricePerPerson;
    private boolean isReserved;
    private double price;

    public BaseBooth(int boothNumber, int capacity, double pricePerPerson) {
        this.boothNumber = boothNumber;
        this.setCapacity(capacity);
        this.pricePerPerson = pricePerPerson;
        this.delicacyOrders = new ArrayList<>();
        this.cocktailOrders = new ArrayList<>();
    }

    public void setCapacity(int capacity) {
        if (capacity < 0) {
            throw new IllegalArgumentException(INVALID_TABLE_CAPACITY);
        }
        this.capacity = capacity;
    }

    public void setNumberOfPeople(int numberOfPeople) {
        if (numberOfPeople <= 0) {
            throw new IllegalArgumentException(INVALID_NUMBER_OF_PEOPLE);
        }
        this.numberOfPeople = numberOfPeople;
    }

    @Override
    public int getBoothNumber() {
        return boothNumber;
    }

    @Override
    public int getCapacity() {
        return capacity;
    }

    @Override
    public boolean isReserved() {
        return isReserved;
    }

    @Override
    public double getPrice() {
        return price;
    }

    @Override
    public void reserve(int numberOfPeople) {
        setNumberOfPeople(numberOfPeople);
        price = numberOfPeople * pricePerPerson;
        isReserved = true;
    }

    @Override
    public double getBill() {
        return getPrice() +
               delicacyOrders.stream().mapToDouble(Delicacy::getPrice).sum() +
               cocktailOrders.stream().mapToDouble(Cocktail::getPrice).sum();
    }

    @Override
    public void clear() {
        delicacyOrders.clear();
        cocktailOrders.clear();
        isReserved = false;
        numberOfPeople = 0;
        price = 0;
    }
}
