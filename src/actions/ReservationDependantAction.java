package actions;

public abstract class ReservationDependantAction extends Action {
    private final Reservation reservation;

    public ReservationDependantAction(Reservation reservation) {
        this.reservation = reservation;
    }

    public Reservation getReservation() {
        return reservation;
    }
}
