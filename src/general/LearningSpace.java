package general;

import actions.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LearningSpace {
    private final int nr;
    private final int capacity;
    private final List<Reservation> reservations;
    private final List<Occupancy> occupancies;
    private final List<Cancellation> cancellations;

    public LearningSpace(int nr, int capacity) {
        this.nr = nr;
        this.capacity = capacity;
        reservations = new ArrayList<>();
        occupancies = new ArrayList<>();
        cancellations = new ArrayList<>();
    }

    // intentionally no remove methods for all lists, reservations should be cancelled and readded if necessary
    public void addReservation(Reservation... r) {
        reservations.addAll(List.of(r));
    }

    // no varargs here so I can return boolean whether the necessary Reservation exists, since
    // occupancy and cancellation both are ReservationDependantActions
    public boolean addOccupancy(Occupancy b) {
        if (reservations.contains(b.getReservation()) &&
                cancellations.stream().noneMatch(c -> c.getReservation().equals(b.getReservation()))) {
            occupancies.add(b);
            return true;
        }
        return false;
    }

    // same thing here
    public boolean addCancellation(Cancellation c) {
        if (reservations.contains(c.getReservation()) &&
                occupancies.stream().noneMatch(o -> o.getReservation().equals(c.getReservation()))) {
           cancellations.add(c);
           return true;
        }
        return false;
    }

    public int getNr() {
        return nr;
    }

    public int getCapacity() {
        return capacity;
    }

    public List<Reservation> getReservations() {
        return reservations;
    }

    public List<Occupancy> getOccupancies() {
        return occupancies;
    }

    public List<Cancellation> getCancellations() {
        return cancellations;
    }

    private List<? extends Action> getSortedMergedLists() {
        return Stream.of(reservations, occupancies, cancellations)
                .flatMap(Collection::stream)
                .sorted(Comparator.comparing(Action::getCreationDateTime))
                .collect(Collectors.toList());
    }

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
