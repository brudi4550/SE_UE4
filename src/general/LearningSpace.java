package general;

import actions.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Class representing a learning space. Normally packaged
 * inside a LearningZone.
 * There are intentionally no remove methods provided
 * for the List fields. Reservations should be cancelled if desired.
 *
 * @author GR47
 */
public class LearningSpace {
    /**
     * The number of this learning space.
     */
    private final int nr;
    /**
     * The capacity of this learning space.
     */
    private final int capacity;
    /**
     * A list of reservations made for this learning space.
     */
    private final List<Reservation> reservations;
    /**
     * A list of actually occurred occupancies for this learning space.
     */
    private final List<Occupancy> occupancies;
    /**
     * A list of cancellations made for this learning space.
     */
    private final List<Cancellation> cancellations;

    /**
     * Constructor for a LearningSpace. Parameters are not checked, the caller
     * needs to check beforehand if the values are valid.
     * @param nr the number of this LearningSpace
     * @param capacity the capacity for this LearningSpace.
     */
    public LearningSpace(int nr, int capacity) {
        this.nr = nr;
        this.capacity = capacity;
        reservations = new ArrayList<>();
        occupancies = new ArrayList<>();
        cancellations = new ArrayList<>();
    }

    /**
     * Add a reservation to the list of reservations for this LearningSpace.
     *
     * @param r the reservation to be added.
     * @return true if the reservation has been added, i.e. a reservation with this reservation
     * number is not already in the list, false if otherwise.
     */
    public boolean addReservation(Reservation r) {
        if(!reservations.contains(r)) {
            reservations.add(r);
            return true;
        }
        return false;
    }

    /**
     * Add an occupancy to the list of occupancies for this LearningSpace. Only adds
     * the occupancy if the reservation the occupancy is referencing can be found in the list
     * of reservations of this LearningSpace and the reservation has not yet been cancelled.
     *
     * @param o the occupancy to be added.
     * @return true if the occupancy has been added, false otherwise.
     */
    public boolean addOccupancy(Occupancy o) {
        if (reservations.contains(o.getReservation()) &&
                cancellations.stream().noneMatch(c -> c.getReservation().equals(o.getReservation()))) {
            occupancies.add(o);
            return true;
        }
        return false;
    }

    /**
     * Add a cancellation to the list of cancellations for this LearningSpace. Only adds
     * the cancellation if the reservation the cancellation is referencing can be found in the list
     * of reservations of this LearningSpace and there is no occupancy referencing the same reservation
     * in the list of occupancies.
     *
     * @param c the cancellation to be added.
     * @return true if the cancellation has been added, false otherwise.
     */
    public boolean addCancellation(Cancellation c) {
        if (reservations.contains(c.getReservation()) &&
                occupancies.stream().noneMatch(o -> o.getReservation().equals(c.getReservation()))) {
           cancellations.add(c);
           return true;
        }
        return false;
    }

    /**
     * Getter for the number of this LearningSpace.
     * @return the number of this LearningSpace.
     */
    public int getNr() {
        return nr;
    }

    /**
     * Getter for the capacity of this LearningSpace.
     * @return the capacity of this LearningSpace.
     */
    public int getCapacity() {
        return capacity;
    }

    /**
     * Getter for the list of reservations in this LearningSpace.
     * @return the list of reservations in this LearningSpace.
     */
    public List<Reservation> getReservations() {
        return reservations;
    }

    /**
     * Getter for the list of occupancies in this LearningSpace.
     * @return the list of occupancies in this LearningSpace.
     */
    public List<Occupancy> getOccupancies() {
        return occupancies;
    }

    /**
     * Getter for the list of cancellations in this LearningSpace.
     * @return the list of cancellations in this LearningSpace.
     */
    public List<Cancellation> getCancellations() {
        return cancellations;
    }

    /**
     * Merges the lists reservations, occupancies and cancellations and sorts them
     * by their creation DateTime.
     * @return the merged list of reservations, occupancies and cancellations
     */
    private List<? extends Action> getSortedMergedLists() {
        return Stream.of(reservations, occupancies, cancellations)
                .flatMap(Collection::stream)
                .sorted(Comparator.comparing(Action::getCreationDateTime))
                .collect(Collectors.toList());
    }

    /**
     * String representation of this LearningSpace.
     * @return String representing this LearningSpace.
     */
    @Override
    public String toString() {
        StringBuilder strb = new StringBuilder();
        strb.append("Lernplatz ").append(nr).append(" fuer ");
        strb.append(capacity).append(" Personen\n");
        for(Action a : getSortedMergedLists()) {
            strb.append(a.toString().indent(8));
        }
        return strb.toString();
    }
}
