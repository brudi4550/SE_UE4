import actions.*;
import general.*;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class Main {

    public static void main(String[] args) {
        //all actions use the default Action Constructor, therefore all creation Dates are set to the current LocalDateTime.
        LocalDate d1 = LocalDate.of(2021, 12, 1);
        LocalDate d2 = LocalDate.of(2021, 12, 31);
        Protocol p = new Protocol(d1, d2);
        LearningZone lz1 = new LearningZone("S3", "EG", "Z01");
        LearningSpace ls1 = new LearningSpace(1, 4);
        Student reservee = new Student("K12345678");
        Reservation r1 = new Reservation("R08154711",
                                         LocalDateTime.of(2021, 12, 3, 9, 15),
                                         LocalDateTime.of(2021, 12, 3, 10, 0),
                                         3,
                                         reservee);
        reservee = new Student("K12345679");
        Reservation r2 = new Reservation("R47110815",
                                         LocalDateTime.of(2021, 12, 6, 9, 15),
                                         LocalDateTime.of(2021, 12, 6, 10, 0),
                                         1,
                                         reservee);
        Reservation r3 = new Reservation("R47110816",
                                         LocalDateTime.of(2021, 12, 6, 9, 15),
                                         LocalDateTime.of(2021, 12, 6, 10, 0),
                                         1,
                                         reservee);
        Occupancy o1 = new Occupancy(r1,
                                     LocalDateTime.of(2021, 12, 3, 9, 21),
                                     LocalDateTime.of(2021, 12, 3, 9, 48));
        Cancellation c1 = new Cancellation(r2);
        ls1.addReservation(r1);
        ls1.addReservation(r2);
        ls1.addReservation(r3);
        ls1.addOccupancy(o1);
        ls1.addCancellation(c1);
        lz1.addLearningSpace(ls1);
        LearningSpace ls2 = new LearningSpace(2, 6);
        lz1.addLearningSpace(ls2);
        LearningSpace ls3 = new LearningSpace(3, 2);
        reservee = new Student("K18765432");
        Reservation r4 = new Reservation("R15081147",
                                         LocalDateTime.of(2021, 12, 4, 17, 30),
                                         LocalDateTime.of(2021, 12, 4, 18, 0),
                                         1,
                                         reservee);
        ls3.addReservation(r4);
        lz1.addLearningSpace(ls3);
        p.addLearningZone(lz1);

        LearningZone lz2 = new LearningZone("B", "O1", "Z01");
        ls1 = new LearningSpace(1, 6);
        reservee = new Student("K12345679");
        Reservation r5 = new Reservation("R47110819",
                                         LocalDateTime.of(2021, 12, 16, 12, 15),
                                         LocalDateTime.of(2021, 12, 16, 12, 45),
                                         5,
                                         reservee);
        ls1.addReservation(r5);
        ls2 = new LearningSpace(2, 4);
        lz2.addLearningSpace(ls1);
        lz2.addLearningSpace(ls2);
        p.addLearningZone(lz2);
        System.out.println(p);

        System.out.println("----------------");
        System.out.println("Statistik:");

        int nrOfReservations = p.apply(AnalyzerFunctionFactory.getCountFunction(Content.RESERVATION));
        System.out.println("Anzahl an Reservierungen: " + nrOfReservations);
        int nrOfOccupancies = p.apply(AnalyzerFunctionFactory.getCountFunction(Content.OCCUPANCY));
        System.out.println("Anzahl an Belegungen: " + nrOfOccupancies);
        int nrOfCancellations = p.apply(AnalyzerFunctionFactory.getCountFunction(Content.CANCELLATION));
        System.out.println("Anzahl an Stornierungen: " + nrOfCancellations + "\n");
        Duration d = p.apply(AnalyzerFunctionFactory.getAvgDurationFunction(Content.RESERVATION));
        System.out.println("Durchschnittliche Reservierungsdauer: " + d.getSeconds()/60 + " Minuten");
        d = p.apply(AnalyzerFunctionFactory.getAvgDurationFunction(Content.OCCUPANCY));
        System.out.println("Durchschnittliche Belegungsdauer: " + d.getSeconds()/60 + " Minuten");
    }

}
