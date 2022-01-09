package actions;

import java.time.format.DateTimeFormatter;

public class Cancellation extends ReservationDependantAction {

    public Cancellation(Reservation reservation) {
        super(reservation);
    }

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
