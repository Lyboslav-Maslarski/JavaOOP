package Encapsulation.Exercise.FootballTeamGenerator;

public class Player {
    private static final String WRONG_STATS = "%s should be between 0 and 100.";
    private String name;
    private int endurance;
    private int sprint;
    private int dribble;
    private int passing;
    private int shooting;

    public Player(String name, int endurance, int sprint, int dribble, int passing, int shooting) {
        setName(name);
        setEndurance(endurance);
        setSprint(sprint);
        setDribble(dribble);
        setPassing(passing);
        setShooting(shooting);
    }

    private void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("A name should not be empty.");
        }
        this.name = name;
    }

    private void setEndurance(int endurance) {
        if (endurance < 0 || endurance > 100) {
            throw new IllegalArgumentException(String.format(WRONG_STATS, "Endurance"));
        }
        this.endurance = endurance;
    }

    private void setSprint(int sprint) {
        if (sprint < 0 || sprint > 100) {
            throw new IllegalArgumentException(String.format(WRONG_STATS, "Sprint"));
        }
        this.sprint = sprint;
    }

    private void setDribble(int dribble) {
        if (dribble < 0 || dribble > 100) {
            throw new IllegalArgumentException(String.format(WRONG_STATS, "Dribble"));
        }
        this.dribble = dribble;
    }

    private void setPassing(int passing) {
        if (passing < 0 || passing > 100) {
            throw new IllegalArgumentException(String.format(WRONG_STATS, "Passing"));
        }
        this.passing = passing;
    }

    private void setShooting(int shooting) {
        if (shooting < 0 || shooting > 100) {
            throw new IllegalArgumentException(String.format(WRONG_STATS, "Shooting"));
        }
        this.shooting = shooting;
    }

    public String getName() {
        return name;
    }

    public double overallSkillLevel() {
        return (endurance + sprint + dribble + passing + shooting) * 1.0 / 5;
    }
}