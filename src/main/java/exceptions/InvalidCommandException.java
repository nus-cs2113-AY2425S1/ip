package exceptions;

/**
 * The InvalidCommandException class represents an exception that is thrown
 * when an invalid command is provided by the user. This extends the
 * Exception class and is used to indicate invalid input in the chatbot.
 */

public class InvalidCommandException extends Exception{
    public InvalidCommandException(String message){
        super(message);
    }
}
