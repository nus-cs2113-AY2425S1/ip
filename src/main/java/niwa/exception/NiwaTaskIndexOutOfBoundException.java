package niwa.exception;

public class NiwaTaskIndexOutOfBoundException extends NiwaException{
    public NiwaTaskIndexOutOfBoundException() {
        super("Index is out of bound!");
    }
    public NiwaTaskIndexOutOfBoundException(int maxIndex) {
        super("Index is out of bound! Your list currently has " + maxIndex + " task(s).");
    }
}
