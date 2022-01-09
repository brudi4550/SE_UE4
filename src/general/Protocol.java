package general;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class Protocol {
    private final LocalDate from;
    private final LocalDate to;
    private final List<LearningZone> learningZones;

    public Protocol(LocalDate startDate, LocalDate endDate) {
        this.from = startDate;
        this.to = endDate;
        this.learningZones = new ArrayList<>();
    }

    public LocalDate getFrom() {
        return from;
    }

    public LocalDate getTo() {
        return to;
    }

    public List<LearningZone> getLearningZones() {
        return learningZones;
    }

    public void addLearningZone(LearningZone z) {
        learningZones.add(z);
    }

    public boolean removeLearningZone(LearningZone z) {
        return learningZones.remove(z);
    }

    public <R> R analyze(Function<List<LearningZone>, R> func) {
        return func.apply(this.learningZones);
    }

    @Override
    public String toString() {
        StringBuilder strb = new StringBuilder();
        DateTimeFormatter df = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        strb.append("Protokoll fuer den Zeitraum vom ");
        strb.append(from.format(df));
        strb.append(" bis ");
        strb.append(to.format(df));
        strb.append("\n\n");
        for(LearningZone l : learningZones) {
            strb.append(l.toString());
            strb.append("\n");
        }
        strb.append("Protokollende");
        return strb.toString();
    }

}
