package exception;
/**
 * Represents an exception that is thrown when an error occurs in the Amy chatbot.
 * The AmyException class extends the Exception class.
 */
public class AmyException extends Exception{
    public AmyException(String message){
        super(message);
    }

}
