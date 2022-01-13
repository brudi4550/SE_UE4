package actions;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Class representing an Occupancy.
 * Always references a given Reservation.
 *
 * @author GR47
 */
public class Occupancy extends ReservationDependentAction implements Timeable {
    /**
     * LocalDateTimes representing the start and end point in time of the Occupancy.
     * Needed for implementing Timeable methods.
     */
    private final LocalDateTime start;
    private final LocalDateTime end;

    /**
     * Constructor for Occupancy. Does not check the given parameters.
     * Caller is responsible for checking valid arguments beforehand.
     *
     * @param reservation Referenced reservation
     * @param start Start DateTime of the Occupancy
     * @param end End DateTime of the Occupancy
     */
    public Occupancy(Reservation reservation, LocalDateTime start, LocalDateTime end) {
        super(reservation);
        this.start = start;
        this.end = end;
    }

    /**
     * Getter for the start DateTime of the occupancy.
     * Implements the specified method of Timeable.
     *
     * @return LocalDateTime
     */
    @Override
    public LocalDateTime getStart() {
        return start;
    }


    /**
     * Getter for the end DateTime of the occupancy.
     * Implements the specified method of Timeable.
     *
     * @return LocalDateTime
     */
    @Override
    public LocalDateTime getEnd() {
        return end;
    }

    /**
     * Getter for the duration of the occupancy.
     * Implements the specified method of Timeable.
     *
     * @return Duration of the occupancy
     */
    @Override
    public Duration getDuration() {
        return Duration.between(start, end);
    }

    /**
     * String representation of the Occupancy.
     * @return String representation
     */
    @Override
    public String toString() {
        StringBuilder strb = new StringBuilder();
        DateTimeFormatter df = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        DateTimeFormatter tf = DateTimeFormatter.ofPattern("HH:mm");
        strb.append("Belegung am ").append(start.format(df)).append(" von ");
        strb.append(start.format(tf)).append(" bis ").append(end.format(tf));
        strb.append(" mit Reservierung ").append(super.getReservation().getNr());
        return strb.toString();
    }

}
