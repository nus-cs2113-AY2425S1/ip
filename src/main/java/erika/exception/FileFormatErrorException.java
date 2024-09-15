package erika.exception;

public class FileFormatErrorException extends RuntimeException {
    public FileFormatErrorException(String message) {
        super("File: " + message + "not found");
    }
}
