package mong.exception;

/**
 * Defines exceptions specific to Mong.
 */
public abstract class MongException extends Exception {
    public MongException(String message) {
        super(message);
    }
}
