public class CustomExceptions {
    static class MissingArgsException extends Exception {
        public MissingArgsException(String message) {
            super(message);
        }
    }
}