public class NiwaLackArgumentException extends NiwaException{
    public NiwaLackArgumentException() {
        super("Not enough arguments! Please check your syntax.");
    }
    public NiwaLackArgumentException(String correctSyntax) {
        super("Not enough arguments! Please check your syntax: " + correctSyntax);
    }
}
