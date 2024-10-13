package exceptions;

/**
 * The {@code TerriException} class represents custom exceptions that are thrown
 * when a user provides improperly formatted or invalid input commands to the Terri chatbot.
 * It extends the {@code Exception} class and is used to communicate errors back to the user.
 */
public class TerriException extends Exception {

    /**
     * Constructs a new {@code TerriException} with a specified error message.
     * This message provides information about what caused the exception.
     *
     * @param exceptionMessage the detailed error message to be displayed when the exception is thrown.
     */
    public TerriException(String exceptionMessage) {
        super(exceptionMessage);
    }
}
