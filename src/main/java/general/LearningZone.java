package general;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Represent a learning zone. Usually packaged inside a protcol.
 *
 * @author GR47
 */
public class LearningZone {
    /**
     * The building this LearningZone is in.
     */
    private final String building;
    /**
     * The floor this LearningZone is on.
     */
    private final String floor;
    /**
     * The consecutive number of this LearningZone.
     */
    private final String consecutiveNr;
    /**
     * A list of LearningSpaces contained inside this LearningZone.
     */
    private final List<LearningSpace> learningSpaces;

    /**
     * Constructor for a LearningZone.
     * @param building the building of the LearningZone
     * @param floor the floor of the LearningZone
     * @param consecutiveNr the consecutive number of the LearningZone
     * @throws IllegalArgumentException if any argument is null
     */
    public LearningZone(String building, String floor, String consecutiveNr) throws IllegalArgumentException {
        if(building == null || floor == null || consecutiveNr == null)
            throw new IllegalArgumentException("Arguments can't be null");
        this.building = building;
        this.floor = floor;
        this.consecutiveNr = consecutiveNr;
        learningSpaces = new ArrayList<>();
    }

    /**
     * Getter for the building of this LearningZone.
     * @return the building of this LearningZone.
     */
    public String getBuilding() {
        return building;
    }

    /**
     * Getter for the floor of this LearningZone.
     * @return the floor of this LearningZone.
     */
    public String getFloor() {
        return floor;
    }

    /**
     * Getter for the learning spaces of this LearningZone.
     * @return a list of LearningSpaces inside this LearningZone.
     */
    public List<LearningSpace> getLearningSpaces() {
        return learningSpaces;
    }

    /**
     * Getter for the consecutive number of this LearningZone.
     * @return the consecutive number of this LearningZone.
     */
    public String getConsecutiveNr() {
        return consecutiveNr;
    }

    /**
     * Add a LearningSpace to the list of LearningSpaces in this
     * LearningZone. Only adds the LearningSpace if it is not yet contained
     * in the list of LearningSpaces.
     * @param s the LearningSpace to be added.
     * @return true if the LearningSpace has been added, false if otherwise.
     * @throws IllegalArgumentException if the learningSpace to be added is null
     */
    public boolean addLearningSpace(LearningSpace s) {
        if(s == null)
            throw new IllegalArgumentException("LearningSpace can't be null");
        if (!learningSpaces.contains(s)) {
            learningSpaces.add(s);
            return true;
        }
        return false;
    }

    /**
     * Remove a LearningSpace from the list of LearningSpaces of this LearningZone.
     *
     * @param s the LearningSpace to be removed.
     * @return true if the LearningSpace has been found and removed, false otherwise.
     * @throws IllegalArgumentException if the learningSpace to be removed is null
     */
    public boolean removeLearningSpace(LearningSpace s) throws IllegalArgumentException {
        if(s == null)
            throw new IllegalArgumentException("LearningSpace can't be null");
        return learningSpaces.remove(s);
    }

    /**
     * Equality is calculated based on the building name, the floor and the consecutive
     * number of the LearningZone.
     * @param o the object the LearningZone should be compared to
     * @return true if the LearningZone is equal to the given object, false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LearningZone that = (LearningZone) o;
        return Objects.equals(building, that.building) && Objects.equals(floor, that.floor) && Objects.equals(consecutiveNr, that.consecutiveNr);
    }

    /**
     * String representation of this LearningZone.
     * @return String representation of this LearningZone.
     */
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
