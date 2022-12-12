package ExamPreparation.Exam14August2022.football.core;

import ExamPreparation.Exam14August2022.football.entities.field.ArtificialTurf;
import ExamPreparation.Exam14August2022.football.entities.field.Field;
import ExamPreparation.Exam14August2022.football.entities.field.NaturalGrass;
import ExamPreparation.Exam14August2022.football.entities.player.Men;
import ExamPreparation.Exam14August2022.football.entities.player.Player;
import ExamPreparation.Exam14August2022.football.entities.player.Women;
import ExamPreparation.Exam14August2022.football.entities.supplement.Liquid;
import ExamPreparation.Exam14August2022.football.entities.supplement.Powdered;
import ExamPreparation.Exam14August2022.football.entities.supplement.Supplement;
import ExamPreparation.Exam14August2022.football.repositories.SupplementRepository;
import ExamPreparation.Exam14August2022.football.repositories.SupplementRepositoryImpl;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import static ExamPreparation.Exam14August2022.football.common.ConstantMessages.*;
import static ExamPreparation.Exam14August2022.football.common.ExceptionMessages.*;

public class ControllerImpl implements Controller {
    private SupplementRepository supplementRepository;
    private Map<String, Field> fieldMap;

    public ControllerImpl() {
        supplementRepository = new SupplementRepositoryImpl();
        fieldMap = new HashMap<>();
    }

    @Override
    public String addField(String fieldType, String fieldName) {
        Field field;
        if (fieldType.equals("ArtificialTurf")) {
            field = new ArtificialTurf(fieldName);
        } else if (fieldType.equals("NaturalGrass")) {
            field = new NaturalGrass(fieldName);
        } else {
            throw new NullPointerException(INVALID_FIELD_TYPE);
        }
        fieldMap.put(fieldName, field);
        return String.format(SUCCESSFULLY_ADDED_FIELD_TYPE, fieldType);
    }

    @Override
    public String deliverySupplement(String type) {
        Supplement supplement;
        if (type.equals("Powdered")) {
            supplement = new Powdered();
        } else if (type.equals("Liquid")) {
            supplement = new Liquid();
        } else {
            throw new IllegalArgumentException(INVALID_SUPPLEMENT_TYPE);
        }
        supplementRepository.add(supplement);
        return String.format(SUCCESSFULLY_ADDED_SUPPLEMENT_TYPE, type);
    }

    @Override
    public String supplementForField(String fieldName, String supplementType) {
        Supplement supplement = supplementRepository.findByType(supplementType);
        if (supplement == null) {
            throw new IllegalArgumentException(String.format(NO_SUPPLEMENT_FOUND, supplementType));
        }
        supplementRepository.remove(supplement);
        fieldMap.get(fieldName).addSupplement(supplement);
        return String.format(SUCCESSFULLY_ADDED_SUPPLEMENT_IN_FIELD, supplementType, fieldName);
    }

    @Override
    public String addPlayer(String fieldName, String playerType, String playerName, String nationality, int strength) {
        if (!playerType.equals("Men") && !playerType.equals("Women")) {
            throw new IllegalArgumentException(INVALID_PLAYER_TYPE);
        }

        Player player;
        Field field = fieldMap.get(fieldName);

        if (playerType.equals("Men") && field.getClass().getSimpleName().equals("NaturalGrass")) {
            player = new Men(playerName, nationality, strength);
        } else if (playerType.equals("Women") && field.getClass().getSimpleName().equals("ArtificialTurf")) {
            player = new Women(playerName, nationality, strength);
        } else {
            return String.format(FIELD_NOT_SUITABLE);
        }

        field.addPlayer(player);
        return String.format(SUCCESSFULLY_ADDED_PLAYER_IN_FIELD, playerType, fieldName);
    }

    @Override
    public String dragPlayer(String fieldName) {
        Field field = fieldMap.get(fieldName);
        field.drag();
        return String.format(PLAYER_DRAG, field.getPlayers().size());
    }

    @Override
    public String calculateStrength(String fieldName) {
        Field field = fieldMap.get(fieldName);
        int sum = field.getPlayers().stream().mapToInt(Player::getStrength).sum();
        return String.format(STRENGTH_FIELD, fieldName, sum);
    }

    @Override
    public String getStatistics() {
        return fieldMap.values().stream().map(Field::getInfo).collect(Collectors.joining(System.lineSeparator()));
    }
}
