package general;

import actions.Content;
import actions.Timeable;

import java.time.Duration;
import java.util.List;
import java.util.function.Function;

/**
 * Non-instantiable Function factory class providing
 * some basic function-factory methods for analyzing a given Protocol.
 *
 * @author GR47
 */
public class AnalyzerFunctionFactory {

    private AnalyzerFunctionFactory() {}

    /**
     * Returns a function counting a given Content Type within a Protocol.
     *
     * @param c The Content Type to be counted.
     * @return Function object
     */
    public static Function<List<LearningZone>, Integer> getCountFunction(Content c) {
        return (l -> {
            int count = 0;
            for(LearningZone lz : l) {
                for(LearningSpace ls : lz.getLearningSpaces()) {
                    count += c.get(ls).size();
                }
            }
            return count;
        });
    }

    /**
     * Returns a function averaging the time of the given Content Type within a Protocol.
     * The Content Type needs to be Timeable.
     *
     * @param c The Content Type whose Duration needs to be averaged.
     * @throws IllegalArgumentException If the Content value is not Timeable.
     * @return Function object
     */
    public static Function<List<LearningZone>, Duration> getAvgDurationFunction(Content c) throws IllegalArgumentException {
        if (c.equals(Content.CANCELLATION)) {
            throw new IllegalArgumentException("Content Type not applicable.");
        }
        return (l -> {
            int count = 0;
            Duration d = Duration.ZERO;
            for(LearningZone lz : l) {
                for(LearningSpace ls : lz.getLearningSpaces()) {
                    for (Timeable t : c.getTimeable(ls)) {
                        count++;
                        d = d.plus(t.getDuration());
                    }
                }
            }
            return d.dividedBy(count);
        });
    }
}
