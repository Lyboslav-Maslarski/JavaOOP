package ExamPreparation.Exam19Dec2020.viceCity.models.neighbourhood;

import ExamPreparation.Exam19Dec2020.viceCity.models.players.Player;

import java.util.Collection;

public interface Neighbourhood {
    void action(Player mainPlayer, Collection<Player> civilPlayers);
}
