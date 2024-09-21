package erika.exception;

import erika.Erika;

public class FileFormatErrorException extends ErikaException {
    public FileFormatErrorException(String message) {
        super("File: " + message + "not found");
    }
}
