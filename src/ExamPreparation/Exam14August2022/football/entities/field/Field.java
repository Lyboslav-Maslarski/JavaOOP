package ExamPreparation.Exam14August2022.football.entities.field;

import ExamPreparation.Exam14August2022.football.entities.player.Player;
import ExamPreparation.Exam14August2022.football.entities.supplement.Supplement;

import java.util.Collection;

public interface Field {
    int sumEnergy();

    void addPlayer(Player player);

    void removePlayer(Player player);

    void addSupplement(Supplement supplement);

    void drag();

    String getInfo();

    Collection<Player> getPlayers();

    Collection<Supplement> getSupplements();

    String getName();
}
