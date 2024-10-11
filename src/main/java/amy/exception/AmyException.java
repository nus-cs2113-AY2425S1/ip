package exception;
public class AmyException extends Exception{
    private String message;
    public AmyException(String message){
        super(message);
    }

}
