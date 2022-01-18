package actions;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a cancelled Reservation.
 *
 * @author GR47
 */
public class Cancellation extends ReservationDependentAction {

    /**
     * Constructor for Cancellation.
     *
     * @param reservation Needed because Cancellation is a ReservationDependentAction.
     * @param creationTime the time the Action was created
     */
    public Cancellation(Reservation reservation, LocalDateTime creationTime) {
        super(reservation, creationTime);
    }

    /**
     * String representation of the Cancellation.
     *
     * @return String representation.
     */
    @Override
    public String toString() {
        StringBuilder strb = new StringBuilder();
        DateTimeFormatter df = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        DateTimeFormatter tf = DateTimeFormatter.ofPattern("HH:mm");
        strb.append("Stornierung der Reservierung ").append(super.getReservation().getNr());
        strb.append(" am ").append(super.getCreationDateTime().format(df));
        strb.append(" um ").append(super.getCreationDateTime().format(tf));
        return strb.toString();
    }
}
