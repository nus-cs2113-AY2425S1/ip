package exceptions;

/**
 * Custom exception class for the XiaoMe application.
 * This exception is thrown when there is an error related to user input
 * or other operational issues within the application.
 */
public class XiaoMeException extends Throwable {
    String error;

    /**
     * Constructs a XiaoMeException with the specified error message.
     *
     * @param error the error message that describes the issue
     */
    public XiaoMeException(String error) {
        this.error = error;
    }

    /**
     * Retrieves the error message associated with this exception.
     *
     * @return the error message
     */
    public String getError() {
        return error;
    }
}
