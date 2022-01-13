package general;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

/**
 * Class representing a Protocol within a given timeframe.
 *
 * @author GR47
 */
public class Protocol {
    /**
     * the start date of the protocol.
     */
    private final LocalDate from;
    /**
     * the end date of the protocol.
     */
    private final LocalDate to;
    /**
     * a list of LearningZones in this protocol.
     */
    private final List<LearningZone> learningZones;

    /**
     * Constructor for a Protocol. Parameters are not checked, caller needs to make sure
     * that arguments are valid.
     * @param startDate the start Date of this protocol
     * @param endDate the end Date of this protocol
     */
    public Protocol(LocalDate startDate, LocalDate endDate) {
        this.from = startDate;
        this.to = endDate;
        this.learningZones = new ArrayList<>();
    }

    /**
     * Getter for the start Date of this protocol.
     * @return LocalDate - the start date of this protocol.
     */
    public LocalDate getFrom() {
        return from;
    }

    /**
     * Getter for the end Date of this protocol.
     * @return LocalDate - the end date of this protocol.
     */
    public LocalDate getTo() {
        return to;
    }

    /**
     * Getter for the list of LearningZones contained inside this protocol.
     * @return the list of LearningZones
     */
    public List<LearningZone> getLearningZones() {
        return learningZones;
    }

    /**
     * Adds LearningZone to this protocol.
     * @param z the LearningZone to be added.
     */
    public void addLearningZone(LearningZone z) {
        learningZones.add(z);
    }

    /**
     * Removes LearningZone from this protocol.
     * @param z the LearningZone to be removed.
     * @return true if the LearningZone is found and removed, false otherwise.
     */
    public boolean removeLearningZone(LearningZone z) {
        return learningZones.remove(z);
    }

    /**
     * Takes a function and applies it to the list of LearningZones
     * in this protocol.
     * @param func The function to be applied.
     * @param <R> The return type of the parameter func.
     * @return A value of type R.
     */
    public <R> R apply(Function<List<LearningZone>, R> func) {
        return func.apply(this.learningZones);
    }

    /**
     * String representation of the protocol.
     * @return String representation.
     */
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
