package conglo.exception;

public class MissingDescription extends CongloException {
    public MissingDescription(String type) {
        super("Oopsies, please add a description of " + type + ".");
    }
}
