package ExamPreparation.Exam19Dec2020.viceCity.core;

import ExamPreparation.Exam19Dec2020.viceCity.core.interfaces.Controller;
import ExamPreparation.Exam19Dec2020.viceCity.models.guns.Gun;
import ExamPreparation.Exam19Dec2020.viceCity.models.guns.Pistol;
import ExamPreparation.Exam19Dec2020.viceCity.models.guns.Rifle;
import ExamPreparation.Exam19Dec2020.viceCity.models.neighbourhood.GangNeighbourhood;
import ExamPreparation.Exam19Dec2020.viceCity.models.neighbourhood.Neighbourhood;
import ExamPreparation.Exam19Dec2020.viceCity.models.players.CivilPlayer;
import ExamPreparation.Exam19Dec2020.viceCity.models.players.MainPlayer;
import ExamPreparation.Exam19Dec2020.viceCity.models.players.Player;
import ExamPreparation.Exam19Dec2020.viceCity.repositories.GunRepository;
import ExamPreparation.Exam19Dec2020.viceCity.common.ConstantMessages;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static ExamPreparation.Exam19Dec2020.viceCity.common.ConstantMessages.*;

public class ControllerImpl implements Controller {
    private Map<String, Player> civilPlayers;
    private Player mainPlayer;
    private GunRepository gunRepository;
    private Neighbourhood neighbourhood;

    public ControllerImpl() {
        this.civilPlayers = new HashMap<>();
        this.mainPlayer = new MainPlayer();
        this.gunRepository = new GunRepository();
        this.neighbourhood = new GangNeighbourhood();
    }

    @Override
    public String addPlayer(String name) {
        CivilPlayer player = new CivilPlayer(name);
        civilPlayers.put(name, player);
        return String.format(PLAYER_ADDED, name);
    }

    @Override
    public String addGun(String type, String name) {
        Gun gun;
        switch (type) {
            case "Pistol":
                gun = new Pistol(name);
                break;
            case "Rifle":
                gun = new Rifle(name);
                break;
            default:
                return GUN_TYPE_INVALID;
        }

        gunRepository.add(gun);

        return String.format(GUN_ADDED, name, type);
    }

    @Override
    public String addGunToPlayer(String name) {
        List<Gun> guns = gunRepository.getModels();
        if (guns.isEmpty()) {
            return GUN_QUEUE_IS_EMPTY;
        }
        Gun gun = guns.get(0);

        if ("Vercetti".equals(name)) {
            mainPlayer.getGunRepository().add(gun);
            gunRepository.remove(gun);
            return String.format(GUN_ADDED_TO_MAIN_PLAYER, gun.getName(), MainPlayer.NAME);
        } else if (!civilPlayers.containsKey(name)) {
            return CIVIL_PLAYER_DOES_NOT_EXIST;
        } else {
            Player player = civilPlayers.get(name);
            player.getGunRepository().add(gun);
            gunRepository.remove(gun);
            return String.format(GUN_ADDED_TO_CIVIL_PLAYER, gun.getName(), player.getName());
        }
    }

    @Override
    public String fight() {
        int civilLifePointsBeforeFight = civilPlayers.values().stream().mapToInt(Player::getLifePoints).sum();
        long civilPlayerBeforeFight = civilPlayers.values().stream().filter(Player::isAlive).count();

        neighbourhood.action(mainPlayer, civilPlayers.values());
        int civilLifePointsAfterFight = civilPlayers.values().stream().mapToInt(Player::getLifePoints).sum();
        long civilPlayerAfterFight = civilPlayers.values().stream().filter(Player::isAlive).count();
        if (mainPlayer.getLifePoints() == MainPlayer.LIFE_POINTS && civilLifePointsBeforeFight == civilLifePointsAfterFight) {
            return FIGHT_HOT_HAPPENED;
        }

        return ConstantMessages.FIGHT_HAPPENED + System.lineSeparator() +
               String.format(ConstantMessages.MAIN_PLAYER_LIVE_POINTS_MESSAGE, mainPlayer.getLifePoints()) + System.lineSeparator() +
               String.format(ConstantMessages.MAIN_PLAYER_KILLED_CIVIL_PLAYERS_MESSAGE, civilPlayerBeforeFight - civilPlayerAfterFight) + System.lineSeparator() +
               String.format(ConstantMessages.CIVIL_PLAYERS_LEFT_MESSAGE, civilPlayerAfterFight);
    }
}
