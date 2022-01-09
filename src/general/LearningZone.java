package general;

import java.util.ArrayList;
import java.util.List;

public class LearningZone {
    private final String building;
    private final String floor;
    private final String consecutiveNr;
    private final List<LearningSpace> learningSpaces;

    public LearningZone(String building, String floor, String consecutiveNr) {
        this.building = building;
        this.floor = floor;
        this.consecutiveNr = consecutiveNr;
        learningSpaces = new ArrayList<>();
    }

    public String getBuilding() {
        return building;
    }

    public String getFloor() {
        return floor;
    }

    public List<LearningSpace> getLearningSpaces() {
        return learningSpaces;
    }

    public String getConsecutiveNr() {
        return consecutiveNr;
    }

    public boolean addLearningSpace(LearningSpace s) {
        if (!learningSpaces.contains(s)) {
            learningSpaces.add(s);
            return true;
        }
        return false;
    }

    public boolean removeLearningSpace(LearningSpace s) {
        return learningSpaces.remove(s);
    }


    @Override
    public String toString() {
         StringBuilder strb = new StringBuilder();
         strb.append("Lernzone ").append(building).append("-");
         strb.append(floor).append("-");
         strb.append(consecutiveNr);
         strb.append("\n");
         for(LearningSpace s : learningSpaces) {
             strb.append(s.toString().indent(4));
         }
         return strb.toString();
    }

}
