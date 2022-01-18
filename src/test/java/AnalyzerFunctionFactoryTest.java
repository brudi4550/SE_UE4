import actions.Cancellation;
import actions.Content;
import actions.Occupancy;
import actions.Reservation;
import general.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

public class AnalyzerFunctionFactoryTest {
    private Protocol p;

    @BeforeEach
    void setupProtocol() {
        p = new Protocol(LocalDate.now(), LocalDate.now());
        LearningZone lz = new LearningZone("A", "EG", "Z01");
        LearningSpace ls = new LearningSpace(1, 10);
        p.addLearningZone(lz);
        lz.addLearningSpace(ls);
        Student s = new Student("K12345678");
        LocalDateTime from = LocalDateTime.of(2022, 1, 20, 9, 15);
        LocalDateTime to = LocalDateTime.of(2022, 1, 20, 10, 10);
        Reservation r1 = new Reservation("R1", from.plusMinutes(3), to.minusMinutes(8), 5, s, LocalDateTime.now());
        Reservation r2 = new Reservation("R2", from.plusMinutes(2), to.minusMinutes(20), 5, s, LocalDateTime.now());
        Reservation r3 = new Reservation("R3", from.plusMinutes(14), to.minusMinutes(5), 5, s, LocalDateTime.now());
        Reservation r4 = new Reservation("R4", from.plusMinutes(8), to.minusMinutes(1), 5, s, LocalDateTime.now());
        Occupancy o1 = new Occupancy(r1, from.plusMinutes(2), to.plusMinutes(1));
        Occupancy o2 = new Occupancy(r2, from.plusMinutes(15), to.plusMinutes(7));
        Cancellation c1 = new Cancellation(r3, LocalDateTime.now());
        Cancellation c2 = new Cancellation(r4, LocalDateTime.now());
        ls.addReservation(r1);
        ls.addReservation(r2);
        ls.addReservation(r3);
        ls.addReservation(r4);
        ls.addOccupancy(o1);
        ls.addOccupancy(o2);
        ls.addCancellation(c1);
        ls.addCancellation(c2);
    }

    //checks if count function calculates expected result
    @Test
    void testGetCountFunction() {
        assertEquals(4, p.apply(AnalyzerFunctionFactory.getCountFunction(Content.RESERVATION)));
        assertEquals(2, p.apply(AnalyzerFunctionFactory.getCountFunction(Content.OCCUPANCY)));
        assertEquals(2, p.apply(AnalyzerFunctionFactory.getCountFunction(Content.CANCELLATION)));
    }

    //null as argument should throw IllegalArgumentException
    @Test
    void testGetCountFunctionWithNull() {
        assertThrows(IllegalArgumentException.class, () -> AnalyzerFunctionFactory.getCountFunction(null));
    }

    //checks if avg duration function calculates expected result for reservations
    @Test
    void testReservationAvgDurationFunction() {
        Duration actual = Duration.ZERO;
        int count = 0;
        for(LearningZone lz : p.getLearningZones()) {
            for(LearningSpace ls : lz.getLearningSpaces()) {
                for(Reservation r : ls.getReservations()) {
                    actual = actual.plus(r.getDuration());
                    count++;
                }
            }
        }
        actual = actual.dividedBy(count);
        Duration funcResult = p.apply(AnalyzerFunctionFactory.getAvgDurationFunction(Content.RESERVATION));
        assertEquals(actual.toMinutes(), funcResult.toMinutes());
    }

    //checks if avg duration function calculates expected result for occupancies
    @Test
    void testOccupancyAvgDurationFunction() {
        Duration actual = Duration.ZERO;
        int count = 0;
        for(LearningZone lz : p.getLearningZones()) {
            for(LearningSpace ls : lz.getLearningSpaces()) {
                for(Occupancy o : ls.getOccupancies()) {
                    actual = actual.plus(o.getDuration());
                    count++;
                }
            }
        }
        actual = actual.dividedBy(count);
        var funcResult = p.apply(AnalyzerFunctionFactory.getAvgDurationFunction(Content.OCCUPANCY));
        assertEquals(actual.toMinutes(), funcResult.toMinutes());
    }

    //null as content should throw an IllegalArgumentException
    @Test
    void testGetAvgDurationFunctionNull() {
        assertThrows(IllegalArgumentException.class, () -> AnalyzerFunctionFactory.getAvgDurationFunction(null));
    }

    //getAvgDurationFunction should throw IllegalArgumentException if the content is not Timeable
    @Test
    void testCancellationAvgDurationFunction() {
        assertThrows(IllegalArgumentException.class, () -> AnalyzerFunctionFactory.getAvgDurationFunction(Content.CANCELLATION));
    }
}
