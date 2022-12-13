package ExamPreparation.Exam22August2022.goldDigger.core;

import ExamPreparation.Exam22August2022.goldDigger.models.discoverer.Anthropologist;
import ExamPreparation.Exam22August2022.goldDigger.models.discoverer.Archaeologist;
import ExamPreparation.Exam22August2022.goldDigger.models.discoverer.Discoverer;
import ExamPreparation.Exam22August2022.goldDigger.models.discoverer.Geologist;
import ExamPreparation.Exam22August2022.goldDigger.models.operation.OperationImpl;
import ExamPreparation.Exam22August2022.goldDigger.models.spot.Spot;
import ExamPreparation.Exam22August2022.goldDigger.models.spot.SpotImpl;
import ExamPreparation.Exam22August2022.goldDigger.repositories.DiscovererRepository;
import ExamPreparation.Exam22August2022.goldDigger.repositories.SpotRepository;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static ExamPreparation.Exam22August2022.goldDigger.common.ConstantMessages.*;
import static ExamPreparation.Exam22August2022.goldDigger.common.ExceptionMessages.*;

public class ControllerImpl implements Controller {
    private DiscovererRepository discovererRepository;
    private SpotRepository spotRepository;
    private int spotInspected;

    public ControllerImpl() {
        discovererRepository = new DiscovererRepository();
        spotRepository = new SpotRepository();
    }

    @Override
    public String addDiscoverer(String kind, String discovererName) {
        Discoverer discoverer;
        if ("Archaeologist".equals(kind)) {
            discoverer = new Archaeologist(discovererName);
        } else if ("Anthropologist".equals(kind)) {
            discoverer = new Anthropologist(discovererName);
        } else if ("Geologist".equals(kind)) {
            discoverer = new Geologist(discovererName);
        } else {
            throw new IllegalArgumentException(DISCOVERER_INVALID_KIND);
        }
        discovererRepository.add(discoverer);
        return String.format(DISCOVERER_ADDED, kind, discovererName);
    }

    @Override
    public String addSpot(String spotName, String... exhibits) {
        Spot spot = new SpotImpl(spotName);
        for (String exhibit : exhibits) {
            spot.getExhibits().add(exhibit);
        }
        spotRepository.add(spot);
        return String.format(SPOT_ADDED, spotName);
    }

    @Override
    public String excludeDiscoverer(String discovererName) {
        Discoverer discoverer = discovererRepository.byName(discovererName);
        if (discoverer == null) {
            throw new IllegalArgumentException(String.format(DISCOVERER_DOES_NOT_EXIST, discovererName));
        }
        discovererRepository.remove(discoverer);
        return String.format(DISCOVERER_EXCLUDE, discovererName);
    }

    @Override
    public String inspectSpot(String spotName) {
        Spot spot = spotRepository.byName(spotName);
        List<Discoverer> discoverers = discovererRepository.getCollection().stream()
                .filter(discoverer -> discoverer.getEnergy() > 45)
                .collect(Collectors.toList());
        if (discoverers.size() == 0) {
            throw new IllegalArgumentException(SPOT_DISCOVERERS_DOES_NOT_EXISTS);
        }
        spotInspected++;
        OperationImpl operation = new OperationImpl();
        operation.startOperation(spot, discoverers);
        long excludedDiscoverers = discoverers.stream().filter(discoverer -> discoverer.getEnergy()==0).count();
        return String.format(INSPECT_SPOT, spotName, excludedDiscoverers);
    }

    @Override
    public String getStatistics() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format(FINAL_SPOT_INSPECT, spotInspected)).append(System.lineSeparator());
        sb.append(FINAL_DISCOVERER_INFO).append(System.lineSeparator());
        Collection<Discoverer> discoverers = discovererRepository.getCollection();
        for (Discoverer discoverer : discoverers) {
            Collection<String> exhibits = discoverer.getMuseum().getExhibits();
            sb.append(String.format(FINAL_DISCOVERER_NAME, discoverer.getName())).append(System.lineSeparator());
            sb.append(String.format(FINAL_DISCOVERER_ENERGY, discoverer.getEnergy())).append(System.lineSeparator());
            sb.append(String.format(FINAL_DISCOVERER_MUSEUM_EXHIBITS,
                            exhibits.size() == 0 ? "None" : String.join(FINAL_DISCOVERER_MUSEUM_EXHIBITS_DELIMITER, exhibits)))
                    .append(System.lineSeparator());
        }
        return sb.toString().trim();
    }
}
