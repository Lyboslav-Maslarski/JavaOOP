package ExamPreparation.Exam18April2022.zoo.core;

import ExamPreparation.Exam18April2022.zoo.entities.animals.Animal;
import ExamPreparation.Exam18April2022.zoo.entities.animals.AquaticAnimal;
import ExamPreparation.Exam18April2022.zoo.entities.animals.TerrestrialAnimal;
import ExamPreparation.Exam18April2022.zoo.entities.areas.Area;
import ExamPreparation.Exam18April2022.zoo.entities.areas.LandArea;
import ExamPreparation.Exam18April2022.zoo.entities.areas.WaterArea;
import ExamPreparation.Exam18April2022.zoo.entities.foods.Food;
import ExamPreparation.Exam18April2022.zoo.entities.foods.Meat;
import ExamPreparation.Exam18April2022.zoo.entities.foods.Vegetable;
import ExamPreparation.Exam18April2022.zoo.repositories.FoodRepositoryImpl;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import static ExamPreparation.Exam18April2022.zoo.common.ConstantMessages.*;
import static ExamPreparation.Exam18April2022.zoo.common.ExceptionMessages.*;

public class ControllerImpl implements Controller {
    private FoodRepositoryImpl foodRepository;
    private Map<String, Area> areaCollection;

    public ControllerImpl() {
        this.foodRepository = new FoodRepositoryImpl();
        this.areaCollection = new HashMap<>();
    }

    @Override
    public String addArea(String areaType, String areaName) {
        Area area;

        if (areaType.equals("WaterArea")) {
            area = new WaterArea(areaName);
        } else if (areaType.equals("LandArea")) {
            area = new LandArea(areaName);
        } else {
            throw new NullPointerException(INVALID_AREA_TYPE);
        }

        areaCollection.put(areaName, area);

        return String.format(SUCCESSFULLY_ADDED_AREA_TYPE, areaType);
    }

    @Override
    public String buyFood(String foodType) {
        Food food;

        if (foodType.equals("Vegetable")) {
            food = new Vegetable();
        } else if (foodType.equals("Meat")) {
            food = new Meat();
        } else {
            throw new IllegalArgumentException(INVALID_FOOD_TYPE);
        }

        foodRepository.add(food);

        return String.format(SUCCESSFULLY_ADDED_FOOD_TYPE, foodType);
    }

    @Override
    public String foodForArea(String areaName, String foodType) {
        Food food = foodRepository.findByType(foodType);

        if (food == null) {
            throw new IllegalArgumentException(String.format(NO_FOOD_FOUND, foodType));
        }

        Area area = areaCollection.get(areaName);
        area.addFood(food);
        foodRepository.remove(food);

        return String.format(SUCCESSFULLY_ADDED_FOOD_IN_AREA, foodType, areaName);
    }

    @Override
    public String addAnimal(String areaName, String animalType, String animalName, String kind, double price) {
        Animal animal;

        if (animalType.equals("AquaticAnimal")) {
            animal = new AquaticAnimal(animalName, kind, price);
        } else if (animalType.equals("TerrestrialAnimal")) {
            animal = new TerrestrialAnimal(animalName, kind, price);
        } else {
            throw new IllegalArgumentException(INVALID_ANIMAL_TYPE);
        }

        Area area = areaCollection.get(areaName);
        String areaType = area.getClass().getSimpleName();

        boolean animalAndAreaAreWaterBased = animalType.equals("AquaticAnimal") && areaType.equals("WaterArea");
        boolean animalAndAreaAreLandBased = animalType.equals("TerrestrialAnimal") && areaType.equals("LandArea");

        if (animalAndAreaAreWaterBased || animalAndAreaAreLandBased) {
            area.addAnimal(animal);
        } else {
            return AREA_NOT_SUITABLE;
        }

        return String.format(SUCCESSFULLY_ADDED_ANIMAL_IN_AREA, animalType, areaName);
    }

    @Override
    public String feedAnimal(String areaName) {
        Area area = areaCollection.get(areaName);
        area.feed();
        return String.format(ANIMALS_FED, area.getAnimals().size());
    }

    @Override
    public String calculateKg(String areaName) {
        Collection<Animal> animals = areaCollection.get(areaName).getAnimals();

        double kgs = animals.stream()
                .mapToDouble(Animal::getKg)
                .sum();

        return String.format(KILOGRAMS_AREA, areaName, kgs);
    }

    @Override
    public String getStatistics() {
        return areaCollection.values().stream()
                .map(Area::getInfo)
                .collect(Collectors.joining(System.lineSeparator()));
    }
}
