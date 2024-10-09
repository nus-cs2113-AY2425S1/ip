package exceptions;
/**
 * IllegalCommandException is thrown when commands are not input by users correctly
 */
public class IllegalCommandException extends Exception {
    private final Runnable uiFunction;
    public IllegalCommandException(Runnable uiFunction) {
        this.uiFunction = uiFunction;
    }
    public void runUiFunction() {
        uiFunction.run();
    }
}
