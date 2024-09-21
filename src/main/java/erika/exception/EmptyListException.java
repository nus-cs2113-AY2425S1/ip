package erika.exception;

import erika.Erika;

public class EmptyListException extends ErikaException {
    public EmptyListException() {
        super("Error: List is empty, please consider adding some items!");
    }

}
