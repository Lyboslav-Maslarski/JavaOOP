package ExamPreparation.Exam12Dec2020.bakery.core;

import ExamPreparation.Exam12Dec2020.bakery.core.interfaces.Controller;
import ExamPreparation.Exam12Dec2020.bakery.entities.bakedFoods.Bread;
import ExamPreparation.Exam12Dec2020.bakery.entities.bakedFoods.Cake;
import ExamPreparation.Exam12Dec2020.bakery.entities.bakedFoods.interfaces.BakedFood;
import ExamPreparation.Exam12Dec2020.bakery.entities.drinks.Tea;
import ExamPreparation.Exam12Dec2020.bakery.entities.drinks.Water;
import ExamPreparation.Exam12Dec2020.bakery.entities.drinks.interfaces.Drink;
import ExamPreparation.Exam12Dec2020.bakery.entities.tables.InsideTable;
import ExamPreparation.Exam12Dec2020.bakery.entities.tables.OutsideTable;
import ExamPreparation.Exam12Dec2020.bakery.entities.tables.interfaces.Table;
import ExamPreparation.Exam12Dec2020.bakery.repositories.interfaces.TableRepository;
import ExamPreparation.Exam12Dec2020.bakery.repositories.interfaces.DrinkRepository;
import ExamPreparation.Exam12Dec2020.bakery.repositories.interfaces.FoodRepository;

import java.util.stream.Collectors;

import static ExamPreparation.Exam12Dec2020.bakery.common.ExceptionMessages.FOOD_OR_DRINK_EXIST;
import static ExamPreparation.Exam12Dec2020.bakery.common.ExceptionMessages.TABLE_EXIST;
import static ExamPreparation.Exam12Dec2020.bakery.common.OutputMessages.*;

public class ControllerImpl implements Controller {

    private FoodRepository<BakedFood> foodRepository;
    private DrinkRepository<Drink> drinkRepository;
    private TableRepository<Table> tableRepository;
    private double totalIncome;

    public ControllerImpl(FoodRepository<BakedFood> foodRepository, DrinkRepository<Drink> drinkRepository, TableRepository<Table> tableRepository) {
        this.foodRepository = foodRepository;
        this.drinkRepository = drinkRepository;
        this.tableRepository = tableRepository;
    }

    @Override
    public String addFood(String type, String name, double price) {
        BakedFood food = this.foodRepository.getByName(name);

        if (food != null) {
            throw new IllegalArgumentException(String.format(FOOD_OR_DRINK_EXIST, type, name));
        }

        switch (type) {
            case "Bread":
                food = new Bread(name, price);
                break;
            case "Cake":
                food = new Cake(name, price);
                break;
        }

        this.foodRepository.add(food);

        return String.format(FOOD_ADDED, name, type);
    }

    @Override
    public String addDrink(String type, String name, int portion, String brand) {
        Drink drink = this.drinkRepository.getByNameAndBrand(name, brand);

        if (drink != null) {
            throw new IllegalArgumentException(String.format(FOOD_OR_DRINK_EXIST, type, name));
        }

        switch (type) {
            case "Tea":
                drink = new Tea(name, portion, brand);
                break;
            case "Water":
                drink = new Water(name, portion, brand);
                break;
        }

        this.drinkRepository.add(drink);

        return String.format(DRINK_ADDED, name, brand);
    }

    @Override
    public String addTable(String type, int tableNumber, int capacity) {
        Table table = tableRepository.getByNumber(tableNumber);

        if (table != null) {
            throw new IllegalArgumentException(String.format(TABLE_EXIST, tableNumber));
        }

        switch (type) {
            case "InsideTable":
                table = new InsideTable(tableNumber, capacity);
                break;
            case "OutsideTable":
                table = new OutsideTable(tableNumber, capacity);
                break;
        }

        this.tableRepository.add(table);

        return String.format(TABLE_ADDED, tableNumber);
    }

    @Override
    public String reserveTable(int numberOfPeople) {
        Table table = this.tableRepository.getAll().stream()
                .filter(t -> !t.isReserved() && t.getCapacity() >= numberOfPeople)
                .findFirst().orElse(null);

        if (table == null) {
            return String.format(RESERVATION_NOT_POSSIBLE, numberOfPeople);
        }

        table.reserve(numberOfPeople);

        return String.format(TABLE_RESERVED, table.getTableNumber(), numberOfPeople);
    }

    @Override
    public String orderFood(int tableNumber, String foodName) {
        Table table = this.tableRepository.getByNumber(tableNumber);
        if (table == null || !table.isReserved()) {
            return String.format(WRONG_TABLE_NUMBER, tableNumber);
        }

        BakedFood food = this.foodRepository.getByName(foodName);
        if (food == null) {
            return String.format(NONE_EXISTENT_FOOD, foodName);
        }

        table.orderFood(food);

        return String.format(FOOD_ORDER_SUCCESSFUL, tableNumber, foodName);
    }

    @Override
    public String orderDrink(int tableNumber, String drinkName, String drinkBrand) {
        Table table = tableRepository.getByNumber(tableNumber);
        if (table == null || !table.isReserved()) {
            return String.format(WRONG_TABLE_NUMBER, tableNumber);
        }

        Drink drink = drinkRepository.getByNameAndBrand(drinkName, drinkBrand);
        if (drink == null) {
            return String.format(NON_EXISTENT_DRINK, drinkName, drinkBrand);
        }

        table.orderDrink(drink);

        return String.format(DRINK_ORDER_SUCCESSFUL, tableNumber, drinkName, drinkBrand);
    }

    @Override
    public String leaveTable(int tableNumber) {
        Table table = tableRepository.getByNumber(tableNumber);

        double tableBill = table.getBill();
        this.totalIncome += tableBill;
        table.clear();

        return String.format(BILL, tableNumber, tableBill);
    }

    @Override
    public String getFreeTablesInfo() {
        return this.tableRepository.getAll().stream()
                .filter(table -> !table.isReserved())
                .map(Table::getFreeTableInfo)
                .collect(Collectors.joining(System.lineSeparator()));
    }

    @Override
    public String getTotalIncome() {
        return String.format(TOTAL_INCOME, this.totalIncome);
    }
}
