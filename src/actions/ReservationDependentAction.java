package actions;

/**
 * Class capturing a reservation if the subclass is
 * dependent on it.
 *
 * @author GR47
 */
public abstract class ReservationDependentAction extends Action {
    /**
     * The captured reservation
     */
    private final Reservation reservation;

    /**
     * Constructor for the reservation.
     * @param reservation The reservation to be captured
     */
    public ReservationDependentAction(Reservation reservation) {
        this.reservation = reservation;
    }

    /**
     * Getter for the captured reservation.
     * @return The captured reservation
     */
    public Reservation getReservation() {
        return reservation;
    }
}
