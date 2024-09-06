public class NiwaInvalidArgumentException extends NiwaException{
    public NiwaInvalidArgumentException() {
        super("Wrong arguments! Please check your syntax.");
    }
    public NiwaInvalidArgumentException(String correctSyntax) {
        super("Wrong arguments! Please check your syntax: " + correctSyntax);
    }
}
