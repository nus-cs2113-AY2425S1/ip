package Exceptions;

public class DescriptionEmptyException extends Exception {
    public String errorMessage;

    public DescriptionEmptyException(String errorMessage) {
        this.errorMessage = errorMessage;
    }
    
}
