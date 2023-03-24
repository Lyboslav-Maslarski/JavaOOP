package ExamPreparation.Exam19Dec2020.viceCity.models.neighbourhood;

import ExamPreparation.Exam19Dec2020.viceCity.models.guns.Gun;
import ExamPreparation.Exam19Dec2020.viceCity.models.players.Player;

import java.util.Collection;

public class GangNeighbourhood implements Neighbourhood {
    @Override
    public void action(Player mainPlayer, Collection<Player> civilPlayers) {
        Collection<Gun> mainPlayerGuns = mainPlayer.getGunRepository().getModels();
        for (Player civilPlayer : civilPlayers) {
            for (Gun gun : mainPlayerGuns) {
                while (civilPlayer.isAlive() && gun.canFire()) {
                    civilPlayer.takeLifePoints(gun.fire());
                }
            }
        }

        for (Player civilPlayer : civilPlayers) {
            for (Gun gun : civilPlayer.getGunRepository().getModels()) {
                while (mainPlayer.isAlive() && gun.canFire()) {
                    mainPlayer.takeLifePoints(gun.fire());
                }
                if (!mainPlayer.isAlive()){
                    break;
                }
            }
            if (!mainPlayer.isAlive()){
                break;
            }
        }
    }
}
