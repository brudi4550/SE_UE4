package actions;

import general.LearningSpace;
import general.LearningZone;

import java.time.Duration;
import java.util.List;
import java.util.function.Function;

public class AnalyzerFunctionFactory {

    private AnalyzerFunctionFactory() {}

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

    public static Function<List<LearningZone>, Duration> getAvgLengthFunction(Content c) {
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
