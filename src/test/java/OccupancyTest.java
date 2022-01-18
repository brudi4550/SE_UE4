import actions.Occupancy;
import actions.Reservation;
import general.Student;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class OccupancyTest {

    //Constructor should not accept any null values
    @Test
    void testConstructorArguments() {
        Reservation r1 = new Reservation("R1", LocalDateTime.now(), LocalDateTime.now(), 10,
                new Student("K12345678"), LocalDateTime.now());
        assertThrows(IllegalArgumentException.class, () -> new Occupancy(r1, null, LocalDateTime.now()));
        assertThrows(IllegalArgumentException.class, () -> new Occupancy(r1, LocalDateTime.now(), null));
        assertThrows(IllegalArgumentException.class, () -> new Occupancy(r1, LocalDateTime.now().plusMinutes(1), LocalDateTime.now()));
        assertDoesNotThrow(() -> new Occupancy(r1, LocalDateTime.now(), LocalDateTime.now().plusMinutes(1)));
    }

    //test all getter methods of Occupancy
    @Test
    void testOccupancyGetterMethods() {
        LocalDateTime from = LocalDateTime.of(2022, 1, 15, 10, 0);
        LocalDateTime to = LocalDateTime.of(2022, 1, 15, 11, 0);
        Reservation r1 = new Reservation("R1", LocalDateTime.now(), LocalDateTime.now(), 10,
                new Student("K12345678"), LocalDateTime.now());
        Occupancy o1 = new Occupancy(r1, from, to);
        assertEquals(from, o1.getStart());
        assertEquals(to, o1.getEnd());
        assertEquals(Duration.between(from, to), o1.getDuration());
    }

    //checks that no Exception is thrown when calling toString
    @Test
    void testOccupancyToString() {
        Reservation r1 = new Reservation("R1", LocalDateTime.now(), LocalDateTime.now(), 10,
                new Student("K12345678"), LocalDateTime.now());
        Occupancy o1 = new Occupancy(r1, LocalDateTime.now(), LocalDateTime.now());
        assertDoesNotThrow(o1::toString);
    }

}
