package ExamPreparation.Exam12Dec2020.bakery.entities.tables;

import ExamPreparation.Exam12Dec2020.bakery.entities.bakedFoods.interfaces.BakedFood;
import ExamPreparation.Exam12Dec2020.bakery.entities.drinks.interfaces.Drink;
import ExamPreparation.Exam12Dec2020.bakery.entities.tables.interfaces.Table;

import java.util.ArrayList;
import java.util.Collection;

import static ExamPreparation.Exam12Dec2020.bakery.common.ExceptionMessages.INVALID_NUMBER_OF_PEOPLE;
import static ExamPreparation.Exam12Dec2020.bakery.common.ExceptionMessages.INVALID_TABLE_CAPACITY;

public abstract class BaseTable implements Table {
    private Collection<BakedFood> foodOrders;
    private Collection<Drink> drinkOrders;
    private int tableNumber;
    private int capacity;
    private int numberOfPeople;
    private double pricePerPerson;
    private boolean isReserved;
    private double price;

    public BaseTable(int tableNumber, int capacity, double pricePerPerson) {
        this.tableNumber = tableNumber;
        this.setCapacity(capacity);
        this.pricePerPerson = pricePerPerson;
        this.foodOrders = new ArrayList<>();
        this.drinkOrders = new ArrayList<>();
    }

    public void setCapacity(int capacity) {
        if (capacity < 0) {
            throw new IllegalArgumentException(INVALID_TABLE_CAPACITY);
        }
        this.capacity = capacity;
    }

    @Override
    public int getTableNumber() {
        return this.tableNumber;
    }

    @Override
    public int getCapacity() {
        return this.capacity;
    }

    @Override
    public int getNumberOfPeople() {
        return this.numberOfPeople;
    }

    @Override
    public double getPricePerPerson() {
        return this.pricePerPerson;
    }

    @Override
    public boolean isReserved() {
        return this.isReserved;
    }

    @Override
    public double getPrice() {
        return this.price;
    }

    @Override
    public void reserve(int numberOfPeople) {
        if (numberOfPeople <= 0) {
            throw new IllegalArgumentException(INVALID_NUMBER_OF_PEOPLE);
        }
        this.isReserved = true;
        this.numberOfPeople = numberOfPeople;
        this.price = numberOfPeople * pricePerPerson;
    }

    @Override
    public void orderFood(BakedFood food) {
        this.foodOrders.add(food);
    }

    @Override
    public void orderDrink(Drink drink) {
        this.drinkOrders.add(drink);
    }

    @Override
    public double getBill() {
        double totalFoodPrice = this.foodOrders.stream().mapToDouble(BakedFood::getPrice).sum();
        double totalDrinkPrice = this.drinkOrders.stream().mapToDouble(Drink::getPrice).sum();

        return totalFoodPrice + totalDrinkPrice + getPrice();
    }

    @Override
    public void clear() {
        this.foodOrders = new ArrayList<>();
        this.drinkOrders = new ArrayList<>();
        this.isReserved = false;
        this.numberOfPeople = 0;
        this.price = 0;
    }

    @Override
    public String getFreeTableInfo() {
        return String.format("Table: %d%nType: %s%nCapacity: %d%nPrice per Person: %.2f",
                this.getTableNumber(), this.getClass().getSimpleName(),
                this.getCapacity(), this.getPricePerPerson());
    }
}
