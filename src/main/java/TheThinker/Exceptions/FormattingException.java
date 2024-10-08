package TheThinker.Exceptions;

/**
 * This exception is thrown whenever there are formatting errors in the command given by the user
 */
public class FormattingException extends Exception{

    public FormattingException(String message){
        super(message);
    }

    public void printErrorMessage(){
        System.out.println(this.getMessage());
        System.out.println("_".repeat(60));
    }
}
