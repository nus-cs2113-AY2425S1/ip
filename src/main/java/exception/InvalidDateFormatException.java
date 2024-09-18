package exception;

public class InvalidDateFormatException extends BentoException {
    public static final String INVALID_DATE_FORMAT_MESSAGE = "\tHmm... That doesn't seem quite right. Make sure your date is of the form dd/MM/yyyy!\n";

    public InvalidDateFormatException() {
        super(INVALID_DATE_FORMAT_MESSAGE);
    }
}
