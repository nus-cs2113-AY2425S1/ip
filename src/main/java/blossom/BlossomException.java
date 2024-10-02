package blossom;

/**
 * Represents exceptions specific to Blossom.
 * This class extends the standard {@code Exception} class and is used to indicate
 * conditions that Blossom might want to catch.
 */
public class BlossomException extends Exception{
    public BlossomException(String message) {
        super(message);
    }
}
