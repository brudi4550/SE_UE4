import actions.Reservation;
import general.Student;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ReservationTest {

    //No null values should be accepted when creating a Reservation
    //checks that Exception is correctly thrown when end DateTime is before start DateTime
    @Test
    void testConstructorArguments() {
        LocalDateTime start = LocalDateTime.now();
        LocalDateTime end = LocalDateTime.now().plusMinutes(10);
        Student reservee = new Student("K12345678");
        assertThrows(IllegalArgumentException.class, () -> new Reservation(null, start, end, 10, reservee, start));
        assertThrows(IllegalArgumentException.class, () -> new Reservation("1", null, end, 10, reservee, start));
        assertThrows(IllegalArgumentException.class, () -> new Reservation("1", start , null, 10, reservee, start));
        assertThrows(IllegalArgumentException.class, () -> new Reservation("1", start , end, -1, reservee, start));
        assertThrows(IllegalArgumentException.class, () -> new Reservation("1", start , end, 10, null, start));
        assertThrows(IllegalArgumentException.class, () -> new Reservation("1", end, start, 10, reservee, start));
    }

    //tests all getter methods of Reservation
    @Test
    void testReservationGetterMethods() {
        LocalDateTime from = LocalDateTime.of(2022, 1, 15, 10, 0);
        LocalDateTime to = LocalDateTime.of(2022, 1, 15, 11, 0);
        Reservation r1 = new Reservation("R1", from, to, 10,
                new Student("K12345678"), LocalDateTime.now());
        assertEquals(from, r1.getStart());
        assertEquals(to, r1.getEnd());
        assertEquals(Duration.between(from, to), r1.getDuration());
        assertEquals(new Student("K12345678"), r1.getReservee());
        assertEquals(10, r1.getNrOfPeople());
    }

    //tests that two reservations should be equal if the reservation number match up
    @Test
    void testEqualsReservation() {
        LocalDateTime from = LocalDateTime.of(2022, 1, 15, 10, 0);
        LocalDateTime to = LocalDateTime.of(2022, 1, 15, 11, 0);
        Reservation r1 = new Reservation("R12182931", from, to, 10,
                new Student("K12345678"), LocalDateTime.now());
        Reservation r2 = new Reservation("R12182931", from, to, 10,
                new Student("K12345678"), LocalDateTime.now());
        assertEquals(r1, r2);
    }

}
