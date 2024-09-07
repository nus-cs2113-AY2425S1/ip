public class AppalException extends Exception {
    public static final String MESSAGE = "Oh no... this is invalid input!" +
            "\nType 'help' to stay fresh on the possible commands!";

    public AppalException() {
        super(MESSAGE);
    }

    public AppalException(String message) {
        super(message);
    }
}
