package niwa.exception;

public class NiwaInvalidSyntaxException extends NiwaException{

    public NiwaInvalidSyntaxException() {
        super("Your syntax is invalid, type 'help' to check for our syntax list!");
    }

}
