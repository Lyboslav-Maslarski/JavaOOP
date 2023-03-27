package ExamPreparation.Exam10April2021.aquarium.core;

import ExamPreparation.Exam10April2021.aquarium.common.ConstantMessages;
import ExamPreparation.Exam10April2021.aquarium.entities.aquariums.Aquarium;
import ExamPreparation.Exam10April2021.aquarium.entities.aquariums.FreshwaterAquarium;
import ExamPreparation.Exam10April2021.aquarium.entities.aquariums.SaltwaterAquarium;
import ExamPreparation.Exam10April2021.aquarium.entities.decorations.Decoration;
import ExamPreparation.Exam10April2021.aquarium.entities.decorations.Ornament;
import ExamPreparation.Exam10April2021.aquarium.entities.decorations.Plant;
import ExamPreparation.Exam10April2021.aquarium.entities.fish.Fish;
import ExamPreparation.Exam10April2021.aquarium.entities.fish.FreshwaterFish;
import ExamPreparation.Exam10April2021.aquarium.entities.fish.SaltwaterFish;
import ExamPreparation.Exam10April2021.aquarium.repositories.DecorationRepository;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import static ExamPreparation.Exam10April2021.aquarium.common.ExceptionMessages.*;

public class ControllerImpl implements Controller {
    private DecorationRepository decorations;
    private Map<String, Aquarium> aquariums;

    public ControllerImpl() {
        this.decorations = new DecorationRepository();
        this.aquariums = new HashMap<>();
    }

    @Override
    public String addAquarium(String aquariumType, String aquariumName) {
        Aquarium aquarium;
        switch (aquariumType) {
            case "FreshwaterAquarium":
                aquarium = new FreshwaterAquarium(aquariumName);
                break;
            case "SaltwaterAquarium":
                aquarium = new SaltwaterAquarium(aquariumName);
                break;
            default:
                throw new NullPointerException(INVALID_AQUARIUM_TYPE);
        }

        aquariums.put(aquariumName, aquarium);

        return String.format(ConstantMessages.SUCCESSFULLY_ADDED_AQUARIUM_TYPE, aquariumType);
    }

    @Override
    public String addDecoration(String type) {
        Decoration decoration;
        switch (type) {
            case "Ornament":
                decoration = new Ornament();
                break;
            case "Plant":
                decoration = new Plant();
                break;
            default:
                throw new IllegalArgumentException(INVALID_DECORATION_TYPE);
        }

        decorations.add(decoration);

        return String.format(ConstantMessages.SUCCESSFULLY_ADDED_DECORATION_TYPE, type);
    }

    @Override
    public String insertDecoration(String aquariumName, String decorationType) {
        Decoration decoration = decorations.findByType(decorationType);
        if (decoration == null) {
            throw new IllegalArgumentException(String.format(NO_DECORATION_FOUND, decorationType));
        }

        decorations.remove(decoration);
        aquariums.get(aquariumName).addDecoration(decoration);

        return String.format(ConstantMessages.SUCCESSFULLY_ADDED_DECORATION_IN_AQUARIUM, decorationType, aquariumName);
    }

    @Override
    public String addFish(String aquariumName, String fishType, String fishName, String fishSpecies, double price) {
        Fish fish;
        switch (fishType) {
            case "FreshwaterFish":
                fish = new FreshwaterFish(fishName, fishSpecies, price);
                break;
            case "SaltwaterFish":
                fish = new SaltwaterFish(fishName, fishSpecies, price);
                break;
            default:
                throw new IllegalArgumentException(INVALID_FISH_TYPE);
        }

        Aquarium aquarium = aquariums.get(aquariumName);

        String aquariumClassName = aquarium.getClass().getSimpleName();
        String fishClassName = fish.getClass().getSimpleName();

        if (!aquariumClassName.substring(0, 5).equals(fishClassName.substring(0, 5))) {
            return ConstantMessages.WATER_NOT_SUITABLE;
        }

        try {
            aquarium.addFish(fish);
        } catch (IllegalStateException e) {
            return e.getMessage();
        }

        return String.format(ConstantMessages.SUCCESSFULLY_ADDED_FISH_IN_AQUARIUM, fishType, aquariumName);
    }

    @Override
    public String feedFish(String aquariumName) {
        Aquarium aquarium = aquariums.get(aquariumName);

        aquarium.feed();

        return String.format(ConstantMessages.FISH_FED, aquarium.getFish().size());
    }

    @Override
    public String calculateValue(String aquariumName) {
        Aquarium aquarium = aquariums.get(aquariumName);
        double aquariumValue = aquarium.getFish().stream().mapToDouble(Fish::getPrice).sum() +
                               aquarium.getDecorations().stream().mapToDouble(Decoration::getPrice).sum();
        return String.format(ConstantMessages.VALUE_AQUARIUM, aquariumName, aquariumValue);
    }

    @Override
    public String report() {
        return aquariums.values().stream().map(Aquarium::getInfo).collect(Collectors.joining(System.lineSeparator()));
    }
}
