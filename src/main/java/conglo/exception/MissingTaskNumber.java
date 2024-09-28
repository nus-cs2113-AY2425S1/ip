package conglo.exception;

public class MissingTaskNumber extends CongloException {
    public MissingTaskNumber(String action) {
        super("Please provide task number to " + action + ".");
    }
}
