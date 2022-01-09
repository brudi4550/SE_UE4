package actions;

import general.Student;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Reservation extends Action implements Timeable {
    private final String nr;
    private final LocalDateTime reservationStart;
    private final LocalDateTime reservationEnd;
    private final int nrOfPeople;
    private final Student reservee;

    // no check if start < end, whoever uses the class needs to deal with that
    public Reservation(String nr, LocalDateTime reservationStart, LocalDateTime reservationEnd, int nrOfPeople, Student reservee) {
        this.nr = nr;
        this.reservationStart = reservationStart;
        this.reservationEnd = reservationEnd;
        this.nrOfPeople = nrOfPeople;
        this.reservee = reservee;
    }

    public int getNrOfPeople() {
        return nrOfPeople;
    }

    public Student getReservee() {
        return reservee;
    }

    public String getNr() {
        return nr;
    }

    @Override
    public LocalDateTime getStart() {
        return reservationStart;
    }

    @Override
    public LocalDateTime getEnd() {
        return reservationEnd;
    }

    @Override
    public Duration getDuration() {
        return Duration.between(reservationStart, reservationEnd);
    }

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
        strb.append(reservee.getStudentNr()).append(" ").append(super.getCreationDateTime().format(df));
        strb.append(" um ").append(super.getCreationDateTime().format(tf));
        return strb.toString();
    }
}
