package actions;

import java.time.LocalDateTime;

/**
 * An abstract class all classes that can be added to
 * LearningSpaces should inherit from.
 *
 * @author GR47
 */
public abstract class Action {
    /**
     * Represents the time the Action was created.
     */
    private final LocalDateTime creationDateTime;

    /**
     * Constructor with defaulted creationDateTime.
     */
    public Action() {
        creationDateTime = LocalDateTime.now();
    }

    /**
     * Constructor with provided creationDateTime.
     *
     * @param creationDateTime DateTime the action was created.
     * @throws IllegalArgumentException if creationDateTime is null
     */
    public Action(LocalDateTime creationDateTime) throws IllegalArgumentException {
        if(creationDateTime == null)
            throw new IllegalArgumentException("Argument can't be null.");
        this.creationDateTime = creationDateTime;
    }

    /**
     * Getter for the DateTime the Action was created.
     *
     * @return LocalDateTime
     */
    public LocalDateTime getCreationDateTime() {
        return creationDateTime;
    }

}
