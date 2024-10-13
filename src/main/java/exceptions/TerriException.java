package exceptions;

public class TerriException extends Exception {

    /**
     * The TerriException class indicates exceptions thrown
     * from improperly formatted command input by the user.
     */

    public TerriException(String exceptionMessage) {
        super(exceptionMessage);
    }
}
