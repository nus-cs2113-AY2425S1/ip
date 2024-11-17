package UranusExceptions;

/**
 * Represents the base class for all exceptions in the Uranus chatbot.
 * It extends the Exception class.
 */
public class UranusExceptions extends Exception{
    public UranusExceptions(){
        super();
    }

    // Overloaded in the event that a message is needed
    public UranusExceptions(String message){
        super(message);
    }
}
