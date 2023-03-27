package ExamPreparation.Exam18April2021.spaceStation.core;

import ExamPreparation.Exam18April2021.spaceStation.common.ConstantMessages;
import ExamPreparation.Exam18April2021.spaceStation.common.ExceptionMessages;
import ExamPreparation.Exam18April2021.spaceStation.repositories.PlanetRepository;
import ExamPreparation.Exam18April2021.spaceStation.repositories.Repository;
import ExamPreparation.Exam18April2021.spaceStation.models.astronauts.Astronaut;
import ExamPreparation.Exam18April2021.spaceStation.models.astronauts.Biologist;
import ExamPreparation.Exam18April2021.spaceStation.models.astronauts.Geodesist;
import ExamPreparation.Exam18April2021.spaceStation.models.astronauts.Meteorologist;
import ExamPreparation.Exam18April2021.spaceStation.models.mission.Mission;
import ExamPreparation.Exam18April2021.spaceStation.models.mission.MissionImpl;
import ExamPreparation.Exam18April2021.spaceStation.models.planets.Planet;
import ExamPreparation.Exam18April2021.spaceStation.models.planets.PlanetImpl;
import ExamPreparation.Exam18April2021.spaceStation.repositories.AstronautRepository;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class ControllerImpl implements Controller {

    private Repository<Astronaut> astronautRepository;
    private Repository<Planet> planetRepository;
    private int planetsExplored;

    public ControllerImpl() {
        this.astronautRepository = new AstronautRepository();
        this.planetRepository = new PlanetRepository();
    }

    @Override
    public String addAstronaut(String type, String astronautName) {
        Astronaut astronaut;
        switch (type) {
            case "Biologist":
                astronaut = new Biologist(astronautName);
                break;
            case "Geodesist":
                astronaut = new Geodesist(astronautName);
                break;
            case "Meteorologist":
                astronaut = new Meteorologist(astronautName);
                break;
            default:
                throw new IllegalArgumentException(ExceptionMessages.ASTRONAUT_INVALID_TYPE);
        }
        astronautRepository.add(astronaut);

        return String.format(ConstantMessages.ASTRONAUT_ADDED, type, astronautName);
    }

    @Override
    public String addPlanet(String planetName, String... items) {
        Planet planet = new PlanetImpl(planetName);

        planet.getItems().addAll(List.of(items));

        planetRepository.add(planet);

        return String.format(ConstantMessages.PLANET_ADDED, planetName);
    }

    @Override
    public String retireAstronaut(String astronautName) {
        Astronaut astronaut = astronautRepository.findByName(astronautName);
        if (astronaut == null) {
            throw new IllegalArgumentException(String.format(ExceptionMessages.ASTRONAUT_DOES_NOT_EXIST, astronautName));
        }
        astronautRepository.remove(astronaut);
        return String.format(ConstantMessages.ASTRONAUT_RETIRED, astronautName);
    }

    @Override
    public String explorePlanet(String planetName) {
        List<Astronaut> astronauts = astronautRepository.getModels().stream().filter(a -> a.getOxygen() > 60).collect(Collectors.toList());
        if (astronauts.size() == 0) {
            throw new IllegalArgumentException(ExceptionMessages.PLANET_ASTRONAUTS_DOES_NOT_EXISTS);
        }
        Planet planet = planetRepository.findByName(planetName);

        Mission mission = new MissionImpl();
        mission.explore(planet, astronauts);
        planetsExplored++;

        long count = astronauts.stream().filter(a -> !a.canBreath()).count();

        return String.format(ConstantMessages.PLANET_EXPLORED, planetName, count);
    }

    @Override
    public String report() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format(ConstantMessages.REPORT_PLANET_EXPLORED, planetsExplored)).append(System.lineSeparator());
        sb.append(ConstantMessages.REPORT_ASTRONAUT_INFO).append(System.lineSeparator());
        Collection<Astronaut> astronauts = astronautRepository.getModels();
        for (Astronaut astronaut : astronauts) {
            sb.append(String.format(ConstantMessages.REPORT_ASTRONAUT_NAME, astronaut.getName())).append(System.lineSeparator());
            sb.append(String.format(ConstantMessages.REPORT_ASTRONAUT_OXYGEN, astronaut.getOxygen())).append(System.lineSeparator());
            Collection<String> items = astronaut.getBag().getItems();
            String bagContent = items.size() == 0 ? "none" : String.join(ConstantMessages.REPORT_ASTRONAUT_BAG_ITEMS_DELIMITER, items);
            sb.append(String.format(ConstantMessages.REPORT_ASTRONAUT_BAG_ITEMS, bagContent)).append(System.lineSeparator());
        }
        return sb.toString().trim();
    }
}
