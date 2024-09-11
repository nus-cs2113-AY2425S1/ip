package tyrone.exceptions;
public class MissingTimeInfoException extends TyroneException {
    public MissingTimeInfoException() {
        super("Missing time information for event.");
    }
}