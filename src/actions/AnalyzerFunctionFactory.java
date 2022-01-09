package actions;

import general.LearningSpace;
import general.LearningZone;

import java.time.Duration;
import java.util.List;
import java.util.function.Function;

public class AnalyzerFunctionFactory {

    private AnalyzerFunctionFactory() {}

    public static Function<List<LearningZone>, Integer> getReservationCountFunction() {
        return (l -> {
            int count = 0;
            for(LearningZone lz : l) {
                for(LearningSpace ls : lz.getLearningSpaces()) {
                    count += ls.getReservations().size();
                }
            }
            return count;
        });
    }

    public static Function<List<LearningZone>, Integer> getOccupancyCountFunction() {
        return (l -> {
            int count = 0;
            for(LearningZone lz : l) {
                for(LearningSpace ls : lz.getLearningSpaces()) {
                    count += ls.getOccupancies().size();
                }
            }
            return count;
        });
    }

    public static Function<List<LearningZone>, Integer> getCancellationsCountFunction() {
        return (l -> {
            int count = 0;
            for(LearningZone lz : l) {
                for(LearningSpace ls : lz.getLearningSpaces()) {
                    count += ls.getCancellations().size();
                }
            }
            return count;
        });
    }

    public static Function<List<LearningZone>, Duration> getAvgReservationLengthFunction() {
        return (l -> {
            int count = 0;
            Duration d = Duration.ZERO;
            for(LearningZone lz : l) {
                for(LearningSpace ls : lz.getLearningSpaces()) {
                    for(Timeable t : ls.getReservations()) {
                        count++;
                        d = d.plus(t.getDuration());
                    }
                }
            }
            return d.dividedBy(count);
        });
    }

    public static Function<List<LearningZone>, Duration> getAvgOccupancyLengthFunction() {
        return (l -> {
            int count = 0;
            Duration d = Duration.ZERO;
            for(LearningZone lz : l) {
                for(LearningSpace ls : lz.getLearningSpaces()) {
                    for(Timeable t : ls.getOccupancies()) {
                        count++;
                        d = d.plus(t.getDuration());
                    }
                }
            }
            return d.dividedBy(count);
        });
    }

}
