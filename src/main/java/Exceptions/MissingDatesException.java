package Exceptions;

public class MissingDatesException extends Exception {
    //nothing
    public String errorMessage;

    public MissingDatesException(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
