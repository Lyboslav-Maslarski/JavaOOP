package ExamPreparation.Exam22August2021.glacialExpedition.core;

import ExamPreparation.Exam22August2021.glacialExpedition.common.ConstantMessages;
import ExamPreparation.Exam22August2021.glacialExpedition.common.ExceptionMessages;
import ExamPreparation.Exam22August2021.glacialExpedition.models.explorers.AnimalExplorer;
import ExamPreparation.Exam22August2021.glacialExpedition.models.explorers.Explorer;
import ExamPreparation.Exam22August2021.glacialExpedition.models.explorers.GlacierExplorer;
import ExamPreparation.Exam22August2021.glacialExpedition.models.explorers.NaturalExplorer;
import ExamPreparation.Exam22August2021.glacialExpedition.models.mission.Mission;
import ExamPreparation.Exam22August2021.glacialExpedition.models.mission.MissionImpl;
import ExamPreparation.Exam22August2021.glacialExpedition.models.states.StateImpl;
import ExamPreparation.Exam22August2021.glacialExpedition.repositories.ExplorerRepository;
import ExamPreparation.Exam22August2021.glacialExpedition.repositories.StateRepository;
import ExamPreparation.Exam22August2021.glacialExpedition.models.states.State;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class ControllerImpl implements Controller {
    private ExplorerRepository explorerRepository;
    private StateRepository stateRepository;
    private int exploredStates;

    public ControllerImpl() {
        this.explorerRepository = new ExplorerRepository();
        this.stateRepository = new StateRepository();
        exploredStates = 0;
    }

    @Override
    public String addExplorer(String type, String explorerName) {
        Explorer explorer;

        if (type.equals("NaturalExplorer")) {
            explorer = new NaturalExplorer(explorerName);
        } else if (type.equals("GlacierExplorer")) {
            explorer = new GlacierExplorer(explorerName);
        } else if (type.equals("AnimalExplorer")) {
            explorer = new AnimalExplorer(explorerName);
        } else {
            throw new IllegalArgumentException(ExceptionMessages.EXPLORER_INVALID_TYPE);
        }

        explorerRepository.add(explorer);

        return String.format(ConstantMessages.EXPLORER_ADDED, type, explorerName);
    }

    @Override
    public String addState(String stateName, String... exhibits) {
        State state = new StateImpl(stateName);

        Collections.addAll(state.getExhibits(), exhibits);

        stateRepository.add(state);

        return String.format(ConstantMessages.STATE_ADDED, stateName);
    }

    @Override
    public String retireExplorer(String explorerName) {
        Explorer explorer = explorerRepository.byName(explorerName);

        if (explorer == null) {
            throw new IllegalArgumentException(String.format(ExceptionMessages.EXPLORER_DOES_NOT_EXIST, explorerName));
        }

        explorerRepository.remove(explorer);

        return String.format(ConstantMessages.EXPLORER_RETIRED, explorerName);
    }

    @Override
    public String exploreState(String stateName) {
        List<Explorer> validExplorers = explorerRepository.getCollection().stream()
                .filter(explorer -> explorer.getEnergy() > 50)
                .collect(Collectors.toList());
        if (validExplorers.isEmpty()) {
            throw new IllegalArgumentException(ExceptionMessages.STATE_EXPLORERS_DOES_NOT_EXISTS);
        }

        State state = stateRepository.byName(stateName);
        Mission mission = new MissionImpl();
        mission.explore(state, validExplorers);
        exploredStates++;
        long countOfRetiredExplorers = validExplorers.stream().filter(explorer -> explorer.getEnergy() == 0).count();

        return String.format(ConstantMessages.STATE_EXPLORER, stateName, countOfRetiredExplorers);
    }

    @Override
    public String finalResult() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(String.format(ConstantMessages.FINAL_STATE_EXPLORED, exploredStates)).append(System.lineSeparator());
        stringBuilder.append(ConstantMessages.FINAL_EXPLORER_INFO).append(System.lineSeparator());
        for (Explorer explorer : explorerRepository.getCollection()) {
            stringBuilder.append(String.format(ConstantMessages.FINAL_EXPLORER_NAME, explorer.getName())).append(System.lineSeparator());
            stringBuilder.append(String.format(ConstantMessages.FINAL_EXPLORER_ENERGY, explorer.getEnergy())).append(System.lineSeparator());
            Collection<String> currentExplorerExhibits = explorer.getSuitcase().getExhibits();
            String exhibits = currentExplorerExhibits.size() == 0 ? "None" : String.join(ConstantMessages.FINAL_EXPLORER_SUITCASE_EXHIBITS_DELIMITER, currentExplorerExhibits);
            stringBuilder.append(String.format(ConstantMessages.FINAL_EXPLORER_SUITCASE_EXHIBITS, exhibits)).append(System.lineSeparator());
        }
        return stringBuilder.toString().trim();
    }
}
