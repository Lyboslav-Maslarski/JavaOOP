package InterfacesAndAbstraction.Exercise.MilitaryElite;

public class Mission {
    private String codeName;
    private MissionStates missionState;

    public Mission(String codeName, String missionState) {
        this.codeName = codeName;
        setMissionState(missionState);
    }

    public void setMissionState(String missionState) {
        if ("inProgress".equals(missionState) || "finished".equals(missionState)) {
            this.missionState = MissionStates.valueOf(missionState);
        } else {
            throw new IllegalStateException();
        }
    }

    public void completeMission() {
        this.missionState = MissionStates.finished;
    }

    @Override
    public String toString() {
        return String.format("Code Name: %s State: %s", codeName, missionState);
    }
}
