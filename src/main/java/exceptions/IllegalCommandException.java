package exceptions;

public class IllegalCommandException extends Exception {
    public IllegalCommandException() {
    }
    public IllegalCommandException(Runnable uiFunction) {
        uiFunction.run();
    }
}
