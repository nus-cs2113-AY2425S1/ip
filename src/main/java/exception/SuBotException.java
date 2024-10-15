package exception;

/**
 * Base <code>Exception</code> for all program-related exceptions
 */
public abstract class SuBotException extends Exception{
    public SuBotException() {
        super();
    }

    public SuBotException(String message) {
        super(message);
    }

    public SuBotException(String message, Throwable cause) {
        super(message, cause);
    }

    public SuBotException(Throwable cause) {
        super(cause);
    }

    public SuBotException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
