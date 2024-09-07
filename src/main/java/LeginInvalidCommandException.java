public class LeginInvalidCommandException extends LeginException{
    private static final String ERROR_MESSAGE = "BROO! No such command!";
    public LeginInvalidCommandException() {
        super(ERROR_MESSAGE);
    }
}
