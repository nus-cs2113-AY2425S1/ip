package exceptions;

/**
 * Custom exceptions used in the application for handling specific error cases.
 */
public class CustomExceptions {

    /**
     * Exception thrown when a required argument is missing.
     */
    public static class MissingArgsException extends Exception {
        /**
         * Constructs a new MissingArgsException with the specified detail message.
         *
         * @param message The detail message explaining the reason for the exception.
         */
        public MissingArgsException(String message) {
            super(message);
        }
    }

    /**
     * Exception thrown when an invalid task index is encountered.
     */
    public static class InvalidTaskIndexException extends Exception {
        /**
         * Constructs a new InvalidTaskIndexException with the specified detail message.
         *
         * @param message The detail message explaining the reason for the exception.
         */
        public InvalidTaskIndexException(String message) {
            super(message);
        }
    }
}
