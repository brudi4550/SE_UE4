package actions;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Occupancy extends ReservationDependantAction implements Timeable {
    private final LocalDateTime start;
    private final LocalDateTime end;

    // same as reservation, no check if start is before end
    public Occupancy(Reservation reservation, LocalDateTime start, LocalDateTime end) {
        super(reservation);
        this.start = start;
        this.end = end;
    }

    @Override
    public LocalDateTime getStart() {
        return start;
    }

    @Override
    public LocalDateTime getEnd() {
        return end;
    }

    @Override
    public Duration getDuration() {
        return Duration.between(start, end);
    }

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
