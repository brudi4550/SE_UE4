package actions;

import java.time.LocalDateTime;

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
     * @throws IllegalArgumentException if reservation is null
     */
    public ReservationDependentAction(Reservation reservation, LocalDateTime creationTime) throws IllegalArgumentException {
        super(creationTime);
        if(reservation == null)
            throw new IllegalArgumentException("Reservation can't be null");
        this.reservation = reservation;
    }

    /**
     * Constructor with default creationDateTime, calling the more general constructor.
     * @param reservation The reservation to be captured
     */
    public ReservationDependentAction(Reservation reservation) {
        this(reservation, LocalDateTime.now());
    }

    /**
     * Getter for the captured reservation.
     * @return The captured reservation
     */
    public Reservation getReservation() {
        return reservation;
    }
}
