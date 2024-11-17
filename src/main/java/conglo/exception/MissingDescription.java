package conglo.exception;

/**
 * Represents an exception thrown when a required description is missing
 * from the user's input, such as when adding a task without specifying details.
 */
public class MissingDescription extends CongloException {

    /**
     * Constructs a new MissingDescription exception with an error message
     * that indicates the type of task that is missing a description.
     *
     * @param type The type of task or command missing a description.
     */
    public MissingDescription(String type) {
        super("Oopsies, please add a description of " + type + ".");
    }
}
