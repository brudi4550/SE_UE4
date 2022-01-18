package actions;

import general.Student;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Class representing a reservation.
 *
 * @author GR47
 */
public class Reservation extends Action implements Timeable {
    /**
     * The reservation number.
     */
    private final String nr;
    /**
     * The start DateTime of the reservation.
     */
    private final LocalDateTime reservationStart;
    /**
     * The end DateTime of the reservation.
     */
    private final LocalDateTime reservationEnd;
    /**
     * The number of people the reservation was made for.
     */
    private final int nrOfPeople;
    /**
     * The student that made the reservation.
     */
    private final Student reservee;

    /**
     * Constructor for Reservation.
     * @param nr Reservation number
     * @param reservationStart Start-DateTime of the Reservation
     * @param reservationEnd End-DateTime of the Reservation
     * @param nrOfPeople Number of people the Reservation was made for
     * @param reservee Student that made the reservation
     * @param creationTime the time the Action was created
     * @throws IllegalArgumentException if any arguments are null or start is after end
     */
    public Reservation(String nr, LocalDateTime reservationStart, LocalDateTime reservationEnd, int nrOfPeople,
                       Student reservee, LocalDateTime creationTime) throws IllegalArgumentException{
        super(creationTime);
        if(nr == null || reservationStart == null || reservationEnd == null || reservee == null ||
                nrOfPeople < 1)
            throw new IllegalArgumentException("No arguments can be null");
        if(reservationStart.isAfter(reservationEnd))
            throw new IllegalArgumentException("Start can't be after end.");
        this.nr = nr;
        this.reservationStart = reservationStart;
        this.reservationEnd = reservationEnd;
        this.nrOfPeople = nrOfPeople;
        this.reservee = reservee;
    }

    /**
     * Getter for the number of people the reservation was made for.
     * @return number of people the reservation was made for
     */
    public int getNrOfPeople() {
        return nrOfPeople;
    }

    /**
     * Getter for the reservee.
     * @return The Student that made the reservation
     */
    public Student getReservee() {
        return reservee;
    }

    /**
     * Getter for the reservation number.
     * @return The reservation number
     */
    public String getNr() {
        return nr;
    }

    /**
     * Getter for the start DateTime.
     * @return a LocalDateTime object of the start point in time
     */
    @Override
    public LocalDateTime getStart() {
        return reservationStart;
    }

    /**
     * Getter for the end DateTime.
     * @return a LocalDateTime object of the end point in time
     */
    @Override
    public LocalDateTime getEnd() {
        return reservationEnd;
    }

    /**
     * Getter for the Duration of the reservation.
     * @return
     */
    @Override
    public Duration getDuration() {
        return Duration.between(reservationStart, reservationEnd);
    }

    /**
     * Checks for equality according to the reservation number.
     * @param o The object that is being compared
     * @return True if the reservation number matches, false if it doesn't.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Reservation that = (Reservation) o;
        return nr.equals(that.nr);
    }

    /**
     * String representation of the Reservation.
     * @return String representation
     */
    @Override
    public String toString() {
        StringBuilder strb = new StringBuilder();
        DateTimeFormatter df = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        DateTimeFormatter tf = DateTimeFormatter.ofPattern("HH:mm");
        String person = nrOfPeople < 2 ? "Person" : "Personen";
        strb.append("Reservierung ").append(nr).append(" fuer ");
        strb.append(reservationStart.format(df)).append(" von ");
        strb.append(reservationStart.format(tf)).append(" bis ");
        strb.append(reservationEnd.format(tf)).append(" fuer ");
        strb.append(nrOfPeople).append(" ").append(person).append(" durch ");
        strb.append(reservee.getStudentNr()).append(" am ").append(super.getCreationDateTime().format(df));
        strb.append(" um ").append(super.getCreationDateTime().format(tf));
        return strb.toString();
    }
}
