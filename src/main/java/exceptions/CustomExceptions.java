package exceptions;

public class CustomExceptions {
    public static class MissingArgsException extends Exception {
        public MissingArgsException(String message) {
            super(message);
        }
    }
}